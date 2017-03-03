/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

/**
 *
 * @author Keith
 */
public class Dec2Hex extends Dec2Num {
    
    public static final String VALUEDESCRIPTION = "Decimal:";
    public static final String RESULTDESCRIPTION = "Hexadecimal:";
    public static final int BASE = 16;
    
    private String hexresult;
    
    public Dec2Hex(String value) {
        super(value, Dec2Hex.BASE);
        
        this.hexresult = "";
    }
    
    
    public String getResult() {
        
        if(!super.isValid()) {
            return "Invalid value.";
        }
        
        for(Integer i : super.getRemainders()) {
            
            if(i <= 9) {
                this.hexresult += String.valueOf(i);
            } else if (i == 10) {
                this.hexresult += "A";
            } else if (i == 11) {
                this.hexresult += "B";
            } else if (i == 12) {
                this.hexresult += "C";
            } else if (i == 13) {
                this.hexresult += "D";
            } else if (i == 14) {
                this.hexresult += "E";
            } else {
                this.hexresult += "F";
            }
        }
        return this.hexresult;
    }
    
}
