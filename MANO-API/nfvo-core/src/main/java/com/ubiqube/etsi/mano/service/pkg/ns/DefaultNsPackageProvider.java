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

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.ubiqube.etsi.mano.dao.mano.NsSap;
import com.ubiqube.etsi.mano.dao.mano.v2.nfvo.NsVirtualLink;
import com.ubiqube.etsi.mano.service.pkg.bean.NsInformations;
import com.ubiqube.etsi.mano.service.pkg.bean.SecurityGroupAdapter;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
public class DefaultNsPackageProvider implements NsPackageProvider {
	@Override
	public NsInformations getNsInformations(final Map<String, String> userData) {
		return new NsInformations();
	}

	@Override
	public Set<NsVirtualLink> getNsVirtualLink(final Map<String, String> userData) {
		return new HashSet<>();
	}

	@Override
	public Set<NsSap> getNsSap(final Map<String, String> userData) {
		return new HashSet<>();
	}

	@Override
	public Set<SecurityGroupAdapter> getSecurityGroups(final Map<String, String> userData) {
		return new HashSet<>();
	}

	@Override
	public Set<String> getNestedNsd(final Map<String, String> userData) {
		return new HashSet<>();
	}

	@Override
	public Set<String> getVnfd(final Map<String, String> userData) {
		return new HashSet<>();
	}
}
