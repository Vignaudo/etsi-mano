package com.ubiqube.etsi.mano.jpa;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.jgrapht.Graph;
import org.jgrapht.graph.DirectedAcyclicGraph;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.ubiqube.etsi.mano.model.nslcm.LcmOperationStateType;
import com.ubiqube.etsi.mano.wf.dao.Connection;
import com.ubiqube.etsi.mano.wf.dao.Task;
import com.ubiqube.etsi.mano.wf.dao.TaskInstance;
import com.ubiqube.etsi.mano.wf.dao.Workflow;
import com.ubiqube.etsi.mano.wf.dao.WorkflowInstance;
import com.ubiqube.etsi.mano.wf.repository.ConnectionRepository;
import com.ubiqube.etsi.mano.wf.repository.TaskInstanceRepository;
import com.ubiqube.etsi.mano.wf.repository.WorkFlowRepository;
import com.ubiqube.etsi.mano.wf.repository.WorkflowInstanceRepository;

@DataJpaTest
public class TaskTest {
	@Autowired
	private WorkFlowRepository workflowRepository;
	@Autowired
	private ConnectionRepository connectionRepository;
	@Autowired
	private WorkflowInstanceRepository workflowInstanceRepository;
	@Autowired
	private TaskInstanceRepository taskInstanceRepository;

	@Test
	void testName() throws Exception {
		assertNotNull(workflowRepository);
	}

	@Test
	void testName2() throws Exception {
		final Graph<Task, Connection> g = new DirectedAcyclicGraph(Connection.class);
		final Task a = new Task("a");
		g.addVertex(a);
		final Task b = new Task("b");
		g.addVertex(b);
		final Task c = new Task("c");
		g.addVertex(c);
		final Task d = new Task("d");
		g.addVertex(d);
		final Task e = new Task("e");
		g.addVertex(e);
		final Task f = new Task("f");
		g.addVertex(f);
		final Set<Task> tasks = new HashSet<>(Arrays.asList(a, b, c, d, e, f));

		final Connection c1 = connect(a, b);
		g.addEdge(a, b);
		final Connection c2 = connect(a, c);
		g.addEdge(a, c);

		final Connection c3 = connect(b, d);
		g.addEdge(b, d);
		final Connection c4 = connect(c, d);
		g.addEdge(c, d);

		final Connection c5 = connect(e, c);
		g.addEdge(e, c);
		final Connection c6 = connect(a, f);
		g.addEdge(a, f);
		final Set<Connection> conns = new HashSet<>(Arrays.asList(c1, c2, c3, c4, c5, c6));

		final Workflow w = new Workflow();
		w.setEdges(conns);
		w.setTasks(tasks);

		final Workflow w2 = workflowRepository.save(w);
		final WorkflowInstance wi = instantiate(w2);
		// should be getRoot(w2).
		final List<TaskInstance> roots = getRoots2(wi);
		roots.forEach(System.out::println);
		assertEquals(2, roots.size());
		roots.forEach(this::start);
	}

	private WorkflowInstance instantiate(final Workflow w2) {
		WorkflowInstance workflowInstance = new WorkflowInstance(w2);
		workflowInstance = workflowInstanceRepository.save(workflowInstance);
		return workflowInstance;
	}

	private void start(final TaskInstance task) {
		task.setTaskStatus(LcmOperationStateType.PROCESSING);
		taskInstanceRepository.save(task);
		// get downStream connections.
		// Check completion.
		// if so Start task.
	}

	private void onFinished(final TaskInstance ti) {
		ti.setTaskStatus(LcmOperationStateType.COMPLETED);
		taskInstanceRepository.save(ti);
		// get upstream connections.
		// foreach flag completed.
		// foreach start task.
	}

	private List<TaskInstance> getRoots2(final WorkflowInstance wi) {
		final HashSet<TaskInstance> ti = new HashSet<>(wi.getTaskInstances());
		wi.getEdges().stream()
				.forEach(x -> ti.remove(x.getTarget()));
		return ti.stream().collect(Collectors.toList());
	}

	private List<Task> getRoots(final Graph<Task, Connection> g) {
		return g.vertexSet().stream()
				.filter(x -> g.incomingEdgesOf(x).isEmpty())
				.collect(Collectors.toList());
	}

	private Connection connect(final Task a, final Task b) {
		final Connection c = new Connection();
		c.setSource(a);
		c.setTarget(b);
		return c;
	}
}
