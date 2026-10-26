package org.example.modello;

import org.example.modello.*;

public class Bici extends Veicolo {
    private Integer marce;
    private Double tariffa;

    public Bici(String codVeicolo, Integer marce, Double tariffa) {
        super(codVeicolo, Stato.LIBERO);
        this.marce = marce;
        this.tariffa = tariffa;
    }

    @Override
    public Double calcolaCosto() {
        return 0.15;
    }

    @Override
    public Double consumoBatteriaPerMinuto() {
        return 0.2;
    }
}
