package gc;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;

public class GCTest {
    public static void main(String[] args) throws InterruptedException {

        AGenerator aGen = new AGenerator();
        Thread t = new Thread(aGen);
        System.out.println("Starting creating weak references");
        t.start();
        Thread.sleep(10000);
        aGen.stop=true;
        System.out.println("waiting 2 sec");
        Thread.sleep(2000);
        System.out.println("unplugging array");
        aGen.list = null;
        Thread.sleep(1000);
        System.out.println("Looking for phantom references");

        PhantomReference pr = new PhantomReference(aGen, null);
        System.out.println(pr);
        System.out.println(pr.get());

        System.out.println("Waiting 10 sec");
        Thread.sleep(10000);
        System.out.println("running GC");
        System.gc();
        System.out.println("done.");


    }
}
