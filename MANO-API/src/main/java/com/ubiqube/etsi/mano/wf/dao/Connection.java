package com.ubiqube.etsi.mano.wf.dao;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Connection {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	@OneToOne
	private Task source;
	@OneToOne
	private Task target;
	@OneToOne
	private Workflow workflow;

	public UUID getId() {
		return id;
	}

	public void setId(final UUID id) {
		this.id = id;
	}

	public Task getSource() {
		return source;
	}

	public void setSource(final Task source) {
		this.source = source;
	}

	public Task getTarget() {
		return target;
	}

	public void setTarget(final Task target) {
		this.target = target;
	}

	public Workflow getWorkflow() {
		return workflow;
	}

	public void setWorkflow(final Workflow workflow) {
		this.workflow = workflow;
	}

}
