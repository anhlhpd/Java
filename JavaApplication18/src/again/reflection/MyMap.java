/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package again.reflection;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author hoang
 */
public class MyMap<DatLaGi, DatLaTheNay> {

    private HashMap<DatLaGi, DatLaTheNay> map = new HashMap<>();

    public void add(DatLaGi key, DatLaTheNay value) {
        map.put(key, value);
    }

    public static void main(String[] args) {
        MyMap<Integer, Student> map = new MyMap<>();
        
    }
}
