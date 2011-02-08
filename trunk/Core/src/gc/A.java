package gc;

public class A {
    int id;
    static int cntr = 0;

    public A() {
        id = cntr++;
        System.out.println("Constructing "+id + " total="+cntr);
    }


    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("finalizing:"+id + "; "+(--cntr)+" remains");
    }
}
