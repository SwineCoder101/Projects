import HttpHelloWorld.BirthdayEntry;
import HttpHelloWorld.MessageBuilder;
import org.hamcrest.Matchers;
import org.junit.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import static org.hamcrest.MatcherAssert.assertThat;


public class MessageBuilderTest {
    private BirthdayEntry birthdayEntry;

    @Test
    public void shouldShowMoreDaysThanAYearWhenBirthdayPassed(){
        //Given Birthday Last Month
        birthdayEntry = new BirthdayEntry("John", "1992-11-23");
        MessageBuilder messageBuilder = new MessageBuilder(birthdayEntry);

        //Then number of days is more than 30
        assertThat(messageBuilder.getDaysLeft(), Matchers.greaterThan(30));

    }

    @Test
    public void shouldShowLessDaysThanAYearWhenBirthdayIsYetToCome(){
        //Given Birthday Next Month
        birthdayEntry = new BirthdayEntry("John", "1992-01-23");
        MessageBuilder messageBuilder = new MessageBuilder(birthdayEntry);

        //Then number of days is less than 30
        assertThat(messageBuilder.getDaysLeft(), Matchers.lessThan(30));

    }

    @Test
    public void shouldShowHappyBirthdayMessageWhen(){
        //Given Birthday is Today
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date todaysDate = Calendar.getInstance().getTime();
        String todaysDateStr = df.format(todaysDate);

        birthdayEntry = new BirthdayEntry("John", todaysDateStr);
        MessageBuilder messageBuilder = new MessageBuilder(birthdayEntry);

        //Then send Happy Birthday!
        assertThat(messageBuilder.getDaysLeft(), Matchers.equalTo(0));
        assertThat(messageBuilder.getMessage(), Matchers.equalTo("Hello, John! Happy birthday!"));
    }


}
