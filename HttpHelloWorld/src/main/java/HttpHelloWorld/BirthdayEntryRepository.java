package HttpHelloWorld;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface BirthdayEntryRepository extends MongoRepository<BirthdayEntry, String> {

    BirthdayEntry findByName(String firstName);

}
