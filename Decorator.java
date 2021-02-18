package advisor;

import com.google.gson.JsonObject;

public abstract class Decorator implements SaveMusic {
    protected SaveMusic music;
    String out;
    Decorator(SaveMusic music){
        this.music = music;
        out = "";
    }
    @Override

    public String writeToText(JsonObject json) {
        return this.music.writeToText(json);
    }

    @Override
    public String returnOutput() {
        return this.music.returnOutput();
    }
}
