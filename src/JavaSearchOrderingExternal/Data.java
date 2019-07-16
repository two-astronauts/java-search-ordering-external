/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JavaSearchOrderingExternal;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.swing.JOptionPane;

/**
 *
 * @author julianhenao
 */
public class Data {

    static void ShowData(File f) throws FileNotFoundException, IOException {
        int clave, k;
        boolean mas = true;
        DataInputStream flujo = null;
        try {
            flujo = new DataInputStream(new BufferedInputStream(new FileInputStream(f)));
            k = 0;
            while (mas) {
                k++;
                System.out.print(flujo.readInt() + " ");
                if (k % 11 == 0) {
                    System.out.println();
                }
            }
        } catch (EOFException eof) {
            System.out.println("***Fin del archivo***");
            try {
                flujo.close();
            } catch (IOException er) {
                er.printStackTrace();
            }
        }
    }
    
    public static void CreateFile(File Ffichero) throws IOException {
        // crear un objeto de tipo archivo
        DataOutputStream archivo = null;
        // creando e inicializando los campos del registro 
        // observar que se debe usar clases numericas apropiadas 
        int clave = 0;
        String nombre = new String("");
        int edad = 0;
        // abriendo archivo, capturando y grabando datos 
        try {
            /* Creando y grabando a un archivo, esta larga la instrucción*/ 
            archivo = new DataOutputStream(new FileOutputStream(Ffichero, true));
            clave = Integer.parseInt(JOptionPane.showInputDialog("Digita un número:"));
            //grabando al archivo 
            archivo.writeInt(clave);
            archivo.close();
        } catch (FileNotFoundException e) {
            /* Archivo no encontrado */
        }
        /* Error al escribir */
    }
}
