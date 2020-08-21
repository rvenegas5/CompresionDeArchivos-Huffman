/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huffmantree;

/**
 *
 * @author Anii BC
 */
public class HuffmanContent {
    
    String text;
    int frecuency;
    int bit;
    
    public HuffmanContent(String text, int frecuency, int bit) {
        this.text = text;
        this.frecuency = frecuency;
        this.bit = bit;
    }
    
    public String getText() {
        return text;
    }
    
    public void setText(String text) {
        this.text = text;
    }

    public int getFrecuency() {
        return frecuency;
    }

    public void setFrecuency(int frecuency) {
        this.frecuency = frecuency;
    }

    public int getBit() {
        return bit;
    }

    public void setBit(int bit) {
        this.bit = bit;
    }
    
    
    
    
    
    
}

