package org.example.servizio;

import org.example.modello.Veicolo;

import java.time.LocalDateTime;

public class Noleggio {
    private Utente utente;
    private Veicolo veicolo;

    public Noleggio(Utente utente, Veicolo veicolo) {
        this.utente = utente;
        this.veicolo = veicolo;
    }

    public Utente getUtente() {
        return utente;
    }

    @Override
    public String toString() {
        return "Il veicolo " + veicolo.getCodVeicolo() + " è in noleggio all'utente " + utente.getCodFiscale();
    }
}
