import java.util.Random;
import java.util.Scanner;
public class GuessNumber {
    public static void main(String[] args) {
        Random random = new Random();
        int ans = random.nextInt(100)+1; //1~100
        int min = 1;
        int max = 100;

        Scanner scn = new Scanner(System.in);
        System.out.print("請輸入整數，猜猜是"+min+"~"+max+"哪個數字: ");

        while(!scn.hasNextInt()){
            System.out.print("輸入的不是整數!!\n再輸入一次: ");
            scn.next();
        }
        
        int a = scn.nextInt();
        while(a!=ans){
            if(min<a&&a<ans){
                System.out.println("猜錯啦!!");
                min = a;
            }else if(max>a&&a>ans){
                System.out.println("猜錯啦!!");
                max = a;
            }else if(a>max || a<min){
                System.out.print("不對吧.. 超出範圍了啦!");
            }
            System.out.print("再猜一次吧! 範圍是"+min+"~"+max+": ");
            a = scn.nextInt();
        }
        System.out.println("答對了!! 答案是 "+ a);
        }
    }

