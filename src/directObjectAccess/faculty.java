/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package directObjectAccess;

import java.io.Serializable;

/**
 *
 * @author dev15
 */
public class faculty implements Serializable {
    String name;
    String course;

    public faculty(String name, String course) {
        this.name = name;
        this.course = course;
    }

    @Override
    public String toString() {
        return "faculty{" + "name=" + name + ", course=" + course + '}';
    }
    
    
    
}
