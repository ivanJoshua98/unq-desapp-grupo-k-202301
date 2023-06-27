package ar.edu.unq.grupok.backenddesappapi.security;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ar.edu.unq.grupok.backenddesappapi.model.Role;
import ar.edu.unq.grupok.backenddesappapi.model.UserModel;
import ar.edu.unq.grupok.backenddesappapi.service.UserService;

@Service
public class CustomUsersDetailsService implements UserDetailsService {

	@Autowired
	private UserService userService;

	public Collection<GrantedAuthority> mapToAuthorities(List<Role> roles){
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
	}
	
	@Override
	public UserDetails loadUserByUsername(String usermail) throws UsernameNotFoundException {
		UserModel user = this.userService.getUserByEmail(usermail);
		return new User(user.getEmail(), 
						user.getPassword(),
						true,
						true,
						true,
						true,
						mapToAuthorities(user.getRoles()));
	}

} 
