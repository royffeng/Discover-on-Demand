package advisor;
import com.google.gson.JsonObject;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;

public interface SaveMusic {
    public String writeToText(JsonObject json);
    public String returnOutput();
    public void accept(Visitor visit);
}
