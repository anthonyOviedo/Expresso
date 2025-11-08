public class Test {

    public static void print(Object arg) { System.out.println(arg); }

    public sealed interface Person permits PersonRecord {}

    public static final class PersonRecord implements Person {
        public final Object name;
        public final Object age;
        public final Object gender;
        public final Account p_acocunt;
        public PersonRecord(Object name, Object age, Object gender, Account p_acocunt) {
            this.name = name;
            this.age = age;
            this.gender = gender;
            this.p_acocunt = p_acocunt;
        }
        @Override public String toString() {
            var __sb = new StringBuilder("PersonRecord{");
            __sb.append("name=").append(name);
            __sb.append(", ");
            __sb.append("age=").append(age);
            __sb.append(", ");
            __sb.append("gender=").append(gender);
            __sb.append(", ");
            __sb.append("p_acocunt=").append(p_acocunt);
            __sb.append('}');
            return __sb.toString();
        }
    }

    public sealed interface Account permits AccountRecord {}

    public static final class AccountRecord implements Account {
        public final String id;
        public final int balance;
        public AccountRecord(String id, int balance) {
            this.id = id;
            this.balance = balance;
        }
        @Override public String toString() {
            var __sb = new StringBuilder("AccountRecord{");
            __sb.append("id=").append(id);
            __sb.append(", ");
            __sb.append("balance=").append(balance);
            __sb.append('}');
            return __sb.toString();
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
        the person uses the data
        //
        */
        var p1 = new PersonRecord("Antony", 28, new AccountRecord("a-123", 123));
        print(p1);
    }
}
