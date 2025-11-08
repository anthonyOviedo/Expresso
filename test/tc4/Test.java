import java.util.function.Function;

public class Test {

    public static void print(Object arg) { System.out.println(arg); }

    public sealed interface Shape permits Circle, Rectangle {}

    public static record Circle(double radius) implements Shape {}

    public static record Rectangle(double length, double width) implements Shape {}

    public static void main(String... args) {
        print("******************** TC4 testing ********************");
        /*
        Versión básica: prueba de pattern matching con data y ^.
        Nil = java null
        */
        Function<Object, String> shape_type = new Function<Object, String>() {
            @Override public String apply(Object s) {
                return ((String) (switch (s) {
                case Circle CircleCase -> {
                    var r = CircleCase.radius();
                    yield "its a Circle";
                }
                case Rectangle RectangleCase -> {
                    var l = RectangleCase.length();
                    var w = RectangleCase.width();
                    yield "its a Rectangle";
                }
                default -> throw new IllegalStateException("Non-exhaustive match");
            }));
            }
        };
        Function<Object, Double> area = new Function<Object, Double>() {
            @Override public Double apply(Object s) {
                return ((double) (switch (s) {
                case Circle CircleCase_1 -> {
                    var r = CircleCase_1.radius();
                    yield ((2 * r) * 3.14);
                }
                case Rectangle RectangleCase_1 -> {
                    var l = RectangleCase_1.length();
                    var w = RectangleCase_1.width();
                    yield (l * w);
                }
                default -> throw new IllegalStateException("Non-exhaustive match");
            }));
            }
        };
        var c = new Circle(5.0);
        var r = new Rectangle(3.0, 4.0);
        print(c);
        print(r);
        print(shape_type.apply(c));
        print(shape_type.apply(r));
        print(area.apply(c));
        print(area.apply(r));
    }
}
