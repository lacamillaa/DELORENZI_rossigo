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
    public Float calcolaCosto(Integer minuti) {
        return 0.15F * minuti;
    }

    @Override
    public Float consumoBatteriaPerMinuto() {
        return 0.2F;
    }

    public Integer getMarce() {
        return marce;
    }
}
