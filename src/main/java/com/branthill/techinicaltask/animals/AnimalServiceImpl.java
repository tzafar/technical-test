package com.branthill.techinicaltask.animals;

import com.branthill.techinicaltask.animals.api.Animal;
import com.branthill.techinicaltask.animals.api.AnimalRequest;
import com.branthill.techinicaltask.common.errors.AnimalNotFoundException;
import com.branthill.techinicaltask.common.errors.GeneralException;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
public class AnimalServiceImpl implements AnimalService {

    private final AnimalRepository animalRepository;

    public AnimalServiceImpl(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }

    @Override
    public void addNew(AnimalRequest animalRequest) {
        animalRepository.addNew(animalRequest);
    }

    @Override
    public Animal findAnimalBy(int animalId) {
        Animal animal = animalRepository.findById(animalId);
        if (animal == null) {
            throw new AnimalNotFoundException();
        }
        return animal;
    }

    @Override
    public void updateAnimal(int animalId, AnimalRequest animalRequest) {
        animalRepository.updateAnimalBy(animalId, animalRequest);
    }

    @Override
    public void deleteAnimal(Integer animalId) {
        animalRepository.deleteAnimalBy(animalId);
    }
}
