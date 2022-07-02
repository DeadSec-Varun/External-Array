package directObjectAccess;

import objectaccess.student;

public class MainGet {

    public static void main(String[] args) throws Exception{


         DirectAccessObject objout=DirectAccessObject.getNewExtObj("RafObj",4);
          
          objout.append(new student(1,"Varun",94.5));
          objout.append(new student(2,"Arun",83.5));
          objout.append(new faculty("Sam","Phyics"));
          objout.append(new student(3,"Tarun",78.5));

        
    }
    
}
