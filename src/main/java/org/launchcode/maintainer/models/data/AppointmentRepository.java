package org.launchcode.maintainer.models.data;

import org.launchcode.maintainer.models.Appointment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface AppointmentRepository extends CrudRepository<Appointment, Integer> {
}
