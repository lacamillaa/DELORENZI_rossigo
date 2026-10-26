package org.example.eccezioni;

public class EmailNonValidaException extends RuntimeException {
    public EmailNonValidaException(String email) {
        super("[ERRORE] Inserita email non valida: " + email);
    }
}
