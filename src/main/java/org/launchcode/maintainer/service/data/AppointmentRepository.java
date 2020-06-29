package org.launchcode.maintainer.service.data;

import org.launchcode.maintainer.models.Appointment;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AppointmentRepository extends CrudRepository<Appointment, Integer> {

    @Query("select b from Appointment b where b.start >= ?1 and b.end <= ?2")
    public List<Appointment> findAllByStartGreaterThanEqualAndEndLessThanEqual(LocalDateTime start, LocalDateTime end);


}
