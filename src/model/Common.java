package model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Common {
    public static String getCurrentDate() {
        LocalDate today = LocalDate.now();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = formatter.format(today);

        return formattedDate;
    }
}
