/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JavaSearchOrderingExternal;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sala203
 */
public class JavaSearchOrderingExternal {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        File file = new File("P:\\two-astronauts\\java-search-ordering-external\\example");
        int[] numbers = null;
        
        try {
            numbers = file.read();
            
            for (int i = 0; i < numbers.length; i++) {
                System.out.println(numbers[i]);
            }
            
        } catch (IOException ex) {
            Logger.getLogger(JavaSearchOrderingExternal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
