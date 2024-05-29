package com.satish.lil.learningspring.web;

import com.satish.lil.learningspring.business.GuestService;
import com.satish.lil.learningspring.business.ReservationService;
import com.satish.lil.learningspring.business.RoomReservation;
import com.satish.lil.learningspring.data.Guest;
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
@RequestMapping("/guests")
public class GuestController {
    private final GuestService guestService;

    public GuestController(GuestService guestService) {
      this.guestService = guestService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String getAllGuests(Model model) {
        List<Guest> guests = guestService.getAllGuests();
        model.addAttribute("guests",guests);
        return "guests"; // template name without the file extension.
    }
}

