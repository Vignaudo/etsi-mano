package com.ubiqube.parser.tosca;

import java.util.Map;

public class NodeTemplate {
	private String type;
	private String name;
	private String description;
	private Object capabilities;
	private Map<String, Object> properties;
	private RequirementDefinition requirements;
	private Map<String, ValueObject> attributes;
	private Map<String, Artifact> artifacts;
	private Map<String, InterfaceType> interfaces;

	public String getType() {
		return type;
	}

	public void setType(final String type) {
		this.type = type;
	}

	public RequirementDefinition getRequirements() {
		return requirements;
	}

	public void setRequirements(final RequirementDefinition requirements) {
		this.requirements = requirements;
	}

	public Object getCapabilities() {
		return capabilities;
	}

	public void setCapabilities(final Object capabilities) {
		this.capabilities = capabilities;
	}

	public Map<String, Object> getProperties() {
		return properties;
	}

	public void setProperties(final Map<String, Object> properties) {
		this.properties = properties;
	}

	public Map<String, ValueObject> getAttributes() {
		return attributes;
	}

	public void setAttributes(final Map<String, ValueObject> attributes) {
		this.attributes = attributes;
	}

	public Map<String, Artifact> getArtifacts() {
		return artifacts;
	}

	public void setArtifacts(final Map<String, Artifact> artifacts) {
		this.artifacts = artifacts;
	}

	public Map<String, InterfaceType> getInterfaces() {
		return interfaces;
	}

	public void setInterfaces(final Map<String, InterfaceType> interfaces) {
		this.interfaces = interfaces;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

}
