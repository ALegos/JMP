package com.epam.jmp.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.persistence.EntityGraph;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.epam.jmp.model.MentorshipProgram;

@Repository
public class MentorshipProgramDAO extends GenericDAO<MentorshipProgram> {
	
	public List<MentorshipProgram> findByOfficeLocation(String office) {
		return this.getAll().stream().filter(p -> office.equalsIgnoreCase(p.getOfficeLocation()))
				.collect(Collectors.toList());
	}
	
	public List<MentorshipProgram> findByStartDate(Date date) {
		List<MentorshipProgram> result = null;
		if (date == null) {
			result = this.getAll();
		} else {
			CriteriaBuilder builder = this.getEntityManager().getCriteriaBuilder();
			CriteriaQuery<MentorshipProgram> cQuery = builder.createQuery(this.getType());
			Root<MentorshipProgram> programRoot = cQuery.from(this.getType());
			cQuery.select(programRoot);
			ParameterExpression<Date> dateParam = builder.parameter(Date.class);
			cQuery.where(builder.greaterThanOrEqualTo(programRoot.get("startDate"), dateParam));
			TypedQuery<MentorshipProgram> query = this.getEntityManager().createQuery(cQuery);
			query.setParameter(dateParam, date);
			result = query.getResultList();
		}
		return result;
	}
	
	@SuppressWarnings("rawtypes")
	public MentorshipProgram getFullByUid(String uid) {
		EntityGraph eg = this.getEntityManager().getEntityGraph("populatedProgram");
		Map<String, Object> hints = new HashMap<>();
		hints.put("javax.persistence.loadgraph", eg);
		return this.getEntityManager().find(this.getType(), uid, hints);
	}
}
