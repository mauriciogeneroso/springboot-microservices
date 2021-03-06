package com.generoso.microservices.core.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import com.generoso.microservices.core.model.Course;

/**
 * @author Mauricio Generoso
 */
@Repository
public interface CourseRepository extends PagingAndSortingRepository<Course, Long> {

}
