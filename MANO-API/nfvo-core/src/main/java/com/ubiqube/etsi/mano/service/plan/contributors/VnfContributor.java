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
package com.ubiqube.etsi.mano.service.plan.contributors;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ubiqube.etsi.mano.dao.mano.ChangeType;
import com.ubiqube.etsi.mano.dao.mano.NsdPackage;
import com.ubiqube.etsi.mano.dao.mano.NsdPackageVnfPackage;
import com.ubiqube.etsi.mano.dao.mano.ScaleInfo;
import com.ubiqube.etsi.mano.dao.mano.VnfInstance;
import com.ubiqube.etsi.mano.dao.mano.VnfPackage;
import com.ubiqube.etsi.mano.dao.mano.v2.nfvo.NsBlueprint;
import com.ubiqube.etsi.mano.dao.mano.v2.nfvo.NsTask;
import com.ubiqube.etsi.mano.dao.mano.v2.nfvo.NsVnfTask;
import com.ubiqube.etsi.mano.exception.NotFoundException;
import com.ubiqube.etsi.mano.factory.NsInstanceFactory;
import com.ubiqube.etsi.mano.model.VnfInstantiate;
import com.ubiqube.etsi.mano.service.NsInstanceService;
import com.ubiqube.etsi.mano.service.VnfmInterface;
import com.ubiqube.etsi.mano.service.graph.nfvo.NsParameters;
import com.ubiqube.etsi.mano.service.graph.nfvo.VnfUow;
import com.ubiqube.etsi.mano.service.graph.vnfm.UnitOfWork;
import com.ubiqube.etsi.mano.service.graph.wfe2.DependencyBuilder;
import com.ubiqube.etsi.mano.service.vim.node.Node;
import com.ubiqube.etsi.mano.service.vim.node.nfvo.VnfNode;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@Service
public class VnfContributor extends AbstractNsContributor {
	private final NsInstanceService nsInstanceService;
	private final VnfmInterface vnfm;

	public VnfContributor(final NsInstanceService _nsInstanceService, final VnfmInterface _vnfm) {
		nsInstanceService = _nsInstanceService;
		vnfm = _vnfm;
	}

	@Override
	public Class<? extends Node> getContributionType() {
		return VnfNode.class;
	}

	@Override
	public List<NsTask> contribute(final NsdPackage bundle, final NsBlueprint blueprint, final Set<ScaleInfo> scaling) {
		final Set<VnfPackage> vnfs = nsInstanceService.findVnfPackageByNsInstance(bundle);
		return vnfs.stream()
				.filter(x -> 0 == nsInstanceService.countLiveInstanceOfVnf(blueprint.getNsInstance(), x.getId()))
				.map(x -> {
					final NsVnfTask sap = createTask(NsVnfTask::new);
					sap.setChangeType(ChangeType.ADDED);
					final NsdPackageVnfPackage nsPackageVnfPackage = find(x, bundle.getVnfPkgIds());
					sap.setNsPackageVnfPackage(nsPackageVnfPackage);
					final VnfInstance vnfmVnfInstance = vnfm.createVnfInstance(x, "VNF instance hold by: " + blueprint.getNsInstance().getId(), x.getId().toString());
					final VnfInstance vnfInstance = NsInstanceFactory.createNsInstancesNsInstanceVnfInstance(vnfmVnfInstance, x);
					vnfInstance.setNsInstance(blueprint.getNsInstance());
					// vnfInstance.setVimConnectionInfo(vimConnectionInfo);
					// vnfInstance.setMetadata(metadata);
					// vnfInstance.setVnfConfigurableProperties(vnfConfigurableProperties);
					sap.setVnfInstance(vnfInstance.getId());
					sap.setAlias(nsPackageVnfPackage.getToscaName());
					sap.setToscaName(nsPackageVnfPackage.getToscaName());
					// XXX Not sure about the profileId is.
					return sap;
				}).collect(Collectors.toList());
	}

	private static NsdPackageVnfPackage find(final VnfPackage vnfPackage, final Set<NsdPackageVnfPackage> vnfPkgIds) {
		return vnfPkgIds.stream()
				.filter(x -> x.getVnfPackage().getId().compareTo(vnfPackage.getId()) == 0)
				.findFirst().orElseThrow(() -> new NotFoundException("VNF Package not found: " + vnfPackage.getId()));
	}

	@Override
	public List<UnitOfWork<NsTask, NsParameters>> convertTasksToExecNode(final Set<NsTask> tasks, final NsBlueprint blueprint) {
		final ArrayList<UnitOfWork<NsTask, NsParameters>> ret = new ArrayList<>();
		tasks.stream()
				.filter(x -> x instanceof NsVnfTask)
				.map(x -> (NsVnfTask) x)
				.forEach(x -> {
					final VnfInstantiate request = new VnfInstantiate();
					ret.add(new VnfUow(x, request, vnfm));
				});
		return ret;
	}

	@Override
	public void getDependencies(final DependencyBuilder dependencyBuilder) {
		// TODO Auto-generated method stub

	}

}
