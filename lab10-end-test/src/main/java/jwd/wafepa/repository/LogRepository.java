package jwd.wafepa.repository;

import java.util.Date;
import java.util.List;

import jwd.wafepa.model.Log;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LogRepository 
	extends JpaRepository<Log, Long>{
	
	@Query("select l from Log l where (l.date = :logDate) and l.duration between :from and :to")
	List<Log> findLogsByDateDuration(@Param("logDate") Date logDate, @Param("from") Integer from, @Param("to") Integer to);
	
//	@Query("select a from Activity a where a.name = :name")
//	List<Activity> findByName(@Param("name") String name);

}
