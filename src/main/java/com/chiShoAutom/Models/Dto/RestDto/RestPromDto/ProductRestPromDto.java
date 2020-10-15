package com.chiShoAutom.Models.Dto.RestDto.RestPromDto;

import com.chiShoAutom.Models.Group;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Currency;
import java.util.Objects;

@Data
@NoArgsConstructor
public class ProductRestPromDto {

    private long id;

    private String name;

    private float price;

    @JsonIgnore
    private Group group;

    private String main_image;

    private Currency currency;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductRestPromDto that = (ProductRestPromDto) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
