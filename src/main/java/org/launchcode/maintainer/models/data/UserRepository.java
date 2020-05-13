package org.launchcode.maintainer.models.data;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.launchcode.maintainer.models.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
}
