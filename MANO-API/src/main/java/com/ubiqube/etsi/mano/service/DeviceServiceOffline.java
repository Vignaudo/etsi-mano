package com.ubiqube.etsi.mano.service;

import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.ubiqube.api.commons.id.DeviceId;
import com.ubiqube.api.entities.device.Manufacturer;
import com.ubiqube.api.entities.device.Model;
import com.ubiqube.api.entities.device.SimpleDevice;
import com.ubiqube.api.exception.ServiceException;
import com.ubiqube.api.interfaces.device.DeviceService;

@Profile("offline")
@Service
public class DeviceServiceOffline implements DeviceService {

	@Override
	public DeviceId getDeviceId(final String nsInstanceId) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteDevice(final DeviceId deviceId, final String name) throws ServiceException {
		// TODO Auto-generated method stub

	}

	@Override
	public SimpleDevice getDeviceModeleAndManId(final DeviceId deviceId) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<Long, Manufacturer> getAvailableManufacturers() throws ServiceException {
		final Map<Long, Manufacturer> res = new HashedMap();
		final Manufacturer man = new Manufacturer() {

			@Override
			public String getName() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public Model getModel(final long manId) {

				return () -> "Model";
			}
		};
		res.put(Long.decode("18"), man);
		return res;
	}

}
