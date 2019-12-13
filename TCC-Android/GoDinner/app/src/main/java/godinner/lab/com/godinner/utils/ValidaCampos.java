package godinner.lab.com.godinner.utils;

import java.util.InputMismatchException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import godinner.lab.com.godinner.model.Cidade;
import godinner.lab.com.godinner.model.Estado;

public class ValidaCampos {

    public static boolean isValidEmail(String email) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9]+(\\.[_A-Za-z0-9]+)*@[a-z0-9]+(\\.[a-z0-9]+)*(\\.[a-z0-9]{2,})$";
        Pattern patternEmail = Pattern.compile(EMAIL_PATTERN);
        Matcher matcherEmail = patternEmail.matcher(email);

        if (matcherEmail.matches()) {
            return true;
        }
        return false;
    }

    public static boolean isValidCep(String cep) {
        String CEP_PATTERN = "[0-9]{5}-?[0-9]{3}$";
        Pattern patternCEP = Pattern.compile(CEP_PATTERN);
        Matcher matcherCEP = patternCEP.matcher(cep);
        if (matcherCEP.matches()) {
            return true;
        }
        return false;
    }

    public static boolean isValidTelefone(String telefone) {
        String FONE_PATTERN = "\\(?[0-9]{2}\\)? ?9[0-9]{4}-?[0-9]{4}$";
        Pattern patternFONE = Pattern.compile(FONE_PATTERN);
        Matcher matcherFONE = patternFONE.matcher(telefone);
        if (matcherFONE.matches()) {
            return true;
        }
        return false;
    }

    public static boolean isValidCpf(String CPF) {
        if (CPF.equals("00000000000") || CPF.equals("11111111111") ||
                CPF.equals("22222222222") || CPF.equals("33333333333") ||
                CPF.equals("44444444444") || CPF.equals("55555555555") ||
                CPF.equals("66666666666") || CPF.equals("77777777777") ||
                CPF.equals("88888888888") || CPF.equals("99999999999") || (CPF.length() != 11))
            return false;

        char dig10, dig11;
        int sm, i, r, num, peso;

        try {
            sm = 0;
            peso = 10;
            for (i = 0; i < 9; i++) {
                num = CPF.charAt(i) - 48;
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11)) {
                dig10 = '0';
            } else {
                dig10 = (char) (r + 48);
            }

            sm = 0;
            peso = 11;
            for (i = 0; i < 10; i++) {
                num = CPF.charAt(i) - 48;
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11)) {
                dig11 = '0';
            } else {
                dig11 = (char) (r + 48);
            }

            if ((dig10 == CPF.charAt(9)) && (dig11 == CPF.charAt(10))) {
                return true;
            } else {
                return false;
            }
        } catch (InputMismatchException erro) {
            return false;
        }
    }

    public static boolean isValidEstado(Estado e) {
        if (e.getIdEstado() == 0) {
            return true;
        }
        return false;
    }

    public static boolean isValidCidade(Cidade c) {
        if (c.getIdCidade() == 0) {
            return true;
        }
        return false;
    }
}
