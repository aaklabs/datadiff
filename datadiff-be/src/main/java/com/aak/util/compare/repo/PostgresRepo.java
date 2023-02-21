package com.aak.util.compare.repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class PostgresRepo extends DBRepo{
	public PostgresRepo(@Autowired @Qualifier("PostgresJdbcTemplate") JdbcTemplate jdbcTemplate) {
		super(jdbcTemplate);
	}
}
