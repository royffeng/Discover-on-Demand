package advisor;

import com.google.gson.JsonObject;
import java.util.*;
public class AddMessage extends Decorator {
    Scanner scanner = new Scanner(System.in);
    AddMessage(SaveMusic music) {
        super(music);
    }
    @Override
    public String writeToText(JsonObject json) {
        String output = music.writeToText(json);
        System.out.println("Enter message you want to add");
        String message = scanner.nextLine();
        out = output + "\n" + message + "\n" + "\n";
        return out;
    }

    @Override
    public String returnOutput() {
        return out;
    }

    @Override
    public void accept(Visitor visit) {
        visit.visit(music);
    }
}
