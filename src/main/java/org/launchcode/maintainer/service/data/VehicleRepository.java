package org.launchcode.maintainer.service.data;

import org.launchcode.maintainer.models.Vehicle;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface VehicleRepository extends CrudRepository<Vehicle, Integer> {

//    public Vehicle findByName(String name);

}
