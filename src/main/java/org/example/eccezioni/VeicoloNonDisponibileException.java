package org.example.eccezioni;

public class VeicoloNonDisponibileException extends RuntimeException {
    public VeicoloNonDisponibileException(String codVeicolo) {
        super("[ERRORE] Il veicolo con codice " + codVeicolo + " attualmente non è disponibile");
    }
}
