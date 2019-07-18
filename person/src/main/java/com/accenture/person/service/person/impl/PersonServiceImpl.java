package com.accenture.person.service.person.impl;

import com.accenture.person.commons.exceptions.business.DataCorruptedException;
import com.accenture.person.commons.exceptions.business.DataNotFoundedException;
import com.accenture.person.commons.exceptions.business.base.BusinessException;
import com.accenture.person.domain.dto.generic.PersonDTO;
import com.accenture.person.domain.entity.Person;
import com.accenture.person.repository.person.PersonRepositoryFacade;
import com.accenture.person.service.person.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PersonServiceImpl implements PersonService {

    private final PersonRepositoryFacade personRepositoryFacade;

    @Autowired
    public PersonServiceImpl(PersonRepositoryFacade personRepositoryFacade) {
        this.personRepositoryFacade = personRepositoryFacade;
    }

    @Override
    public List<PersonDTO> findAll() throws  DataNotFoundedException {
        return personRepositoryFacade.findAllPersons().stream()
                .map(person -> PersonDTO.builder()
                        .numberId(person.getNumberId())
                        .idType(person.getIdType())
                        .firstName(person.getFirstName())
                        .lastName(person.getLastName())
                        .birthDate(person.getBirthDate())
                        .email(person.getEmail())
                        .genre(person.getGenre())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public Set<PersonDTO> findAllUnique() throws DataNotFoundedException {
        return personRepositoryFacade.findAllUniquePersons().stream()
                .map(person -> PersonDTO.builder()
                        .numberId(person.getNumberId())
                        .idType(person.getIdType())
                        .firstName(person.getFirstName())
                        .lastName(person.getLastName())
                        .birthDate(person.getBirthDate())
                        .email(person.getEmail())
                        .genre(person.getGenre())
                        .build())
                .collect(Collectors.toSet());
    }

    @Override
    public PersonDTO findById(Long idNumber, String idType) throws BusinessException, DataNotFoundedException {
        return personRepositoryFacade.findPersonByIdentification(idNumber, idType).map(
                person -> PersonDTO.builder()
                        .numberId(person.getNumberId())
                        .idType(person.getIdType())
                        .firstName(person.getFirstName())
                        .lastName(person.getLastName())
                        .birthDate(person.getBirthDate())
                        .email(person.getEmail())
                        .genre(person.getGenre())
                        .build()).orElseThrow(DataNotFoundedException::new);
    }

    @Override
    public Long create(PersonDTO person) throws DataCorruptedException {

            return personRepositoryFacade.createPerson(
                    Person.builder()
                            .numberId(person.getNumberId())
                            .idType(person.getIdType())
                            .firstName(person.getFirstName())
                            .lastName(person.getLastName())
                            .birthDate(person.getBirthDate())
                            .email(person.getEmail())
                            .genre(person.getGenre())
                            .build());

    }

    @Override
    public void update(PersonDTO person, Long idNumber, String type) throws BusinessException {
        personRepositoryFacade.updatePerson(
                Person.builder()
                        .birthDate(person.getBirthDate())
                        .genre(person.getGenre())
                        .email(person.getEmail())
                        .firstName(person.getFirstName())
                        .lastName(person.getLastName())
                        .numberId(person.getNumberId())
                        .idType(person.getIdType())
                        .build(), idNumber, type);
    }

    @Override
    public void delete(PersonDTO person) throws BusinessException, DataCorruptedException {
        personRepositoryFacade.deletePerson(Person.builder().idType(person.getIdType()).numberId(person.getNumberId()).build());
    }
}
