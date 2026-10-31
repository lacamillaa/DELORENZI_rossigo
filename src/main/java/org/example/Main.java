package org.example;

import org.example.modello.Bici;
import org.example.modello.Monopattino;
import org.example.modello.Veicolo;
import org.example.servizio.FlottaService;
import org.example.servizio.Utente;

import java.util.ArrayList;

import static java.lang.IO.println;

public class Main {
    static void main() {
        ArrayList<Utente> users = new ArrayList<>();
        try {
            users.add(new Utente("Mario Rossi", "mrossi@gmail.com", "RSSMRA80B23L840R"));
            users.add(new Utente("Luisa Verdi", "luisa.verdi@yahoo.it", "VRDLSU65C47L219F"));
            users.add(new Utente("Chiara Bianchi", "bianchichiara92@outlook.com", "BNCCHR92P56G273V"));
            users.add(new Utente("Elio Galli", "e.galli.business@gmail.com", "ELIOGALLI45@"));
        } catch (RuntimeException e) {
            println(e.getMessage());
        }
        ArrayList<Veicolo> veicoli = new ArrayList<>();
        try {
            veicoli.add(new Bici("ABCDEFGH", 6, 0.15F));
            veicoli.add(new Monopattino("EF34552H", 15.0F));
            veicoli.add(new Monopattino("RG42192A", 32.0F));
            // scenario: si aggiunge un veicolo con codice già registrato
            veicoli.add(new Bici("RG42192A", 5, 0.25F));
        } catch (RuntimeException e) {
            println(e.getMessage());
        }
        for (Utente u : users) {
            try {
                FlottaService.registraUtente(u);
            } catch (RuntimeException e) {
                println(e.getMessage());
            }
        }
        for (Veicolo v : veicoli) {
            try {
                FlottaService.registraVeicolo(v);
            } catch (RuntimeException e) {
                println(e.getMessage());
            }
        }
        // scenario: si noleggia una bici
        try {
            Utente u = FlottaService.getUtente("RSSMRA80B23L840R");
            Veicolo v = FlottaService.getVeicolo("ABCDEFGH");
            u.ricaricaCredito(0.5f);
            FlottaService.sbloccaVeicolo(v.getCodVeicolo(), u.getCodFiscale());
            // scenario: un utente prova a sbloccare un veicolo già in uso
            Utente u2 = FlottaService.getUtente("VRDLSU65C47L219F");
            FlottaService.sbloccaVeicolo(v.getCodVeicolo(), u2.getCodFiscale());
        } catch (RuntimeException e) {
            println(e.getMessage());
        }
        try {
            Veicolo v = FlottaService.getVeicolo("ABCDEFGH");
            // scenario: l'utente non ha credito sufficiente
            FlottaService.terminaNoleggio(v.getCodVeicolo(), 35);
        } catch (RuntimeException e) {
            println(e.getMessage());
        }
    }
}
