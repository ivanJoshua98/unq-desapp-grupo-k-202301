package ar.edu.unq.grupok.backenddesappapi.service;

import ar.edu.unq.grupok.backenddesappapi.model.Role;

public interface RoleService {

	public void saveRole(Role role);
	public Role getByName(String name);
}
