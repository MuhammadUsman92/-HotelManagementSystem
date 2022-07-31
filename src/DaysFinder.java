import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DaysFinder {
    public int calulateDays(String inDate, String outDate) throws ParseException {
        long answer;
        SimpleDateFormat myFormat = new SimpleDateFormat("dd-MM-yyyy");
            Date dateBefore = myFormat.parse(inDate);
            Date dateAfter = myFormat.parse(outDate);
            long difference = dateAfter.getTime() - dateBefore.getTime();
            answer = (difference / (1000 * 60 * 60 * 24));
        System.out.println(answer+"dats");
            return 10;
    }
}
