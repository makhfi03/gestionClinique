package util;

public class Test {
    
{
       System.out.println("non static 1");
}
    
   static {
       System.out.println("static 1");
}
   
   static {
       System.out.println("static 2");
}

{
       System.out.println("non static 2");
}

    public Test() {
        System.out.println("construct");
    }

    
    public static void main(String[] args) {
        new Test();                                  
    }

}