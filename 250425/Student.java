public class Student {  //建立 class 類別
    private String name;
    private int age;

    public Student(String name, int age){  //建立屬性 (建構子)
        this.name = name;
        this.age = age;
    }

    public Student(){ //空的建構子

    }

    public void introduction(){  //建立方法1 (行為)
        System.out.println("我叫"+name+"，今年"+age+"歲");
    }

    public int getAge(){ //建立方法2 (行為)
        return age;
    }

    public String getName(){
        return name;
    }
    
}
