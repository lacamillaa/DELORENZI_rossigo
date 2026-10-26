package org.example.eccezioni;

public class CodiceFiscaleNonValidoException extends RuntimeException {
    public CodiceFiscaleNonValidoException(String codFiscale) {
        super("[ERRORE] Codice fiscale non valido: " + codFiscale);
    }
}
