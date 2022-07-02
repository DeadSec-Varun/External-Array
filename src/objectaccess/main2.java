/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objectaccess;

/**
 *
 * @author dev15
 */
public class main2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        
        RafObj objin=RafObj.getRafObj("RafObj");
        //Rafbyte objin=Rafbyte.getRafbyte("RafByte");
          
        //System.out.println(objin.get());
        System.out.println(objin.get());
        System.out.println(objin.get());
        try{System.out.println(objin.get());}catch(Exception e){}
        System.out.println(objin.get());


        //System.out.println(objin.get());
        //System.out.println(objin.get());  



    }
    
}
