package sa.crm.service.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import sa.crm.service.entity.CRMUser;

@Repository
public interface CRMUserRepository extends CrudRepository<CRMUser, Long> {
	
	Optional<CRMUser> findByUsername(String username);
}
