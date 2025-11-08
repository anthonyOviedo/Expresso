public class Test {

    public static void print(Object arg) { System.out.println(arg); }

    public sealed interface Person permits Person {}

    public static final class Person implements Person {
        public final Object name;
        public final Object age;
        public final Object gender;
        public Person(Object name, Object age, Object gender) {
            this.name = name;
            this.age = age;
            this.gender = gender;
        }
        @Override public String toString() {
            var __sb = new StringBuilder("Person{");
            __sb.append("name=").append(name);
            __sb.append(", ");
            __sb.append("age=").append(age);
            __sb.append(", ");
            __sb.append("gender=").append(gender);
            __sb.append('}');
            return __sb.toString();
        }
    }

    public sealed interface Gender permits Male, Female {}

    public static final class Male implements Gender {
        public Male() {
        }
        @Override public String toString() {
            return "Male";
        }
    }

    public static final class Female implements Gender {
        public Female() {
        }
        @Override public String toString() {
            return "Female";
        }
    }

    public static void main(String... args) {
        /////////////// TC3 //////////////////
        /*
        //
        Prueban que los data se transpilan correctamente a Java y que se pueden instanciar
        (operador ^ is for creating a new object from class ).
        sealed interfaces y permits con record
        Nil = Null (in java)
        */
        var p1 = new Person("Antony", 28, new Male());
        print(p1);
        var p2 = new Person("Estef", 27, Nil);
        print(p2);
    }
}
