package org.example.servizio;

import it.kamaladafrica.codicefiscale.*;
import org.apache.commons.validator.routines.*;
import org.example.eccezioni.*;

import java.util.ArrayList;
import java.util.Optional;

public class Utente {
    private static Integer USER_COUNT = 0;
    private static ArrayList<Utente> USER_MAP = new ArrayList<>();

    private Integer id;
    private String nome;
    private String email;
    private String codFiscale;
    private Double credito;

    public Utente(String nome, String email, String codFiscale) {
        EmailValidator validator = EmailValidator.getInstance(true, true);
        if (validator.isValid(email)) this.email = email;
        else throw new EmailNonValidaException(email);
        if (CodiceFiscale.isFormatValid(codFiscale)) this.codFiscale = codFiscale;
        else throw new CodiceFiscaleNonValidoException(codFiscale);
        Utente.USER_COUNT++;
        this.id = Utente.USER_COUNT;
        this.nome = nome;
        USER_MAP.add(this);
    }

    public static Optional<Utente> getUtenteById(Integer id) {
        try {
            return Optional.of(USER_MAP.get(id));
        } catch (IndexOutOfBoundsException e) {
            return Optional.empty();
        }
    }

    public String getCodFiscale() {
        return codFiscale;
    }
}
