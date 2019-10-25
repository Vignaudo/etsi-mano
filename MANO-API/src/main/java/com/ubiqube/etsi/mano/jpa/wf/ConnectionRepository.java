package com.ubiqube.etsi.mano.jpa.wf;

import java.util.List;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import com.ubiqube.etsi.mano.dao.wf.Connection;
import com.ubiqube.etsi.mano.dao.wf.Workflow;

public interface ConnectionRepository extends CrudRepository<Connection, UUID> {

	List<Connection> findByWorkflowAndSource(Workflow w2, UUID object);

}
