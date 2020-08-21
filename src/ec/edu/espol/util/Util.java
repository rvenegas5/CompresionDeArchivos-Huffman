/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.util;

/**
 *
 * @author Anii BC
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Anii BC
 */
public class Util {
 
    
    public static String LeerTexto(String nombre) throws IOException{
        String cadena=null;
        try {
            File archivo = new File (nombre);
            FileReader fr = new FileReader (archivo);
            BufferedReader br = new BufferedReader(fr);
            cadena= br.readLine();
            
          
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Util.class.getName()).log(Level.SEVERE, null, ex);
        }
         return cadena;
    }
    
    
    
    
    
        
      public static HashMap<String,Integer> getFrecuencies(String letras){
        HashMap<String,Integer> table = new HashMap<>();
        
        int i;
        for(i=0;i<letras.length();i=i+1){
            char letra = letras.charAt(i);
           
            if(!table.containsKey(String.valueOf(letra))){
                table.put(String.valueOf(letra), 1);
            }else{
                Integer content = table.get(String.valueOf(letra)) + 1;
                table.put(String.valueOf(letra), content);
            }
            
        }
        
        return table;
    }

      
      
      public static String HexToBinary(String Hex){
       int i = Integer.parseInt(Hex,16);
       String Bin = Integer.toBinaryString(i);
       return Bin;
      }
    
      
      
      
      
   
    
    
    
    
    
    
    
    
    
    
}

