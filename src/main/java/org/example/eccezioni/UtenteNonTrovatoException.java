package org.example.eccezioni;

public class UtenteNonTrovatoException extends RuntimeException {
    public UtenteNonTrovatoException(Integer id) {
        super("[ERRORE] Utente con id " + id + " non trovato");
    }
}
