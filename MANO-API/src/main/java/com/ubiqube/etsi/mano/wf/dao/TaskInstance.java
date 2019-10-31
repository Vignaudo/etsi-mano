package com.ubiqube.etsi.mano.wf.dao;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.ubiqube.etsi.mano.model.nslcm.LcmOperationStateType;

@Entity
public class TaskInstance {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

	@OneToOne
	private Task task;
	@Enumerated(EnumType.STRING)
	private LcmOperationStateType taskStatus = LcmOperationStateType.STARTING;

	public TaskInstance() {
		// Nothing.
	}

	public TaskInstance(final Task _task) {
		task = _task;
	}

	public Task getTask() {
		return task;
	}

	public void setTask(final Task task) {
		this.task = task;
	}

	public LcmOperationStateType getTaskStatus() {
		return taskStatus;
	}

	public void setTaskStatus(final LcmOperationStateType taskStatus) {
		this.taskStatus = taskStatus;
	}

	public UUID getId() {
		return id;
	}

	public void setId(final UUID id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "TaskInstance [id=" + id + ", Instance of: " + task.getName() + ", taskStatus=" + taskStatus + "]";
	}

}
