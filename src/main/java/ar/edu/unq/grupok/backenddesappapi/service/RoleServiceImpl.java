package ar.edu.unq.grupok.backenddesappapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import ar.edu.unq.grupok.backenddesappapi.model.AppException;
import ar.edu.unq.grupok.backenddesappapi.model.Role;
import ar.edu.unq.grupok.backenddesappapi.persistence.RoleRepository;

@Service
public class RoleServiceImpl implements RoleService{
	
	@Autowired
	private RoleRepository roleRepository;

	@Override
	public void saveRole(Role role) {
		this.roleRepository.save(role);
		
	}

	@Override
	public Role getByName(String name) {
		return this.roleRepository.findByName(name).orElseThrow(() -> new AppException("Role not found by name: " + name, HttpStatus.NOT_FOUND));
	}

	
	
}
