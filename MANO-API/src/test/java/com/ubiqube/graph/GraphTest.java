package com.ubiqube.graph;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import org.jgrapht.Graph;
import org.jgrapht.ListenableGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultListenableGraph;
import org.jgrapht.graph.DirectedAcyclicGraph;
import org.jgrapht.traverse.DepthFirstIterator;
import org.jgrapht.traverse.TopologicalOrderIterator;
import org.junit.jupiter.api.Test;

public class GraphTest {

	@Test
	void testName() throws Exception {
		final ListenableGraph<Vdu, DefaultEdge> g = new DefaultListenableGraph<>(new DirectedAcyclicGraph(DefaultEdge.class));
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

		System.out.println("==========================");
		System.out.println("Root Nodes: " + getRoots(g));
		// So A& E can start now
		System.out.println("==> vduA have finished.");
		// A is finished but not E, we send event to child vertex.
		onFinishTask(g, vduA);

		// C cannot start but B can start.
		System.out.println("==> vduE have finished.");
		// E have finished.
		Set<DefaultEdge> oe = g.outgoingEdgesOf(vduE);
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
		System.out.println("=========================== Sequential safe list.");
		final TopologicalOrderIterator<Vdu, DefaultEdge> orderIterator = new TopologicalOrderIterator<>(g);
		while (orderIterator.hasNext()) {
			final Vdu vertexVdu = orderIterator.next();
			System.out.println(" >" + vertexVdu);
		}
	}

	private void onFinishTask(final ListenableGraph<Vdu, DefaultEdge> g, final Vdu vduA) {
		final Set<DefaultEdge> oe = g.outgoingEdgesOf(vduA);
		oe.stream().forEach(x -> checkPreCondition(x));
	}

	private Object checkPreCondition(final DefaultEdge x) {
		// Target of x.
		System.out.println("Check precondition of: " + x);
		return null;
	}

	public <T, E> List<T> getRoots(final Graph<T, E> graph) {
		return asStream(new DepthFirstIterator<>(graph))
				.filter(x -> graph.incomingEdgesOf(x).isEmpty())
				.collect(Collectors.toList());
	}

	public static <T> Stream<T> asStream(final Iterator<T> sourceIterator) {
		return asStream(sourceIterator, false);
	}

	public static <T> Stream<T> asStream(final Iterator<T> sourceIterator, final boolean parallel) {
		final Iterable<T> iterable = () -> sourceIterator;
		return StreamSupport.stream(iterable.spliterator(), parallel);
	}
}
