import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Properties;
import java.util.Iterator;

public class JNDITest {
    public static void main(String[] args) {
        try {
            InitialContext ic = new InitialContext();
            System.out.println(ic.lookup("ejb/SessionComm"));
        }
        catch (Exception e) {
            e.printStackTrace();
        }


    }
}
