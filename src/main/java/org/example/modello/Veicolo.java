package org.example.modello;
import org.apache.commons.lang3.Validate;

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

    public abstract Double calcolaCosto(Integer minuti);
    public abstract Double consumoBatteriaPerMinuto();
}
