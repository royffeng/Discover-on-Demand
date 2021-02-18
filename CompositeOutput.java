package advisor;

import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import static advisor.View.printPage;

public class CompositeOutput implements SaveMusic {
    private final String title;
    private String outputExample;
    private final List<SaveMusic> musicList = new ArrayList<SaveMusic>();
    CompositeOutput(){
        title = "";
        outputExample = "";
    }
    public void addToList(SaveMusic music) {
        musicList.add(music);
        outputExample += music.returnOutput();
    }
    @Override
    public String writeToText(JsonObject json) {
        return outputExample;
    }

    @Override
    public String returnOutput() {
        for(SaveMusic music:musicList) {
            music.returnOutput();
        }
        return outputExample;
    }

    @Override
    public void accept(Visitor visit) {
        visit.visit(this);
    }
}
