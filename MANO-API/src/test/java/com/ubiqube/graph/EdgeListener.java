package com.ubiqube.graph;

import org.jgrapht.event.GraphEdgeChangeEvent;
import org.jgrapht.event.GraphListener;
import org.jgrapht.event.GraphVertexChangeEvent;

public class EdgeListener<V> implements GraphListener<V, ConnectivityEdge> {

	@Override
	public void vertexAdded(final GraphVertexChangeEvent<V> e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void vertexRemoved(final GraphVertexChangeEvent<V> e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void edgeAdded(final GraphEdgeChangeEvent<V, ConnectivityEdge> e) {
		final ConnectivityEdge edge = e.getEdge();
		edge.setSource(e.getEdgeSource());
		edge.setTarget(e.getEdgeTarget());
	}

	@Override
	public void edgeRemoved(final GraphEdgeChangeEvent<V, ConnectivityEdge> e) {
		// TODO Auto-generated method stub

	}

}
