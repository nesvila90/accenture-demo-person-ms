package com.accenture.person.domain.entity;


import com.accenture.person.domain.entity.base.BaseDocument;
import lombok.*;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@Builder
@Document
@EqualsAndHashCode(callSuper = false, of = "numberId")
@AllArgsConstructor
@NoArgsConstructor
public class Person extends BaseDocument {

    @Indexed(unique = true)
    private Long numberId;
    private String idType;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private String genre;
    private String email;


}
