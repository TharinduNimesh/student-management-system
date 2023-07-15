package model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Common extends Mysql {

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
    
    public static void changeKey(String key) {
        String query = "UPDATE secret_key SET `key` = '"+ key +"' WHERE id = 1";
        try {
            update(query);
        } catch (Exception ex) {
            Logger.getLogger(Common.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
