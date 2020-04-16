package com.ubiqube.etsi.mano.wf.dao;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.ubiqube.etsi.mano.model.nslcm.LcmOperationStateType;

@Entity
public class ConnectionInstance {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	@OneToOne
	private Connection connection;
	@OneToOne
	private TaskInstance source;
	@OneToOne
	private TaskInstance target;

	private LcmOperationStateType state;

	public ConnectionInstance() {
		// Nothing.
	}

	public ConnectionInstance(final Connection _connection, final TaskInstance _source, final TaskInstance _target) {
		connection = _connection;
		source = _source;
		target = _target;
	}

	public Connection getConnection() {
		return connection;
	}

	public void setConnection(final Connection connection) {
		this.connection = connection;
	}

	public TaskInstance getSource() {
		return source;
	}

	public void setSource(final TaskInstance source) {
		this.source = source;
	}

	public TaskInstance getTarget() {
		return target;
	}

	public void setTarget(final TaskInstance target) {
		this.target = target;
	}

	public UUID getId() {
		return id;
	}

	public void setId(final UUID id) {
		this.id = id;
	}

	public LcmOperationStateType getState() {
		return state;
	}

	public void setState(final LcmOperationStateType state) {
		this.state = state;
	}

}