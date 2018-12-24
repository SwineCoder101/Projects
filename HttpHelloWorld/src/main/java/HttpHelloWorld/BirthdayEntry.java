package HttpHelloWorld;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document
public class BirthdayEntry {
    @Id
    public String id;

    private String name;
    private String dateOfBirth;

    public BirthdayEntry(String name,String dateOfBirth){
        this.dateOfBirth=dateOfBirth;
        this.name=name;
    }

    public void setName(String name){ this.name = name; }

    public void setBirthdate(String dateOfBirth){ this.dateOfBirth = dateOfBirth; }

    public String getName(){
        return(this.name);
    }

    public String getBirthDate(){
        return(this.dateOfBirth);
    }

    @Override
    public String toString() {
        return String.format(
                "BirthdayEntry[id=%s, name='%s', dateOfBirth='%s']",
                id, name, dateOfBirth);
    }

}
