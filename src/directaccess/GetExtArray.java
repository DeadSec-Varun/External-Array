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
public class GetExtArray {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        // Get Data
        
        System.out.println("Enter your file name");
        String file=sc.nextLine();
        ExtArr exArr = ExtArr.getExtArr(file);
        long size=exArr.size;
        
       
        System.out.println("Enter index to retrieve data or -1 for complete data\n");
        long i=0;
        while(true){
            System.out.print("Your choice: ");
            i=sc.nextLong();
            if(i==-1){
                for(long j=0;j<size;j++){
                    try {
                        System.out.print(exArr.get(j)+" ");
                    } catch (IOException ex) {
                        System.out.println("Out of bounds");
                    }
                }
                    System.out.println();
            }
            else{
                try {
                    System.out.println(exArr.get(i));
                } catch (IOException ex) {
                      System.out.println("Exit....");
                      return;
                }
            }
        }
        
    }
    
}
