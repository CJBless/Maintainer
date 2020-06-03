package org.launchcode.maintainer.models.data;

import org.launchcode.maintainer.models.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.awt.print.Pageable;
import java.time.LocalDateTime;
import java.util.List;

@Repository
//@Transactional
public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {

}
