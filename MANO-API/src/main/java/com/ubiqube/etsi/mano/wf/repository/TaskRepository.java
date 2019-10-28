package com.ubiqube.etsi.mano.wf.repository;

import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.ubiqube.etsi.mano.repository.CrudRepository;
import com.ubiqube.etsi.mano.wf.dao.Task;

@Repository
public interface TaskRepository extends CrudRepository<Task> {

	Task findById(UUID uuid);
}
