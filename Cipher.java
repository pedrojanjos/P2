import java.util.*;
import java.util.List;
import java.text.Normalizer;

public class Cipher {

    public static String list_To_String(ArrayList<String> list){
        StringBuilder sb2 = new StringBuilder();
        int h = 0;
        for (String d : list) {
            if (h++ == list.size() - 1) {
                sb2.append(d);
            } else {
                sb2.append(d).append(" ");
            }
        }
        return sb2.toString();
    }

    static String q = null;     //Variável auxiliar para guardar a string retornada na recursividade da função

    public static String explore(List<String> words, String s, String guarda) {

        ArrayList<String> n = new ArrayList<>();

        String s1 = s;
        String guardar = null;
        int verifica = 0;
        int i = 0;
        int t = 0;
        int sz = s.length();
        String originalGuarda = guarda;

        while (true) {
            for (String aux : words) {
                if (s.startsWith(aux) && !aux.equals(guardar)) {
                    for (String x : words) {  //fazer verificação se existe outra palavra com o mesmo começo

                        if (x.startsWith(aux) && !x.equals(aux) && !x.equals(guarda) && !x.equals(guardar)) {
                            guardar = x;
                            q = explore(words, s, guardar);
                            verifica++;
                        }
                    }
                    if (verifica == 0 && guarda.equals(originalGuarda)){
                        if(!originalGuarda.equals("ç")){
                            s = s.substring(guarda.length());
                            sz -= guarda.length();
                            n.add(guarda);
                            originalGuarda = "";
                        }else{
                            if(i != words.size() - 1){
                                s = s.substring(aux.length() + 1);
                                sz -= aux.length();
                                n.add(aux);
                            }else{
                                sz -= aux.length();
                                n.add(aux);
                                return list_To_String(n);
                            }
                        }
                    }else{
                        s = s.substring(aux.length());
                        sz -= aux.length();
                        n.add(aux);
                    }
                }

                if (i++ == words.size() - 1){
                    if(s.equals(s1)){
                        return null;
                    }
                    for (String aux2 : words) {
                        if (s.startsWith(aux2)) {
                            t++;
                        }
                    }
                    if (t == 0) {
                        int taarabt = 0;
                        int z = 1;
                        do{ // aflorumdia -> florumdia
                            for (String weigl : words){
                                if (s.startsWith(weigl)) {
                                    taarabt++;
                                }
                            }
                            if (taarabt != 0){
                                break;
                            }else {
                                s = s.substring(z, sz);
                                sz--;
                            }
                        }while (sz != 1);
                    }
                }
            }
            if(sz == 1){
                return list_To_String(n);
            }
            i = 0;
            t = 0;
        }
    }

    public static String normalized(String naturalText) {

        String str = Normalizer.normalize(naturalText, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
        str = str.replaceAll("[^a-zA-Z0-9]", " ");
        str = str.replaceAll("\\s", "");    //Retira os espaços em branco de uma string
        str = str.toLowerCase();

        return str;
    }

    public static String encode(String frase, int count) {

        Random r = new Random();
        int guarda_div = 0;
        int sz = frase.length();

        if (sz % count == 0) {
            guarda_div = count;
        }
        char[] extra = new char[16];   //Array com os caracteres random
        int SZ = frase.length();

        if (guarda_div == 0) {
            while (true) {
                SZ += 1;    //Novo tamanho da string
                if (SZ % count == 0) {
                    int grimaldo = SZ - sz;
                    for (int j = 0; j < grimaldo; j++) {    //Adicionar no array SZ letras random que perdencem à frase
                        extra[j] = frase.charAt(r.nextInt(frase.length()));
                    }
                    break;
                }
            }
        }
        StringBuilder otamendi = new StringBuilder();
        if (SZ != sz) {
            int odisseas = SZ - sz;
            for (int i = 0; i < odisseas; i++) {
                otamendi.append(extra[i]);  //concatenar a string frase com as letras random
            }
        }
        String gaitan = frase + otamendi;

        char[] aa = new char[SZ];
        int h = 0;

        //Cifrar o texto
        for (int i = 0; i < SZ; i++) {
            //linha a linha
            if(h == SZ){
                break;
            }
            for (int j = i; j < SZ; j += count) {
                if (h != SZ) {
                    aa[h] = gaitan.charAt(j);
                    h++;
                }
            }
        }
        StringBuilder encriptar = new StringBuilder();
        for (int i = 0; i < SZ; i++) {
            encriptar.append(aa[i]);
        }
        return encriptar.toString();
    }

    public static List<Integer> findDividers(int x){

        ArrayList<Integer> list = new ArrayList<>();

        for(int i = 1; i<= x; i++){
            if(x%i == 0){
                list.add(i);
            }
        }
        return list;
    }

    public static List<String> breakCipher(String cipherText, List<String> words){

        List<String> l = new ArrayList<>();  //lista das palavras desencriptadas
        List<Integer> l2;

        //Encriptar
        l2 = findDividers(cipherText.length());

        for(Integer in : l2){
            l.add(encode(cipherText, in));
        }

        //Fazer as possíveis frases

        List<String> result = new ArrayList<>();

        String luisao = "ç";

        for (String s : l) {
            result.add(explore(words, s, luisao));
            if(q != null){
                result.add(q);
                q = null;
            }
        }
        result.removeIf(Objects::isNull);

        return result;
    }
}

class main{
    public static void main(String[] args) {

        var words = java.util.List.of("um", "uma", "dia", "noite", "flor");
        var plainText = "uma flor um dia";
        var normText = Cipher.normalized(plainText);
        var cipherText = Cipher.encode(normText, 5);
//  BREAK CIPHER
        var candidates = Cipher.breakCipher(cipherText, words);
// TEST IF plainText EXISTS IN candidates
        var ok = candidates.stream().anyMatch(x -> x.startsWith(plainText));
        System.out.println(ok);

    }
}