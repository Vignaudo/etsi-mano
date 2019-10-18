package com.ubiqube.graph;

public class ConnectivityEdge {
	private Object source;
	private Object target;

	public Object getSource() {
		return source;
	}

	public void setSource(final Object source) {
		this.source = source;
	}

	public Object getTarget() {
		return target;
	}

	public void setTarget(final Object target) {
		this.target = target;
	}

	@Override
	public String toString() {
		return "ConnectivityEdge [source=" + source + ", target=" + target + "]";
	}

}
