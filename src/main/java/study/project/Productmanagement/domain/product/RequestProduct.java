package study.project.Productmanagement.domain.product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RequestProduct(
        @NotNull
        @NotBlank
        String name,

        @NotNull
        Integer price_in_cents
) {}
