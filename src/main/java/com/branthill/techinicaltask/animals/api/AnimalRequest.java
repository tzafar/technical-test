package com.branthill.techinicaltask.animals.api;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AnimalRequest {
    private String animalName;
    private Integer vetId;
    private Integer animalTypeId;
}
