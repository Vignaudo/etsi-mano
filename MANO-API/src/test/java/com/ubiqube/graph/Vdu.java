package com.ubiqube.graph;

public class Vdu {
	private String name;

	public Vdu(final String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Vdu [name=" + name + "]";
	}

}
