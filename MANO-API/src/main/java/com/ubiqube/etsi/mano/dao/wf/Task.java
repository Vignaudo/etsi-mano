package com.ubiqube.etsi.mano.dao.wf;

import java.util.UUID;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.ubiqube.etsi.mano.model.nslcm.LcmOperationStateType;

@Entity
public class Task {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	private String name;
	private String externalJobId;

	@Enumerated(EnumType.STRING)
	private LcmOperationStateType status;

	@OneToOne
	private Workflow workflow;

	private String clazz;

	@Embedded
	private Configuration configuration;

	public Task() {
		// Nothing.
	}

	public Task(final String _name) {
		name = _name;
	}

	public UUID getId() {
		return id;
	}

	public void setId(final UUID id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public String getExternalJobId() {
		return externalJobId;
	}

	public void setExternalJobId(final String externalJobId) {
		this.externalJobId = externalJobId;
	}

	public LcmOperationStateType getStatus() {
		return status;
	}

	public void setStatus(final LcmOperationStateType status) {
		this.status = status;
	}

	public Workflow getWorkflow() {
		return workflow;
	}

	public void setWorkflow(final Workflow workflow) {
		this.workflow = workflow;
	}

	public String getClazz() {
		return clazz;
	}

	public void setClazz(final String clazz) {
		this.clazz = clazz;
	}

	public Configuration getConfiguration() {
		return configuration;
	}

	public void setConfiguration(final Configuration configuration) {
		this.configuration = configuration;
	}

}
