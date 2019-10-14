package com.ubiqube.graph;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.ListenableGraph;
import org.jgrapht.alg.connectivity.KosarajuStrongConnectivityInspector;
import org.jgrapht.alg.interfaces.StrongConnectivityAlgorithm;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultListenableGraph;
import org.jgrapht.traverse.DepthFirstIterator;
import org.junit.jupiter.api.Test;

public class GraphTest {

	@Test
	void testName() throws Exception {
		final ListenableGraph<Vdu, DefaultEdge> g = new DefaultListenableGraph<>(new DefaultDirectedGraph<>(DefaultEdge.class));
		final Vdu vduA = new Vdu("A");
		final Vdu vduB = new Vdu("B");
		final Vdu vduC = new Vdu("C");
		final Vdu vduD = new Vdu("D");
		final Vdu vduE = new Vdu("E");
		final Vdu vduF = new Vdu("F");
		g.addVertex(vduA);
		g.addVertex(vduB);
		g.addVertex(vduC);
		g.addVertex(vduD);
		g.addVertex(vduE);
		g.addVertex(vduF);

		g.addEdge(vduA, vduB);
		g.addEdge(vduA, vduC);

		g.addEdge(vduB, vduD);
		g.addEdge(vduC, vduD);

		g.addEdge(vduE, vduC);
		g.addEdge(vduE, vduF);

		final StrongConnectivityAlgorithm<Vdu, DefaultEdge> scAlg = new KosarajuStrongConnectivityInspector<>(g);
		final List<Graph<Vdu, DefaultEdge>> stronglyConnectedSubgraphs = scAlg.getStronglyConnectedComponents();

		Iterator<Vdu> iter = new DepthFirstIterator<>(g);
		Vdu vertex;
		while (iter.hasNext()) {
			vertex = iter.next();
			System.out.println(
					"Vertex " + vertex + " is connected to: "
							+ g.edgesOf(vertex));
		}
		System.out.println("==========================");
		iter = new DepthFirstIterator<>(g);
		while (iter.hasNext()) {
			vertex = iter.next();
			System.out.println(
					"Vertex " + vertex + " is connected to: "
							+ g.incomingEdgesOf(vertex));
		}
		// So A& E can start now
		System.out.println("==> vduA have finished.");
		// A is finished but not E, we send event to child vertex.
		Set<DefaultEdge> oe = g.outgoingEdgesOf(vduA);
		for (final Object element : oe) {
			final DefaultEdge defaultEdge = (DefaultEdge) element;
			System.out.println("" + defaultEdge);
		}
		// C cannot start but B can start.
		System.out.println("==> vduE have finished.");
		// E have finished.
		oe = g.outgoingEdgesOf(vduE);
		for (final Object element : oe) {
			final DefaultEdge defaultEdge = (DefaultEdge) element;
			System.out.println("" + defaultEdge);
		}
		// C & F can start.
		System.out.println("==> vduF have finished.");
		oe = g.outgoingEdgesOf(vduF);
		for (final DefaultEdge element : oe) {
			System.out.println("" + element);
		}
		// F is the end.
	}
}
