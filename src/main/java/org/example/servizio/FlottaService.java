package org.example.servizio;

import org.example.eccezioni.*;
import org.example.modello.*;

import java.time.LocalDateTime;
import java.util.*;

import static java.lang.IO.println;

public class FlottaService {
    private static HashMap<String, Veicolo> veicoliReg = new HashMap<>();
    private static HashMap<String, Utente> utentiReg = new HashMap<>();
    private static HashMap<String, Noleggio> noleggioHashMap = new HashMap<>();

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

    public static void stampaUtenti() {
        for (Utente u : utentiReg.values()) {
            println(u);
        }
    }

    public static void stampaVeicoli() {
        for (Veicolo v : veicoliReg.values()) {
            println(v);
        }
    }

    public static void stampaVeicoli(Stato stato) {
        for (Veicolo v : veicoliReg.values()) {
            if (v.getStato() == stato) {
                println(v.getCodVeicolo());
            }
        }
    }

    public static void stampaNoleggi() {
        for (Noleggio n : noleggioHashMap.values()) {
            println(n);
        }
    }

    public static Utente getUtente(String codFiscale) {
        if (!utentiReg.containsKey(codFiscale)) {
            throw new UtenteNonTrovatoException(codFiscale);
        }
        return utentiReg.get(codFiscale);
    }

    public static Veicolo getVeicolo(String codVeicolo) {
        if (!veicoliReg.containsKey(codVeicolo)) {
            throw new VeicoloNonTrovatoException(codVeicolo);
        }
        return veicoliReg.get(codVeicolo);
    }

    public static void sbloccaVeicolo(String codVeicolo, String codFiscale) {
        if (!utentiReg.containsKey(codFiscale)) {
            throw new UtenteNonTrovatoException(codFiscale);
        }
        Utente u = utentiReg.get(codFiscale);
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
        // noleggia il veicolo all'utente
        Noleggio n = new Noleggio(u.getCodFiscale(), v.getCodVeicolo());
        noleggioHashMap.put(v.getCodVeicolo(), n);
        v.setStato(Stato.IN_USO);
    }

    public static void terminaNoleggio(String codVeicolo, Integer minutiUsati) {
        Veicolo v = veicoliReg.getOrDefault(codVeicolo, null);
        if (v == null) throw new VeicoloNonTrovatoException(codVeicolo);
        Float costoTot = v.calcolaCosto(minutiUsati) + 1;
        Float consumoBatteria = v.consumoBatteriaPerMinuto() * minutiUsati;
        // addebita il costoTot all'utente
        Noleggio n = noleggioHashMap.getOrDefault(codVeicolo, null);
        if (n == null) return;
        String codU = n.getCodUtente();
        try {
            if (!utentiReg.containsKey(codU)) {
                throw new UtenteNonTrovatoException(codU);
            }
            Utente u = utentiReg.get(codU);
            u.addebita(costoTot);
        } catch (CreditoInsufficienteException e) {
            println(e.getMessage());
        } catch (UtenteNonTrovatoException e) {
            println(e.getMessage());
        }
        v.scalaBatteria(consumoBatteria);
        if (v.getBatteria() < 15) {
            mettiInRicarica(codVeicolo);
        }
        else {
            v.setStato(Stato.LIBERO);
        }
        noleggioHashMap.remove(codVeicolo);
    }

    public static void mettiInRicarica(String codVeicolo) {
        Veicolo v = veicoliReg.get(codVeicolo);
        v.setStato(Stato.IN_RICARICA);
    }
}
