package com.ubiqube.etsi.mano.jpa;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import com.ubiqube.etsi.mano.wf.dao.Task;

public interface TaskRepository extends CrudRepository<Task, UUID> {

}
