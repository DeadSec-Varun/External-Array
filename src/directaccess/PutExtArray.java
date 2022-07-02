/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package directaccess;

import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author dev15
 */
public class PutExtArray {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Scanner sc=new Scanner(System.in);
        System.out.print("To open existing file exter 1 ");
        long i=sc.nextLong();
        
        System.out.println("Enter file name");
        String file=sc.nextLine();
        ExtArr exArr;
        long size;
        
        if(i==1){
            exArr = ExtArr.getExtArr(file);
            size=exArr.size;
        }
        else{
            System.out.print("Enter size: ");
            size=sc.nextLong();
            exArr = ExtArr.getNewExtArr(file,size);
        }
        
        System.out.println("\nExisting Array");
        for(long j=0;j<size;j++){
            try {
                    System.out.print(exArr.get(j)+" ");
                } catch (IOException ex) {
                    System.out.println("Out of bounds");
                }
        }
        
        ///////////////////////////
        
        System.out.println("Enter value and index to store data\n");
        int value;
        while(true){
            System.out.print("Your choice: ");
            value=sc.nextInt();
            i=sc.nextLong();
            
            if(i>=size){
               System.out.println("Exceeds array size");
               continue;
            }
                try {
                    exArr.put(value,i);
                } catch (IOException ex) {
                      System.out.println("Exit....");
                      return;
                }
        }        
        //////////////////////////
    
    }
}