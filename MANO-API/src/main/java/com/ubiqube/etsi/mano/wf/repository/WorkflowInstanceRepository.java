package com.ubiqube.etsi.mano.wf.repository;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ubiqube.etsi.mano.wf.dao.WorkflowInstance;

@Repository
public interface WorkflowInstanceRepository extends CrudRepository<WorkflowInstance, UUID> {

}
