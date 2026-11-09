package org.example;

import org.example.database.*;
import org.example.modello.*;
import org.example.servizio.*;

import java.sql.SQLException;
import java.util.*;
import static java.lang.IO.*;

public class Main {
    static void main() {
        ArrayList<Utente> users = new ArrayList<>();
        try {
            users = Database.getInstance().caricaUtenti();
        } catch (SQLException e) {
            println("Errore nel caricamento del database");
        }
        ArrayList<Veicolo> veicoli = new ArrayList<>();
        try {
            veicoli = Database.getInstance().caricaVeicoli();
        } catch (SQLException e) {
            println(e.getMessage());
        }
        ArrayList<Noleggio> noleggi = new ArrayList<>();
        try {
            noleggi = Database.getInstance().caricaNoleggi();
        } catch (SQLException e) {
            println(e.getMessage());
        }
        for (Utente u : users) {
            try {
                FlottaService.registraUtente(u);
                println(u);
            } catch (RuntimeException e) {
                println(e.getMessage());
            }
        }
        for (Veicolo v : veicoli) {
            try {
                FlottaService.registraVeicolo(v);
                println(v);
            } catch (RuntimeException e) {
                println(e.getMessage());
            }
        }
        for (Noleggio n : noleggi) {
            try {
                FlottaService.sbloccaVeicolo(n.getCodVeicolo(), n.getCodUtente());
                println(n);
            } catch (RuntimeException e) {
                println(e.getMessage());
            }
        }
    }
}
