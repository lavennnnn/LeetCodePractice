package Methods;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        HashMap<Integer, String> hashMap = new HashMap<>();
        String str1 = "yes";
        String str2 = "no";
        hashMap.put(1, str1);
        hashMap.put(1, str2);
        System.out.println(hashMap.get(1));
    }
}