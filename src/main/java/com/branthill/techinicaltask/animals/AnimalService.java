package com.branthill.techinicaltask.animals;

import com.branthill.techinicaltask.animals.api.Animal;
import com.branthill.techinicaltask.animals.api.AnimalRequest;

public interface AnimalService {
    void addNew(AnimalRequest animalRequest);

    Animal findAnimalBy(int animalId);

    void updateAnimal(int animalId, AnimalRequest animalRequest);

    void deleteAnimal(Integer animalId);
}
