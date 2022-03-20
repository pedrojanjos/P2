import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class SimpleFileProvider extends AbstractProvider {

    private final List<String> list = new ArrayList<>();

    SimpleFileProvider(String filename) throws java.io.IOException{

        BufferedReader reader;

        reader = new BufferedReader(new FileReader(filename));
        String line = reader.readLine();

        while (line != null) {
            list.add(Cipher.normalized(line));
            line = reader.readLine();
        }

        reader.close();

        Set<String> uniqueString = new HashSet<>(list);
        List<String> x = new ArrayList<>(uniqueString);
        Collections.sort(x);

        list.removeAll(list);
        list.addAll(x);

    }
    @Override
    public List<String> getWords() {
        return list;
    }
}

class main3{
    public static void main(String[] args) throws Exception {

        AbstractProvider p = new SimpleFileProvider("simplefile_04.txt");
        java.util.List<String> words = p.getWords();
        if (!words.equals(java.util.List.of("a", "b"))) throw new Exception("FAIL");
        System.out.print("PASS");
    }
}