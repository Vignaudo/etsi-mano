package com.ubiqube.etsi.mano.dao.mano;

import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.Valid;

import com.ubiqube.etsi.mano.model.IpOverEthernetAddressDataIpAddresses.TypeEnum;

@Entity
public class IpOverEthernetAddressDataIpAddressesEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(columnDefinition = "BINARY(16)")
	private UUID id;

	@Enumerated(EnumType.STRING)
	private TypeEnum type = null;

	@Valid
	@ElementCollection
	private List<String> fixedAddresses = null;

	private Integer numDynamicAddresses = null;

	private IpOverEthernetAddressDataAddressRangeEntity addressRange = null;

	private String subnetId = null;

	@OneToOne
	private IpOverEthernetAddressDataEntity ipOverEthernetAddressDataEntity;

	public UUID getId() {
		return id;
	}

	public void setId(final UUID id) {
		this.id = id;
	}

	public TypeEnum getType() {
		return type;
	}

	public void setType(final TypeEnum type) {
		this.type = type;
	}

	public List<String> getFixedAddresses() {
		return fixedAddresses;
	}

	public void setFixedAddresses(final List<String> fixedAddresses) {
		this.fixedAddresses = fixedAddresses;
	}

	public Integer getNumDynamicAddresses() {
		return numDynamicAddresses;
	}

	public void setNumDynamicAddresses(final Integer numDynamicAddresses) {
		this.numDynamicAddresses = numDynamicAddresses;
	}

	public IpOverEthernetAddressDataAddressRangeEntity getAddressRange() {
		return addressRange;
	}

	public void setAddressRange(final IpOverEthernetAddressDataAddressRangeEntity addressRange) {
		this.addressRange = addressRange;
	}

	public String getSubnetId() {
		return subnetId;
	}

	public void setSubnetId(final String subnetId) {
		this.subnetId = subnetId;
	}

	public IpOverEthernetAddressDataEntity getIpOverEthernetAddressDataEntity() {
		return ipOverEthernetAddressDataEntity;
	}

	public void setIpOverEthernetAddressDataEntity(final IpOverEthernetAddressDataEntity ipOverEthernetAddressDataEntity) {
		this.ipOverEthernetAddressDataEntity = ipOverEthernetAddressDataEntity;
	}

}