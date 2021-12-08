package com.branthill.techinicaltask.animals.api;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Animal {
    private String name;
    private Vet vet;
    private AnimalType animalType;

}
