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
public abstract class Dec2Num implements Conversion {

    private String origval, emsg;
    private boolean valid;
    private int base;
    private ArrayList<String> resultsteps;
    private ArrayList<Integer> remainders;

    public Dec2Num(String value, int base) {

        this.emsg = "";
        this.origval = value;
        this.base = base;

        try {
            long n = Long.parseLong(value);
            if (n < 0) {
                emsg = "Bad decimal value: must be positive.";
                this.valid = false;
            } else {
                this.valid = true;
                resultsteps = new ArrayList<>();
                remainders = new ArrayList<>();
                convertByRecur(n);
            }
        } catch (NumberFormatException e) {
            emsg = "Illegal value: not a decimal integer";
            this.valid = false;
        }
    }

    @Override
    public boolean isValid() {

        return this.valid;
    }

    private void convertByRecur(long n) {
        int r = 0;

        r = (int) (n % this.base);
        long newval = n / this.base;
        resultsteps.add(n + " divided by " + this.base + " = "  + newval + " w/remainder of: " + r);

        if (newval > 0) {
            convertByRecur(newval);     // recursive call
        }
        remainders.add(r);
    }
    
    
    @Override
    public ArrayList<String> getProcessLog() {
        return this.resultsteps;
    }
    
    @Override
    public String getValue() {
        return this.origval;
    }
    
    
    @Override
    public String getErrorMessage() {
        return this.emsg;
    }
    
    
    @Override
    public abstract String getResult();
    
    
    // need to get remainders array
    protected ArrayList<Integer> getRemainders() {
        
        return this.remainders;
    }

}
