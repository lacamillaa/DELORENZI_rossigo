package org.example.modello;

public class Bici extends Veicolo {
    private Integer marce;
    private Double tariffa;

    public Bici(String codVeicolo, Integer marce, Double tariffa) {
        super(codVeicolo);
        this.marce = marce;
        this.tariffa = tariffa;
    }

    @Override
    public Double calcolaCosto(Integer minuti) {
        return 0.15 * minuti;
    }

    @Override
    public Double consumoBatteriaPerMinuto() {
        return 0.2;
    }

    public Integer getMarce() {
        return marce;
    }
}
