package advisor;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Data {
    protected static final String NEW = "https://api.spotify.com/v1/browse/new-releases";
    protected static final String FEATURED = "https://api.spotify.com/v1/browse/featured-playlists";
    protected static final String CATEGORIES = "https://api.spotify.com/v1/browse/categories";
    protected static final String PLAYLISTS = "https://api.spotify.com/v1/browse/categories/%s/playlists";
    protected static final String CLIENT_ID = "d541c06c54b04f6a834f31598ee11fe0";
    protected static final String CLIENT_SECRET = "414953f879e1489bb34553b32b4ce550";
    protected static final String GRANT_TYPE = "authorization_code";
    protected static final String REDIRECT_URI = "http://localhost:8080";
    protected static final String RESPONSE_TYPE = "code";
    protected static final HttpClient client = HttpClient.newBuilder().build();
    protected static HttpRequest request;
    protected static HttpResponse<String> response;
    protected static String authorizationCode;
    protected static String accessToken;
}
