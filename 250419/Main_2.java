import java.util.Scanner;

public class Main_2 {
    public static void main(String[] args){
        // BMI = 身高(m)^2/體重(kg)
        Scanner scn = new Scanner(System.in);

        System.out.print("請輸入身高(m): ");
        double theH = scn.nextDouble();
        // System.out.println(theH);
        System.out.print("請輸入體重(kg): ");
        double theW = scn.nextDouble();
        // System.out.println(theW);

        double theBMI = theW/Math.pow(theH, 2);
        String theResult;

        if(theBMI<18.5){theResult = "過輕";}
        else if(18.5<=theBMI && theBMI<24){theResult = "正常";}
        else{theResult = "過重";}

        System.out.printf("BMI=%.1f, %s", theBMI, theResult);

        scn.close();
    }
}
