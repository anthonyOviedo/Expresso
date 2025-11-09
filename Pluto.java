import java.util.function.BiFunction;
import java.util.function.Function;

public class Pluto {

    public static void print(Object arg) { System.out.println(arg); }

    public sealed interface List permits Nil, Cons {}

    public static record Nil() implements List {}

    public static record Cons(Object car, List cdr) implements List {}

    public static void main(String... args) {
        /*
         Drafts for test cases and demo 11/06/2025
         loriacarlos@gmail.com
        //
         Warnings:
        	* They could have some syntax issues (I haven't checked it)
        	* They might be very much "involved" (far beyond Earth?)
        //
         In this file only TC3 and TC4 type examples
        //
        */
        List nil = new Nil();
        List list_666 = new Cons(666, ((List) (nil)));
        List list_777 = new Cons(777, ((List) (list_666)));
        List list_888 = new Cons(888, ((List) (list_777)));
        var myList = list_888;
        /*
          Potential Issues
          * identifier a in case
          * Type of f is UnaryOperator<Any>.
          * method is .apply
        */
        BiFunction<Function<Object, Object>, List, List> map = new BiFunction<Function<Object, Object>, List, List>() {
            @Override public Object apply(Function<Object, Object> f, List a) {
                return (switch (a) {
            case Nil() -> {
                yield a;
            }
            case Cons(var first, var rest) -> {
                yield new Cons(f.apply(first), ((List) (this.apply(f, rest))));
            }
            default -> throw new IllegalStateException("Non-exhaustive match");
        });
            }
        };
        print(("myList mapped" + map.apply((((int) ((x) -> x)) + 1), myList)));
    }
}
