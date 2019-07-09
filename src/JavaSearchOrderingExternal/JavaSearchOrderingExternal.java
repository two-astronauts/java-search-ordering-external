/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JavaSearchOrderingExternal;

import static JavaSearchOrderingExternal.DirectMerge.crearAtchivo;
import static JavaSearchOrderingExternal.DirectMerge.leer;
import static JavaSearchOrderingExternal.DirectMerge.mezclaDirecta;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
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
    public static void main(String[] args) throws IOException, Exception {
//        Scanner s = new Scanner(System.in);
//        File f = new File("ArchivoOrigen.dat");
//        int opcion = 0;
//        while (opcion != 4) {
//            System.out.println("menun"
//                    + "1.-agregarn"
//                    + "2.-leern"
//                    + "3.-ordenarn"
//                    + "4.-salir");
//            System.out.println("que opcion desea realizar");
//            opcion = s.nextInt();
//            switch (opcion) {
//                case 1:
//                    crearAtchivo(f);
//                    System.out.println("dato agregado");
//                    break;
//                case 2:
//                    leer(f);
//                    System.out.println("datos mostrados con exito");
//                    break;
//                case 3:
//                    mezclaDirecta(f);
//                    System.out.println("Datos ordenados exitosamente");
//                case 4:
//                    System.out.println("programa finalizado");
//                    break;
//                default:
//                    System.out.println("opcion invalida");
//                    break;
//            }
//
//        }

        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        String nombreArchivo = null;
        NaturalMerge mezcla1 = new NaturalMerge();
        //Solicita el nombre de un archivo para poder ordenarlo
        System.out.println("Nombre del archivo:");
        nombreArchivo = br.readLine();
        //Despliega el contenido del archivo sin ordenar
        mezcla1.desplegar(nombreArchivo);
        //Ordena el contenido del archivo
        mezcla1.ordenar(nombreArchivo);
        //Verifica que el archivo este ordenado correctamente
        mezcla1.verificarOrdenamiento(nombreArchivo);
    }
}
