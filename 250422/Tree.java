import java.util.Scanner;

public class Tree {
    public static void main(String[] args){
        Scanner scn = new Scanner(System.in);
        System.out.print("請輸入樹要有幾層: ");
        while(!scn.hasNextInt()){
            System.out.println("要輸入整數唷!!");
            System.out.print("再輸入一次: ");
            scn.next();
        }
        int x = scn.nextInt();

        int k=0;
        for(int i=0; i<x; i++){
            space(x,i);
            for(int j=0; j<=i*2; j++){
                System.out.print("*");
            }
            space(x,i);
            System.out.println();
        }
        scn.close();
    }
    public static void space(int x, int i){
        for(int n=0; n < x-(i+1); n++){
            System.out.print(" ");
        }         
    }
}
