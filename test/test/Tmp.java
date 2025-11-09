 public static void print(Object arg) { System.out.println(arg); }

   import java.util.function.BinaryOperator;

sealed interface list<T> permits Nil, Cons {}

record Nil<T>() implements list<T> {}

record Cons<T>(T car, list<T> cdr) implements list<T> {}

public class Saturn {
    public static <T> T first(list<T> a) {
        return switch (a) {
            case Nil<T> n  -> null;
            case Cons<T> c -> c.car();
        };
    }

    public static <T> list<T> rest(list<T> a) {
        return switch (a) {
            case Nil<T> n  -> null;
            case Cons<T> c -> c.cdr();
        };
    }

    public static <T> int length(list<T> a) {
        return switch (a) {
            case Nil<T> n  -> 0;
            case Cons<T> c -> 1 + length(c.cdr());
        };
    }

    public static <T> list<T> copy(list<T> a) {
        return switch (a) {
            case Nil<T> n  -> n;
            case Cons<T> c -> new Cons<>(c.car(), copy(c.cdr()));
        };
    }

    public static <T> list<T> append(list<T> a, list<T> b) {
        return switch (a) {
            case Nil<T> n  -> b;
            case Cons<T> c -> new Cons<>(c.car(), append(c.cdr(), b));
        };
    }

    public static void main(String[] args) {
        BinaryOperator<list<Integer>> append_as_lambda = ExpressoLists::append;
        var double_list = (java.util.function.Function<list<Integer>, list<Integer>>)
                a -> append_as_lambda.apply(a, a);

        list<Integer> nil      = new Nil<>();
        list<Integer> list_666 = new Cons<>(666, nil);
        list<Integer> list_777 = new Cons<>(777, list_666);
        list<Integer> list_888 = new Cons<>(888, list_777);
        list<Integer> myList   = list_888;

        System.out.println("myList=" + myList);
        System.out.println("myList first=" + first(myList));
        System.out.println("myList rest=" + rest(myList));
        System.out.println("myList length=" + length(myList));
        System.out.println("myList twice=" + double_list.apply(myList));

        list<Integer> myList_copy = copy(myList);
        System.out.println("myList copy=" + myList_copy);
        System.out.println("myList == myList_copy should be true? " + myList.equals(myList_copy));
    }
}
