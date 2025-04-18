public class Main_2 {
    public static void main(String[] args){
        // BMI = 身高(m)^2/體重(kg)
        double theH = 1.67;
        double theW = 66.9336;
        double theBMI = theW/Math.pow(theH, 2);
        String theResult;
        // System.out.print(theBMI);
        if(theBMI<18.5){theResult = "過輕";}
        else if(18.5<=theBMI && theBMI<24){theResult = "正常";}
        else{theResult = "過重";}

        System.out.printf("BMI=%.1f, %s", theBMI, theResult);
    }
}
