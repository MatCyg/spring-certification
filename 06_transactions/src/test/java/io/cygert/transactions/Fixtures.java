package io.cygert.transactions;

import io.cygert.transactions.model.Application;
import io.cygert.transactions.model.Review;

public class Fixtures {

    public static final Application WEATHER_APPLICATION =
            Application.builder()
                       .id(1L)
                       .name("Weather")
                       .review(Review.builder()
                                     .id(1L)
                                     .rating(3.8)
                                     .description("Good enough")
                                     .build())
                       .review(Review.builder()
                                     .id(2L)
                                     .rating(4.5)
                                     .description("Excellent accuracy")
                                     .build())
                       .build();

    public static Application newCalculatorApplication() {
        return Application.builder()
                          .name("Calculator " + System.currentTimeMillis())
                          .review(Review.builder()
                                        .rating(4.0)
                                        .description("Good for basic stuff")
                                        .build())
                          .review(Review.builder()
                                        .rating(2.5)
                                        .description("Missing scientific mode")
                                        .build())
                          .build();
    }
}