/**
 *     Copyright (C) 2019-2020 Ubiqube.
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package com.ubiqube.etsi.mano.service.pkg.ns;

import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Service;

import com.ubiqube.etsi.mano.dao.mano.NsSap;
import com.ubiqube.etsi.mano.dao.mano.NsdPackage;
import com.ubiqube.etsi.mano.dao.mano.NsdPackageNsdPackage;
import com.ubiqube.etsi.mano.dao.mano.NsdPackageVnfPackage;
import com.ubiqube.etsi.mano.dao.mano.OnboardingStateType;
import com.ubiqube.etsi.mano.dao.mano.PackageOperationalState;
import com.ubiqube.etsi.mano.dao.mano.VnfPackage;
import com.ubiqube.etsi.mano.dao.mano.v2.nfvo.NsVirtualLink;
import com.ubiqube.etsi.mano.exception.GenericException;
import com.ubiqube.etsi.mano.exception.NotFoundException;
import com.ubiqube.etsi.mano.jpa.NsdPackageJpa;
import com.ubiqube.etsi.mano.repository.NsdRepository;
import com.ubiqube.etsi.mano.service.VnfPackageService;
import com.ubiqube.etsi.mano.service.event.EventManager;
import com.ubiqube.etsi.mano.service.event.NotificationEvent;
import com.ubiqube.etsi.mano.service.pkg.PackageVersion;
import com.ubiqube.etsi.mano.service.pkg.bean.NsInformations;
import com.ubiqube.etsi.mano.service.pkg.bean.SecurityGroupAdapter;

import ma.glasnost.orika.MapperFacade;

@Service
public class NsPackageOnboardingImpl {

	private final EventManager eventManager;

	private final NsPackageManager packageManager;

	private final MapperFacade mapper;

	private final VnfPackageService vnfPackageService;

	private final NsdRepository nsdRepository;

	private final NsdPackageJpa nsdPackageJpa;

	public NsPackageOnboardingImpl(final EventManager eventManager, final NsPackageManager packageManager, final MapperFacade _mapper, final NsdRepository _nsdRepository, final NsdPackageJpa _nsdPackageJpa, final VnfPackageService _vnfPackageService) {
		super();
		this.eventManager = eventManager;
		this.packageManager = packageManager;
		mapper = _mapper;
		nsdRepository = _nsdRepository;
		nsdPackageJpa = _nsdPackageJpa;
		vnfPackageService = _vnfPackageService;
	}

	public void nsOnboarding(@NotNull final UUID objectId) {
		final NsdPackage nsPackage = nsdPackageJpa.findById(objectId).orElseThrow(() -> new NotFoundException("NS Package " + objectId + " Not found."));
		final byte[] data = nsdRepository.getBinary(objectId, "nsd");
		final NsPackageProvider packageProvider = packageManager.getProviderFor(data);
		if (null != packageProvider) {
			mapNsPackage(packageProvider, nsPackage);
		}
		nsPackage.setNsdOnboardingState(OnboardingStateType.ONBOARDED);
		nsPackage.setNsdOperationalState(PackageOperationalState.ENABLED);
		nsdPackageJpa.save(nsPackage);
		eventManager.sendNotification(NotificationEvent.NS_PKG_ONBOARDING, nsPackage.getId());
	}

	private void mapNsPackage(final NsPackageProvider packageProvider, final NsdPackage nsPackage) {
		final Map<String, String> userData = nsPackage.getUserDefinedData();
		final NsInformations nsInformations = packageProvider.getNsInformations(userData);
		mapper.map(nsInformations, nsPackage);
		final Set<NsSap> nsSap = packageProvider.getNsSap(userData);
		nsPackage.setNsSaps(nsSap);
		final Set<NsVirtualLink> nsVirtualLink = packageProvider.getNsVirtualLink(userData);
		nsPackage.setNsVirtualLinks(nsVirtualLink);
		final Set<SecurityGroupAdapter> sgAdapters = packageProvider.getSecurityGroups(userData);
		sgAdapters.forEach(x -> nsPackage.getNsSaps().stream()
				.filter(y -> x.getTargets().contains(y.getToscaName()))
				.forEach(y -> y.addSecurityGroups(x.getSecurityGroup())));
		final Set<NsdPackageVnfPackage> vnfds = packageProvider.getVnfd(userData).stream()
				.map(x -> {
					nsInformations.getFlavorId();
					final Optional<VnfPackage> optPackage = getVnfPackage(x);
					final VnfPackage vnfPackage = optPackage.orElseThrow(() -> new NotFoundException("Vnfd descriptor_id not found: " + x));
					final NsdPackageVnfPackage nsdPackageVnfPackage = new NsdPackageVnfPackage();
					nsdPackageVnfPackage.setNsdPackage(nsPackage);
					nsdPackageVnfPackage.setToscaName(x);
					nsdPackageVnfPackage.setVnfPackage(vnfPackage);
					vnfPackage.addNsdPackage(nsdPackageVnfPackage);
					vnfPackageService.save(vnfPackage);
					return nsdPackageVnfPackage;
				})
				.collect(Collectors.toSet());
		nsPackage.setVnfPkgIds(vnfds);
		final Set<NsdPackageNsdPackage> nsds = packageProvider.getNestedNsd(userData).stream()
				.map(x -> {
					final NsdPackage nestedNsd = nsdPackageJpa.findByNsdInvariantId(x).orElseThrow(() -> new NotFoundException("Nsd invariant_id not found: " + x));
					final NsdPackageNsdPackage nsdnsd = new NsdPackageNsdPackage();
					nsdnsd.setParent(nsPackage);
					nsdnsd.setChild(nestedNsd);
					nsdnsd.setToscaName(x);
					return nsdnsd;
				})
				.collect(Collectors.toSet());
		nsPackage.setNestedNsdInfoIds(nsds);
	}

	private Optional<VnfPackage> getVnfPackage(final String flavor, final String descriptorId, final String version) {
		int part = 0;
		if (flavor != null) {
			part++;
		}
		if (descriptorId != null) {
			part++;
		}
		if (version != null) {
			part++;
		}
		if (part == 0) {
			return Optional.empty();
		}
		if (part == 1) {
			return vnfPackageService.findByDescriptorId(descriptorId);
		} else if (part == 2) {
			return vnfPackageService.findByDescriptorIdAndSoftwareVersion(descriptorId, version);
		} else if (part == 3) {
			return vnfPackageService.findByDescriptorIdFlavorIdVnfdVersion(descriptorId, flavor, version);
		}
		throw new GenericException("Unknown version " + part);
	}

	private Optional<VnfPackage> getVnfPackage(final String x) {
		final PackageVersion pv = new PackageVersion(x);
		return getVnfPackage(pv.getFlavorId(), pv.getName(), pv.getVersion());
	}
}
