package com.satish.lil.learningspring.webservice;

import com.satish.lil.learningspring.business.GuestService;
import com.satish.lil.learningspring.business.ReservationService;
import com.satish.lil.learningspring.business.RoomReservation;
import com.satish.lil.learningspring.business.RoomService;
import com.satish.lil.learningspring.data.Guest;
import com.satish.lil.learningspring.data.Room;
import com.satish.lil.learningspring.util.DateUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Date;

@RestController
@RequestMapping("/api")
public class WebServiceController {
    private final DateUtils dateUtils;
    private final ReservationService reservationService;

    private final GuestService guestService;

    private final RoomService roomService;

    public WebServiceController(DateUtils dateUtils, ReservationService reservationService, GuestService guestService, RoomService roomService) {
        this.dateUtils = dateUtils;
        this.reservationService = reservationService;
        this.guestService = guestService;
        this.roomService = roomService;
    }

    // Testing::: Run the curl in GIT BASH
    // curl GET http://localhost:8080/reservations
    @RequestMapping(path="/reservations",method = RequestMethod.GET)
    public List<RoomReservation> getReservations(@RequestParam(value="date",required = false) String dateString) {
        Date date = dateUtils.createDateFromDateString(dateString);
        return reservationService.getRoomReservationsForDate(date);
    }

    // Testing::: Run the curl in GIT BASH
    // curl GET http://localhost:8080/guests
    @RequestMapping(path="/guests",method = RequestMethod.GET)
    public List<Guest> getHotelGuests() {
        return guestService.getAllGuests();
    }

    // Testing::: Run the curl in GIT BASH
    // curl -i -X POST -H 'Content-Type: application/json' -d '{"firstName": "Satish", "lastName": "Chanda"}' http://localhost:8080/api/guests
    @PostMapping(path="/guests")
    @ResponseStatus(HttpStatus.CREATED)
    public void addGuest(@RequestBody Guest guest) {
        guestService.addGuest(guest);
    }

    // Testing::: Run the curl in GIT BASH
    // curl GET http://localhost:8080/api/rooms
    @RequestMapping(path="/rooms",method = RequestMethod.GET)
    public List<Room> getAllRooms() {
        return roomService.getAllRooms();
    }
}
