package com.generoso.microservices.endpoint.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.generoso.microservices.endpoint.model.Course;
import com.generoso.microservices.endpoint.repository.CourseRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Mauricio Generoso
 */
@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CourseService {

  private final CourseRepository repository;

  public Iterable<Course> list(Pageable pageable) {
    log.info("Listing all courses");
    return repository.findAll(pageable);
  }
}
