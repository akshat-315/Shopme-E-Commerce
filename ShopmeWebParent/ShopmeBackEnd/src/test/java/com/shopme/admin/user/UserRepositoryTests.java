package com.shopme.admin.user;

import com.shopme.common.entity.Role;
import com.shopme.common.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class UserRepositoryTests {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    public void testCreateUserWithSingleRole(){
        Role roleAdmin = testEntityManager.find(Role.class, 1);
        User user = new User("akshat315.sh@gmail.com", "root", "akshat", "sharma");
        user.addRole(roleAdmin);

        User savedUser = userRepository.save(user);
        assertThat(savedUser.getId()).isGreaterThan(0);
    }

    @Test
    public void testCreateUserWithTwoRoles(){
        User userVivek = new User("vivekgarg@gmail.com", "12345", "Vivek", "Garg");
        Role roleEditor = new Role(3);
        Role roleAssisstant = new Role(5);

        userVivek.addRole(roleEditor);
        userVivek.addRole(roleAssisstant);

        User savedUser = userRepository.save(userVivek);

        assertThat(savedUser.getId()).isGreaterThan(0);
    }

    @Test
    public void testListAllUsers(){
        Iterable<User> users = userRepository.findAll();
        users.forEach(user -> System.out.println(user));
    }

    @Test
    public void testGetUserById(){
        User user = userRepository.findById(2).get();
        System.out.println(user);
        assertThat(user).isNotNull();
    }

    @Test
    public void testUpdateUserDetails(){
        User user = userRepository.findById(1).get();
        user.setEnabled(true);
        userRepository.save(user);
    }

    @Test
    public void testUpdateUserRoles(){
        User user = userRepository.findById(2).get();
        Role roleEditor = new Role(3);
        Role roleSalesPerson = new Role(2);

        user.getRoles().remove(roleEditor);
        user.addRole(roleSalesPerson);

        userRepository.save(user);
    }

    @Test
    public void testDeleteUser(){
        userRepository.deleteById(1);
    }

    @Test
    public void testGetUserByEmail(){
        String email = "akshman315@Gmail.com";
        User user = userRepository.getUserByEmail(email);
        assertThat(user).isNotNull();
    }

    @Test
    public void testCountById(){
        Integer id = 111;
        Long countById = userRepository.countById(id);

        assertThat(countById).isNotNull().isGreaterThan(0);
    }

    @Test
    public void testDisableUser(){
        Integer id = 2;
        userRepository.updateEnabledStatus(id, false);
    }

}
