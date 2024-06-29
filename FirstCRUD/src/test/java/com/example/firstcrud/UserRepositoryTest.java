package com.example.firstcrud;

import com.example.firstcrud.user.User;
import com.example.firstcrud.user.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class UserRepositoryTest {
    @Autowired private UserRepository repo;

    @Test
    public void testAddNew(){
        User user = new User();
        user.setFirstName("ziyad mehdi");
        user.setLastName("Sabiir");
        user.setEmail("Mziyadsabir@gmail.com");
        user.setPassword("123456");
        User saved = repo.save(user);
        Assertions.assertThat(saved).isNotNull();
        Assertions.assertThat(saved.getId()).isGreaterThan(0);


    }

}
