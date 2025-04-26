public class Main {
    public static void main(String[] args) {
        
        Student M = new Student("Mickey", 18);
        M.introduction();
        
        int age = M.getAge();
        System.out.println(age);
        
        String name = M.getName();
        System.out.println(name);

        Student H = new Student();
        H.setAge(20);
        H.introduction(); //沒有設定 Student 的 Name 屬性 (= null)

        Student A = new Student();
        A.setSex(2);
        System.out.println(A.getSex());
    }    
}
