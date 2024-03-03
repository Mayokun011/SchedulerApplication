package com.amex.Scheduler.Application.Controller;


import com.amex.Scheduler.Application.Services.UserServices;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class UserController {

    private UserServices userService;
}
