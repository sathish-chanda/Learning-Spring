package com.satish.lil.learningspring.web;

import com.satish.lil.learningspring.business.ReservationService;
import com.satish.lil.learningspring.business.RoomReservation;
import com.satish.lil.learningspring.data.Reservation;
import com.satish.lil.learningspring.util.DateUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/reservations")
public class RoomReservationController {
    private final ReservationService reservationService;

    private final DateUtils dateUtils;

    public RoomReservationController(ReservationService reservationService, DateUtils dateUtils) {
        this.reservationService = reservationService;
        this.dateUtils = dateUtils;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String getReservations(@RequestParam(value="date",required=false) String dateString, Model model) {
        Date date = dateUtils.createDateFromDateString(dateString);
        List<RoomReservation> roomReservations = reservationService.getRoomReservationsForDate(date);
        model.addAttribute("roomReservations",roomReservations);
        return "roomres"; // template name without the file extension.
    }
}
