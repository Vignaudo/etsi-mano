package com.ubiqube.etsi.mano.wf.dao;

import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.ubiqube.etsi.mano.model.nslcm.LcmOperationStateType;

@Entity
public class WorkflowInstance {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

	@OneToOne(optional = false)
	private Workflow workflow;

	private Date startDate = new Date();

	@Enumerated(EnumType.STRING)
	private LcmOperationStateType status = LcmOperationStateType.STARTING;

	@OneToMany
	private Set<TaskInstance> taskInstances;
	@OneToMany
	private Set<ConnectionInstance> edges = new HashSet<>();

	public WorkflowInstance() {
		// Nothing.
	}

	public WorkflowInstance(final Workflow w2) {
		workflow = w2;
		taskInstances = w2.getTasks().stream()
				.map(TaskInstance::new)
				.collect(Collectors.toSet());
		final Map<Task, TaskInstance> map = taskInstances.stream().collect(Collectors.toMap(TaskInstance::getTask, Function.identity()));
		edges = w2.getEdges().stream()
				.map(x -> new ConnectionInstance(x, map.get(x.getSource()), map.get(x.getTarget())))
				.collect(Collectors.toSet());
	}

	public UUID getId() {
		return id;
	}

	public void setId(final UUID id) {
		this.id = id;
	}

	public Workflow getWorkflow() {
		return workflow;
	}

	public void setWorkflow(final Workflow workflow) {
		this.workflow = workflow;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(final Date startDate) {
		this.startDate = startDate;
	}

	public LcmOperationStateType getStatus() {
		return status;
	}

	public void setStatus(final LcmOperationStateType status) {
		this.status = status;
	}

	public Set<TaskInstance> getTaskInstances() {
		return taskInstances;
	}

	public void setTaskInstances(final Set<TaskInstance> taskInstances) {
		this.taskInstances = taskInstances;
	}

	public Set<ConnectionInstance> getEdges() {
		return edges;
	}

	public void setEdges(final Set<ConnectionInstance> edges) {
		this.edges = edges;
	}

}
