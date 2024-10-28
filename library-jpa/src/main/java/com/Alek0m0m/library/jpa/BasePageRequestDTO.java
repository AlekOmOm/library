package com.Alek0m0m.library.jpa;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.lang.Nullable;

import java.util.Objects;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class BasePageRequestDTO {
    // extend class
    // set default values through:
        // constructor
        // or Setters
    // override validation() if stricter validation is needed

    // Default values:
    private int page = 0;
    private int size = 10;
    private String field = "id"; // notable default value
    private Sort.Direction direction = Sort.Direction.ASC;

    // --------------------- Main methods ---------------------

    public Pageable getPageable(BasePageRequestDTO dto) {
        Integer page = resolveValue(dto.getPage(), this.getPage());
        Integer size = resolveValue(dto.getSize(), this.getSize());
        String field = resolveValue(dto.getField(), this.getField());
        Sort.Direction direction = resolveValue(dto.getDirection(), this.getDirection());

        validate(page, size);

        return PageRequest.of(page, size, Sort.by(direction, field));
    }



    // --------------------- Helper methods ---------------------

    private <T> T resolveValue(T value, T defaultValue) {
        return Objects.nonNull(value) ? value : defaultValue;
    }

    public void validate(int page, int size) {
        if (page < 0) {
            throw new IllegalArgumentException("Page number must be greater than or equal to 0");
        }

        if (size < 1) {
            throw new IllegalArgumentException("Page size must be greater than 0");
        }
    }
}
