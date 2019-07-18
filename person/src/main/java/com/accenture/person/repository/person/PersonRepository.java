package com.accenture.person.repository.person;

import com.accenture.person.domain.entity.Person;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.Optional;

@Repository
public interface PersonRepository extends MongoRepository<Person, BigInteger> {

    Optional<Person> findPersonByNumberIdAndIdType(Long idNumber, String idType);

}
