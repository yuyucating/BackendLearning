import java.util.Scanner;

public class greeting_sum{
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        
        System.out.print("請輸入名字: ");
        String n = scn.next();
        greeting(n);

        System.out.print("請輸入整數1: ");
        while(!scn.hasNextInt()){
            System.out.println("輸入的不是整數...\n請再輸入一次整數1: ");
            scn.next();
        }
        int a=scn.nextInt();

        System.out.print("請輸入整數2: ");
        while(!scn.hasNextInt()){
            System.out.println("輸入的不是整數...\n請再輸入一次整數2: ");
            scn.next();
        }
        int b=scn.nextInt();

        System.out.println("將加等於: "+Sum(a,b));

        scn.close();
    }
    public static void greeting(String name){
        System.out.println("Hello!! "+ name);
    }
    
    public static int Sum (int a, int b){
        int sum = a + b;
        return sum;
    }
}