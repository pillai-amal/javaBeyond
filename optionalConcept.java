import java.util.Optional;

class OptionalConcept{
    public static void main(String[] args) {
        String s = null;
        String s2 = "Java";

        Optional <String> obj = Optional.ofNullable(s);
        System.out.println(obj);
    }
}
