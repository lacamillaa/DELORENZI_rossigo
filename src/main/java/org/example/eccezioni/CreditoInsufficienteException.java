package org.example.eccezioni;

public class CreditoInsufficienteException extends RuntimeException {
    public CreditoInsufficienteException(Float costo, Float credito) {
        super("[ERRORE] Credito (" + credito + "eur) è insufficiente per una spesa di " + costo + "eur");
    }
}
