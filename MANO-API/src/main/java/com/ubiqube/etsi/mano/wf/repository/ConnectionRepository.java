package com.ubiqube.etsi.mano.wf.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import com.ubiqube.etsi.mano.wf.dao.Connection;
import com.ubiqube.etsi.mano.wf.dao.Workflow;

public interface ConnectionRepository extends CrudRepository<Connection, UUID> {

	List<Connection> findByWorkflowAndSource(Workflow w2, UUID object);

}
