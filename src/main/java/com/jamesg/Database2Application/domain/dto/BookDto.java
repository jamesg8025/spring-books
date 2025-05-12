package com.jamesg.Database2Application.domain.dto;

import com.jamesg.Database2Application.domain.entities.AuthorEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookDto {

    private String isbn;

    private String title;

    private AuthorDto authorEntity; // Changed to Author object instead of authorId to establish a relationship
}
