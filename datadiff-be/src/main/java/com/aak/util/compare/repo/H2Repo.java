package com.aak.util.compare.repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class H2Repo extends DBRepo{
	public H2Repo(@Autowired @Qualifier("H2dbJdbcTemplate") JdbcTemplate jdbcTemplate) {
		super(jdbcTemplate);
	}
}
