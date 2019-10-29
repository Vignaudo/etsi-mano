package com.ubiqube.etsi.mano.wf.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ubiqube.etsi.mano.wf.dao.ConnectionInstance;

@Repository
public interface ConnectionInstanceRepository extends CrudRepository<ConnectionInstance, UUID> {

	List<ConnectionInstance> findByTarget(UUID id);

	List<ConnectionInstance> findBySource(UUID id);
}
