package org.example.servizio;

import org.example.eccezioni.*;
import org.example.modello.*;

import java.util.*;

public class FlottaService {
    private static HashMap<String, Veicolo> veicoliReg = new HashMap<>();
    private static HashMap<String, Utente> utentiReg = new HashMap<>();

    public static void registraVeicolo(Veicolo v) {
        if (veicoliReg.containsKey(v.getCodVeicolo())) {
            throw new VeicoloGiàRegistratoException(v);
        }
        veicoliReg.put(v.getCodVeicolo(), v);
    }

    public static void registraUtente(Utente u) {
        if (utentiReg.containsKey(u.getCodFiscale())) {
            throw new UtenteGiàRegistratoException(u);
        }
        utentiReg.put(u.getCodFiscale(), u);
    }

    // se la batteria è scarica termina noleggio
    public static void sbloccaVeicolo(String codVeicolo, String codFiscale) {
        if (!utentiReg.containsKey(codFiscale)) {
            throw new UtenteNonTrovatoException(codFiscale);
        }
        if (!veicoliReg.containsKey(codVeicolo)) {
            throw new VeicoloNonTrovatoException(codVeicolo);
        }
        // fa il noleggio
        Veicolo v = veicoliReg.get(codVeicolo);
        if (v.getStato() != Stato.LIBERO) {
            throw new VeicoloNonDisponibileException(codVeicolo);
        }
        if (v.getBatteria() <= 15) {
            throw new BatteriaScaricaException(codVeicolo, v.getBatteria());
        }
    }
}
