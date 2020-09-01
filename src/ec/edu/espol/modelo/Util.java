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

        try {
            File file = new File(nombreArchivo);
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String linea;
            while ((linea = br.readLine()) != null) {
                line += (String)linea;
            }
            br.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        System.out.println(line);
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

        //left pad the string result with 0s if converting to BigInteger removes them.
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
        BufferedWriter bw;
        PrintWriter escribir;
        PrintWriter escribirCompress;
        BufferedWriter bwCompress;
        try {
            if (file.exists()) {
                file.delete();
                file = new File(nombreArchivo);
            }
            if (file_compress.exists()){
                file_compress.delete();
                file_compress = new File(nombreArchivo + "_compress.txt");
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

//    public static void main(String[] args) {
//        /*Util u = new Util();
//        HuffmanTree h = new HuffmanTree();
//        h.calcularArbol(u.calcularFrecuencias(u.leerTexto("./prueba.txt")));
//        HashMap<String, String> m = h.calcularCodigos();
//        String cod = HuffmanTree.codificar(u.leerTexto("./prueba.txt"), m);
//        String codHex = u.binarioHexadecimal(cod);
//        System.out.println(codHex);
//        Util u1 = new Util();
//        u.guardarTexto("./prueba.txt", codHex, m);*/
//
//        //String s = HuffmanTree.codificar("AAAAAABBBBBBBBCCCCCDDDDDDDEEEEEEFFFFFFFFGGGGGGGGGGG", m);
//        //System.out.println(s.length());
//        //String e = "0010010010010010011101101101101101101101100000000000000001011011011011011011011001001001001001001111111111111111111111110101010101010101010101";
//        //System.out.println(e.length());
//        //u.guardarTexto("./prueba.txt", u.binarioHexadecimal(e), m);
//        // String s =  decodificar(u.leerTexto("./prueba.txt"), m);
//        //System.out.println(decodificar(u.HexToBinary(u.leerTexto("./prueba.txt")), m));
//        
//        Util u = new Util();
//        String s = HuffmanTree.decodificar(u.hexadecimalBinario(u.leerTexto("./prueba.txt")), HuffmanTree.obtenerCodigos("./prueba.txt_compress.txt"));
//        System.out.println(s);
//        u.guardarTexto("./prueba.txt", s, HuffmanTree.obtenerCodigos("./prueba.txt_compress.txt"));
//    }
}
