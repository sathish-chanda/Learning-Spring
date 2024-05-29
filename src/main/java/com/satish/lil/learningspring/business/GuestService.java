package com.satish.lil.learningspring.business;

import com.satish.lil.learningspring.data.Guest;
import com.satish.lil.learningspring.data.GuestRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class GuestService {
    private final GuestRepository guestRepository;

    public GuestService(GuestRepository guestRepository) {
        this.guestRepository = guestRepository;
    }

    public List<Guest> getAllGuests() {
        return guestRepository.findAll();
    }
}
