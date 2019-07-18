package com.accenture.person.domain.entity.base;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;

import java.math.BigInteger;
import java.time.LocalDate;

public abstract class BaseDocument {

    @Id
    private BigInteger id;

    @Version
    private Integer version;

    @CreatedDate
    private LocalDate createdDate;

    @LastModifiedDate
    private LocalDate lastModifiedDate;
}
