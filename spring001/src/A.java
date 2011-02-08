public class A {
    public static void main(String[] args) {
        System.out.println(new A().toString());
    }

    @Override
    public final String toString() {
        return super.toString();
    }
}

class B extends A{

}