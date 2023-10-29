package com.cst438.domain;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Query;


public interface UserRepository extends CrudRepository <User, Integer> {
  @Query("select a from User a where a.email order by a.id")
	User findByEmail(@Param("email") String email);
}
