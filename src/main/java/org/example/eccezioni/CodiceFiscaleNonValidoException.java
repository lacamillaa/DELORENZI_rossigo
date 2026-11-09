package org.example.eccezioni;

public class CodiceFiscaleNonValidoException extends RuntimeException {
    private String codFiscale;

    public CodiceFiscaleNonValidoException(String codFiscale) {
        this.codFiscale = codFiscale;
        super("[ERRORE] Codice fiscale non valido: " + codFiscale);
    }

    public String getCodFiscale() {
        return codFiscale;
    }
}
