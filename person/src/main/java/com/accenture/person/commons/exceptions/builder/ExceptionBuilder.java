package com.accenture.person.commons.exceptions.builder;

import com.accenture.person.commons.enums.ErrorCode;
import com.accenture.person.commons.exceptions.GeneralException;
import com.accenture.person.commons.exceptions.business.DataCorruptedException;
import com.accenture.person.commons.exceptions.business.DataNotFoundedException;
import com.accenture.person.commons.exceptions.business.base.BusinessException;

public class ExceptionBuilder {

    private ErrorCode errorCode;
    private String message;
    private Throwable rootException;

    private ExceptionBuilder() {

    }

    public static ExceptionBuilder newBuilder() {
        return new ExceptionBuilder();
    }


    public ExceptionBuilder withCode(ErrorCode errorCode) {
        this.errorCode = errorCode;
        return this;
    }

    public ExceptionBuilder withMessage(String message) {
        this.message = message;
        return this;
    }

    public ExceptionBuilder withRootException(Throwable exception) {
        this.rootException = exception;
        return this;
    }

    public BusinessException buildBusinessException() {
        return new BusinessException(this.message, this.rootException, this.errorCode);
    }

    public DataCorruptedException buildDataCorruptedException() {
        return new DataCorruptedException(this.message, this.rootException, this.errorCode);
    }


    public DataNotFoundedException buildDataNotFoundedException() {
        return new DataNotFoundedException(this.message, this.rootException, this.errorCode);
    }


    public GeneralException buildGeneralException() {
        return new GeneralException(this.message, this.rootException, this.errorCode);
    }

}
