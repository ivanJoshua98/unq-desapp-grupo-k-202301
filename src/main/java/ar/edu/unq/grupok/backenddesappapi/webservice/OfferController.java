package ar.edu.unq.grupok.backenddesappapi.webservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.edu.unq.grupok.backenddesappapi.model.Crypto;
import ar.edu.unq.grupok.backenddesappapi.model.InvalidPublishedPriceException;
import ar.edu.unq.grupok.backenddesappapi.model.Offer;
import ar.edu.unq.grupok.backenddesappapi.model.User;
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
	public ResponseEntity<Offer> createOffer(@RequestBody OfferDTO newOffer) throws InvalidPublishedPriceException{
		Crypto crypto = this.cryptoService.getCryptoBySymbol(newOffer.getCryptoSymbol());
		User user = this.userService.getUserByEmail(newOffer.getAuthorEmail());
		
		
		Offer offer = new Offer(crypto,
								newOffer.getAmountOfCrypto(),
								newOffer.getPriceOfCrypto(),
								newOffer.getAmountInPesos(),
								user,
								newOffer.getOperationType());
		return ResponseEntity.status(HttpStatus.CREATED).body(offerService.saveOffer(offer));
	}
	
	@Operation(summary = "Get all created offers")
	@GetMapping("/offers")
	public ResponseEntity<List<OfferDTO>> createdOffers(){
		return ResponseEntity.ok()
							 .body(offerService.getAllOffers()
									 .stream()
									 	.map(this::convertOfferEntityToOfferDTO).toList());
	}
	
	
	public OfferDTO convertOfferEntityToOfferDTO(Offer offer) {
		OfferDTO offerDTO = new OfferDTO();
		offerDTO.setCryptoSymbol(offer.getCrypto().getSymbol());
		offerDTO.setAmountOfCrypto(offer.getAmountOfCrypto());
		offerDTO.setPriceOfCrypto(offer.getPriceOfCrypto());
		offerDTO.setAmountInPesos(offer.getAmountInPesos());
		offerDTO.setAuthorEmail(offer.getAuthor().getEmail());
		offerDTO.setOperationType(offer.getOperationType());
		return offerDTO;
	}
	
}
