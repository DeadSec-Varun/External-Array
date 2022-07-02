
package ObjectIndex;

import directObjectAccess.faculty;
import objectaccess.student;

public class Insert {

  
    public static void main(String[] args) throws Exception{
        ObjIndx obj=ObjIndx.getNewExtObj("file",5);
        
        obj.put(new student(1,"Varun",95), 1);
        obj.put(new faculty("Arun","Math"), 3);
        
        obj.put(new student(1,"Varun",100), 1);
        obj.put(new student(4,"Tarun",70), 0);        
        obj.append(new faculty("Arun","Math"));
        
        obj.append(new faculty("John","Physics"));   //sized to 6
        obj.put(new faculty("Jack","CSE"), 5);
        
        try { 
            Object o;
            for(int i=0;i<7;i++){
            o=obj.get(i);
            System.out.println(o==null?"No object found":(o.getClass().getName().equals("objectaccess.student")?(student)o:(faculty)o));
            }
        } catch (IndexOutOfBoundsException ex) {
            System.out.println("Index out of bound"+ ex);
        }
        
        obj.closeExArr();
        
        ObjIndx obj2=ObjIndx.getExtObj("file");
        System.out.println(obj2.getSize());
        System.out.println(obj2.getLastIndex());
    
    }
    
}
