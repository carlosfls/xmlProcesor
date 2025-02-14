package com.example.xmlporcessor.model.complex_xml;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@NoArgsConstructor
public class Book {

    private String category;
    private String isbn;
    private String title;
    private Author author;
}
