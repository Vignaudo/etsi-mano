package com.ubiqube.etsi.mano.mapper;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.IntStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.ubiqube.etsi.mano.exception.GenericException;

@Service
public class JsonWalker {

	private static final Logger LOG = LoggerFactory.getLogger(JsonWalker.class);

	private final ObjectMapper mapper;

	public JsonWalker(final ObjectMapper _mapper) {
		mapper = _mapper;
	}

	public void walk(final String _patchDocument, final BeanListener beanListener) {
		try {
			LOG.debug("JsonWalking ");
			final JsonNode patch = mapper.readTree(_patchDocument);
			walkInner(patch, beanListener);
		} catch (final IOException _e) {
			throw new GenericException(_e);
		}
	}

	private static void walkInner(final JsonNode jsonNode, final BeanListener beanListener) {
		if (jsonNode.isObject()) {
			final Iterator<Map.Entry<String, JsonNode>> iter = jsonNode.fields();
			iter.forEachRemaining(x -> handleObject(x, beanListener));
		} else if (jsonNode.isArray()) {
			final ArrayNode arrayNode = (ArrayNode) jsonNode;
			IntStream.range(0, arrayNode.size()).forEach(x -> handleArray(x, arrayNode, beanListener));
		} else if (jsonNode.isValueNode()) {
			beanListener.addProperty(jsonNode.asText());
		}
	}

	private static void handleObject(final Map.Entry<String, JsonNode> entry, final BeanListener beanListener) {
		beanListener.complexStart(entry.getKey());
		walkInner(entry.getValue(), beanListener);
		beanListener.complexEnd();
	}

	private static void handleArray(final int i, final ArrayNode arrayNode, final BeanListener beanListener) {
		final JsonNode val = arrayNode.get(i);
		beanListener.listElementStart(i);
		if (val.isValueNode()) {
			beanListener.addProperty(val.asText());
		} else {
			walkInner(val, beanListener);
		}
		beanListener.listElementEnd();
	}
}
