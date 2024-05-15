package com.srinivas.movieservice.DTO;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Data
public class MovieDTO {
    private String name;
    private String genre;
    private String language;
    private List<String> actors = new ArrayList<>();
}
