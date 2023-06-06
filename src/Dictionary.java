import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.*;
import java.nio.charset.StandardCharsets;


public class Dictionary {

    private static final String BASE_URL = "https://api.dictionaryapi.dev/api/v2/entries/en/";

    public static String dictionaryDef(String userWord) {
        if (userWord.equals("")){
            return "You put in nothing. What are you doing?";
        }
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out, StandardCharsets.UTF_8));
        String url = BASE_URL + userWord;
        String urlResponse = "";

        try {
            URI myUri = URI.create(url);
            HttpRequest request = HttpRequest.newBuilder().uri(myUri).build();
            HttpClient client = HttpClient.newHttpClient();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            urlResponse = response.body();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        JSONArray jsonArr = null;
        JSONObject jsonObj1 = null;
        try {
            jsonArr = new JSONArray(urlResponse);
        } catch (Exception e) {
            jsonObj1 = new JSONObject(urlResponse);
        }

        if (jsonArr != null) {
            String build = "";
            String word = "";
            String phonetics = "";
            String add = "";

            for (int i = 0; i < jsonArr.length(); i++) {
                JSONObject jsonObj = jsonArr.getJSONObject(i);
                word = jsonObj.getString("word");

                JSONArray phoneticsArr = jsonObj.getJSONArray("phonetics");

                for (int k = 0; k < phoneticsArr.length(); k++) {
                    JSONObject a = phoneticsArr.getJSONObject(k);
                    if (a.has("text")) {
                        phonetics = a.getString("text");
                    }
                }

                JSONArray defintionsArr = jsonObj.getJSONArray("meanings");

                for (int j = 0; j < defintionsArr.length(); j++) {
                    JSONObject a = defintionsArr.getJSONObject(j);
                    String partOfSpeech = a.getString("partOfSpeech");
                    JSONArray subDefArr = a.getJSONArray("definitions");
                    for (int l = 0; l < subDefArr.length(); l++) {
                        String example = "";
                        JSONObject defObj = subDefArr.getJSONObject(l);
                        if (defObj.has("example")){
                            example = defObj.getString("example");
                        }
                        String printDef = defObj.getString("definition");
                        if (!example.equals("")){
                            add += "\nPart of Speech: " + partOfSpeech + "\nDefinition: " + printDef + "\nExample: " + example + "\n";
                        } else {
                            add += "\nPart of Speech: " + partOfSpeech + "\nDefinition: " + printDef + "\n";
                        }
                    }
                }
            }

            build = "Word: " + word + "\nPronunciation: " + phonetics + "\n" + add;
            return build;
        } else {
            return jsonObj1.getString("title") + "\n" + jsonObj1.getString("message");
        }
    }
}


