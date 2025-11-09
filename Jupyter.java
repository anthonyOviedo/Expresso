import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Consumer;
import java.util.function.DoubleUnaryOperator;
import java.util.function.DoubleBinaryOperator;

public class Jupyter {

    public static void print(Object arg) { System.out.println(arg); }

    public static void main(String... args) {
        /*
         Drafts for test cases and demo 11/06/2025
         loriacarlos@gmail.com
         Warnings:
        	* They could have some syntax issues (I haven't checked it)
        	* They might be very much "involved" (beyond Earth?)
        //
         In this file only TC1 and TC2 type examples
        //
        */
        ////////////// TC1 /////////////////
        /*
        //
          Repeats a string n-times using a recursive two-parameters fun
          Uses ==
          Potential Issue two-parameters type
        //
          Define a Lambda (recognize type Function<Integer, String> and generate .apply)
        //
        */
        BiFunction<Integer, String, String> fill = new BiFunction<Integer, String, String>() {
            @Override public String apply(Integer n, String s) {
                return ((n == 0) ? "" : (s + this.apply(((int) ((((int) (n)) - 1))), s)));
            }
        };
        print(fill.apply(((int) (80)), "-"));
        /*
          Potential issues:
          * Defines a lambda of type Function<Integer, String>
          * Generate line.apply
        */
        Function<Integer, String> line = (n) -> (fill.apply(((int) (n)), "_") + "\n");
        print(line.apply(((int) (80))));
        /*
        * Defines a lambda returning 'void'; recognize type as a Consumer<Integer>
        * Generate .accept)
        */
        Consumer<Integer> print_line = (n) -> print(line.apply(((int) (n))));
        // Call it. Potential Issue: generate as Cosumer<Integer> type and .accept
        print_line.accept(((int) (80))); // print_line.accept(80)
        // Potential Issue: String concat "+"
        print(((("The only " + true) + " answer to the meaning of life is: ") + ((1 + (666 / 666)) + (20 * 2))));
        print_line.accept(((int) (80)));
        /////////////// TC2 //////////////////
        print("*** Newton-Raphson TC2 **** ");
        DoubleUnaryOperator abs = (x) -> ((x >= 0) ? x : -(x)); // Uses >= and unary -
        //  Potential issue: type is UnaryOperator<Double> and method is apply
        print(("Test abs:" + (abs.applyAsDouble(((double) (((double) (-(666)))))) == abs.applyAsDouble(((double) (((double) (-(-(666))))))))));
        double EPSILON = 1.0E-11; // Fully uppercased id. Issue floating-point using scientific notation
        print(("EPSILON=" + EPSILON));
        DoubleBinaryOperator improve = (a, x) -> (0.5 * (((double) (x)) + (((double) (a)) / ((double) (x))))); // BinaryOperator<Double> .apply
        /*
          Potential Issues:
          Type is BiPredicate<Double>
          abs --> abs.apply
        //
        */
        BiFunction<Double, Double, Boolean> converges = (a, x) -> (abs.applyAsDouble(((double) ((Math.pow(x, 2) - ((int) (a)))))) < EPSILON);
        /*
          Potential Issues:
          Type is BinaryOperator<Double>
          converges --> converges.test
        //
        */
        DoubleBinaryOperator next = (a, x) -> (converges.apply(((double) (a)), ((double) (x))) ? x : improve.applyAsDouble(((double) (a)), ((double) (x))));
        /*
         Potential Issues:
         converges --> converges.test
        //
        */
        DoubleBinaryOperator sqrt_iterate = new DoubleBinaryOperator() {
            @Override public double applyAsDouble(double a, double x0) {
                return (converges.apply(((double) (a)), ((double) (x0))) ? x0 : this.applyAsDouble(((double) (a)), ((double) (improve.applyAsDouble(((double) (a)), ((double) (x0)))))));
            }
        };
        DoubleUnaryOperator sqrt = new DoubleUnaryOperator() {
            @Override public double applyAsDouble(double a) {
                return sqrt_iterate.applyAsDouble(((double) (a)), ((double) ((((double) (a)) * 0.5))));
            }
        };
        Function<Double, String> test_sqrt = (a) -> ((a + " -sqrt-> ") + sqrt.applyAsDouble(((double) (a))));
        print(test_sqrt.apply(((double) (4))));
        print(((Math.pow(sqrt.applyAsDouble(((double) (4))), 2) + " ==? ") + 4.0));
        print(((sqrt.applyAsDouble(((double) (2))) + " ==? ") + 1.4142135623730951));
        //////////////////////////////
    }
}
