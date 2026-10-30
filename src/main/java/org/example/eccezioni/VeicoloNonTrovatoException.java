package org.example.eccezioni;

public class VeicoloNonTrovatoException extends RuntimeException {
    public VeicoloNonTrovatoException(String codVeicolo) {
        super("[ERRORE] Veicolo con codice " + codVeicolo + " non è stato trovato");
    }
}
