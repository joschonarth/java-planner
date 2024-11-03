package com.joschonarth.planner.trip;

import com.joschonarth.planner.exception.InvalidDateException;
import com.joschonarth.planner.participant.ParticipantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.UUID;

@Service
public class TripService {

    @Autowired
    private TripRepository tripRepository;

    @Autowired
    private ParticipantService participantService;

    public TripCreateResponse createTrip(TripRequestPayload payload) {
        LocalDateTime startsAt = LocalDateTime.parse(payload.starts_at(), DateTimeFormatter.ISO_DATE_TIME);
        LocalDateTime endsAt = LocalDateTime.parse(payload.ends_at(), DateTimeFormatter.ISO_DATE_TIME);

        validateDates(startsAt, endsAt);

        Trip newTrip = new Trip(payload);
        tripRepository.save(newTrip);

        participantService.registerParticipantsToEvent(payload.emails_to_invite(), newTrip);

        return new TripCreateResponse(newTrip.getId());
    }

    public Optional<Trip> getTripDetails(UUID id) {
        return tripRepository.findById(id);
    }

    public Optional<Trip> updateTrip(UUID id, TripRequestPayload payload) {
        Optional<Trip> trip = tripRepository.findById(id);

        if (trip.isPresent()) {
            LocalDateTime startsAt = LocalDateTime.parse(payload.starts_at(), DateTimeFormatter.ISO_DATE_TIME);
            LocalDateTime endsAt = LocalDateTime.parse(payload.ends_at(), DateTimeFormatter.ISO_DATE_TIME);

            validateDates(startsAt, endsAt);

            Trip rawTrip = trip.get();
            rawTrip.setEndsAt(endsAt);
            rawTrip.setStartsAt(startsAt);
            rawTrip.setDestination(payload.destination());

            tripRepository.save(rawTrip);
            return Optional.of(rawTrip);
        }
        return Optional.empty();
    }

    public Optional<Trip> confirmTrip(UUID id) {
        Optional<Trip> trip = tripRepository.findById(id);

        if (trip.isPresent()) {
            Trip rawTrip = trip.get();
            rawTrip.setIsConfirmed(true);
            tripRepository.save(rawTrip);

            participantService.triggerConfirmationEmailToParticipants(id);
            return Optional.of(rawTrip);
        }
        return Optional.empty();
    }

    private void validateDates(LocalDateTime startsAt, LocalDateTime endsAt) {
        if (startsAt.isAfter(endsAt)) {
            throw new InvalidDateException("The start date must be earlier than the end date.");
        }
    }
}

