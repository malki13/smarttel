package com.telcom.ups.monolitico.util.data;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.joda.time.DateTime;
import org.joda.time.Days;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Conversion {

    public static String getHexadecimal(String caracter) {
        return "0" + Integer.toHexString(Integer.parseInt(caracter));
    }

    public static String toHexadecimal(String cadena) {
        StringBuilder strBuilder = new StringBuilder();
        for (int i = 0; i < cadena.length(); i++) {
            strBuilder.append(getHexadecimal(Character.toString(cadena.charAt(i))));
        }
        return strBuilder.toString();
    }

    public static String toBase64(String cadena) throws DecoderException {
        String res = toHexadecimal(cadena);
        byte[] decodedHex = org.apache.commons.codec.binary.Hex.decodeHex(res.toCharArray());
        return Base64.encodeBase64String(decodedHex);
    }

    public static String toBase64FromCadenaHex(String cadena) throws DecoderException {
        return Base64.encodeBase64String(Hex.decodeHex(cadena.toCharArray()));
    }

    public static boolean fechaValida(String fecha) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSS'Z'");
        Date fechaDispositivo = formatter.parse(fecha);
        Date fechaActual = new Date();
        int resta = Days.daysBetween(new DateTime(fechaDispositivo), new DateTime(fechaActual)).getDays();
        if (resta <= 1) return true;
        return false;
    }
}
