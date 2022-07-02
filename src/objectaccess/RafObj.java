/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objectaccess;

import java.io.*;

/**
 *
 * @author dev15
 */
public class RafObj {
    
    
    String file;
    RandomAccessFile raf;
    ObjectOutput out;
    ObjectInput in;
    
    
    

    public static RafObj getRafObj(String fileName) {


        RafObj exArr = new RafObj();
        exArr.file = fileName;

        try {
            exArr.raf = new RandomAccessFile(exArr.file, "rw");
        } catch (FileNotFoundException ex) {
            System.out.println("System error "+ex);
            return null;
        }
        return exArr;
    }

   

    private RafObj() {

    }
    
    FileDescriptor getFD() throws IOException{
        return raf.getFD();
    }
    
    private void findLoc() throws IOException {
        raf.seek(raf.length());
    }

    public student get() throws Exception {
        //raf.seek(i*new student().getSize());
        in=new ObjectInputStream(new FileInputStream(this.getFD()));
        return (student) in.readObject();
    }

    public void put(student value) throws IOException {
        findLoc();
        out=new ObjectOutputStream(new FileOutputStream(this.getFD()));
        out.writeObject(value);
    }
    
}
