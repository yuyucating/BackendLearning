import java.util.Scanner;

public class Sum {
    public static void main(String[] args){
        Scanner scn = new Scanner(System.in);
        System.out.print("請輸入名字: ");
        String name = scn.next();
        greeting(name);
        System.out.print("請輸入整數1: ");

        while(!scn.hasNextInt()){
            System.out.print("輸入錯誤唷~ 請再輸入一次整數1: ");
            scn.next();
        }
        int a = scn.nextInt();

        System.out.print("請輸入整數2: ");

        while(!scn.hasNextInt()){
            System.out.print("輸入錯誤唷~ 請再輸入一次整數2: ");
            scn.next();
        }
        int b = scn.nextInt();

        int sum = add(a,b);
        System.out.println("相加結果 = "+sum);

        scn.close();
    }

    public static int add(int a, int b){
        int sum = a + b;
        return sum;
    }
    public static void greeting (String name){
        System.out.println("Hello! "+name);
    }
}
