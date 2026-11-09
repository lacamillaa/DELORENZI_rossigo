package org.example.database;

import org.example.eccezioni.CodiceFiscaleNonValidoException;
import org.example.eccezioni.EmailNonValidaException;
import org.example.modello.*;
import org.example.servizio.Noleggio;
import org.example.servizio.Utente;

import java.sql.*;
import java.lang.*;
import java.util.*;

import static java.lang.IO.println;

public class Database {
    private static Database instance;
    private Connection connection;

    private Database() throws SQLException {
        String url = "jdbc:sqlite:rossigo.db";
        connection = DriverManager.getConnection(url);
        System.out.println("Connesso al database");
    }

    public static Database getInstance() throws SQLException {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }

    public boolean isConnected() {
        try {
            if (connection == null || !connection.isValid(5)) {
                System.err.println("Connection is null or invalid");
                return false;
            }
        } catch (SQLException e) {
            System.err.println("Connection is null or invalid");
            return false;
        }
        return true;
    }

    public ArrayList<Veicolo> caricaVeicoli() {
        if (!isConnected()) return null;
        String query = "SELECT * from veicoli";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet rs = statement.executeQuery();
            ArrayList<Veicolo> result = new ArrayList<>();
            while (rs.next()) {
                Veicolo v;
                String codVeicolo = rs.getString("cod_veicolo");
                Float batteria = rs.getFloat("batteria");
                Stato stato = Stato.valueOf(rs.getString("stato"));
                Boolean isBici = rs.getBoolean("bici");
                if (isBici) {
                    Integer marce = rs.getInt("marce");
                    Float tariffa = rs.getFloat("tariffa");
                    v = new Bici(codVeicolo, marce, tariffa);
                }
                else {
                    Float velocitàMax = rs.getFloat("velocitàMax");
                    v = new Monopattino(codVeicolo, velocitàMax);
                }
                v.setBatteria(batteria);
                v.setStato(stato);
                result.add(v);
            }
            return result;
        } catch (SQLException e) {
            System.err.println("Error in query: " + query);
        }
        return new ArrayList<>();
    }

    public ArrayList<Utente> caricaUtenti() {
        if (!isConnected()) return null;
        String query = "SELECT * from utenti";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet rs = statement.executeQuery();
            ArrayList<Utente> result = new ArrayList<>();
            while (rs.next()) {
                Utente u;
                String codFiscale = rs.getString("cod_fiscale");
                String nome = rs.getString("nome");
                String email = rs.getString("email");
                Float credito = rs.getFloat("credito");
                Float debito = rs.getFloat("debito");
                try {
                    u = new Utente(nome, email, codFiscale);
                    u.setCredito(credito);
                    u.setDebito(debito);
                    result.add(u);
                } catch (CodiceFiscaleNonValidoException | EmailNonValidaException e) {
                    println(e.getMessage());
                }
            }
            return result;
        } catch (SQLException e) {
            System.err.println("Error in query: " + query);
        }
        return new ArrayList<>();
    }

    public ArrayList<Noleggio> caricaNoleggi() {
        if (!isConnected()) return null;
        String query = "SELECT * from noleggi";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet rs = statement.executeQuery();
            ArrayList<Noleggio> result = new ArrayList<>();
            while (rs.next()) {
                String codFiscale = rs.getString("cod_utente");
                String codVeicolo = rs.getString("cod_veicolo");
                Noleggio n = new Noleggio(codFiscale, codVeicolo);
                result.add(n);
            }
            return result;
        } catch (SQLException e) {
            System.err.println("Error in query: " + query);
        }
        return new ArrayList<>();
    }
}
