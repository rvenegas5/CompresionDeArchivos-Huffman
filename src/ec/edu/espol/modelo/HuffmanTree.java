/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.modelo;

/**
 *
 * @author WILLIAM
 */
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

    public void buildHuffmanTree(HashMap<String, Integer> mapa) {
        // count frequency of appearance of each character
        // and store it in a map

        // Create a priority queue to store live nodes of Huffman tree
        // Notice that highest priority item has lowest frequency
        PriorityQueue<Node> pq;
        pq = new PriorityQueue<>(Comparator.comparingInt(l -> l.freq));

        // Create a leaf node for each character and add it
        // to the priority queue.
        for (var entry : mapa.entrySet()) {
            pq.add(new Node(entry.getKey().charAt(0), entry.getValue()));
        }

        // do till there is more than one node in the queue
        while (pq.size() != 1) {
            // Remove the two nodes of highest priority
            // (lowest frequency) from the queue
            Node left = pq.poll();
            Node right = pq.poll();

            // Create a new internal node with these two nodes as children
            // and with frequency equal to the sum of the two nodes
            // frequencies. Add the new node to the priority queue.
            int sum = left.freq + right.freq;
            pq.add(new Node('\0', sum, left, right));
        }

        // root stores pointer to root of Huffman Tree
        root = pq.peek();

        // traverse the Huffman tree and store the Huffman codes in a map
        // Map<Character, String> huffmanCode = new HashMap<>();
        // encode(root, "", huffmanCode);
        // print the Huffman codes
        // System.out.println("Huffman Codes are : " + huffmanCode);
        // System.out.println("Original string was : " + text);
        // print encoded string
//        StringBuilder sb = new StringBuilder();
//        for (char c : text.toCharArray()) {
//            sb.append(huffmanCode.get(c));
//        }
//        System.out.println("Encoded string is : " + sb);
        // traverse the Huffman Tree again and this time
        // decode the encoded string
//        int index = -1;
//        System.out.print("Decoded string is: ");
//        while (index < sb.length() - 2) {
//            index = decode(root, index, sb);
//        }
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

    // test
//    public static void main(String[] args) {
//        HashMap<String, Integer> mapa = new HashMap<>();
//        mapa.put("a", 5);
//        mapa.put("b", 9);
//        mapa.put("c", 12);
//        mapa.put("d", 13);
//        mapa.put("e", 16);
//        mapa.put("f", 45);
//        Util u = new Util();
//        HuffmanTree h = new HuffmanTree();
//        h.buildHuffmanTree(u.calcularFrecuencias(u.leerTexto("./prueba.txt")));
//        HashMap<String, String> m = h.calcularCodigos();
//        System.out.println(m);
//        u.guardarTexto("./prueba.txt", u.binarioHexadecimal(HuffmanTree.codificar(u.leerTexto("./prueba.txt"), m)), h.calcularCodigos());
//        
//    }
}
