/**
 *     Copyright (C) 2019-2020 Ubiqube.
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package com.ubiqube.etsi.mano.dao.mano;

import java.util.UUID;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@EntityListeners(AuditListener.class)
public class NsVlProfile implements BaseEntity, Auditable {
	/** Serial. */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

	@Embedded
	private Qos qos;

	private Integer linkBitrateRoot;

	private Integer linkBitrateLeaf;

	private Integer maxBitrateRequirementsRoot;

	private Integer maxBitrateRequirementsLeaf;

	private Audit audit;

	private String serviceAvailability;

	@Override
	public UUID getId() {
		return id;
	}

	public void setId(final UUID id) {
		this.id = id;
	}

	public Qos getQos() {
		return qos;
	}

	public void setQos(final Qos qos) {
		this.qos = qos;
	}

	public Integer getLinkBitrateRoot() {
		return linkBitrateRoot;
	}

	public void setLinkBitrateRoot(final Integer linkBitrateRoot) {
		this.linkBitrateRoot = linkBitrateRoot;
	}

	public Integer getLinkBitrateLeaf() {
		return linkBitrateLeaf;
	}

	public void setLinkBitrateLeaf(final Integer linkBitrateLeaf) {
		this.linkBitrateLeaf = linkBitrateLeaf;
	}

	public Integer getMaxBitrateRequirementsRoot() {
		return maxBitrateRequirementsRoot;
	}

	public void setMaxBitrateRequirementsRoot(final Integer maxBitrateRequirementsRoot) {
		this.maxBitrateRequirementsRoot = maxBitrateRequirementsRoot;
	}

	public Integer getMaxBitrateRequirementsLeaf() {
		return maxBitrateRequirementsLeaf;
	}

	public void setMaxBitrateRequirementsLeaf(final Integer maxBitrateRequirementsLeaf) {
		this.maxBitrateRequirementsLeaf = maxBitrateRequirementsLeaf;
	}

	@Override
	public Audit getAudit() {
		return audit;
	}

	@Override
	public void setAudit(final Audit audit) {
		this.audit = audit;
	}

	public String getServiceAvailability() {
		return serviceAvailability;
	}

	public void setServiceAvailability(final String serviceAvailability) {
		this.serviceAvailability = serviceAvailability;
	}

}
