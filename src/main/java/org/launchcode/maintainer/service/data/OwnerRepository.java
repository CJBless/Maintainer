package org.launchcode.maintainer.service.data;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.launchcode.maintainer.models.Owner;

@Repository
//@Transactional
public interface OwnerRepository extends CrudRepository<Owner, Integer> {
}
