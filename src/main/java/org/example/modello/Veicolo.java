package org.example.modello;

import org.apache.commons.lang3.Validate;

public abstract class Veicolo {
    protected enum Stato {
        LIBERO, IN_USO, IN_RICARICA
    }

    private String codVeicolo;
    private Float batteria;
    private Stato stato;

    public Veicolo(String codVeicolo, Stato stato) {
        this.codVeicolo = codVeicolo;
        this.batteria = 100.0F;
        this.stato = stato;
    }

    public abstract Double calcolaCosto();
    public abstract Double consumoBatteriaPerMinuto();
}
