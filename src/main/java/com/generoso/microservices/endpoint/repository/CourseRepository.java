package com.generoso.microservices.endpoint.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import com.generoso.microservices.endpoint.model.Course;

/**
 * @author Mauricio Generoso
 */
public interface CourseRepository extends PagingAndSortingRepository<Course, Long> {

}
