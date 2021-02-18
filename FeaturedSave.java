package advisor;

import com.google.gson.JsonObject;

import java.util.ArrayList;

import static advisor.View.printPage;

public class FeaturedSave implements SaveMusic {
    private final String title;
    protected String out;
    private static int countNew;
    FeaturedSave() {
        title = "Category: Featured Songs";
        out = "";
    }
    @Override
    public String writeToText(JsonObject json) {
        StringBuilder output = new StringBuilder();
        if (countNew > 0){
            output.append(title).append(" ").append(countNew).append("\n").append("\n");
        }
        else{
            output.append(title).append("\n").append("\n");
        }
        var playlists = json.get("playlists").getAsJsonObject();
        playlists.get("items").getAsJsonArray().forEach(item -> {
            var playlist = item.getAsJsonObject();
            var name = playlist.get("name").getAsString();
            var url = playlist.get("external_urls").getAsJsonObject().get("spotify").getAsString();
            output.append(name).append("\n");
            output.append(url).append("\n").append("\n");
        });
        out = output.toString();
        return output.toString();
    }

    @Override
    public String returnOutput() {
        return out;
    }

    @Override
    public void accept(Visitor visit) {
        visit.visit(this);
    }
}
