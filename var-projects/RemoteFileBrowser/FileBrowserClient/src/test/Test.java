package test;

public class Test {
    public static void main(String[] args) throws Exception {
        Test test = new Test();
        new Thread(new T(test)).start();
        Thread.sleep(1000);
        new Thread(new T(test)).start();
        Test.l("Waiting  10 sec");
        Thread.sleep(10000);
        test.unfreeze();
    }


    synchronized void unfreeze(){
        notifyAll();
    }
    synchronized void syncmethod() {
        Test.l("Printing from " + Thread.currentThread().toString());
        try {
            Test.l("wait!!!");
            this.wait();
            Test.l("after wait!!!!!");
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void l(String msg) {
        System.out.println(Thread.currentThread().toString()+ ":"+msg);
    }
}

class T implements Runnable {
    static int cntr=1;
    int n;
    Test t;

    public T(Test t) {
        n = cntr++;
        this.t = t;
    }

    public void run() {
        while (!Thread.interrupted()) {
            try {
                Test.l("Pause for 5 sec");
                Thread.sleep(5000);
                Test.l("Calling sync method from thread " + this.toString());
                t.syncmethod();
                Test.l("Pause for 5 sec");
                Thread.sleep(5000);

            }
            catch (Exception e) {
                Thread.currentThread().interrupt();
            }
        }

    }

    public String toString() {
        return this.getClass().toString()+"@"+n;
    }
}


