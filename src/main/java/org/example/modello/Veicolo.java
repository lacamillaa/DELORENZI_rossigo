package org.example.modello;
import org.apache.commons.lang3.Validate;

import java.util.*;

public abstract class Veicolo {
    private String codVeicolo;
    private Float batteria;
    private Stato stato;

    public Veicolo(String codVeicolo) {
        this.codVeicolo = codVeicolo;
        this.batteria = 100.0F;
        this.stato = Stato.LIBERO;
    }

    public String getCodVeicolo() {
        return codVeicolo;
    }

    public Float getBatteria() {
        return batteria;
    }

    public void setBatteria(Float batteria) {
        this.batteria = batteria;
    }

    public Stato getStato() {
        return stato;
    }

    public void setStato(Stato stato) {
        this.stato = stato;
    }

    public void scalaBatteria(Float consumo) {
        this.batteria -= consumo;
        this.batteria = Math.max(this.batteria, 0);
        if (this.batteria <= 15) {
            this.setStato(Stato.IN_RICARICA);
        }
    }

    @Override
    public String toString() {
        return ("Stato attuale: " + getStato()) + "\n" +
                ("Livello batteria: " + getBatteria());
    }

    public abstract Float calcolaCosto(Integer minuti);
    public abstract Float consumoBatteriaPerMinuto();
}
