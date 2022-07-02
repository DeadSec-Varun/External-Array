/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package directObjectAccess;

import java.io.*;

/**
 *
 * @author dev15
 */
public class DirectAccessObject {

    private String data_file;
    private String index_file;
    long size;
    RandomAccessFile raf_data;
    RandomAccessFile raf_index;
    ObjectOutput out;
    ObjectInput in;

    public static DirectAccessObject getNewExtObj(String fileName, long size) {

        File fileObj = new File(fileName);
        if (fileObj.exists()) {
            System.out.println("File already present");
            return null;
        }

        DirectAccessObject obj = new DirectAccessObject();
        obj.data_file = fileName;
        obj.index_file = fileName + "-index";
        obj.size = size;

        try {
            obj.raf_data = new RandomAccessFile(obj.data_file, "rw");
            obj.raf_index = new RandomAccessFile(obj.index_file, "rw");
        } catch (FileNotFoundException ex) {
            System.out.println("System error " + ex);
            return null;
        }
        try {
            obj.raf_index.writeLong(0L);
        } catch (IOException ex) {
            System.out.println("System error " + ex);
        }
        return obj;
    }

    public static DirectAccessObject getExtObj(String fileName) {

        DirectAccessObject obj = new DirectAccessObject();
        try {
            obj.raf_data = new RandomAccessFile(fileName, "rw");
            obj.raf_index = new RandomAccessFile(fileName + "-index", "rw");
        } catch (FileNotFoundException ex) {
            System.out.println("File Not Found " + ex);
            return null;
        }
        obj.data_file = fileName;
        obj.index_file = fileName + "-index";

        try {
            obj.size = obj.raf_index.length() / 8;
        } catch (IOException ex) {
            System.out.println("System error " + ex);
        }
        return obj;
    }

    private DirectAccessObject() {

    }

    private long findLoc(long pos) throws IOException {
        raf_index.seek((pos) * 8);
        return raf_index.readLong();
    }

    private void findEOF() throws IOException {
        raf_data.seek(raf_data.length());
    }

    private long findSize(long pos) throws IOException {
        return findLoc(pos) - findLoc(pos - 1);
    }

    public Object get(long pos) throws Exception {
        int start = (int) findLoc(pos - 1);
        int len = (int) findSize(pos);
        //if(start !=0) start --;

        byte[] b = new byte[len];
        raf_data.seek(start);
        raf_data.read(b);
        //raf_data.read(b, start, len);

        in = new ObjectInputStream(new ByteArrayInputStream(b));
        return in.readObject();
    }

    public void put(Object value, int idx) throws IOException {
        
    }
    
    public void append(Object value) throws IOException {
        findEOF();

        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        out = new ObjectOutputStream(bout);
        out.writeObject(value);
        raf_data.write(bout.toByteArray());

        raf_index.writeLong(raf_data.length());
    }

}
