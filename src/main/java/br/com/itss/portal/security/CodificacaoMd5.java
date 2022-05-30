package br.com.itss.portal.security;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

public class CodificacaoMd5 {

    public static String codificar(String mensagem) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(mensagem.getBytes());
            BigInteger no = new BigInteger(1, messageDigest);
            String hashtext = no.toString(16);

            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }

            return hashtext;
        }

        catch (NoSuchAlgorithmException e) {
            System.out.println("Algoritmo incorreto: " + e);
            return null;
        }
    }

    public static boolean comparar(CharSequence mensagem, String codigo){
        String mensagemCodificada = Objects.requireNonNull(codificar(mensagem.toString())).toUpperCase();

        return mensagemCodificada.equals(codigo.toUpperCase());
    }
}
