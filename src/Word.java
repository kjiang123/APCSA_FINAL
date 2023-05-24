import java.util.ArrayList;

public class Word {

    private String word;
    private String def;
    private String origin;
    private String example;
    private ArrayList<String> antonyms;

    public Word(String word, String def, String origin, String example, ArrayList<String> antonyms){
        this.word = word;
        this.def = def;
        this.origin = origin;
        this.example = example;
        this.antonyms = antonyms;
    }
}
