import java.math.BigDecimal;
import java.text.DecimalFormat;

public class A {
    public static void main(String[] args) {
        DecimalFormat df = new DecimalFormat("#,###.###");
        System.out.println(df.format(new BigDecimal("110.01")));

    }
}
