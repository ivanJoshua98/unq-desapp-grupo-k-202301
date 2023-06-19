package ar.edu.unq.grupok.backenddesappapi.dto;

import java.time.LocalDateTime;

public class CryptoVolumeDTO {

    private LocalDateTime requestDate;

    private String cryptoSymbol;

    private Double priceOfCrypto;

    private Double totalAmountUSD;

    private Integer totalAmountPesos;

    private Integer totalAmountOfCrypto;

    private Integer priceOfCryptoInPesos;

    // Getters And Setters

    public LocalDateTime getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(LocalDateTime requestDate) {
        this.requestDate = requestDate;
    }

    public String getCryptoSymbol() {
        return cryptoSymbol;
    }

    public void setCryptoSymbol(String cryptoSymbol) {
        this.cryptoSymbol = cryptoSymbol;
    }

    public Double getPriceOfCrypto() {
        return priceOfCrypto;
    }

    public void setPriceOfCrypto(Double priceOfCrypto) {
        this.priceOfCrypto = priceOfCrypto;
    }

    public Double getTotalAmountUSD() {
        return totalAmountUSD;
    }

    public void setTotalAmountUSD(Double totalAmountUSD) {
        this.totalAmountUSD = totalAmountUSD;
    }

    public Integer getTotalAmountPesos() {
        return totalAmountPesos;
    }

    public void setTotalAmountPesos(Integer totalAmountPesos) {
        this.totalAmountPesos = totalAmountPesos;
    }

    public Integer getTotalAmountOfCrypto() {
        return totalAmountOfCrypto;
    }

    public void setTotalAmountOfCrypto(Integer totalAmountOfCrypto) {
        this.totalAmountOfCrypto = totalAmountOfCrypto;
    }

    public Integer getPriceOfCryptoInPesos() {
        return priceOfCryptoInPesos;
    }

    public void setPriceOfCryptoInPesos(Integer priceOfCryptoInPesos) {
        this.priceOfCryptoInPesos = priceOfCryptoInPesos;
    }
}
