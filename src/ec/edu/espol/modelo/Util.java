package ec.edu.espol.modelo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
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
        String line = "";
        File file = new File(nombreArchivo);
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
            String linea;
            while ((linea = br.readLine()) != null) {
                line += (String) linea;
            }
            br.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                if (null != br) {
                    br.close();
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }

        return line;
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

    public String hexadecimalBinario(String hex) {
        String valorHex = "";
        int guion = 0;
        for (char c : hex.toCharArray()) {
            if (c != '-') {
                valorHex += c + "";
            } else {
                guion++;
            }
        }
        int len = valorHex.length() * 4;
        String bin = new BigInteger(valorHex, 16).toString(2);

        if (bin.length() < len) {
            int diff = len - bin.length();
            String pad = "";
            for (int i = 0; i < diff; ++i) {
                pad = pad.concat("0");
            }
            bin = pad.concat(bin);
        }
        return bin.substring(0, bin.length() - guion);

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
        BufferedWriter bw = null;
        BufferedWriter bwCompress = null;
        PrintWriter escribir = null;
        PrintWriter escribirCompress = null;

        try {
            if (file.exists()) {
                file.delete();
                file = new File(nombreArchivo);
            }
            if (file_compress.exists()) {
                file_compress.delete();
                file_compress = new File(nombreArchivo + "_compress.txt");
            }
            bw = new BufferedWriter(new FileWriter(file, true));
            escribir = new PrintWriter(bw);
            bwCompress = new BufferedWriter(new FileWriter(file_compress, true));
            escribirCompress = new PrintWriter(bwCompress);

            escribir.println(texto);
            escribir.flush();

            Iterator<HashMap.Entry<String, String>> itr = mapa.entrySet().iterator();

            while (itr.hasNext()) {
                HashMap.Entry<String, String> entry = itr.next();
                escribirCompress.println(entry.getKey() + "|" + entry.getValue());
            }
            escribirCompress.flush();

        } catch (IOException ex) {
            Logger.getLogger(Util.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (null != bw) {
                    bw.close();
                }
                if (null != bwCompress) {
                    bwCompress.close();
                }
                if (null != escribir)
                    escribir.close();
                if (null != escribirCompress)
                    escribirCompress.close();
            } catch (IOException e2) {
                System.out.println(e2.getMessage());
            }

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
