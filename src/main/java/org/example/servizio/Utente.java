package org.example.servizio;

import it.kamaladafrica.codicefiscale.*;
import org.apache.commons.validator.routines.*;
import org.example.eccezioni.*;

import java.util.ArrayList;
import java.util.Optional;

import static java.lang.IO.print;
import static java.lang.IO.println;

public class Utente {
    private String nome;
    private String email;
    private String codFiscale;
    private Float credito = 0.0F;
    private Float debito = 0.0F;

    public Utente(String nome, String email, String codFiscale) {
        EmailValidator validator = EmailValidator.getInstance(true, true);
        if (validator.isValid(email)) this.email = email;
        else throw new EmailNonValidaException(email);
        if (CodiceFiscale.isFormatValid(codFiscale)) this.codFiscale = codFiscale;
        else throw new CodiceFiscaleNonValidoException(codFiscale);
        this.nome = nome;
    }

    public String getCodFiscale() {
        return codFiscale;
    }

    public Float getDebito() {
        return debito;
    }

    public void setCredito(Float credito) {
        this.credito = credito;
    }

    public void setDebito(Float debito) {
        this.debito = debito;
    }

    public void ricaricaCredito(Float somma) {
        // riduci immediatamente il debito
        // somma -= debito
        // se somma < 0 (debito > somma): debito -= somma; return;
        // else debito = 0;
        // poi aggiungi al credito
        somma -= this.debito;
        if (somma < 0) {
            this.debito -= somma;
            return;
        }
        else {
            this.debito = 0.0F;
        }
        this.credito += somma;
    }

    public void addebita(Float costo) {
        if (costo > this.credito) {
            this.debito += costo - this.credito;
            Float credito = this.credito;
            this.credito = 0.0F;
            throw new CreditoInsufficienteException(costo, credito, this);
        }
        this.credito -= costo;
    }

    @Override
    public String toString() {
        return (nome + " (cod.fiscale " + codFiscale + ")") + "\n" +
                ("email: " + email) + "\n" +
                ("credito attuale: " + credito + "eur") + "\n" +
                ("debito: " + debito + "eur");
    }
}
