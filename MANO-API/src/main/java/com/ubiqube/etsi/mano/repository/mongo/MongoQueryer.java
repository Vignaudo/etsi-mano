package com.ubiqube.etsi.mano.repository.mongo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.ubiqube.etsi.mano.exception.GenericException;
import com.ubiqube.etsi.mano.grammar.AstBuilder;
import com.ubiqube.etsi.mano.grammar.Node;
import com.ubiqube.etsi.mano.grammar.Node.Operand;

public class MongoQueryer {

	public Query getCriteria(final List<Node> nodes) {
		final Query query = new Query();
		final Criteria base = new Criteria();
		final List<Criteria> criterias = convertNodeList(nodes);
		if (!criterias.isEmpty()) {
			base.andOperator(criterias.toArray(new Criteria[criterias.size()]));
		}
		query.addCriteria(base);
		return query;
	}

	public Query getCriteria(final String filter) {
		final AstBuilder astBuilder = new AstBuilder(filter);
		return getCriteria(astBuilder.getNodes());
	}

	private List<Criteria> convertNodeList(final List<Node> nodes) {
		final List<Criteria> criterias = new ArrayList<>();
		for (final Node node : nodes) {
			Criteria crit = Criteria.where(node.getName());
			crit = applyOp(crit, node.getOp(), node.getValue());
			criterias.add(crit);
		}

		return criterias;
	}

	private Criteria applyOp(final Criteria crit, final Operand op, final String value) {
		switch (op) {
		case EQ:
			return crit.is(value);
		case NEQ:
			return crit.ne(value);
		case GT:
			return crit.gt(value);
		case GTE:
			return crit.gte(value);
		case LT:
			return crit.lt(value);
		case LTE:
			return crit.lte(value);
		case CONT:
			return crit.ne(value);
		case NCONT:
		default:
			throw new GenericException("Unknown query Op: " + op);
		}
	}

}
