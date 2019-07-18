package com.accenture.person.repository.person.impl;

import com.accenture.person.commons.enums.ErrorCode;
import com.accenture.person.commons.exceptions.builder.ExceptionBuilder;
import com.accenture.person.commons.exceptions.business.DataCorruptedException;
import com.accenture.person.commons.exceptions.business.DataNotFoundedException;
import com.accenture.person.commons.exceptions.business.base.BusinessException;
import com.accenture.person.domain.entity.Person;
import com.accenture.person.repository.person.PersonRepository;
import com.accenture.person.repository.person.PersonRepositoryFacade;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Component
@Log4j2
public class PersonRepositoryFacadeImpl implements PersonRepositoryFacade {

    private final PersonRepository personRepository;

    @Autowired
    public PersonRepositoryFacadeImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public List<Person> findAllPersons() throws DataNotFoundedException {
        log.info("Seeking All Persons...");
        if (getPeople().isPresent()) {
            List<Person> personList = getPeople().get();
            log.debug("Persons Founded... ");
            personList.forEach(log::debug);
            return personList;
        } else {
            log.warn("Not found Persons, Empty list...");
            throw ExceptionBuilder.newBuilder()
                    .withMessage("Not found Persons, Empty list...")
                    .buildDataNotFoundedException();
        }
    }

    @Override
    public Set<Person> findAllUniquePersons() throws DataNotFoundedException {
        log.info("Seeking All Uniques Persons...");

        if (getPeople().isPresent()) {
            Optional<List<Person>> persons = getPeople();
            log.debug("Persons Founded... ");
            persons.get().forEach(log::debug);
            return new HashSet<>(persons.get());
        } else {
            log.warn("Not found Persons, Empty list...");
            throw ExceptionBuilder.newBuilder()
                    .withMessage("Not found Persons, Empty list...")
                    .buildDataNotFoundedException();
        }
    }

    @Override
    public Optional<Person> findPersonByIdentification(Long idNumber, String idType) throws BusinessException {
        log.info("Seeking Person by ID {} and type {}", idNumber, idType);
        Optional<Person> person = personRepository.findPersonByNumberIdAndIdType(idNumber, idType);
        if (person.isPresent()) {
            log.debug("Person Result: {}", person);
            return person;
        } else {
            log.error("Error Seeking Person: {}", person);
            throw ExceptionBuilder.newBuilder().withCode(ErrorCode.BUSINESS_EXCEPTION)
                    .withMessage("Person not found by identification")
                    .buildBusinessException();
        }

    }

    private Optional<List<Person>> getPeople() {
        return Optional.ofNullable(personRepository.findAll());
    }

    @Override
    public Long createPerson(Person person) throws DataCorruptedException {
        try {
            log.info("Persist Person... {}", person.toString());
            personRepository.save(person);
            return person.getNumberId();
        } catch (Exception e) {
            log.error("Error Persist Person... {}", person.toString());
            throw ExceptionBuilder.newBuilder().withCode(ErrorCode.PERSISTENCE_EXCEPTION)
                    .withMessage("Error Persist Person.")
                    .buildDataCorruptedException();
        }

    }

    @Override
    public void updatePerson(Person person, Long idNumber, String type) throws BusinessException {
        log.info("Update Person... {}", person.toString());
        Optional<Person> personFounded = personRepository.findPersonByNumberIdAndIdType(idNumber, type);
        if (personFounded.isPresent()) {
            Person personUpdated = personFounded.get();
            personUpdated.setNumberId(person.getNumberId());
            personUpdated.setIdType(person.getIdType());
            personUpdated.setFirstName(person.getFirstName());
            personUpdated.setLastName(person.getLastName());
            personUpdated.setBirthDate(person.getBirthDate());
            personUpdated.setEmail(person.getEmail());
            personUpdated.setGenre(person.getGenre());

            log.debug("Person to be updated: {}", person.toString());
            personRepository.save(personUpdated);
        } else {
            log.error("Error Updating Person... {}", person.toString());
            throw ExceptionBuilder.newBuilder().withCode(ErrorCode.BUSINESS_EXCEPTION)
                    .withMessage("Person to be Updated, not could be found")
                    .buildBusinessException();
        }
    }

    @Override
    public void deletePerson(Person person) throws DataCorruptedException {
        log.info("Remove Person... {}", person.toString());
        Optional<Person> personToRemove = personRepository.findPersonByNumberIdAndIdType(person.getNumberId(), person.getIdType());
        if (personToRemove.isPresent()) {
            log.debug("Person to be removed: {}", person.toString());
            personRepository.delete(personToRemove.get());
        } else {
            log.error("Error removing Person... {}", person.toString());
            throw ExceptionBuilder.newBuilder().withCode(ErrorCode.BUSINESS_EXCEPTION)
                    .withMessage("Person to be removed, not could be found")
                    .buildDataCorruptedException();
        }
    }
}
