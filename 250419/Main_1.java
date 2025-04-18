public class Main_1 {
    public static void main(String[] args){
        int thisYear = 1112;

        if((thisYear%4==0 && thisYear%100!=0) || thisYear%400==0){
            System.out.print(thisYear+" 是閏年");
        }else{
            System.out.print(thisYear+" 不是閏年");
        }
    }
}
