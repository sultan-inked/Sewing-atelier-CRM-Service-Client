package sa.crm.service.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import sa.crm.service.entity.CRMRole;
import sa.crm.service.repository.CRMRoleRepository;

@Service
@RequiredArgsConstructor
public class CRMRoleService {
	
	private final CRMRoleRepository roleRepository;
	
	public CRMRole getUserRole() {
		return roleRepository.findByName("ROLE_USER").get();
	}
}
