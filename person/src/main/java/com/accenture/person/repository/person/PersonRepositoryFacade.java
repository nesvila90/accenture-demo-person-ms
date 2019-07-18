package com.accenture.person.repository.person;

import com.accenture.person.commons.exceptions.business.DataCorruptedException;
import com.accenture.person.commons.exceptions.business.DataNotFoundedException;
import com.accenture.person.commons.exceptions.business.base.BusinessException;
import com.accenture.person.domain.entity.Person;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface PersonRepositoryFacade {

    List<Person> findAllPersons() throws DataNotFoundedException;

    Set<Person> findAllUniquePersons() throws DataNotFoundedException;

    Optional<Person> findPersonByIdentification(Long idNumber, String idType) throws BusinessException;

    Long createPerson(Person person) throws DataCorruptedException;

    void updatePerson(Person person, Long idNumber, String type) throws BusinessException;

    void deletePerson(Person person) throws BusinessException, DataCorruptedException;

}
