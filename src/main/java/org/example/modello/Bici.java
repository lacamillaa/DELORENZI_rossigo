package org.example.modello;

public class Bici extends Veicolo {
    private Integer marce;
    private Float tariffa;

    public Bici(String codVeicolo, Integer marce, Float tariffa) {
        super(codVeicolo);
        this.marce = marce;
        this.tariffa = tariffa;
    }

    @Override
    public Float calcolaCosto(Integer minuti) {
        return tariffa * minuti;
    }

    @Override
    public Float consumoBatteriaPerMinuto() {
        return 0.2F;
    }

    @Override
    public String toString() {
        return ("BICI " + getCodVeicolo()) + "\n" +
                super.toString() + "\n" +
                ("# marce: " + marce) + "\n" +
                ("tariffa: " + tariffa + "eur/min");
    }
}
