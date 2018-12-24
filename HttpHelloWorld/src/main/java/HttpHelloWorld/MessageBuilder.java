package HttpHelloWorld;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

//    message : Hello, John! Your birthday is in 5 days
//    message : Hello, John ! Happy birthday!

public class MessageBuilder {
    private String message;
    private String happyBirthdayStr =" Happy birthday!";
    private BirthdayEntry birthdayEntry;
    private int daysLeft;
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");


    public MessageBuilder(BirthdayEntry birthdayEntry){
        this.birthdayEntry=birthdayEntry;
        this.message = "Hello, " + birthdayEntry.getName() + "!" + workOutDays();
    }

    public int getDaysLeft(){return daysLeft;}

    public String getMessage(){
        return(message);
    }

    private Date getNextBirthDate(int year) throws ParseException{
        String dateOfBirthStr = year + "-" + birthdayEntry.getBirthDate().substring(5,9);


        Date nextBirthday = dateFormat.parse(dateOfBirthStr);
        return(nextBirthday);
    }

    private int getYear(Date date){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return (cal.get(Calendar.YEAR));
    }

    private String getFormattedDate(Date date){
        return dateFormat.format(date);
    }

    private String workOutDays(){

        try {
            Date todaysDate = new Date();
            int thisYear = getYear(todaysDate);
            Date nextBirthday = getNextBirthDate(thisYear);

            if (getFormattedDate(todaysDate).equals(birthdayEntry.getBirthDate())){
                return happyBirthdayStr;
            }
            else if (nextBirthday.getTime() < todaysDate.getTime()){
                nextBirthday = getNextBirthDate(thisYear+1);
            }

            long diffInMillies = Math.abs(todaysDate.getTime() - nextBirthday.getTime());
            long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);

            daysLeft = Math.toIntExact(diff);

            happyBirthdayStr = " Your birthday is in " + daysLeft + " days";

        }catch(ParseException pe){
            pe.printStackTrace();
        }

        return happyBirthdayStr;
    }

}
