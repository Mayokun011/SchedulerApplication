package com.amex.Scheduler.Application.ScheduledJobs;


import com.amex.Scheduler.Application.Email.EmailQueue;
import com.amex.Scheduler.Application.Repository.UserRepository;
import com.amex.Scheduler.Application.Services.UserServices;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
public class Birthday {

    private final UserServices userServices;
    private final UserRepository userRepository;
    private final EmailQueue email;

    @Scheduled(cron = "0 0 11 * * *")
    public void sendBirthdayMailJob(){
        Flux.fromIterable(userRepository.getUserByBirthday())
                .doOnNext(email::birthdayMail)
                .subscribe();
    }
}
