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
public class Rafbyte {

    String file;
    RandomAccessFile raf;
    ObjectOutput out;
    ObjectInput in;

    public static Rafbyte getRafbyte(String fileName) {

        Rafbyte exArr = new Rafbyte();
        exArr.file = fileName;

        try {
            exArr.raf = new RandomAccessFile(exArr.file, "rw");
        } catch (FileNotFoundException ex) {
            System.out.println("System error " + ex);
            return null;
        }
        return exArr;
    }

    private Rafbyte() {

    }

    private void findEOF() throws IOException {
        raf.seek(raf.length());
    }

    public void get() throws Exception {
        //raf.seek(i*new student().getSize());
        byte[] b = new byte[(int) raf.length()];
        raf.read(b);
        in = new ObjectInputStream(new ByteArrayInputStream(b));
        try {
            while (true) {
                System.out.println((student) in.readObject());
            }
        } catch (IOException e) {
            System.out.println("EOF");
            return;
        }
    }

    public void put(student value) throws IOException {
        findEOF();
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        out = new ObjectOutputStream(bout);
        out.writeObject(value);
        raf.write(bout.toByteArray());
    }
}
