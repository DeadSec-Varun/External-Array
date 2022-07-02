package ObjectIndex;

import java.io.*;

public class ObjIndx {
    //private String data_file;
    //private String index_file;
    private long size;
    private RandomAccessFile raf_data;
    private RandomAccessFile raf_index;
    private RandomAccessFile configure;
    private long lastIndex; 
    
    public static ObjIndx getNewExtObj(String fileName, long size) {

        File fileObj = new File(fileName);
        if (fileObj.exists()) {
            System.out.println("File already present");
            return null;
        }

        ObjIndx obj = new ObjIndx();
        obj.size = size;

        try {
            obj.raf_data = new RandomAccessFile(fileName+".data", "rw");
            obj.raf_index = new RandomAccessFile(fileName+".idx", "rw");
            obj.configure = new RandomAccessFile(fileName+"config","rw");
        } catch (FileNotFoundException ex) {
            System.out.println("System error " + ex);
            return null;
        }
        try {
            obj.lastIndex=-1L;
            obj.configure.seek(0);
            obj.configure.writeLong(obj.lastIndex);
            for(long i=0;i<size;i++){
                obj.raf_index.writeLong(-1L);
                obj.raf_index.writeLong(-1L);
            }
        } catch (IOException ex) {
            System.out.println("System error " + ex);
        }
        return obj;
    }

    public static ObjIndx getExtObj(String fileName) {

        ObjIndx obj = new ObjIndx();
        try {
            obj.raf_data = new RandomAccessFile(fileName+".data", "rw");
            obj.raf_index = new RandomAccessFile(fileName + ".idx", "rw");
            obj.configure = new RandomAccessFile(fileName+"config","rw");
        } catch (FileNotFoundException ex) {
            System.out.println("File Not Found " + ex);
            return null;
        }
        try {
            obj.size = obj.raf_index.length() / 16;
            obj.lastIndex=obj.configure.readLong();
        } catch (IOException ex) {
            System.out.println("System error " + ex);
        }
        return obj;
    }

    private ObjIndx() {

    }
    
    
//    private long findLoc(long pos) throws IOException {
//        raf_index.seek(pos * 16);
//        return raf_index.readLong();
//    }

    private void findEOF() throws IOException {
        raf_data.seek(raf_data.length());
    }
//
//    private long findSize(long pos) throws IOException {
//        raf_index.seek(pos * 16 + 8);
//        return raf_index.readLong();
//    }
    
    private byte[] getByteArray(Serializable value)throws IOException{
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        ObjectOutput out = new ObjectOutputStream(bout);
        out.writeObject(value);
        return bout.toByteArray();
    }
    
    private Object getObject(byte[] data) throws IOException, ClassNotFoundException {
        ObjectInput in = new ObjectInputStream(new ByteArrayInputStream(data));
        return in.readObject();
    }
    
    //private readStream(Object value)throws IOException{
        
    //}

    public Object get(long indx) throws Exception {
        if(indx >= size) {
            throw new IndexOutOfBoundsException();
        }
        
        raf_index.seek(indx*16);
        long start;
        if((start=raf_index.readLong())==-1){
            return null;
        }
        int len = (int) raf_index.readLong();
        byte[] b = new byte[len];
        raf_data.seek(start);
        raf_data.read(b);
        //raf_data.read(b, start, len);

        return getObject(b);
    }

    public void put(Serializable value, int idx) throws IOException {
        if(idx>=size){
            throw new ArrayIndexOutOfBoundsException();
        }
        
        findEOF();
        
        if(lastIndex<idx){
            lastIndex=idx;
        }
        long startLength=raf_data.length();
        raf_data.write(getByteArray(value));
        long endLength=raf_data.length();

        raf_index.seek(idx*16);
        raf_index.writeLong(startLength);
        raf_index.writeLong(endLength-startLength);
        //System.out.println(lastIndex);
    }
    
    public void append(Serializable value) throws IOException {
        findEOF();           
        long startLength=raf_data.length();
        raf_data.write(getByteArray(value));
        long endLength=raf_data.length();

        raf_index.seek(lastIndex*16);
        raf_index.writeLong(startLength);
        raf_index.writeLong(endLength-startLength);
        if(++lastIndex==size){
            size++;
        }
        //System.out.println(lastIndex);
    }
    
    public void closeExArr() throws IOException {
        raf_data.close();
        raf_index.close();
        configure.seek(0);
        configure.writeLong(lastIndex);
        configure.close();
    }
    
    public long getSize() {
        return this.size;
    }
    
    public long getLastIndex() {
        return this.lastIndex;
    }

}