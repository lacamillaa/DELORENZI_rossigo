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

    public void setUtente(Utente utente) {
        this.utente = utente;
    }

    public Veicolo getVeicolo() {
        return veicolo;
    }

    public void setVeicolo(Veicolo veicolo) {
        this.veicolo = veicolo;
    }
}
