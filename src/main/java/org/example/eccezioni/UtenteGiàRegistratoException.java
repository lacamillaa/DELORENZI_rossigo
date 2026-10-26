package org.example.eccezioni;

import org.example.servizio.Utente;

public class UtenteGiàRegistratoException extends RuntimeException {
    public UtenteGiàRegistratoException(Utente u) {
        super("[ERRORE] Utente con cod.fiscale " + u.getCodFiscale() + "è già registrato");
    }
}
