package com.ubiqube.etsi.mano.service.event.jms;

import java.util.Map;

import org.springframework.jms.core.JmsTemplate;

import com.ubiqube.etsi.mano.service.event.ActionType;
import com.ubiqube.etsi.mano.service.event.EventManager;
import com.ubiqube.etsi.mano.service.event.NotificationEvent;

//@Service
public class JmsEventManager implements EventManager {
	private final JmsTemplate jmsTemplate;

	public JmsEventManager(final JmsTemplate _jmsTemplate) {
		super();
		jmsTemplate = _jmsTemplate;
	}

	@Override
	public void sendNotification(final NotificationEvent notificationEvent, final String objectId) {
		final EventMessage msg = new EventMessage(notificationEvent, objectId);
		jmsTemplate.convertAndSend("system.notifications", msg);
	}

	@Override
	public void sendAction(final ActionType actionType, final String objectId, final Map<String, Object> parameters) {
		final ActionMessage msg = new ActionMessage(actionType, objectId, parameters);
		jmsTemplate.convertAndSend("system.actions", msg);
	}

}