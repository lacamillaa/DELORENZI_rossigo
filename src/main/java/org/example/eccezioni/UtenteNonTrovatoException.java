package org.example.eccezioni;

public class UtenteNonTrovatoException extends RuntimeException {
    public UtenteNonTrovatoException(String id) {
        super("[ERRORE] Utente con id " + id + " non trovato");
    }
}
