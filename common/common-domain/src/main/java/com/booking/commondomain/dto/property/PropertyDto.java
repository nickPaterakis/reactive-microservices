package com.booking.commondomain.dto.property;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PropertyDto {

    @NotNull
    private Long id;

    @NotEmpty
    @Length(min=5, max=100)
    private String title;

    @NotEmpty
    private String propertyType;

    @NotEmpty
    private String guestSpace;

    @NotNull
    @Max(value = 16)
    private Integer maxGuestNumber;

    @NotNull
    private Integer bedroomNumber;

    @NotNull
    private Integer bathNumber;

    @NotNull
    @Digits(integer = 10, fraction = 2)
    private Float pricePerNight;

    @NotEmpty
    private String country;

    @NotEmpty
    private String image;

    @NotEmpty
    private Set<String> amenities;
}
