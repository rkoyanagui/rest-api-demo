package com.rkoyanagui.rest_api_demo.daos;

import com.rkoyanagui.rest_api_demo.entities.User;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long>
{
  List<User> findByFirstNameContainsAndLastNameContainsAllIgnoreCaseOrderByLastNameAsc(
      String firstName,
      String lastName
  );
}
