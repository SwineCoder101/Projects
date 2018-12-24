package HttpHelloWorld;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BirthdayEntryService {

    @Autowired
    private BirthdayEntryRepository repository;

    public BirthdayEntry create(BirthdayEntry birthdayEntry){
        return  repository.save(birthdayEntry);
    }

    public BirthdayEntry findByName(String name){
        return repository.findByName(name);
    }

}
