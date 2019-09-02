package com.ubiqube.etsi.mano.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.springframework.stereotype.Service;

import com.ubiqube.etsi.mano.exception.GenericException;

@Service
public class PropertiesConfiguration implements Configuration {

	private final Properties props;

	public PropertiesConfiguration() {
		props = new Properties();
		final String confDirName = System.getProperty("user.home") + "/.mano/";
		final String filename = confDirName + "configuration.properties";

		File confDir = new File(confDirName);
		if (! confDir.exists()) {
			confDir.mkdir();
                }

		File confFile = new File(filename);
		if (! confFile.exists()) {
			try {
				confFile.createNewFile();
			} catch (final IOException e) {
				throw new GenericException(e);
			}
		}

		try (InputStream inStream = new FileInputStream(filename);) {
			props.load(inStream);
		} catch (final IOException e) {
			throw new GenericException(e);
		}

	}

	@Override
	public <T> T get(String _key) {
		return (T) props.get(_key);
	}

}