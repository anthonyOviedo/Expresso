import java.util.function.BiFunction;

public class HelloWorld1 {

    public static void print(Object arg) { System.out.println(arg); }

    public static void main(String... args) {
        // HelloWorld1.expresso
        /*
          As HelloWorld0 but with a Binary Function
          @author hooNous
          @since Oct 6th, 2025
        */
        int x = 6;
        print(x);
        int y = 3;
        print(y);
        BiFunction<Integer, Integer, Integer> f = (x_1, z) -> (((int) Math.pow(z, x_1) + (x_1 * z)) + 1);
        print(f.apply(x, y)); // Expected 748
    }
}
