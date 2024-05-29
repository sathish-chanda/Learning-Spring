package com.satish.lil.learningspring.business;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.satish.lil.learningspring.data.Guest;
import com.satish.lil.learningspring.data.GuestRepository;
import com.satish.lil.learningspring.data.Reservation;
import com.satish.lil.learningspring.data.ReservationRepository;
import com.satish.lil.learningspring.data.Room;
import com.satish.lil.learningspring.data.RoomRepository;
import org.springframework.stereotype.Service;

@Service
public class ReservationService {
    //    @Autowired // Issue: Can't perform mockTests with this approach.
    private final RoomRepository roomRepository; // Don't want to change these dependencies as All these classes are singleton in IoC container.
//    @Autowired // Issue: Can't perform mockTests with this approach.
    private final GuestRepository guestRepository; // Don't want to change these dependencies as All these classes are singleton in IoC container.
//    @Autowired // Issue: Can't perform mockTests with this approach.
    private final ReservationRepository reservationRepository; // Don't want to change these dependencies as All these classes are singleton in IoC container.

//    @Autowired // Class will fail if we remove the Spring from the picture.
//    public void setRoomRepository(RoomRepository roomRepository) {
//        this.roomRepository = roomRepository;
//    }

//    @Autowired // Class will fail if we remove the Spring from the picture.
//    public void setGuestRepository(GuestRepository guestRepository) {
//        this.guestRepository = guestRepository;
//    }

//    @Autowired // Class will fail if we remove the Spring from the picture.
//    public void setReservationRepository(ReservationRepository reservationRepository) {
//        this.reservationRepository = reservationRepository;
//    }

// Java will create the objects with only available constructor. The below one will become a default constructor. Spring IoC will create this object with default constructor. If we have more than one constructor then do the AutoWired for the spring.
    public ReservationService(RoomRepository roomRepository, GuestRepository guestRepository, ReservationRepository reservationRepository) {
        this.roomRepository = roomRepository;
        this.guestRepository = guestRepository;
        this.reservationRepository = reservationRepository;
    }

    // Java will create the objects with only available constructor. The below one will become a default constructor. Spring IoC will create this object with default constructor. If we have more than one constructor then do the AutoWired for the spring.
//    public ReservationService(RoomRepository roomRepository, GuestRepository guestRepository) {
//        this.roomRepository = roomRepository;
//        this.guestRepository = guestRepository;
//        this.reservationRepository = null;
//    }

    public List<RoomReservation> getRoomReservationsForDate(Date date) {
        Iterable<Room> rooms = this.roomRepository.findAll();
        Map<Long, RoomReservation> roomReservationMap = new HashMap();
        rooms.forEach(room -> {
            RoomReservation roomReservation = new RoomReservation();
            roomReservation.setRoomId(room.getId());
            roomReservation.setRoomName(room.getName());
            roomReservation.setRoomNumber(room.getRoomNumber());
            roomReservationMap.put(room.getId(), roomReservation);
        });
        Iterable<Reservation> reservations = this.reservationRepository.findReservationByReservationDate(new java.sql.Date(date.getTime()));
        reservations.forEach(reservation -> {
            RoomReservation roomReservation = roomReservationMap.get(reservation.getRoomId());
            roomReservation.setDate(date);
            Guest guest = this.guestRepository.findById(reservation.getGuestId()).get();
            roomReservation.setFirstName(guest.getFirstName());
            roomReservation.setLastName(guest.getLastName());
            roomReservation.setGuestId(guest.getGuestId());
        });
        List<RoomReservation> roomReservations = new ArrayList<>();
        for (Long id : roomReservationMap.keySet()) {
            roomReservations.add(roomReservationMap.get(id));
        }
        roomReservations.sort(new Comparator<RoomReservation>() {
            @Override
            public int compare(RoomReservation o1, RoomReservation o2) {
                if (o1.getRoomName().equals(o2.getRoomName())) {
                    return o1.getRoomNumber().compareTo(o2.getRoomNumber());
                }
                return o1.getRoomName().compareTo(o2.getRoomName());
            }
        });
        return roomReservations;
    }
}