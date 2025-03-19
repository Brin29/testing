package com.brienn.testing.repository;

import com.brienn.testing.entity.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class UserRepositoryTests {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void UserRepository_SaveAll_ReturnSavedUser(){

        // Arrange
        User user = User.builder()
                .firstName("Breiner")
                .lastName("Parra")
                .build();

        // Act
        User saveUser = userRepository.save(user);

        System.out.println("data" + saveUser);

        // Assert
        Assertions.assertThat(saveUser).isNotNull();
        Assertions.assertThat(saveUser.getId()).isGreaterThan(0);
    }

    @Test
    public void UserRepository_GetAll_ReturnMoreThanOneUser(){
        User user = User.builder()
                .firstName("Steven")
                .lastName("Parra")
                .build();

        User user2 = User.builder()
                .firstName("Andres")
                .lastName("Diaz")
                .build();

        userRepository.save(user);
        userRepository.save(user2);

        List<User> userList = userRepository.findAll();

        Assertions.assertThat(userList).isNotNull();
        Assertions.assertThat(userList.size()).isEqualTo(2);
    }

    @Test
    public void UserRepository_FindById_ReturnUser(){
        User user = User.builder()
                .firstName("Steven")
                .lastName("Parra")
                .build();


        userRepository.save(user) ;

        User userList  = userRepository.findById(user.getId()).get();

        Assertions.assertThat(userList).isNotNull();
    }

    // Custom Query Method
    @Test
    public void UserRepository_FindByType_ReturnUserNotNull(){
        User user = User.builder()
                .firstName("Steven")
                .lastName("Parra")
                .build();


        userRepository.save(user);

        User userList  = userRepository.findByFirstName(user.getFirstName()).get();

        Assertions.assertThat(userList).isNotNull();
    }

    @Test
    public void UserRepository_UpdatePokemon_ReturnUserNotNull(){
        User user = User.builder()
                .firstName("Steven")
                .lastName("Parra")
                .build();

        userRepository.save(user);

        User userSave = userRepository.findById(user.getId()).get();
        userSave.setFirstName("Brandon");
        userSave.setLastName("Jhon");

        User updateUser = userRepository.save(userSave);

        Assertions.assertThat(updateUser.getFirstName()).isNotNull();
        Assertions.assertThat(updateUser.getLastName()).isNotNull();
    }

    @Test
    public void UserRepository_UserDelete_ReturnUserIsEmpty(){
        // Arrange
        User user = User.builder()
                .firstName("Steven")
                .lastName("Parra")
                .build();

        userRepository.save(user);

        // Act
        userRepository.deleteById(user.getId());
        Optional<User> userReturn = userRepository.findById(user.getId());

        // Assert
        Assertions.assertThat(userReturn).isEmpty();
    }
}