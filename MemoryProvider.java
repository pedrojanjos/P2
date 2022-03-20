import java.util.*;

public class MemoryProvider extends AbstractProvider{
    private final List<String> list = new ArrayList<>();

    public void addWord(String word) {

        String str = null;
        if(word.length() > 0 ){
            str = Cipher.normalized(word);
        }
        list.add(str);

    }

    public List<String> getWords() {

        Set<String> uniqueString = new HashSet<>(list);
        List<String> x = new ArrayList<>(uniqueString);
        Collections.sort(x);

        return x;
    }
}
