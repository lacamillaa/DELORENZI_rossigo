package org.example.servizio;

import it.kamaladafrica.codicefiscale.CodiceFiscale;
import org.apache.commons.validator.routines.*;
import org.example.eccezioni.CodiceFiscaleNonValidoException;
import org.example.eccezioni.EmailNonValidaException;

import java.io.InputStream;
import java.util.Map;

public class Utente {
    private static Integer USER_COUNT = 0;

    private Integer id;
    private String nome;
    private String email;
    private String codFiscale;
    private Double credito;

    public Utente(String nome, String email, String codFiscale) {
        Utente.USER_COUNT++;
        this.id = Utente.USER_COUNT;
        this.nome = nome;
        EmailValidator validator = EmailValidator.getInstance(true, true);
        if (validator.isValid(email)) this.email = email;
        else throw new EmailNonValidaException(email);
        if (CodiceFiscale.isFormatValid(codFiscale)) this.codFiscale = codFiscale;
        else throw new CodiceFiscaleNonValidoException(codFiscale);
    }

    public String getCodFiscale() {
        return codFiscale;
    }
}
