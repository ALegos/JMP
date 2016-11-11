package com.epam.jmp.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.persistence.EntityGraph;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;

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
		TypedQuery<Person> query = getEntityManager().createQuery(queryString.toString(), Person.class);
		query.setParameter("number", number);
		query.setParameter("status", status);
		return query.getResultList();
	}
	
	public List<Person> findByIsManagerFlag(Boolean isManager) {
		List<Person> result = null;
		if (isManager == null) {
			result = this.getAll();
		} else {
			CriteriaBuilder builder = this.getEntityManager().getCriteriaBuilder();
			CriteriaQuery<Person> cQuery = builder.createQuery(this.getType());
			Root<Person> pesonRoot = cQuery.from(this.getType());
			cQuery.select(pesonRoot);
			ParameterExpression<Boolean> isManagerParam = builder.parameter(Boolean.class);
			cQuery.where(builder.equal(pesonRoot.get("isManager"), isManagerParam));
			TypedQuery<Person> query = this.getEntityManager().createQuery(cQuery);
			query.setParameter(isManagerParam, isManager);
			result = query.getResultList();
		}
		return result;
	}
	
	@SuppressWarnings("rawtypes")
	public Person getByUidWithManagerAndAssignment(String uid) {
		EntityGraph eg = this.getEntityManager().getEntityGraph("personWithManagerAndAssignment");
		Map<String, Object> hints = new HashMap<>();
		hints.put("javax.persistence.loadgraph", eg);
		return this.getEntityManager().find(this.getType(), uid, hints);
	}
	
}
