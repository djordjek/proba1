package jwd.wafepa.repository.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import jwd.wafepa.model.QUser;
import jwd.wafepa.model.User;
import jwd.wafepa.model.enumeration.UserRole;

import java.util.Optional;

import java.util.List;

import javax.inject.Inject;

import com.querydsl.jpa.JPQLQueryFactory;

public class UserRepositoryImpl {
	
	private final Logger log = LoggerFactory.getLogger(UserRepositoryImpl.class);
	
	/*@Inject
    private JPQLQueryFactory factory;

    public List<User> findByFirstName(String firstName) {
        log.trace(".findByFirstName(firstName: {})", firstName);
        final QUser user = QUser.user;
        return factory.select(user).from(user).where(user.firstName.eq(firstName)).fetch();
    }

    public List<User> findByLastName(String lastName) {
        log.trace(".findByLastName(lastName: {})", lastName);
        final QUser user = QUser.user;
        return factory.select(user).from(user).where(user.lastName.eq(lastName)).fetch();
    }

    public List<User> findByEmail(String email) {
        log.trace(".findByEmail(email: {})", email);
        final QUser user = QUser.user;
        return factory.select(user).from(user).where(user.email.eq(email)).fetch();
    }

    public List<User> findByCompany(String company) {
        log.trace(".findByCompany(company: {})", company);
        final QUser user = QUser.user;
        return factory.select(user).from(user).where(user.company.eq(company)).fetch();
    }

    public List<User> findByRole(UserRole role) {
        log.trace(".findByRole(role: {})", role);
        final QUser user = QUser.user;
        return factory.select(user).from(user).where(user.role.eq(role)).fetch();
    }

    public Optional<User> findByUsername(String username) {
        log.trace(".findByUsername(username: {})", username);
        final QUser user = QUser.user;
        return Optional.ofNullable(factory.select(user).from(user).where(user.username.eq(username)).fetchOne());
    }
    public List<User> findByPasswordHash(String passwordHash) {
        log.trace(".findByPasswordHash(passwordHash)");
        final QUser user = QUser.user;
        return factory.select(user).from(user).where(user.passwordHash.eq(passwordHash)).fetch();
    }

    public List<User> allUsers() {
        log.trace(".allUsers()");
        final QUser user = QUser.user;
        return factory.select(user).from(user).fetch();
    }*/


}
