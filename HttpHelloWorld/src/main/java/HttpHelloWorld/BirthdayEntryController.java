package HttpHelloWorld;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
public class BirthdayEntryController {

    @Autowired
    private BirthdayEntryService birthdayEntryService;

    @PutMapping(value = "/hello/{name}", consumes = {"application/json"})
    public ResponseEntity<String> putBirthdayEntry(
            @PathVariable("name") String name, @RequestBody DateOfBirth dateOfBirth){

        BirthdayEntry birthdayEntry = new BirthdayEntry(name,dateOfBirth.getDateOfBirth());

        birthdayEntryService.create(birthdayEntry);

        return new ResponseEntity<String>( "", HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/hello/{name}", method = GET)
    public ResponseEntity<String> getBirthdayEntry(
            @PathVariable("name") String name){

        BirthdayEntry birthdayEntry =  birthdayEntryService.findByName(name);

        MessageBuilder messageBuilder = new MessageBuilder(birthdayEntry);

        return new ResponseEntity<String>( messageBuilder.getMessage(), HttpStatus.OK);

    }
}
