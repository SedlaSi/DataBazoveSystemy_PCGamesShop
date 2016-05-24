package src.util;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Util {
    //http://stackoverflow.com/a/15190787
    public static String stripAccents(String s) {
        s = Normalizer.normalize(s, Normalizer.Form.NFD);
        s = s.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
        return s;
    }

    public static <T> List<T> getSubList(List<T> list, int count, Random random) {
        List<T> subList = new ArrayList<>();

        if (random == null || list == null || count > list.size()) {
            return null;
        }

        for (int i = 0; i < count; ) {
            T item = list.get(random.nextInt(list.size()));

            if (!subList.contains(item)) {
                subList.add(item);
                i++;
            }
        }

        return subList;
    }
}
