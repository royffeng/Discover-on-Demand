package advisor;

import com.google.gson.JsonObject;
import java.util.*;
public class AddTitle extends Decorator {
    Scanner scanner = new Scanner(System.in);
    AddTitle(SaveMusic music){
        super(music);
    }
    @Override
    public String writeToText(JsonObject json) {
        String output = music.writeToText(json);
        System.out.println("Enter Title you want to add");
        String message = scanner.nextLine();
        out = message + "\n" + output;
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
