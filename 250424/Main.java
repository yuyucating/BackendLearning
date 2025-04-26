public class Main{
    public static void main(String[] args) {
        String name = "Una";
        int week = 8;

        greet(name, week);
    }

    public static void greet(String name, int week){
        char w;
        switch(week){
            case 1: 
                w = '一';
                break;
            case 2:
                w = '二';
                break;
            case 3:
                w = '三';
                break;
            case 4:
                w = '四';
                break;
            case 5:
                w = '五';
                break;
            case 6:
                w = '六';
                break;
            case 7:
                w = '日';
                break;
            default:
                w = '錯';
        }

        if (w=='錯'){
            System.out.println("輸入錯誤!");  
        } else {
            System.out.println("你好，"+name+"! 今天是星期"+w+"。");  
        }   
    }
}