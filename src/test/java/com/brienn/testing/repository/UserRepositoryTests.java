package com.brienn.testing.repository;

import com.brienn.testing.entity.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class UserRepositoryTests {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void UserRepository_SaveAll_ReturnSavedUser(){

        // Arrange
        User user = User.builder()
                .first_name("Breiner")
                .last_name("Parra")
                .build();

        // Act
        User saveUser = userRepository.save(user);

        System.out.println("data" + saveUser);

        // Assert
        Assertions.assertThat(saveUser).isNotNull();
        Assertions.assertThat(saveUser.getId()).isGreaterThan(0);
    }
}