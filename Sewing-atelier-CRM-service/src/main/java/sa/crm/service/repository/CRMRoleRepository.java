package sa.crm.service.repository;

import java.util.Optional;

import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;

import sa.crm.service.entity.CRMRole;

@Repository
public interface CRMRoleRepository extends CrudRepository<CRMRole, Integer> {
	
	Optional<CRMRole> findByName(String name);
}
