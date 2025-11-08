public class Test {

    public static void print(Object arg) { System.out.println(arg); }

    public sealed interface Shape permits Circle, Rectangle {}

    public static final class Circle implements Shape {
        public final double radius;
        public Circle(double radius) {
            this.radius = radius;
        }
        @Override public String toString() {
            var __sb = new StringBuilder("Circle{");
            __sb.append("radius=").append(radius);
            __sb.append('}');
            return __sb.toString();
        }
    }

    public static final class Rectangle implements Shape {
        public final double length;
        public final double width;
        public Rectangle(double length, double width) {
            this.length = length;
            this.width = width;
        }
        @Override public String toString() {
            var __sb = new StringBuilder("Rectangle{");
            __sb.append("length=").append(length);
            __sb.append(", ");
            __sb.append("width=").append(width);
            __sb.append('}');
            return __sb.toString();
        }
    }

    public static void main(String... args) {
        print("******************** TC4 testing ********************");
        /*
        Versión básica: prueba de pattern matching con data y ^.
        Nil = java null
        */
    }
}
