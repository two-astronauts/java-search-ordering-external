/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JavaSearchOrderingExternal;

import static JavaSearchOrderingExternal.Data.CreateFile;
import static JavaSearchOrderingExternal.Data.ShowData;
import static JavaSearchOrderingExternal.DirectMerge.mezclaDirecta;
import java.io.File;
import java.io.IOException;
import javax.swing.JOptionPane;

/**
 *
 * @author sala203
 */
public class JavaSearchOrderingExternal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, Exception {
        File f = new File("DirectMerge.dat");
        int option = 0;
        String menu = "MENU\n1.Agregar número\n2.Mostrar\n3.Ordenar por mezcla Directa\n4.Ordenar por mezcla Natural"
            + "\n0.Salir\nDigite la opcion";
        do{
            try{
                option = Integer.parseInt(JOptionPane.showInputDialog(menu));
                switch(option) {
                    case 1:
                        CreateFile(f);
                        System.out.println("Dato agregado correctamente");
                        break;
                    case 2:
                        ShowData(f);
                        break;
                    case 3:
                        mezclaDirecta(f);
                        System.out.println("Datos ordenados exitosamente");
                    case 4:
                        System.out.println("...");
                        break;
                    case 0:
                        break;
                    default:
                        System.out.println("opcion invalida");
                        break;
                }
            }catch(Exception e) {
                break;
            }
        } while(option != 0);

//        InputStreamReader isr = new InputStreamReader(System.in);
//        BufferedReader br = new BufferedReader(isr);
//        String nombreArchivo = null;
//        NaturalMerge mezcla1 = new NaturalMerge();
//        //Solicita el nombre de un archivo para poder ordenarlo
//        System.out.println("Nombre del archivo:");
//        nombreArchivo = br.readLine();
//        //Despliega el contenido del archivo sin ordenar
//        mezcla1.desplegar(nombreArchivo);
//        //Ordena el contenido del archivo
//        mezcla1.ordenar(nombreArchivo);
//        //Verifica que el archivo este ordenado correctamente
//        mezcla1.verificarOrdenamiento(nombreArchivo);
    }
}
