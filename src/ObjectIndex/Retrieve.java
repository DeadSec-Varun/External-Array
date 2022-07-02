
package ObjectIndex;

import directObjectAccess.faculty;
import java.io.EOFException;
import objectaccess.student;


public class Retrieve {

 
    public static void main(String[] args)throws Exception{
            ObjIndx obj=ObjIndx.getExtObj("file");
            //System.out.print("Enter the index: ");
            //int indx=new Scanner(System.in).nextInt();
            Object o;
        try {   
            for(int i=0;i<7;i++){
            o=obj.get(i);
            System.out.println(o==null?"No object found":(o.getClass().getName().equals("objectaccess.student")?(student)o:(faculty)o));
            }
        } catch (IndexOutOfBoundsException ex) {
            System.out.println("Index out of bound"+ ex);
        }
    }
    
}
