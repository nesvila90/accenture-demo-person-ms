package com.accenture.person.service.base;


import com.accenture.person.commons.exceptions.business.DataCorruptedException;
import com.accenture.person.commons.exceptions.business.DataNotFoundedException;
import com.accenture.person.commons.exceptions.business.base.BusinessException;
import com.accenture.person.domain.dto.generic.PersonDTO;

import java.util.List;
import java.util.Set;

public interface BaseService<T> {

    List<T> findAll() throws BusinessException, DataNotFoundedException;

    Set<T> findAllUnique() throws BusinessException, DataNotFoundedException;

    T findById(Long idNumber, String idType) throws BusinessException, DataNotFoundedException;

    Long create(PersonDTO personDTO) throws DataCorruptedException;

    void update(PersonDTO person, Long idNumber, String type) throws BusinessException;

    void delete(PersonDTO person) throws BusinessException, BusinessException, DataCorruptedException;
}
