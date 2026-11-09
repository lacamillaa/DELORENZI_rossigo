package org.example.servizio;

import org.example.modello.Veicolo;

import java.time.LocalDateTime;

public class Noleggio {
    private String codUtente;
    private String codVeicolo;

    public Noleggio(String codUtente, String codVeicolo) {
        this.codUtente = codUtente;
        this.codVeicolo = codVeicolo;
    }

    public String getCodUtente() {
        return codUtente;
    }

    public String getCodVeicolo() {
        return codVeicolo;
    }

    @Override
    public String toString() {
        return "Il veicolo " + codVeicolo + " è in noleggio all'utente " + codUtente;
    }
}
