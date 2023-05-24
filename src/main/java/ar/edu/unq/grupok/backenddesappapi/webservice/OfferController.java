package ar.edu.unq.grupok.backenddesappapi.webservice;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import ar.edu.unq.grupok.backenddesappapi.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import ar.edu.unq.grupok.backenddesappapi.service.CryptoService;
import ar.edu.unq.grupok.backenddesappapi.service.OfferService;
import ar.edu.unq.grupok.backenddesappapi.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Offer services", description = "Manage offers of the application")
@RestController
@Transactional
@RequestMapping("/p2p")
public class OfferController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private CryptoService cryptoService;
	
	@Autowired
	private OfferService offerService;
	
	
	@Operation(summary = "Create a new offer")
	@PostMapping("/createOffer")
	public ResponseEntity<OfferDTO> createOffer(@RequestBody OfferDTO newOffer){
		Crypto crypto = this.cryptoService.getCryptoBySymbol(newOffer.getCryptoSymbol());
		User user = this.userService.getUserByEmail(newOffer.getAuthorEmail());
		
		Offer offer = new Offer(crypto,
								newOffer.getAmountOfCrypto(),
								newOffer.getPriceOfCrypto(),
								newOffer.getAmountInPesos(),
								user,
								newOffer.getOperationType());
		
		user.addOperation(offer);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(convertOfferEntityToOfferDTO(offerService.saveOffer(offer)));
	}
	
	@Operation(summary = "Get all created offers")
	@GetMapping("/offers")
	public ResponseEntity<List<OfferDTO>> createdOffers(){
		return ResponseEntity.ok()
							 .body(offerService.getAllOffers()
									 .stream()
									 	.map(this::convertOfferEntityToOfferDTO).toList());
	}
	
	@Operation(summary = "Get a open offers from a user")
	@GetMapping("/open-offers/{email}")
	public ResponseEntity<List<OpenOfferDTO>> openOffersFromAUser(@PathVariable String email){
		
		User user = userService.getUserByEmail(email);
		List<Offer> openOffers = user.getOffers().stream().filter(offer -> offer.getOfferState() == OfferState.OPEN).toList();
		
		return ResponseEntity.ok()
							 .body(openOffers
									 .stream()
									 	.map(this::convertOfferEntityToOpenOfferDTO).toList());
	}

	@PutMapping("/offers/transact/{id}")
    public ResponseEntity<OfferWithActionProcessedDTO> reportTransaction(@PathVariable UUID id,@RequestBody UserEmailDTO userEmail) {
        Offer offer = offerService.getOfferById(id);
		User user = userService.getUserByEmail(userEmail.getUserEmail());
		
		user.reportTransaction(offer, LocalDateTime.now());

		OfferWithActionProcessedDTO offerWithActionProcessedDTO = convertOfferEntityToOfferWithActionProcessedDTO(offer);
		
        offerService.saveOffer(offer);
        
        return ResponseEntity.ok(offerWithActionProcessedDTO);
    }
	
	@PutMapping("/offers/confirm/{id}")
    public ResponseEntity<OfferWithActionProcessedDTO> confirmTransaction(@PathVariable UUID id){ 
        Offer offer = offerService.getOfferById(id);
		User user = userService.getUserByEmail(offer.getAuthor().getEmail());
		
		user.confirmTransferReceived(offer, LocalDateTime.now());

		OfferWithActionProcessedDTO offerWithActionProcessedDTO = convertOfferEntityToOfferWithActionProcessedDTO(offer);
		
        offerService.saveOffer(offer);
        
        return ResponseEntity.ok(offerWithActionProcessedDTO);
    }
	
	@PutMapping("/offers/cancel/{id}")
    public ResponseEntity<OfferWithActionProcessedDTO> cancelTransaction(@PathVariable UUID id,@RequestBody UserEmailDTO userEmail) {
        Offer offer = offerService.getOfferById(id);
		User user = userService.getUserByEmail(userEmail.getUserEmail() );
		
		user.cancelOperation(offer);

		OfferWithActionProcessedDTO offerWithActionProcessedDTO = convertOfferEntityToOfferWithActionProcessedDTO(offer);
		
        offerService.saveOffer(offer);
        
        return ResponseEntity.ok(offerWithActionProcessedDTO);
    }

	public OfferDTO convertOfferEntityToOfferDTO(Offer offer) {
		OfferDTO offerDTO = new OfferDTO();
		offerDTO.setCryptoSymbol(offer.getCrypto().getSymbol());
		offerDTO.setAmountOfCrypto(offer.getAmountOfCrypto());
		offerDTO.setPriceOfCrypto(offer.getPriceOfCrypto());
		offerDTO.setAmountInPesos(offer.getAmountInPesos());
		offerDTO.setAuthorEmail(offer.getAuthor().getEmail());
		offerDTO.setOperationType(offer.getOperationType());
		offerDTO.setOfferState(offer.getOfferState());
		return offerDTO;
	}
	
	public OpenOfferDTO convertOfferEntityToOpenOfferDTO(Offer offer) {
		OpenOfferDTO openOfferDTO = new OpenOfferDTO();
		openOfferDTO.setCreationDate(offer.getCreationDate());
		openOfferDTO.setOperationType(offer.getOperationType());
		openOfferDTO.setCryptoSymbol(offer.getCrypto().getSymbol());
		openOfferDTO.setAmountOfCrypto(offer.getAmountOfCrypto());
		openOfferDTO.setPriceOfCryptoInTheOffer(offer.getPriceOfCrypto());
		openOfferDTO.setAmountInPesos(offer.getAmountInPesos());
		openOfferDTO.setAuthorEmail(offer.getAuthor().getEmail());
		openOfferDTO.setNumberOfOperations(offer.getAuthor().getSuccessfulOperations().size());
		try {
			openOfferDTO.setReputation(offer.getAuthor().getReputation().toString());
		} catch (AppException e) {
			openOfferDTO.setReputation(e.getMessage());
		}
		
		if (offer.getOperationType() == OperationType.BUY) {
			openOfferDTO.setAddressToOperate(offer.getAuthor().getCriptoWallet());
		}
		
		if (offer.getOperationType() == OperationType.SALE) {
			openOfferDTO.setAddressToOperate(offer.getAuthor().getCvu());
		}
		
		return openOfferDTO;
	}
	
	public OfferWithActionProcessedDTO convertOfferEntityToOfferWithActionProcessedDTO(Offer offer) {
		OfferWithActionProcessedDTO offerWithActionProcessedDTO = new OfferWithActionProcessedDTO();
		
		offerWithActionProcessedDTO.setCreationDate(offer.getCreationDate());
		offerWithActionProcessedDTO.setCryptoSymbol(offer.getCrypto().getSymbol());
		offerWithActionProcessedDTO.setAmountOfCrypto(offer.getAmountOfCrypto());
		offerWithActionProcessedDTO.setPriceOfCryptoInTheOffer(offer.getPriceOfCrypto());
		offerWithActionProcessedDTO.setAmountInPesos(offer.getAmountInPesos());
		offerWithActionProcessedDTO.setAuthorEmail(offer.getAuthor().getEmail());
		offerWithActionProcessedDTO.setOperationType(offer.getOperationType());
		offerWithActionProcessedDTO.setOfferState(offer.getOfferState());
		offerWithActionProcessedDTO.setClientEmail(offer.getClient().getEmail());
		offerWithActionProcessedDTO.setTradingStartDate(offer.getTradingStartDate());
		return offerWithActionProcessedDTO;
	}
}
