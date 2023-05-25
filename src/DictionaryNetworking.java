import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import org.json.JSONArray;
import org.json.JSONObject;


public class Dictionary {

    private static final String BASE_URL = "https://api.dictionaryapi.dev/api/v2/entries/en/";

    public static Word dictionaryDef(String userWord){
        String url = BASE_URL + userWord;
        String urlResponse = "";

        try{
            URI myUri = URI.create(url);
            HttpRequest request = HttpRequest.newBuilder().uri(myUri).build();
            HttpClient client = HttpClient.newHttpClient();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            urlResponse = response.body();
        } catch (Exception e){
            System.out.println(e.getMessage());
        }

        JSONObject jsonObj = new JSONObject(urlResponse);



        }
    }


