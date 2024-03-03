package com.amex.Scheduler.Application.Services;


import com.amex.Scheduler.Application.Email.EmailRequest;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class EmailService {

    @Autowired
    private SendGrid sendGrid;

    public Response sendemail(EmailRequest emailrequest){
      Mail mail=new Mail(new Email("amosmayokun@gmail.com"),
              emailrequest.getSubject(),new
              Email(emailrequest.getTo()), new Content("text/html", emailrequest.getBody()));
    mail.setReplyTo(new Email("amosmayokun@gmail.com"));
    Request request = new Request();

    com.sendgrid.Response response = null;

    try

    {
        request.setMethod(Method.POST);
        request.setEndpoint("mail/send");
        request.setBody(mail.build());
        response = this.sendGrid.api(request);
    } catch(IOException e)

    {
        System.out.println(e.getMessage());
    }

    return response;
}

}