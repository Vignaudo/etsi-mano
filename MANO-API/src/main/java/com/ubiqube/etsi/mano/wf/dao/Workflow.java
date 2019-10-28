package com.ubiqube.etsi.mano.wf.dao;

import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
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

	@OneToMany(cascade = CascadeType.ALL)
	private Set<Connection> edges;

	@OneToMany(cascade = CascadeType.ALL)
	private Set<Task> tasks;

	@Enumerated(EnumType.STRING)
	private LcmOperationStateType workflowStatus;

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

	public LcmOperationStateType getWorkflowStatus() {
		return workflowStatus;
	}

	public void setWorkflowStatus(final LcmOperationStateType workflowStatus) {
		this.workflowStatus = workflowStatus;
	}

	public ProblemDetails getProblemDetails() {
		return problemDetails;
	}

	public void setProblemDetails(final ProblemDetails problemDetails) {
		this.problemDetails = problemDetails;
	}

}
