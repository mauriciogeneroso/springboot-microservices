package com.generoso.microservices.core.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import com.generoso.microservices.core.model.Course;

/**
 * @author Mauricio Generoso
 */
public interface CourseRepository extends PagingAndSortingRepository<Course, Long> {

}
