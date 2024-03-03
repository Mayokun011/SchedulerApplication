package com.amex.Scheduler.Application.Email;


import com.amex.Scheduler.Application.Entity.User;
import com.amex.Scheduler.Application.Services.EmailService;
import com.sendgrid.Response;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class EmailQueue {

    private EmailService emailservice;

    public void birthdayMail(User user){
        log.info("Send happy birthday mail to ->{}", user);
        Response response = happyBirthdayMail(user);
        if(response.getStatusCode() == 200 || response.getStatusCode() == 202)
            log.info("Happy birthday email was sent to -> " + user.getEmail());

    }

    private Response happyBirthdayMail(User user){
        StringBuilder message = new StringBuilder();
        message.append("<h4>").append("Hurray!!! Happy Birthday to You ")
                .append(user.getUserName())
                .append("Many More Years to Come");
        message.append("<h5>").append("May Lines Begin to Fall for You in Plesant Places as from ")
                .append(user.getDob()).append(". Enjoy your Day!!!");
        EmailRequest emailRequest = new EmailRequest(user.getEmail(), "Hurray!!!", message.toString());
        Response response = emailservice.sendemail(emailRequest);
        return response;
    }
}
