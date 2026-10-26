package org.example.servizio;

import org.example.modello.Veicolo;

import java.time.LocalDateTime;

public class Noleggio {
    private Utente utente;
    private Veicolo veicolo;
    private LocalDateTime oraInizio;
    private LocalDateTime oraFine;

    public Noleggio(Utente utente, Veicolo veicolo, LocalDateTime oraInizio, LocalDateTime oraFine) {
        this.utente = utente;
        this.veicolo = veicolo;
        this.oraInizio = oraInizio;
        this.oraFine = oraFine;
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

    public LocalDateTime getOraInizio() {
        return oraInizio;
    }

    public void setOraInizio(LocalDateTime oraInizio) {
        this.oraInizio = oraInizio;
    }

    public LocalDateTime getOraFine() {
        return oraFine;
    }

    public void setOraFine(LocalDateTime oraFine) {
        this.oraFine = oraFine;
    }
}
