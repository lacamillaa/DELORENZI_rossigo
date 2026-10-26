package org.example.servizio;

import org.example.eccezioni.VeicoloGiàRegistratoException;
import org.example.modello.*;

import java.util.*;

public class FlottaService {
    private static HashMap<String, Veicolo> veicoliReg = new HashMap<>();

    public static void registraVeicolo(Veicolo v) {
        if (veicoliReg.containsKey(v.getCodVeicolo())) {
            throw new VeicoloGiàRegistratoException(v);
        }
        veicoliReg.put(v.getCodVeicolo(), v);
    }
}
