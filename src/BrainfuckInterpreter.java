import com.brainfuck.Brainfuck;

public class BrainfuckInterpreter {
    public static void main(String[] args) throws Exception {
        Brainfuck brainfuck = new Brainfuck("C:\\Users\\Users\\IdeaProjects\\Brainfuck\\src\\input.txt");
        brainfuck.run();
    }
}
