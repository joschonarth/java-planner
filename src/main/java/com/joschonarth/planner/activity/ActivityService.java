package com.joschonarth.planner.activity;

import com.joschonarth.planner.exception.InvalidDateException;
import com.joschonarth.planner.trip.Trip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class ActivityService {

    @Autowired
    private ActivityRepository repository;

    public ActivityResponse registerActivity(ActivityRequestPayload payload, Trip trip) {
        validateActivityDate(LocalDateTime.parse(payload.occurs_at()), trip);

        Activity newActivity = new Activity(payload.title(), payload.occurs_at(), trip);
        this.repository.save(newActivity);

        return new ActivityResponse(newActivity.getId());
    }

    public List<ActivityData> getAllActivitiesFromTrip(UUID tripId) {
        return this.repository.findByTripId(tripId).stream().map(activity -> new ActivityData(activity.getId(), activity.getTitle(), activity.getOccursAt())).toList();
    }

    private void validateActivityDate(LocalDateTime occursAt, Trip trip) {
        if (occursAt.isBefore(trip.getStartsAt()) || occursAt.isAfter(trip.getEndsAt())) {
            throw new InvalidDateException("The activity date must be within the trip period.");
        }
    }
}
