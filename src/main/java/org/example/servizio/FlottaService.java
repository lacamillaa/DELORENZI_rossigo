package org.example.servizio;

import org.example.eccezioni.UtenteGiàRegistratoException;
import org.example.eccezioni.VeicoloGiàRegistratoException;
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
}
