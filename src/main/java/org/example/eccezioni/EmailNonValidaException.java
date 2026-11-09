package org.example.eccezioni;

public class EmailNonValidaException extends RuntimeException {
    private String email;

    public EmailNonValidaException(String email) {
        this.email = email;
        super("[ERRORE] Inserita email non valida: " + email);
    }

    public String getEmail() {
        return email;
    }
}
