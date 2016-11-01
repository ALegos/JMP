package com.epam.jmp.dao;

import java.util.List;
import java.util.Optional;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.epam.jmp.model.Person;

@Repository
@Transactional
public class PersonDAO extends GenericDAO<Person> {
	
	public Optional<Person> findByEmail(String email) {
		return this.getAll().stream().filter(p -> email.equals(p.getEmail())).findFirst();
	}
	
	public List<Person> findMentorsWithMoreThanSpecifiedMentees(Boolean status, Integer number) {
		final StringBuffer queryString = new StringBuffer("SELECT p from ");
		queryString.append(getType().getSimpleName()).append(" p ");
		queryString.append("JOIN p.subordinates s");
		queryString.append("WHERE p.subordinates IS NOT EMPTY AND SIZE(p.subordinates) > :number");
		queryString.append("AND s.excluded = :status");
		TypedQuery<Person> query = getManager().createQuery(queryString.toString(), Person.class);
		query.setParameter("number", number);
		query.setParameter("status", status);
		return query.getResultList();
	}
	
}
