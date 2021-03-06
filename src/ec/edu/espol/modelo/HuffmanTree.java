package ec.edu.espol.modelo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 *
 * @author WILLIAM
 */
public class HuffmanTree {

    Node root;

    private class Node {

        char ch;
        int freq;
        Node left = null, right = null;

        Node(char ch, int freq) {
            this.ch = ch;
            this.freq = freq;
        }

        public Node(char ch, int freq, Node left, Node right) {
            this.ch = ch;
            this.freq = freq;
            this.left = left;
            this.right = right;
        }

    }

    public static void encode(Node root, String str, Map<Character, String> huffmanCode) {
        if (root == null) {
            return;
        }

        // found a leaf node
        if (root.left == null && root.right == null) {
            huffmanCode.put(root.ch, str);
        }

        encode(root.left, str + '0', huffmanCode);
        encode(root.right, str + '1', huffmanCode);
    }

    public static int decode(Node root, int index, StringBuilder sb) {
        if (root == null) {
            return index;
        }

        // found a leaf node
        if (root.left == null && root.right == null) {
            System.out.print(root.ch);
            return index;
        }

        index++;

        if (sb.charAt(index) == '0') {
            index = decode(root.left, index, sb);
        } else {
            index = decode(root.right, index, sb);
        }

        return index;
    }

    public void calcularArbol(HashMap<String, Integer> mapa) {

        PriorityQueue<Node> pq;
        pq = new PriorityQueue<>(Comparator.comparingInt(l -> l.freq));

        for (var entry : mapa.entrySet()) {
            pq.add(new Node(entry.getKey().charAt(0), entry.getValue()));
        }

        while (pq.size() != 1) {
            Node left = pq.poll();
            Node right = pq.poll();

            int sum = left.freq + right.freq;
            pq.add(new Node('\0', sum, left, right));
        }

        root = pq.peek();
    }

    public HashMap<String, String> calcularCodigos() {
        HashMap<String, String> resultado = new HashMap<>();
        Map<Character, String> huffmanCode = new HashMap<>();
        encode(root, "", huffmanCode);
        for (Map.Entry<Character, String> entry : huffmanCode.entrySet()) {
            resultado.put(entry.getKey() + "", entry.getValue());
        }
        return resultado;
    }

    public static String codificar(String text, HashMap<String, String> huffmanCode) {
        String s = "";
        for (char c : text.toCharArray()) {
            s = s + huffmanCode.get(c + "");
        }
        return s;
    }

    public static String decodificar(String texto, HashMap<String, String> mapa) {
        String resultado = "";
        String aux = "";
        for (char c : texto.toCharArray()) {
            aux += c + "";
            for (Map.Entry<String, String> e : mapa.entrySet()) {
                String key = e.getKey();
                String value = e.getValue();
                if (value.equals(aux)) {
                    resultado += key;
                    aux = "";
                }
            }
        }
        return resultado;
    }

    public static HashMap<String, String> obtenerCodigos(String archivo) {
        HashMap<String, String> mapa = new HashMap<>();
        File file = new File(archivo);
        BufferedReader br = null;
        try {

            br = new BufferedReader(new FileReader(file));
            String linea;

            while ((linea = br.readLine()) != null) {
                String[] l = linea.split("\\|");

                String key = l[0];
                String value = l[1];
                mapa.put(key, value);
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
                if (file.delete()) {
                    System.out.println(file.getName() + " ha sido eliminado");
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
        return mapa;
    }

}
