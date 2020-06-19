package org.launchcode.maintainer.models.data;

import org.launchcode.maintainer.models.AppointmentType;
import org.springframework.data.repository.CrudRepository;

public interface AppointmentTypeRepository extends CrudRepository<AppointmentType, Integer> {
}
