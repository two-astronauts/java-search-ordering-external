/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JavaSearchOrderingExternal;

/**
 *
 * @author User
 */
public class HashMapSearch {
    
    public void hashMap(int[] numerosArray, int[] array) {
        int i;
        for(i = 0; i < numerosArray.length; i++) {
            int indice = numerosArray[i] % numerosArray.length;
            System.out.println("El indice " + indice +" para el valor " + numerosArray[i]);
            // Tratando las colisiones
            while(array[indice] != -1) {
                indice++;
                System.out.println("Ocurrió una colisión en el índice " + (indice-1) + " cambiar al índice " + indice);
                indice %= numerosArray.length;
            }
            array[indice] = numerosArray[i];
        }
    }
    
    public int search(int[] numeros, int numero) {
        int indice = numero % numeros.length;
        int contador = 0;
        while(numeros[indice] != -1) {
            if(numeros[indice] == numero) {
                System.out.println("El número " + numero + " fue encontrado en el índice " + indice);
                return numeros[indice];
            }
            indice++;
            indice %= numeros.length;
            contador++;
            if(contador > numeros.length) {
                break;
            }
        }
        return -1;
    }
    
    public void show(int[] numeros) {
        int incremento = 0, j, i;
        for(i = 0; i < 1; i++) {
            incremento += numeros.length;
            for(j = 0; j < numeros.length*7; j++){
                System.out.print("-");
            }
            System.out.println();
            for(j = incremento - numeros.length; j < incremento; j++) {
                System.out.format("| %3s " + " ", j);
            }
            System.out.println("|");
            for(int n = 0; n < numeros.length*7; n++) {
                System.out.print("-");
            }
            System.out.println();
            for(j = incremento - numeros.length; j < incremento; j++) {
                if(numeros[j] == -1) {
                    System.out.println("|      ");
                }else {
                    System.out.print(String.format("| %3s " + " ", numeros[j]));
                }
            }
            System.out.println("|");
            for(j = 0; j < numeros.length*7; j++) {
                System.out.print("-");
            }
            System.out.println();
        }
    }
    
    
}
