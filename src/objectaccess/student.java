/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objectaccess;

import java.io.Serializable;
/**
 *
 * @author dev15
 */
public class student implements Serializable {
    
    
    int roll;
    String name;
    double marks;
    
    student(){}

    public student(int roll, String name, double marks) {
        this.roll = roll;
        this.name = name;
        this.marks = marks;
    }

    @Override
    public String toString() {
        return "student{" + "roll=" + roll + ", name=" + name + ", marks=" + marks + '}';
    }
    
  //  long getSize(){
   //     return ObjectSizeFetcher.getObjectSize(this);
    //}
    
    
}

/*class ObjectSizeFetcher{
    private static Instrumentation instrumentation;
    
    public static void premain(String args,Instrumentation inst){
        instrumentation=inst;
    }
    
    public static long getObjectSize(student o){
        return instrumentation.getObjectSize(o);
    }
}
*/

