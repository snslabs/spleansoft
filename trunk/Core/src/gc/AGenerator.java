package gc;

import java.util.List;
import java.util.ArrayList;

public class AGenerator implements Runnable {
    boolean stop;
    List<A> list = new ArrayList<A>();
    public void run() {
        while(!Thread.interrupted() && !stop){
            try {
                Thread.sleep(200);
                list.add(new A());
            }
            catch (InterruptedException e) {
                stop=true;
            }
        }
    }

    public List getList() {
        return list;
    }
}
