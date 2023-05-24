package ar.edu.unq.grupok.backenddesappapi.model;

import java.time.LocalDateTime;

public class CryptoVolume {

    private LocalDateTime requestDate;

    private String CryptoSymbol;

    private Double priceOfCrypto;

    private Double totalAmountUSD;

    private Integer totalAmountPesos;

    private Integer totalAmountOfCrypto;

    private Integer priceOfCryptoInPesos;

    public CryptoVolume() {
        this.requestDate = LocalDateTime.now();
        this.totalAmountUSD = 0.0;
        this.totalAmountPesos = 0;
        this.totalAmountOfCrypto = 0;
    }

    public LocalDateTime getRequestDate() { return requestDate; }

    public String getCryptoSymbol() {
        return CryptoSymbol;
    }

    public void setCryptoSymbol(String cryptoSymbol) {
        CryptoSymbol = cryptoSymbol;
    }

    public Double getPriceOfCrypto() {
        return priceOfCrypto;
    }

    public void setPriceOfCrypto(Double priceOfCrypto) {
        this.priceOfCrypto = priceOfCrypto;
    }

    public Integer getPriceOfCryptoInPesos() {
        return priceOfCryptoInPesos;
    }

    public void setPriceOfCryptoInPesos(Integer priceOfCryptoInPesos) {
        this.priceOfCryptoInPesos = priceOfCryptoInPesos;
    }

    public Double getTotalAmountUSD() {
        return totalAmountUSD;
    }

    public void incrementTotalAmountUSD(Double amountOfUSD) {
        this.totalAmountUSD += amountOfUSD;
    }

    public Integer getTotalAmountPesos() {
        return totalAmountPesos;
    }

    public void incrementTotalAmountPesos(Integer amountOfPesos) {
        this.totalAmountPesos += amountOfPesos;
    }

    public Integer getTotalAmountOfCrypto() {
        return totalAmountOfCrypto;
    }

    public void incrementTotalAmountOfCrypto(Integer amountOfCrypto) {
        this.totalAmountOfCrypto += amountOfCrypto;
    }

}
