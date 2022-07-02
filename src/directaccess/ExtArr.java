/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package directaccess;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 *
 * @author dev15
 */
public class ExtArr {

    String file;
    long size;
    RandomAccessFile raf;

    public static ExtArr getNewExtArr(String fileName,long size) {

        File fileObj = new File(fileName);
        if (fileObj.exists()) {
            System.out.println("File already present");
            return null;
        }

        ExtArr exArr = new ExtArr();
        exArr.file = fileName;
        exArr.size = size;

        try {
            exArr.raf = new RandomAccessFile(exArr.file, "rw");
        } catch (FileNotFoundException ex) {
            System.out.println("System error");
            return null;
        }
        
        for (int i = 0; i < size; i++) {
            try {
                exArr.raf.writeInt(0);
            } catch (IOException ex) {
               System.out.println("System error");
            }
        }
        return exArr;
    }

    public static ExtArr getExtArr(String fileName) {

        ExtArr exArr = new ExtArr();
        try {
            exArr.raf = new RandomAccessFile(fileName, "rw");
        } catch (FileNotFoundException ex) {
            System.out.println("File Not Found "+ex);
            return null;
        }
        exArr.file = fileName;
        
        try {
            exArr.size = exArr.raf.length() / 4 ;
        } catch (IOException ex) {
            System.out.println("System error "+ex);
        }
        return exArr;
    }

    private ExtArr() {

    }
    
    private void findLoc(long pos) throws IOException {
        raf.seek(pos * 4);
    }

    public int get(long pos) throws IOException {
        findLoc(pos);
        return raf.readInt();
    }

    public void put(int value, long pos) throws IOException {
        findLoc(pos);
        raf.writeInt(value);
    }

}
