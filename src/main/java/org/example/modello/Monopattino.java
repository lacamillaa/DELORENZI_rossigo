package org.example.modello;

public class Monopattino extends Veicolo {
    private Float velocitàMax;

    public Monopattino(String codVeicolo, Float velocitàMax) {
        super(codVeicolo);
        this.velocitàMax = velocitàMax;
    }

    @Override
    public Double calcolaCosto(Integer minuti) {
        return 0.25 * minuti;
    }

    @Override
    public Double consumoBatteriaPerMinuto() {
        return 0.5;
    }

    public boolean isLimitatoInZonaPedonale() {
        return this.velocitàMax > 30.0;
    }
}
