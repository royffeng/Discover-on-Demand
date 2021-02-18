package advisor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Visitor {
    public void visit(SaveMusic music){
        String check = music.returnOutput();
        int i = 0;
        Pattern p = Pattern.compile("https://open.spotify.com");
        Matcher m = p.matcher(check);
        while (m.find()) {
            i++;
        }
        System.out.println(i);
    }
    public void visit(CompositeOutput compositeOutput){
        String check = compositeOutput.returnOutput();
        int i = 0;
        Pattern p = Pattern.compile("https://open.spotify.com");
        Matcher m = p.matcher(check);
        while (m.find()) {
            i++;
        }
        System.out.println(i);
    }
}
