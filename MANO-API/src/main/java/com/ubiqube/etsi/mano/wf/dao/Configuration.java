package com.ubiqube.etsi.mano.wf.dao;

import javax.persistence.Embeddable;

@Embeddable
public class Configuration {

	private String content;

	public String getContent() {
		return content;
	}

	public void setContent(final String content) {
		this.content = content;
	}

}
