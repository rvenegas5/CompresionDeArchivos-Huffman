
package ec.edu.espol.modelo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
/**
 *
 * @author WILLIAM
 */
public class ReadFile {
    
    public static Map<Character, Integer> readTXTFile(String path){
        Map<Character, Integer> frequency = new HashMap<>();
        try {
            File file = new File (path);
            FileReader fr = new FileReader (file);
            BufferedReader br = new BufferedReader(fr);
            String linea;
            
            while((linea = br.readLine()) != null){
                Character c;
                for (int i = 0; i < linea.length(); i++){
                    c = linea.charAt(i);
                    if (!frequency.containsKey(c)){
                        frequency.put(c, 1);
                    }
                    else{
                        frequency.put(c, frequency.get(c) + 1);
                    }
                }
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return frequency;
    }
    
}
