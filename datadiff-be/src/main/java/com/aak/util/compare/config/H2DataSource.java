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

	@Bean(name="PostgresDataSource",destroyMethod = "close")
	public DataSource postgresDataSource(){
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
	
	@Bean(name="PostgresJdbcTemplate")
	@Primary
	public JdbcTemplate postgresJdbcTemplate(@Qualifier("PostgresDataSource") DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}
	
	@Bean(name="PostgresNamedParameterJdbcTemplate")
	@Primary
	public NamedParameterJdbcTemplate postgresNamedParameterJdbcTemplate(@Qualifier("PostgresDataSource") DataSource dataSource) {
		return new NamedParameterJdbcTemplate(postgresJdbcTemplate(dataSource));
	}
	
	@Bean(name="PostgresTransactionManager")
	@Primary
	public DataSourceTransactionManager PostgresTransactionManager(@Qualifier("PostgresDataSource") DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}
}
