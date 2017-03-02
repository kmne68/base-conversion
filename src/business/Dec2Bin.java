
package business;

/**
 *
 * @author: 
 */
public class Dec2Bin extends Dec2Num {
    
    public static final String VALUEDESCRIPTION =  "Decimal";
    public static final String RESULTDESCRIPTION = "Binary";
    public static final int BASE = 2;
    
    private String binaryresult;
    
    public Dec2Bin(String value) {
        super(value, Dec2Bin.BASE);        

        this.binaryresult = "";
    }
    
    
    // formerly getBinaryValue()
    @Override
    public String getResult() {
        
        if(!super.isValid()) {
            return "Invalid value.";
        }
        
        for(Integer i : super.getRemainders()) {
            this.binaryresult += String.valueOf(i);
        }
        return this.binaryresult;
    }

}
