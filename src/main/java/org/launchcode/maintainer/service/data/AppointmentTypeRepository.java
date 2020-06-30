package org.launchcode.maintainer.service.data;

import org.launchcode.maintainer.models.AppointmentType;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface AppointmentTypeRepository extends CrudRepository<AppointmentType, Integer>,
                                                    PagingAndSortingRepository<AppointmentType, Integer> {
    List<AppointmentType> findAll(Sort sort);
}
