package ar.edu.unq.grupok.backenddesappapi.dto;

import java.io.Serializable;

public class TokenDTO implements Serializable {

	  private static final long serialVersionUID = 1L;

	  private final String jwtToken;

	  public TokenDTO(String jwtToken) {
	    this.jwtToken = jwtToken;
	  }

	  public String getJwtToken() {
	    return this.jwtToken;
	  }
	}