package com.shopme.admin.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserRestController {

    @Autowired
    UserService userService;

    public String checkDuplicateEmail(@Param("email") String email){
        return userService.isEmailUnique(email) ? "OK" : "Duplicate";
    }

}
