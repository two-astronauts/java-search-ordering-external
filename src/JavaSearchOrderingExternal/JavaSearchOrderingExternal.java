/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JavaSearchOrderingExternal;

import static JavaSearchOrderingExternal.Data.CreateFile;
import static JavaSearchOrderingExternal.Data.ShowData;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
        int error = 0;
        String menu = "MENU\n1.Agregar número\n2.Mostrar\n3.Ordenar por mezcla Directa\n4.Ordenar por mezcla Natural"
            + "\n5.Verificar ordenamiento\n0.Salir\nDigite la opcion";
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
                        error = verificarOrdenamiento("DirectMerge.dat");
                        if(error == 1) {
                            System.out.println("El archivo esta ordenado");
                        }else if(error == 0) {
                            DirectMerge direct = new DirectMerge();
                            direct.directMerge(f);
                            System.out.println("Datos ordenados exitosamente");
                        }
                    case 4:
                        error = verificarOrdenamiento("DirectMerge.dat");
                        if(error == 1) {
                            System.out.println("El archivo esta ordenado");
                        }else if(error == 0) {
                            NaturalMerge natural = new NaturalMerge();
                            natural.naturalMerge("DirectMerge.dat");
                            System.out.println("Datos ordenados exitosamente");
                        }
                        break;
                    case 5:
                        error = verificarOrdenamiento("DirectMerge.dat");
                        if(error == 1) {
                            System.out.println("El archivo esta ordenado");
                        }else if(error == 0) {
                            System.out.println("El archivo no esta ordenado");
                        }
                        break;
                    case 0:
                        break;
                    default:
                        System.out.println("Opción invalida");
                        break;
                }
            }catch(Exception e) {
                break;
            }
        } while(option != 0);
    }
    
    //Metodo para verificar el correcto orden en el archivo
    public static int verificarOrdenamiento(String nombreArchivo) throws IOException {
        Integer actual = null;
        Integer anterior = null;
        //Variable booleana para indicar el estado del archivo
        boolean estaOrdenado = true;
        DataInputStream dis = null;
        try {
            dis = new DataInputStream(new FileInputStream(nombreArchivo));
            //Ciclo para verificar el orden del archivo
            //Comenzar siempre por averiguar si hay datos dentro del archivo
            while (dis.available() != 0) {
                //En un primer momento los indices quedan a la par
                anterior = actual;
                //actual se encargara de ir "jalando" a anterior
                actual = dis.readInt();
                //En la segunda vuelta, el indice anterior ocupa la posicion
                //del indice actual y a partir de aqui, el indice actual
                //se despega del anterior
                if (anterior == null) {
                    anterior = actual;
                }
                //Comparacion de los datos contenidos en actual y anterior
                //Condicion: Si el dato anterior es lexicograficamente mayor al actual
                if (anterior.compareTo(actual) > 0) {
                    System.out.println("Error en el ordenamiento");
                    //Actualizacion de la variable booleana que indica el estado del archivo
                    estaOrdenado = false;
                    //Interrupcion del ciclo
                    break;
                }
            }
            //Si la variable booleana conservo su valor original de true, desplegar un mensaje
            return estaOrdenado ? 1 : 0;
        } catch (FileNotFoundException e) {
            System.out.println("Error de Apertura-Lectura archivo: " + nombreArchivo);
            return -1;
        } catch (IOException e) {
            System.out.println("Error de lectura archivo: " + nombreArchivo);
            return -1;
        } finally {
            //Verificar siempre que el archivo este abierto antes de intentar cerrarlo
            if (dis != null) {
                dis.close();
            }
        }
    }
}
