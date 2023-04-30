package com.booking.reservationservice.service.emailservice;

import com.booking.commondomain.dto.reservation.ReservationDetailsDto;
import com.booking.commondomain.dto.user.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.MessagingException;

@Component
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final EmailServiceHelper emailServiceHelper;
    private final SpringTemplateEngine thymeleafTemplateEngine;

    @Override
    public void sendReservationDetailsToOwner(ReservationDetailsDto reservationDetailsDto, UserDto customer) throws MessagingException {
        Context ctx = emailServiceHelper.getContext(reservationDetailsDto, customer);

        String htmlBody = thymeleafTemplateEngine.process("email-new-reservation.html", ctx);
        String subject = "New Reservation at " + reservationDetailsDto.getPropertyReservationDataDto().getTitle();

        emailServiceHelper.sendEmail(reservationDetailsDto, subject, htmlBody);
    }

    @Override
    public void sendCancellationMessage(ReservationDetailsDto reservationDetailsDto, UserDto customer) throws MessagingException {
        Context ctx = emailServiceHelper.getContext(reservationDetailsDto, customer);

        String htmlBody = thymeleafTemplateEngine.process("email-cancellation.html", ctx);
        String subject = "Cancellation from " + customer.getFirstName() + " " + customer.getLastName();

        emailServiceHelper.sendEmail(reservationDetailsDto, subject, htmlBody);
    }
}
