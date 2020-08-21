package ec.edu.espol.modelo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

/**
 *
 * @author WILLIAM
 */
public class Util {

    public String leerTexto(String nombreArchivo) {
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

    public HashMap<Character, Integer> calcularFrecuencias(String texto) {
        HashMap<Character, Integer> frequency = new HashMap<>();

        Character c;
        for (int i = 0; i < texto.length(); i++) {
            c = texto.charAt(i);
            if (!frequency.containsKey(c)) {
                frequency.put(c, 1);
            } else {
                frequency.put(c, frequency.get(c) + 1);
            }
        }
        return frequency;
    }

}
