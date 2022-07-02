package directObjectAccess;

import java.util.Scanner;
import objectaccess.student;

public class MainPut {

    public static void main(String[] args) throws Exception {
        Scanner sc=new Scanner(System.in);
        DirectAccessObject objIn=DirectAccessObject.getExtObj("RafObj");
        
        System.out.print("Enter choice: ");
        
        int i=sc.nextInt();
        Object obj=objIn.get(i);       
        obj=obj.getClass().getName().equals("objectaccess.student")?(student)obj:(faculty)obj;
        System.out.println("\n"+obj);

    }
    
}
