package com.amex.Scheduler.Application.Services.Impl;

import com.amex.Scheduler.Application.Entity.User;
import com.amex.Scheduler.Application.Repository.UserRepository;
import com.amex.Scheduler.Application.Services.UserServices;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Service
@AllArgsConstructor
public class UserServicesImpl implements UserServices {

    private UserRepository userRepository;

    private int getRemainingBirthDay(User user){
        LocalDate currentDate = LocalDate.now();
        long age = ChronoUnit.YEARS.between(user.getDob(), currentDate);
        LocalDate nextBirthday = user.getDob().plusYears(age);
        if(nextBirthday.isBefore(currentDate)){
            nextBirthday = user.getDob().plusYears(age + 1);
        }
        long daysUntilNextBirthday = ChronoUnit.DAYS.between(currentDate, nextBirthday);
        int days = Math.toIntExact(daysUntilNextBirthday);
        return days;
    }

    public boolean isBirthday(User user){
        return getRemainingBirthDay(user) == 0;
    }
}

