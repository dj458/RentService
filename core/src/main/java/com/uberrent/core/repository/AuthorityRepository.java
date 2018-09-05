package com.uberrent.core.repository;

import com.uberrent.core.domain.Authority;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorityRepository extends CrudRepository<Authority,Long> {

    @Query("select a from Authority a where user_id= ?1")
    List<Authority> findAuthoritiesByUserId(Long userId);

}
