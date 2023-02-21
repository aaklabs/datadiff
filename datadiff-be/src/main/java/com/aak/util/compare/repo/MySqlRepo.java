package com.aak.util.compare.repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class MySqlRepo extends DBRepo{

	public MySqlRepo(@Autowired @Qualifier("MySqlJdbcTemplate") JdbcTemplate jdbcTemplate) {
		super(jdbcTemplate);
	}

}
