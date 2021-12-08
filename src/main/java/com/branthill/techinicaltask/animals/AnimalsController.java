package com.branthill.techinicaltask.animals;

import com.branthill.techinicaltask.animals.api.Animal;
import com.branthill.techinicaltask.animals.api.AnimalRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AnimalsController {
    private  final AnimalService animalService;

    @Autowired
    public AnimalsController(AnimalService animalService) {
        this.animalService = animalService;
    }

    @PostMapping(value = "/animals")
    public ResponseEntity<AnimalRequest> storeAnimal(@RequestBody AnimalRequest animalRequest) {
        animalService.addNew(animalRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping(value = "/animals/{animalId}")
    public ResponseEntity<Animal> findAnimal(@PathVariable("animalId") Integer animalId){
        return ResponseEntity.ok().body(animalService.findAnimalBy(animalId));
    }

    @PutMapping(value = "/animals/{animalId}")
    public ResponseEntity updateAnimal(@PathVariable("animalId") Integer animalId, @RequestBody AnimalRequest animalRequest){
        animalService.updateAnimal(animalId, animalRequest);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/animals/{animalId}")
    public ResponseEntity deleteAnimal(@PathVariable("animalId") Integer animalId){
        animalService.deleteAnimal(animalId);
        return ResponseEntity.ok().build();
    }
}
