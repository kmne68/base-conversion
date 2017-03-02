/*
 * Keith Emery
 * Java 4, Program 5
 * Interfaces
 * March 3, 2017
 */
package business;

import java.util.ArrayList;

/**
 *
 * @author Keith
 */
public interface Conversion {
    
    public String getValue();
    public String getResult();
    public boolean isValid();
    public ArrayList<String> getProcessLog();
    public String getErrorMessage();
    
} // end interface Conversion
