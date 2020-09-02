package com.generoso.microservices.core.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import com.generoso.microservices.core.model.ApplicationUser;

/**
 * @author Mauricio Generoso
 */
@Repository
public interface ApplicationUserRepository extends PagingAndSortingRepository<ApplicationUser, Long> {

  ApplicationUser findByUsername(String username);
}
