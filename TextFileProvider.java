import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class TextFileProvider extends AbstractProvider {

    private final List<String> list = new ArrayList<>();

    TextFileProvider(String fileName) throws java.io.IOException {

        FileReader fr = new FileReader(fileName);
        BufferedReader br = new BufferedReader(fr);
        String line;

         while((line = br.readLine()) != null){
             String[] words = line.split("\\W+");
             for(String s: words){
                 if(!Objects.equals(s, "")){
                     list.add(Cipher.normalized(s));
                 }
             }
         }
         br.close();
         fr.close();

        Set<String> uniqueString = new HashSet<>(list);     //remover os duplicados de uma lista
        list.removeAll(list);
        List<String> x = new ArrayList<>(uniqueString);     //Ordenar a lista por ordem
        Collections.sort(x);
        list.addAll(x);

    }
    @Override
    public List<String> getWords(){
        return list;
    }
}

class main4{
    public static void main(String[] args) throws Exception {

        AbstractProvider p = new TextFileProvider("simplefile_04.txt");
        java.util.List<String> words = p.getWords();
        System.out.println(words);
        if (!words.equals(java.util.List.of("a", "b"))) throw new Exception("FAIL");
        System.out.print("PASS");
    }
}