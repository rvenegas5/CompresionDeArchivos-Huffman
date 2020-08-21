package ec.edu.espol.modelo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.lang.StringBuilder;

/**
 *
 * @author WILLIAM
 */
public class Util {

    public static String leerTexto(String nombreArchivo) {
        String linea = null;

        try {
            File file = new File(nombreArchivo);
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            linea = br.readLine();

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return linea;
    }

    public static HashMap<String, Integer> calcularFrecuencias(String texto) {
        HashMap<String, Integer> frequency = new HashMap<>();

        String c;
        for (int i = 0; i < texto.length(); i++) {
            c = texto.charAt(i) + "";
            if (!frequency.containsKey(c)) {
                frequency.put(c, 1);
            } else {
                frequency.put(c, frequency.get(c) + 1);
            }
        }
        return frequency;
    }

    public static String HexToBinary(String Hex) {
        int i = Integer.parseInt(Hex, 16);
        String Bin = Integer.toBinaryString(i);
        return Bin;
    }

    public static String binarioHexadecimal(String binario) {
        String resultado = "";
        String completarHex = "";
        while (binario.length() % 4 != 0) {
            binario += "0";
            completarHex += "-";
        }

        for (int i = 0; i < binario.length(); i += 4) {

            int numero = Integer.parseInt(binario.substring(i, i + 4), 2);
            String reprHex = Integer.toString(numero, 16);
            resultado += reprHex;
        }
        return (resultado + completarHex).toUpperCase();
    }
}
