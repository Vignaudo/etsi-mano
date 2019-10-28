package com.ubiqube.etsi.mano.wf.dao;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToOne;

import com.ubiqube.etsi.mano.model.nslcm.LcmOperationStateType;

public class TaskInstance {
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

}
