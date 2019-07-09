/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JavaSearchOrderingExternal;

/**
 *
 * @author sala203
 */
public class SearchMethods {

    public String sequentialSearch(int[] paramArray, int paramData) {
        String posicion = "";
        for (int i = 0; i < paramArray.length; i++) {
            if (paramArray[i] == paramData) {
                posicion += i + ",";
            }
        }
        return posicion;
    }

}
