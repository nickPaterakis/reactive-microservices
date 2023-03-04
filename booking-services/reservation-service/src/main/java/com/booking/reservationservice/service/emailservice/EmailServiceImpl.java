package com.booking.reservationservice.service.emailservice;

import com.booking.commondomain.dto.reservation.ReservationDetailsDto;
import com.booking.commondomain.dto.user.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Component
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender emailSender;

    private final SpringTemplateEngine thymeleafTemplateEngine;

    @Autowired
    public EmailServiceImpl(JavaMailSender emailSender, @Qualifier("templateEngine") SpringTemplateEngine thymeleafTemplateEngine) {
        this.emailSender = emailSender;
        this.thymeleafTemplateEngine = thymeleafTemplateEngine;
    }

    @Override
    public void sendReservationDetailsToOwner(ReservationDetailsDto reservationDetailsDto, UserDto customer) throws MessagingException {
        Context ctx = getContext(reservationDetailsDto, customer);

        String htmlBody = thymeleafTemplateEngine.process("email-new-reservation.html", ctx);

        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

        helper.setTo(reservationDetailsDto.getUserDto().getEmail());
        helper.setSubject("New Reservation at " + reservationDetailsDto.getPropertyReservationDataDto().getTitle());
        helper.setText(htmlBody, true);

        emailSender.send(message);
    }

    @Override
    public void sendCancellationMessage(ReservationDetailsDto reservationDetailsDto, UserDto customer) throws MessagingException {
        Context ctx = getContext(reservationDetailsDto, customer);

        String htmlBody = thymeleafTemplateEngine.process("email-cancellation.html", ctx);

        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

        helper.setTo(reservationDetailsDto.getUserDto().getEmail());
        helper.setSubject("Cancellation from " + customer.getFirstName() + " " + customer.getLastName());
        helper.setText(htmlBody, true);

        emailSender.send(message);
    }

    Context getContext(ReservationDetailsDto reservationDetailsDto, UserDto customer) {
        Context ctx = new Context();

        ctx.setVariable("title", reservationDetailsDto.getPropertyReservationDataDto().getTitle());
        ctx.setVariable("firstName", customer.getFirstName());
        ctx.setVariable("lastName", customer.getLastName());
        ctx.setVariable("email", customer.getEmail());
        ctx.setVariable("checkIn", reservationDetailsDto.getCheckIn());
        ctx.setVariable("checkOut", reservationDetailsDto.getCheckOut());
        ctx.setVariable("price", reservationDetailsDto.getPrice());
        ctx.setVariable("propertyType",reservationDetailsDto.getPropertyReservationDataDto().getPropertyType());
        ctx.setVariable("location", reservationDetailsDto.getPropertyReservationDataDto().getLocation());
        ctx.setVariable("pricePerNight", reservationDetailsDto.getPropertyReservationDataDto().getPricePerNight());

        return ctx;
    }
}
