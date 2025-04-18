public class Main {
    public static void main(String[] args){

        double toy = 37;
        int itemCount = 2;
        double totalPrice;
        double TAX_RATE = 0.05;

        double coupon = 15;
        boolean isDiscount = false;


        if (isDiscount) {
            totalPrice = (toy * itemCount - coupon) * (1 + TAX_RATE);
            System.out.print("總金額: " + totalPrice);
        } else {
            totalPrice = toy * itemCount * (1 + TAX_RATE);
            System.out.print("總金額: " + totalPrice);
        }
    }
    
}
