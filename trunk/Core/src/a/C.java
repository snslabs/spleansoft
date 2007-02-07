package a;

public class C extends A{

    public C() {
        System.out.println("C");
    }

    public void f(A a){
        System.out.println("method A");
    }

    public void f(B b){
        System.out.println("method B");
    }
}
