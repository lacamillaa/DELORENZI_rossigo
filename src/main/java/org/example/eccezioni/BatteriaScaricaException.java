package org.example.eccezioni;

public class BatteriaScaricaException extends RuntimeException {
    public BatteriaScaricaException(String codVeicolo, Float batteria) {
        super("[ERRORE] La batteria del veicolo " + codVeicolo + " è al " + batteria + "%");
    }
}
