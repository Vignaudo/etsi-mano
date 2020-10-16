package com.ubiqube.etsi.mano.service.rest;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

import com.ubiqube.etsi.mano.service.Configuration;

@Service
public class NfvoRestImpl extends AbstractRest {
	private final String url;
	private final MultiValueMap<String, String> auth = new HttpHeaders();

	public NfvoRestImpl(final Configuration _conf) {
		url = _conf.build("nfvo.url").notNull().build();
		final String user = _conf.get("nfvo.user");
		if (null != user) {
			final String password = _conf.build("nfvo.password").withDefault("").build();
			auth.add("Authorization", authBasic(user, password));
		}
	}

	@Override
	protected String getUrl() {
		return url;
	}

	@Override
	protected MultiValueMap<String, String> getAutorization() {
		return auth;
	}

}
