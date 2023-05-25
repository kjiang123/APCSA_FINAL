import java.util.ArrayList;

public class Word {

    private String word;
    private String def;
    private String partOfSpeech;
    private String pronounce;
    private String origin;
    private String example;
    private ArrayList<String> antonyms;
    private ArrayList<String> synonyms;

    public Word(String word, String def, String partOfSpeech, String pronounce, String origin, String example, ArrayList<String> antonyms, ArrayList<String> synonyms){
        this.word = word;
        this.def = def;
        this.pronounce = pronounce;
        this.partOfSpeech = partOfSpeech;
        this.origin = origin;
        this.example = example;
        this.antonyms = antonyms;
        this.synonyms = synonyms;
    }

    public String getWord(){
        return word;
    }

    public String getDef(){
        return def;
    }

    public String getPartOfSpeech(){
        return partOfSpeech;
    }

    public String getPronounce(){
        return pronounce;
    }

    public String getOrigin(){
        return origin;
    }

    public String getExample(){
        return example;
    }

    public ArrayList<String> getAntonyms(){
        return antonyms;
    }

    public ArrayList<String> getSynonyms(){
        return synonyms;
    }

}
