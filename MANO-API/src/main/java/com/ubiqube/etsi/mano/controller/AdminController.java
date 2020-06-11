package com.ubiqube.etsi.mano.controller;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ubiqube.etsi.mano.dao.mano.VimConnectionInformation;
import com.ubiqube.etsi.mano.jpa.VimConnectionInformationJpa;
import com.ubiqube.etsi.mano.model.VimConnectionInfo;
import com.ubiqube.etsi.mano.service.vim.VimManager;

import ma.glasnost.orika.MapperFacade;
import springfox.documentation.annotations.ApiIgnore;

@Controller
@ApiIgnore
@RequestMapping("/admin")
public class AdminController {
	private final VimConnectionInformationJpa vciJpa;
	private final MapperFacade mapper;
	private final VimManager vimManager;

	public AdminController(final VimConnectionInformationJpa vciJpa, final MapperFacade mapper, final VimManager vimManager) {
		super();
		this.vciJpa = vciJpa;
		this.mapper = mapper;
		this.vimManager = vimManager;
	}

	@PostMapping(value = "/vim/register")
	public ResponseEntity<VimConnectionInfo> registerVim(@RequestBody final VimConnectionInfo body) {
		VimConnectionInformation vci = mapper.map(body, VimConnectionInformation.class);
		vci = vciJpa.save(vci);
		vimManager.rebuildCache();
		return ResponseEntity.ok(mapper.map(vci, VimConnectionInfo.class));
	}

	@DeleteMapping(value = "/vim/{id}")
	public ResponseEntity<Void> deleteVim(@PathVariable("id") final String id) {
		vciJpa.deleteById(UUID.fromString(id));
		vimManager.rebuildCache();
		return ResponseEntity.noContent().build();
	}

	@GetMapping(value = "/vim")
	public ResponseEntity<Iterable<VimConnectionInformation>> listVim() {
		final Iterable<VimConnectionInformation> vci = vciJpa.findAll();
		return ResponseEntity.ok(vci);
	}

}
