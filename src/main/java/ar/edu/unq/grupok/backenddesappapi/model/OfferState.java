package ar.edu.unq.grupok.backenddesappapi.model;

public enum OfferState {

	OPEN,   // When offer is published
	INPROCESS, //When offer is accepted and transaction is reported 
	CLOSE,  //When received transaction is confirmed by offer's author/user who sells
	CANCELLED // When offer is cancelled by system
	
}
