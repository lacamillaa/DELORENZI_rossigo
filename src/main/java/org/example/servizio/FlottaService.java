package org.example.servizio;

import org.example.eccezioni.UtenteGiàRegistratoException;
import org.example.eccezioni.UtenteNonTrovatoException;
import org.example.eccezioni.VeicoloGiàRegistratoException;
import org.example.modello.*;

import java.util.*;

public class FlottaService {
    private static HashMap<String, Veicolo> veicoliReg = new HashMap<>();
    private static HashMap<String, Utente> utentiReg = new HashMap<>();
    private static ArrayList<Noleggio> noleggi = new ArrayList<>();

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
    public static void sbloccaVeicolo(String codVeicolo, Integer idUtente) {
        if (Utente.getUtenteById(idUtente).isEmpty()) {
            throw new UtenteNonTrovatoException(idUtente);
        }
        // fa il noleggio

    }
}
