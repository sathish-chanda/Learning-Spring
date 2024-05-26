package com.satish.lil.learningspring.data;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name="RESERVATION")

public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="RESERVATION_ID")
    private long reservationId;
    @Column(name="ROOM_ID")
    private long roomId;
    @Column(name="GUEST_ID")
    private long guestId;
    @Column(name="RES_DATE")
    private Date reservationDate;

    public long getReservationId() {
        return reservationId;
    }

    public long getRoomId() {
        return roomId;
    }

    public long getGuestId() {
        return guestId;
    }

    public Date getReservationDate() {
        return reservationDate;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + reservationId +
                ", roomId=" + roomId +
                ", guestId=" + guestId +
                ", resDate='" + reservationDate + '\'' +
                '}';
    }
}
