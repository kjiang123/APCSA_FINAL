import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import org.json.JSONArray;
import org.json.JSONObject;


public class Dictionary {

    private static final String BASE_URL = "https://api.dictionaryapi.dev/api/v2/entries/en/";

    public static void dictionaryDef(String userWord){
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

        JSONArray jsonArr = new JSONArray(urlResponse);
        JSONObject jsonObj = jsonArr.getJSONObject(0);

        String word = jsonObj.getString("word");

        JSONArray phonetics = jsonObj.getJSONArray("phonetics");
        for (int i = 0; i < phonetics.length(); i++){
            JSONObject a = phonetics.getJSONObject(i);
            if (a.has("text")) {
                String text = a.getString("text");
                System.out.println(text);
            }
        }

        System.out.println(word);





        }
    }


