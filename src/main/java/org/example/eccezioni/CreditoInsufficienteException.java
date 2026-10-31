package org.example.eccezioni;

import org.example.servizio.Utente;

public class CreditoInsufficienteException extends RuntimeException {
    public CreditoInsufficienteException(Float costo, Float credito, Utente u) {
        super("[ERRORE] Credito (" + credito + "eur) è insufficiente per una spesa di " + costo + "eur"
                + "\nL'utente " + u.getCodFiscale() + " ha un debito di " + u.getDebito() + "eur");
    }
}
