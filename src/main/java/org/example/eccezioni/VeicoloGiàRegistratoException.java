package org.example.eccezioni;

import org.example.modello.Veicolo;

public class VeicoloGiàRegistratoException extends RuntimeException {
    public VeicoloGiàRegistratoException(Veicolo v) {
        super("[ERRORE] Veicolo " + v.getCodVeicolo() + " è già registrato");
    }
}
