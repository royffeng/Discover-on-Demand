package advisor;

import com.google.gson.JsonObject;

import java.util.ArrayList;

import static advisor.View.printPage;

public class NewSave implements SaveMusic {
    private final String title;
    private static int countNew;
    protected String out;
    NewSave(){
        title = "Category: New Songs";
        out = "";
        countNew = 0;
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
        var albums = json.get("albums").getAsJsonObject();
        albums.get("items").getAsJsonArray().forEach(item -> {
            var album = item.getAsJsonObject();
            var name = album.get("name").getAsString();
            var url = album.get("external_urls").getAsJsonObject().get("spotify").getAsString();
            var artists = new ArrayList<>();
            album.get("artists").getAsJsonArray().forEach(artist -> artists.add(artist.getAsJsonObject().get("name").getAsString()));
            output.append(name).append("\n");
            output.append(artists).append("\n");
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
