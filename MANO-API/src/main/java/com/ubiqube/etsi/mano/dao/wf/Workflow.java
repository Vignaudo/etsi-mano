package com.ubiqube.etsi.mano.dao.wf;

import java.util.Set;
import java.util.UUID;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.ubiqube.etsi.mano.model.ProblemDetails;
import com.ubiqube.etsi.mano.model.nslcm.LcmOperationStateType;

@Entity
public class Workflow {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

	@OneToMany
	private Set<Connection> edges;
	@OneToMany
	private Set<Task> tasks;
	@Enumerated(EnumType.STRING)
	private LcmOperationStateType status;
	@Embedded
	private ProblemDetails problemDetails;

	public UUID getId() {
		return id;
	}

	public void setId(final UUID id) {
		this.id = id;
	}

	public Set<Connection> getEdges() {
		return edges;
	}

	public void setEdges(final Set<Connection> edges) {
		this.edges = edges;
	}

	public Set<Task> getTasks() {
		return tasks;
	}

	public void setTasks(final Set<Task> tasks) {
		this.tasks = tasks;
	}

	public LcmOperationStateType getStatus() {
		return status;
	}

	public void setStatus(final LcmOperationStateType status) {
		this.status = status;
	}

	public ProblemDetails getProblemDetails() {
		return problemDetails;
	}

	public void setProblemDetails(final ProblemDetails problemDetails) {
		this.problemDetails = problemDetails;
	}

}
