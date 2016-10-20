import com.brainfuck.Brainfuck;

public class BrainfuckInterpreter {
    public static void main(String[] args) throws Exception {
        Brainfuck brainfuck = new Brainfuck(args[0]);
        brainfuck.run();
    }
}
