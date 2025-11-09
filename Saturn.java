import java.util.function.Function;
import java.util.function.BiFunction;

public class Saturn {

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
        	* They might be very much "involved" (beyond Earth?)
         In this file only TC3 and TC4 type examples
        //
        */
        /*
          Potential issues
          none
          underscore pattern
          Nil pattern is Nil() in Java
        //
        */
        Function<List, Object> first = (a) -> (switch (a) {
            case Nil() -> {
                yield null;
            }
            case Cons(var f, _) -> {
                yield f;
            }
            default -> throw new IllegalStateException("Non-exhaustive match");
        });
        Function<List, Object> rest = (a) -> (switch (a) {
            case Nil() -> {
                yield null;
            }
            case Cons(_, var r) -> {
                yield r;
            }
            default -> throw new IllegalStateException("Non-exhaustive match");
        });
        Function<List, Object> length = new Function<List, Object>() {
            @Override public Object apply(List a) {
                return (switch (a) {
            case Nil() -> {
                yield 0;
            }
            case Cons(_, var r) -> {
                yield (1 + ((Integer) (this.apply(r))));
            }
            default -> throw new IllegalStateException("Non-exhaustive match");
        });
            }
        };
        Function<List, Object> copy = new Function<List, Object>() {
            @Override public Object apply(List a) {
                return (switch (a) {
            case Nil() -> {
                yield a;
            }
            case Cons(var f, var r) -> {
                yield new Cons(f, ((List) (this.apply(r))));
            }
            default -> throw new IllegalStateException("Non-exhaustive match");
        });
            }
        };
        BiFunction<List, List, Object> append = new BiFunction<List, List, Object>() {
            @Override public Object apply(List a, List b) {
                return (switch (a) {
            case Nil() -> {
                yield b;
            }
            case Cons(var f, var r) -> {
                yield new Cons(f, ((List) (this.apply(r, b))));
            }
            default -> throw new IllegalStateException("Non-exhaustive match");
        });
            }
        };
        /*
         Potential issues
         Type is BinaryOperator<list>
        //
        */
        BiFunction<List, List, Object> append_as_lambda = (a, b) -> append.apply(a, b);
        /*
         Potential issues
         * Type is BinaryOperator<list>
         * Method is append_as_lambda.apply
        //
        */
        Function<List, Object> double_list = (a) -> append_as_lambda.apply(a, a);
        //////////////////////////////////////////////////
        List nil = new Nil();
        List list_666 = new Cons(666, ((List) (nil)));
        List list_777 = new Cons(777, ((List) (list_666)));
        List list_888 = new Cons(888, ((List) (list_777)));
        var myList = list_888;
        // Tests
        print(("myList=" + myList));
        print(("myList first=" + first.apply(myList)));
        print(("myList rest=" + rest.apply(myList)));
        print(("myList length=" + length.apply(myList)));
        print(("myList twice =" + double_list.apply(myList)));
        var myList_copy = copy.apply(myList);
        print(("myList copy =" + myList_copy));
        print((("myList == myList_copy should be true?" + copy) == myList_copy));
    }
}
