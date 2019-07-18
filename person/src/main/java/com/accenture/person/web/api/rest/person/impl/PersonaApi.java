package com.accenture.person.web.api.rest.person.impl;

import com.accenture.person.commons.exceptions.business.DataCorruptedException;
import com.accenture.person.commons.exceptions.business.DataNotFoundedException;
import com.accenture.person.commons.exceptions.business.base.BusinessException;
import com.accenture.person.domain.dto.generic.PersonDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PersonaApi {

    ResponseEntity<Long> registerPerson(PersonDTO persona) throws DataCorruptedException;

    ResponseEntity<Void> updatePerson(PersonDTO person, Long numberId, String type) throws BusinessException;

    ResponseEntity<Void> removePerson(Long id, String type) throws DataCorruptedException, BusinessException;

    ResponseEntity<PersonDTO> findPersonById(String type, Long identification) throws DataNotFoundedException, BusinessException;

    ResponseEntity<List<PersonDTO>> findAll() throws DataNotFoundedException, BusinessException;

}
