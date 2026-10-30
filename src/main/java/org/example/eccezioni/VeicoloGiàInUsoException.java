package org.example.eccezioni;

public class VeicoloGiàInUsoException extends RuntimeException {
    public VeicoloGiàInUsoException(String codVeicolo) {
        super("[ERRORE] Il veicolo con codice " + codVeicolo + " attualmente non è disponibile");
    }
}
