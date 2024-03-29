package ru.practicum.request;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.practicum.event.Event;

import java.util.List;

public interface ParticipationRequestRepository extends JpaRepository<ParticipationRequest, Long> {

    ParticipationRequest findByRequesterIdAndEventId(Long userId, Long eventId);

    List<ParticipationRequest> findByRequesterId(Long userId);

    Integer countByEventIdAndStatus(Long eventId, ParticipationRequestStatus status);

    List<ParticipationRequest> findByEventIn(List<Event> userEvents);

    @Modifying
    @Query("update ParticipationRequest r set r.status = :newStatus where (r.event = :event and r.status = :searchStatus)")
    void updateRequestStatusByEventIdAndStatus(Event event,
                                               @Param(value = "searchStatus") ParticipationRequestStatus searchStatus,
                                               @Param(value = "newStatus") ParticipationRequestStatus newStatus);
}