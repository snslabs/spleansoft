package a;

public class CoreTest1 {
    public static void main(String[] args) throws Throwable {
        System.out.println(sub());

    }

    private static  int sub(){
        try{
            return 1;
        }
        finally{
            return 2;
        }
    }
}
