package com.uberrent.core.repository;

import com.uberrent.core.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    List<User> findAll();

    @Query("select a from User a where a.username = ?1")
    Optional<User> findByUsername (String username);

    @Query("select a from User a where a.username =?1 OR a.email=?1")
    Optional<User> findUsernameOrEmail(String usernameOrEmail);

   // @Query("select * from User where username =?a or a.email=?b")
}
