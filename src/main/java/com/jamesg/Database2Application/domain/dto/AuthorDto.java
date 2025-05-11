package com.jamesg.Database2Application.domain.dto;

// This class is a Data Transfer Object (DTO) for the Author entity.
// It is used to transfer data between the client and server
// so that the presentation layer has no knowledge of the Author entity.
// This Dto is a POJO (Plain Old Java Object) and is not a JPA entity.

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthorDto {

    // Looks just like the Author entity, but is not a JPA entity
    private Long id; // Long so it can be null

    private String name;

    private Integer age; // Integer so it can be null
}
