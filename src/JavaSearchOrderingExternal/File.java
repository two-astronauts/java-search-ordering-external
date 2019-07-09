/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JavaSearchOrderingExternal;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author sala203
 */
public class File {
    
    public String route;
    
    public File(String paramRoute) {
        this.route = paramRoute;
    }
    
    public int[] read() throws FileNotFoundException, IOException {
        String data = "";
        String[] array = null;
        int[] arrayConvert = null;
        FileReader fileReader = new FileReader(this.route);
        try (BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            String row;
            while ((row = bufferedReader.readLine()) != null) {
                data = data.concat(row + "_");
            }
        }
        if (!"".equals(data)) {
            data = data.substring(0, data.length() - 1);
            array = data.split("_");
            arrayConvert = new int[array.length];
            for (int i = 0; i < array.length; i++) {
                arrayConvert[i] = Integer.parseInt(array[i]);
            }
        }
        return arrayConvert;
    }
}
