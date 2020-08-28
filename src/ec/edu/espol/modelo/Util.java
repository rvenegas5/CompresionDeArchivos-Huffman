package ec.edu.espol.modelo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

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

    public HashMap<String, Integer> calcularFrecuencias(String texto) {
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

    public String HexToBinary(String Hex) {
        int i = Integer.parseInt(Hex, 16);
        String Bin = Integer.toBinaryString(i);
        return Bin;
    }

    public String binarioHexadecimal(String binario) {
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

    public void guardarTexto(String nombreArchivo, String texto, HashMap<String, String> mapa) {
        File file = new File(nombreArchivo);
        File file_compress = new File(nombreArchivo + "_compress.txt");
        BufferedWriter bw;
        PrintWriter escribir;
        PrintWriter escribirCompress;
        BufferedWriter bwCompress;
        try {
            if (file.exists()) {
                file.delete();
                file = new File(nombreArchivo);
            }
            bw = new BufferedWriter(new FileWriter(file, true));
            escribir = new PrintWriter(bw);
            bwCompress = new BufferedWriter(new FileWriter(file_compress, true));
            escribirCompress = new PrintWriter(bwCompress);

            // escribimos el nuevo texto en el archivo
            escribir.println(texto);
            escribir.flush();
            escribir.close();
            bw.close();

            Iterator<HashMap.Entry<String, String>> itr = mapa.entrySet().iterator();

            while (itr.hasNext()) {
                HashMap.Entry<String, String> entry = itr.next();
                escribirCompress.println(entry.getKey() + "|" + entry.getValue());
            }
            escribirCompress.flush();
            escribirCompress.close();
            bwCompress.close();
        } catch (IOException ex) {
            Logger.getLogger(Util.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public HashMap<String, String> leerMapa(String nombreArchivo) {
        HashMap<String, String> codigos = new HashMap<>();

        try {
            File file = new File(nombreArchivo);
            FileReader fr;
            fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] l = linea.split("\\|");
                codigos.put(l[0], l[1]);
            }
        } catch (IOException ex) {
            Logger.getLogger(Util.class.getName()).log(Level.SEVERE, null, ex);
        }
        return codigos;
    }

}
