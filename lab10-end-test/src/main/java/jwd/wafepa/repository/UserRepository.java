package jwd.wafepa.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jwd.wafepa.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	@Query("select a from User a where a.firstName = :firstName")
	List<User> findByFirstName(@Param("firstName") String firstName);
	
	@Query("select a from User a where a.username = :username")
	Optional<User> findByUsername(@Param("username") String username);
	
	//@Query("select u from User u join u.address address")
	//List<User> findAllUserForAddress();
	
	//@Query("select distinct  a from Activity a join a.logs logss where a.id is not null")
	//Page<Activity> findOnlyHasLogsActivity(Pageable pageable);

}
