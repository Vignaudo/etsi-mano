package com.ubiqube.etsi.mano.dao.wf;

import java.util.Set;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Connection {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	@OneToMany
	private Set<Task> source;
	@OneToMany
	private Set<Task> target;
	@ManyToOne
	private Workflow workflow;

	public UUID getId() {
		return id;
	}

	public void setId(final UUID id) {
		this.id = id;
	}

	public Set<Task> getSource() {
		return source;
	}

	public void setSource(final Set<Task> source) {
		this.source = source;
	}

	public Set<Task> getTarget() {
		return target;
	}

	public void setTarget(final Set<Task> target) {
		this.target = target;
	}

	public Workflow getWorkflow() {
		return workflow;
	}

	public void setWorkflow(final Workflow workflow) {
		this.workflow = workflow;
	}

}
