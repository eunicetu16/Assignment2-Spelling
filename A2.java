import java.io.IOException;

public class A2 {
    public static void main(String[] args) throws IOException {
        Spelling spelling = new Spelling(args[0]);
        spelling.suggest("accomodate", Integer.parseInt(args[1]));
    }
}
