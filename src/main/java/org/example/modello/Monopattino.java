package org.example.modello;

public class Monopattino extends Veicolo {
    private Float velocitàMax;

    public Monopattino(String codVeicolo, Float velocitàMax) {
        super(codVeicolo);
        this.velocitàMax = velocitàMax;
    }

    @Override
    public Float calcolaCosto(Integer minuti) {
        return 0.25F * minuti;
    }

    @Override
    public Float consumoBatteriaPerMinuto() {
        return 0.5F;
    }

    public boolean isLimitatoInZonaPedonale() {
        return this.velocitàMax > 30.0;
    }
}
