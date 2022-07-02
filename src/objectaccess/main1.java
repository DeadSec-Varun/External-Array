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
public class main1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
          //RafObj objout=RafObj.getRafObj("RafObj");
          Rafbyte objout=Rafbyte.getRafbyte("RafByte");
          
          objout.put(new student(3,"Arun",74.5));
    }
    
}
