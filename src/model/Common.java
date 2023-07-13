package model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

public class Common {

    public static String getCurrentDate() {
        LocalDate today = LocalDate.now();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = formatter.format(today);

        return formattedDate;
    }

    public static <K, V> K getKeyFromValue(HashMap<K, V> map, V value) {
        for (HashMap.Entry<K, V> entry : map.entrySet()) {
            if (value.equals(entry.getValue())) {
                return entry.getKey();
            }
        }
        return null;
    }
}
