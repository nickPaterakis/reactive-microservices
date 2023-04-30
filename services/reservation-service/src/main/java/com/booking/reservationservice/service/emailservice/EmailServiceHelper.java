package com.booking.reservationservice.service.emailservice;

import com.booking.commondomain.dto.reservation.ReservationDetailsDto;
import com.booking.commondomain.dto.user.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Component
@RequiredArgsConstructor
public class EmailServiceHelper {

    private final JavaMailSender emailSender;

    public void sendEmail(ReservationDetailsDto reservationDetailsDto, String subject, String htmlBody) throws MessagingException {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

        helper.setTo(reservationDetailsDto.getUserDto().getEmail());
        helper.setSubject(subject);
        helper.setText(htmlBody, true);

        emailSender.send(message);
    }

    public Context getContext(ReservationDetailsDto reservationDetailsDto, UserDto customer) {
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
