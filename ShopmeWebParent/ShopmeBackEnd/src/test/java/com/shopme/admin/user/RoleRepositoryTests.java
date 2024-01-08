package com.shopme.admin.user;

import com.shopme.common.entity.Role;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) //Because we need to test against real database and spring tests against inMemory database by default.
@Rollback(value = false) //transactions are not rolled back after each test, meaning that any changes made to the database during the test will persist. This can be useful when you want to inspect the database state after a test or when testing scenarios where data is committed to the database.
public class RoleRepositoryTests {

    @Autowired
    RoleRepository roleRepository;

    @Test
    public void testCreateFirstRole(){
        Role roleAdmin = new Role("Admin", "manage everything");
        Role savedRole = roleRepository.save(roleAdmin);
        assertThat(savedRole.getId()).isGreaterThan(0);
    }

    @Test
    public void testCreateRestRoles(){
        Role roleSalesperson = new Role("SalesPerson", "manage product price, customers, shipping, orders and sales report");
        Role roleEditor = new Role("Editor", "manage categories, brands, products, articles and menus");
        Role roleShipper = new Role("Shipper", "view products, view orders and update order status");
        Role roleAssisstant = new Role("Assisstant", "manage questions and reviews");

        roleRepository.saveAll(List.of(roleSalesperson, roleEditor, roleShipper, roleAssisstant));
    }
}
