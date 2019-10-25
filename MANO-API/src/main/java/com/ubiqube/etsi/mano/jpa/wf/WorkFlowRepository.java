package com.ubiqube.etsi.mano.jpa.wf;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import com.ubiqube.etsi.mano.dao.wf.Workflow;

public interface WorkFlowRepository extends CrudRepository<Workflow, UUID> {

}
