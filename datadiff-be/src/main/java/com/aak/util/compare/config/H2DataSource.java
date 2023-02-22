package com.aak.util.compare.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class H2DataSource {


	@Autowired
	private Environment env;

	@Bean(name="H2dbDataSource",destroyMethod = "close")
	public DataSource H2dbDataSource(){
		   HikariConfig hikariConfig = new HikariConfig();
		    hikariConfig.setDriverClassName(env.getProperty("dc.h2.db.driver-class-name"));
		    hikariConfig.setJdbcUrl(env.getProperty("dc.h2.db.url")); 
		    hikariConfig.setUsername(env.getProperty("dc.h2.db.user"));
		    hikariConfig.setPassword(env.getProperty("dc.h2.db.pass"));
		
	    hikariConfig.setMaximumPoolSize(5);
	    hikariConfig.setAllowPoolSuspension(true);
	    hikariConfig.setMinimumIdle(5);
	    hikariConfig.setConnectionTimeout(300000); // 300 seconds
	    hikariConfig.setMaxLifetime(900000); //900 seconds
	    hikariConfig.setConnectionTestQuery("SELECT 1");

	    hikariConfig.setPoolName("HIKARI_H2_CONNECTION_POOL");
	    HikariDataSource dataSource = new HikariDataSource(hikariConfig);

	    return dataSource;
	}
	
	@Bean(name="H2dbJdbcTemplate")
	@Primary
	public JdbcTemplate H2dbJdbcTemplate(@Qualifier("H2dbDataSource") DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}
	
	@Bean(name="H2dbNamedParameterJdbcTemplate")
	@Primary
	public NamedParameterJdbcTemplate H2dbNamedParameterJdbcTemplate(@Qualifier("H2dbDataSource") DataSource dataSource) {
		return new NamedParameterJdbcTemplate(H2dbJdbcTemplate(dataSource));
	}
	
	@Bean(name="H2dbTransactionManager")
	@Primary
	public DataSourceTransactionManager H2dbTransactionManager(@Qualifier("H2dbDataSource") DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}
}
