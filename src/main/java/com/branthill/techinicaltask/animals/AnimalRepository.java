package com.branthill.techinicaltask.animals;

import com.branthill.techinicaltask.animals.api.Animal;
import com.branthill.techinicaltask.animals.api.AnimalRequest;
import com.branthill.techinicaltask.animals.api.AnimalType;
import com.branthill.techinicaltask.animals.api.Vet;
import com.branthill.techinicaltask.common.H2ConnectionProvider;
import com.branthill.techinicaltask.common.errors.AnimalNotFoundException;
import com.branthill.techinicaltask.common.errors.GeneralException;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@Repository
public class AnimalRepository {
    public void addNew(AnimalRequest animalRequest) {
        Statement stmt;
        try {
            stmt = H2ConnectionProvider.getDatabaseConnection().createStatement();
            String sql = "INSERT INTO ANIMALS (animal_name, vet_id, animal_type_id) values ('" + animalRequest.getAnimalName() + "'," + animalRequest.getVetId() + "," + animalRequest.getAnimalTypeId() + ")";
            stmt.execute(sql);
        } catch (SQLException e) {
            throw new GeneralException();
        }
    }

    public Animal findById(int animalId) {
        try {
            Statement stmt = H2ConnectionProvider.getDatabaseConnection().createStatement();
            String sql = "SELECT * FROM ANIMALS LEFT JOIN VETS ON ANIMALS.VET_ID = VETS.ID LEFT JOIN ANIMAL_TYPES ON ANIMALS.ANIMAL_TYPE_ID = ANIMAL_TYPES.ID WHERE ANIMALS.ID = " + animalId;
            ResultSet resultSet = stmt.executeQuery(sql);
            if (resultSet.next()) {
                return new Animal(resultSet.getString("animal_name"), new Vet(resultSet.getString("vet_name")), new AnimalType(resultSet.getString("name")));
            }
        } catch (SQLException e){
            throw new GeneralException();
        }

        return null;
    }

    public void updateAnimalBy(int animalId, AnimalRequest animalRequest) {
        Statement statement;
        try {
            statement = H2ConnectionProvider.getDatabaseConnection().createStatement();

            String findAnimalSql = "Select * from ANIMALS where id = " + animalId;
            ResultSet resultSet = statement.executeQuery(findAnimalSql);
            if (!resultSet.next()) {
                throw new AnimalNotFoundException();
            }

            String updateSql = "UPDATE ANIMALS SET ANIMAL_NAME = '" + animalRequest.getAnimalName() + "', VET_ID = " + animalRequest.getVetId() + ", ANIMAL_TYPE_ID = " + animalRequest.getAnimalTypeId() + " where id = " + animalId;
            statement.execute(updateSql);
        } catch (SQLException e) {
            throw new GeneralException();
        }
    }

    public void deleteAnimalBy(Integer animalId) {
        try {
            Statement statement = H2ConnectionProvider.getDatabaseConnection().createStatement();
            String sql = "DELETE FROM ANIMALS WHERE ID = " + animalId;
            statement.execute(sql);
        } catch (SQLException e) {
            throw new GeneralException();
        }
    }
}
