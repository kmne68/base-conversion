/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import java.util.ArrayList;

/**
 *
 * @author Keith
 */
public class Hex2Dec implements Conversion {
    
    public static final String VALUEDESCRIPTION = "Hexadecimal";
    public static final String RESULTDESCRIPTION = "Decimal";
    private String hexDigits = "0123456789ABCDEF";
    
    private String originalValue;
    private String result;
    private ArrayList<String> resultsteps;
    private String emsg;
    private boolean valid;
    
    
    public Hex2Dec(String value) {
        originalValue = value;
        emsg = "";
        this.resultsteps = null;
        
        if(valid = isValid()) {
            convert();
        } else {
            emsg = "Illegal value: digits 0 - 9 or A - F only";
        }
    }
    
    
    public ArrayList<String> getProcessLog() {
        
        return this.resultsteps;
    }
    
    
    public boolean isValid() {
        
        // check every position of the input string to see if it
        // mathces a hex digit
        
        for(int i = 0; i < this.originalValue.length(); i++) {
            // is the character in the hexDigits string?
            if(hexDigits.indexOf(this.originalValue.toUpperCase().substring(i, i + 1)) == -1) {
                // charater not found in hexDigits
                return false;
            }
        }
        return true;
    }
    
    
    @Override
    public String getErrorMessage() {
        return this.emsg;
    }
    
    
    private void convert() {
     
        String reverse = new StringBuilder(this.originalValue).reverse().toString().toUpperCase();
        long r = 0;
        this.resultsteps = new ArrayList<>();
        for (int i = 0; i < reverse.length(); i++) {
            int multiplier = this.hexDigits.indexOf(reverse.substring(i, i + 1));
            long p = (long) Math.pow(16, i) * multiplier;
            r += p;
            this.resultsteps.add("There is a(n) " + p + " in the number (" + multiplier + " * 16, " + i + ")");            
        }
        this.result = String.valueOf(r);        
    }
    
    
    @Override
    public String getResult() {
        return this.result;
    }
    
    
    @Override
    public String getValue() {
        return this.originalValue;
    }
        
}
