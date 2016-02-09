package com.example;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Yao on 1/2/2016.
 */
@Repository
public interface TaskRepository extends CrudRepository<Task, Long> {

}

