import java.util.Scanner;

public class Main_1 {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        System.out.print("請輸入年份: ");
        // int thisYear;
        
        if (scanner.hasNextInt()){
            int thisYear = scanner.nextInt();
            System.out.println("輸入格式正確!! 您輸入的是 "+thisYear);
            if((thisYear%4==0 && thisYear%100!=0) || thisYear%400==0){
                System.out.print(thisYear+" 是閏年");
            }else{
                System.out.print(thisYear+" 不是閏年");
            }
        }else{
            System.out.println("輸入的不是整數..");
        }
        scanner.close();
    }
}
