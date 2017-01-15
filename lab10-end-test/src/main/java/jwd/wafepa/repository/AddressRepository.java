package jwd.wafepa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jwd.wafepa.model.Address;
import jwd.wafepa.model.Todo;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long>{

}
