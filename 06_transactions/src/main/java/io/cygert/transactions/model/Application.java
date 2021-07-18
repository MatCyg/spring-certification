package io.cygert.transactions.model;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Application {
    private Long id;
    private String name;
    @Singular
    private List<Review> reviews = new ArrayList<>();
}