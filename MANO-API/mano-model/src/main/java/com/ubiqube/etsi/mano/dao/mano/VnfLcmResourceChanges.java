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

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Embeddable
public class VnfLcmResourceChanges implements Serializable {
	/** Serial. */
	private static final long serialVersionUID = 1L;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn
	private Set<VnfInstantiatedCompute> affectedVnfcs = new HashSet<>();

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn
	private Set<VnfInstantiatedVirtualLink> affectedVirtualLinks = new HashSet<>();

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn
	private Set<VnfInstantiatedStorage> affectedVirtualStorages = new HashSet<>();

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn
	private Set<VnfInstantiatedExtCp> affectedExtCp = new HashSet<>();

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn
	private Set<VnfInstantiatedMonitoring> instanceMonitors;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn
	private Set<VnfInstantiatedDnsZone> dnsZones;

	public Set<VnfInstantiatedCompute> getAffectedVnfcs() {
		return affectedVnfcs;
	}

	public void setAffectedVnfcs(final Set<VnfInstantiatedCompute> affectedVnfcs) {
		this.affectedVnfcs = affectedVnfcs;
	}

	public void addAffectedVnfcs(final VnfInstantiatedCompute affectedCompute) {
		if (null == affectedVnfcs) {
			affectedVnfcs = new HashSet<>();
		}
		affectedVnfcs.add(affectedCompute);
	}

	public Set<VnfInstantiatedVirtualLink> getAffectedVirtualLinks() {
		return affectedVirtualLinks;
	}

	public void setAffectedVirtualLinks(final Set<VnfInstantiatedVirtualLink> affectedVirtualLinks) {
		this.affectedVirtualLinks = affectedVirtualLinks;
	}

	public Set<VnfInstantiatedStorage> getAffectedVirtualStorages() {
		return affectedVirtualStorages;
	}

	public void setAffectedVirtualStorages(final Set<VnfInstantiatedStorage> affectedVirtualStorages) {
		this.affectedVirtualStorages = affectedVirtualStorages;
	}

	public void addAffectedVirtualLink(final VnfInstantiatedVirtualLink affectedVirtualLink) {
		if (null == affectedVirtualLinks) {
			affectedVirtualLinks = new HashSet<>();
		}
		affectedVirtualLinks.add(affectedVirtualLink);
	}

	public void addAffectedVirtualStorage(final VnfInstantiatedStorage affectedVs) {
		if (null == affectedVirtualStorages) {
			affectedVirtualStorages = new HashSet<>();
		}
		affectedVirtualStorages.add(affectedVs);
	}

	public Set<VnfInstantiatedExtCp> getAffectedExtCp() {
		return affectedExtCp;
	}

	public void setAffectedExtCp(final Set<VnfInstantiatedExtCp> affectedExtCp) {
		this.affectedExtCp = affectedExtCp;
	}

	public void addAffectedExtCp(final VnfInstantiatedExtCp _affectedExtCp) {
		if (null == affectedVirtualStorages) {
			affectedVirtualStorages = new HashSet<>();
		}
		affectedExtCp.add(_affectedExtCp);
	}

	public Set<VnfInstantiatedMonitoring> getInstanceMonitors() {
		return instanceMonitors;
	}

	public void setInstanceMonitors(final Set<VnfInstantiatedMonitoring> instanceMonotors) {
		this.instanceMonitors = instanceMonotors;
	}

	public void addAffectedMonitoring(final VnfInstantiatedMonitoring instanceMonotor) {
		if (null == instanceMonitors) {
			instanceMonitors = new HashSet<>();
		}
		instanceMonitors.add(instanceMonotor);
	}

	public Set<VnfInstantiatedDnsZone> getDnsZones() {
		return dnsZones;
	}

	public void setDnsZones(final Set<VnfInstantiatedDnsZone> dnsZones) {
		this.dnsZones = dnsZones;
	}

	public void addAffectedDnsZone(final VnfInstantiatedDnsZone aVs) {
		if (null == dnsZones) {
			dnsZones = new HashSet<>();
		}
		dnsZones.add(aVs);
	}

}