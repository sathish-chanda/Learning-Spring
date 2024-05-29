package com.satish.lil.learningspring.util;

import java.util.Date;
import java.util.List;

import com.satish.lil.learningspring.business.ReservationService;
import com.satish.lil.learningspring.business.RoomReservation;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class AppStartupEvent implements ApplicationListener<ApplicationReadyEvent> {
//    @Autowired // Issue: Can't perform mockTests with this approach.
    private final ReservationService reservationService;
//    @Autowired // Issue: Can't perform mockTests with this approach.
    private final DateUtils dateUtils;

    public AppStartupEvent(ReservationService reservationService, DateUtils dateUtils) {
        this.reservationService = reservationService;
        this.dateUtils = dateUtils;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        Date date = this.dateUtils.createDateFromDateString("2022-01-01");
        List<RoomReservation> reservations = this.reservationService.getRoomReservationsForDate(date);
        reservations.forEach(System.out::println);
    }
}
