/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JavaSearchOrderingExternal;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 *
 * @author julianhenao
 */
public class NaturalMerge {
    
    public void naturalMerge(String nombreArchivo) {
        int index = 0;
        while (particion(nombreArchivo, "DirectAux1.dat", "DirectAux2.dat")) {
            //Imprime el numero de particiones-fusiones que le llevo a los
            //metodos de particion y fusion el ordenar el archivo
            System.out.println("Fusion " + ++index);
            fusion(nombreArchivo, "DirectAux1.dat", "DirectAux2.dat");
        }
    }

    //Metodo para generar particiones de secuencias
    public boolean particion(String nombreArchivo, String archivo1, String archivo2) {
        //Se utilizara una logica similar a la del metodo de verificar orden
        //por lo que los indices son declarados de la misma manera
        Integer actual = null;
        Integer anterior = null;
        //Variable para controlar el indice del archivo al cual se va a escribir.
        //El archivo en cuestion es declarado dentro de un arreglo de archivos
        int indexOutputStream = 0;
        //Variable que determina si existe un cambio de secuencia en el ordenamiento
        boolean hayCambioDeSecuencia = false;
        //Declaracion de los objetos asociados a los archivos y del arreglo de archivos
        //que sirven para las particiones
        DataOutputStream dos[] = new DataOutputStream[2];
        DataInputStream dis = null;
        try {
            //Abre o crea los archivos
            dos[0] = new DataOutputStream(new FileOutputStream(archivo1, false));
            dos[1] = new DataOutputStream(new FileOutputStream(archivo2, false));
            dis = new DataInputStream(new FileInputStream(nombreArchivo));
            //Primero, verifica si existen datos en el archivo que se va a leer
            while (dis.available() != 0) {
                //Utiliza la misma logica para las variables que almacenan los datos
                //que en el metodo de la verificacion del orden
                anterior = actual;
                actual = dis.readInt();
                if (anterior == null) {
                    anterior = actual;
                }
                //Cambio de secuencia. Manipulacion del indice del arreglo de archivos
                if (anterior.compareTo(actual) > 0) {
                    indexOutputStream = indexOutputStream == 0 ? 1 : 0;
                    //Actualizacion de la variable booleana, esto indica la existencia
                    //de un cambio de secuencia
                    hayCambioDeSecuencia = true;
                }
                //Imprimir el dato contenido en actual y escribirlo en el archivo correspondiente
                //System.out.println(indexOutputStream + ") "+ actual);
                dos[indexOutputStream].writeInt(actual);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error lectura/escritura");
        } catch (IOException e) {
            System.out.println("Error en la creacion o apertura del archivo 1");
        } finally {
            //Verificar para cada archivo, que efectivamente se encuentre abierto
            //antes de cerrarlo
            try {
                if (dis != null) {
                    dis.close();
                }
                if (dos[0] != null) {
                    dos[0].close();
                }
                if (dos[1] != null) {
                    dos[1].close();
                }
            } catch (IOException ex) {
                System.out.println("Error al cerrar archivos");
            }
        }
        //El valor retornado sirve para determinar cuando existe una particion
        return hayCambioDeSecuencia;
    }

    //Metodo de fusion de los datos obtenidos en el metodo de particion
    public void fusion(String nombreArchivo, String archivo1, String archivo2) {
        //Variables para almacenar los datos de los archivos
        //que contienen las particiones
        Integer[] actual = new Integer[2];
        Integer[] anterior = new Integer[2];
        boolean[] finArchivo = new boolean[2];
        int indexArchivo = 0;
        //Creacion de los objetos asociacos a los archivos
        DataOutputStream dos = null;
        DataInputStream dis[] = new DataInputStream[2];
        try {
            //Abre o crea los archivos
            dis[0] = new DataInputStream(new FileInputStream(archivo1));
            dis[1] = new DataInputStream(new FileInputStream(archivo2));
            dos = new DataOutputStream(new FileOutputStream(nombreArchivo, false));

            //Condicion principal: debe haber datos en ambos archivos de lectura
            //Es importante notar que al inicio siempre hay al menos un dato en
            //cada archivo, de otra forma el metodo de particion hubiera
            //generado una sola secuencia y no entrariamos a la fusion.
            while (dis[0].available() != 0 && dis[1].available() != 0) {
                // 1era vez: inicializar con la primera palabra de cada archivo
                if (anterior[0] == null && anterior[1] == null) {
                    anterior[0] = actual[0] = dis[0].readInt();
                    anterior[1] = actual[1] = dis[1].readInt();
                }
                // al inicio del procesamiento de dos secuencias, anterior y
                // actual apuntan a la primer palabra de cada secuencia.
                anterior[0] = actual[0];
                anterior[1] = actual[1];
                // mezclamos las dos secuencias hasta que una acaba
                while (anterior[0].compareTo(actual[0]) <= 0
                        && anterior[1].compareTo(actual[1]) <= 0) {
                    indexArchivo = (actual[0].compareTo(actual[1]) <= 0) ? 0 : 1;
                    dos.writeInt(actual[indexArchivo]);
                    anterior[indexArchivo] = actual[indexArchivo];
                    // salir del while cuando no haya datos, pero ya procesamos
                    // el ultimo nombre del archivo
                    if (dis[indexArchivo].available() != 0) {
                        actual[indexArchivo] = dis[indexArchivo].readInt();
                    } else {
                        finArchivo[indexArchivo] = true;
                        break;
                    }
                }
                // en este punto indexArchivo nos dice que archivo causo
                // que salieramos del while anterior, por lo que tenemos
                // que purgar el otro archivo
                indexArchivo = indexArchivo == 0 ? 1 : 0;
                while (anterior[indexArchivo].compareTo(actual[indexArchivo]) <= 0) {
                    dos.writeInt(actual[indexArchivo]);
                    anterior[indexArchivo] = actual[indexArchivo];
                    if (dis[indexArchivo].available() != 0) {
                        actual[indexArchivo] = dis[indexArchivo].readInt();
                    } else {
                        finArchivo[indexArchivo] = true;
                        break;
                    }
                }
            }
            // purgar los dos archivos en caso de que alguna secuencia
            // haya quedado sola al final del archivo.
            // Para salir del while anterior alguno de los 2 archivos
            // debio terminar, por lo que a lo mas uno de los dos whiles
            // siguientes se ejecutara
            if (!finArchivo[0]) {
                dos.writeInt(actual[0]);
                while (dis[0].available() != 0) {
                    dos.writeInt(dis[0].readInt());
                }
            }
            if (!finArchivo[1]) {
                dos.writeInt(actual[1]);
                while (dis[1].available() != 0) {
                    dos.writeInt(dis[1].readInt());
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println(e);
        } catch (IOException e) {
            System.err.println(e);
        } finally {
            //Verificar para cada archivo, que efectivamente se encuentre abierto
            //antes de cerrarlo
            try {
                if (dis[0] != null) {
                    dis[0].close();
                }

                if (dis[1] != null) {
                    dis[1].close();
                }

                if (dos != null) {
                    dos.close();
                }
            } catch (IOException ex) {
                System.out.println("Error al cerrar archivos");
            }
        }
    }
}
