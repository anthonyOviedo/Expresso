public class Test {

    public static void print(Object arg) { System.out.println(arg); }

    public sealed interface MyList permits Cons, Nil {}

    public static record Cons(int head, MyList tail) implements MyList {}

    public static record Nil() implements MyList {}

    public static void main(String... args) {
    }
}
