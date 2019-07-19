/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JavaSearchOrderingExternal;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 *
 * @author sala203
 */
public class SequentialSearch {

    public boolean sequentialSearch(String paramNombreArchivo, int paramData) throws IOException {
        try (DataInputStream dis = new DataInputStream(new FileInputStream(paramNombreArchivo))) {
            while (dis.available() != 0) {
               if(dis.readInt() == paramData) {
                   return true;
               }
            }
            return false;
        } catch (FileNotFoundException e) {
            System.out.println("Error de Apertura-Lectura archivo: " + paramNombreArchivo);
            return false;
        } catch (IOException e) {
            System.out.println("Error de lectura archivo: " + paramNombreArchivo);
            return false;
        } 
    }

}
