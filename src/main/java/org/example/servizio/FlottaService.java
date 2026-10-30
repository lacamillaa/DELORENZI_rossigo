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

    // se la batteria è scarica termina noleggio
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
        // noleggia il veicolo all'utente per 4h massimo
        Noleggio n = new Noleggio(u, v, LocalDateTime.now(), LocalDateTime.now().plusHours(4));
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
        Utente u = n.getUtente();
        try {
            u.addebita(costoTot);
        } catch (CreditoInsufficienteException e) {
            println(e.getMessage());
            println("L'utente " + u.getCodFiscale() + " è in debito di " + u.getDebito() + "eur");
        }
        v.scalaBatteria(consumoBatteria);
        if (v.getBatteria() < 15) {
            v.setStato(Stato.IN_RICARICA);
        }
        else {
            v.setStato(Stato.LIBERO);
        }
        noleggioHashMap.remove(codVeicolo);
    }
}
