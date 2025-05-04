package com.jamesg.Database2Application.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Author {
    private Long id; // Long so it can be null

    private String name;

    private Integer age; // Integer so it can be null

}
