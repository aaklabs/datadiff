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

@Configuration
public class MySqlDataSource {

	@Autowired
	private Environment env;

	@Primary
	@Bean(name="MysqlDataSource",destroyMethod = "close")
	public DataSource mysqlDataSource(){
	    HikariConfig hikariConfig = new HikariConfig();
	    hikariConfig.setDriverClassName(env.getProperty("dc.mysql.db.driver-class-name"));
	    hikariConfig.setJdbcUrl(env.getProperty("dc.mysql.db.url")); 
	    hikariConfig.setUsername(env.getProperty("dc.mysql.db.user"));
	    hikariConfig.setPassword(env.getProperty("dc.mysql.db.pass"));

	    hikariConfig.setMaximumPoolSize(5);
	    hikariConfig.setAllowPoolSuspension(true);
	    hikariConfig.setMinimumIdle(5);
	    hikariConfig.setConnectionTimeout(300000); // 300 seconds
	    hikariConfig.setMaxLifetime(900000); //900 seconds
	    hikariConfig.setConnectionTestQuery("SELECT 1");

	    hikariConfig.addDataSourceProperty("cachePrepStmts", true);
	    hikariConfig.addDataSourceProperty("prepStmtCacheSize", 250);
	    hikariConfig.addDataSourceProperty("prepStmtCacheSqlLimit", 2048);
	    hikariConfig.addDataSourceProperty("useServerPrepStmts", true);
	    hikariConfig.addDataSourceProperty("initializationFailFast", true);
	    hikariConfig.setPoolName("HIKARI_MYSQL_CONNECTION_POOL");
	    HikariDataSource dataSource = new HikariDataSource(hikariConfig);

	    return dataSource;
	}
	
	@Primary
	@Bean(name="MySqlJdbcTemplate")
	public JdbcTemplate mysqlJdbcTemplate(@Qualifier("MysqlDataSource") DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}
	
	@Primary
	@Bean(name="MySqlNamedParameterJdbcTemplate")
	public NamedParameterJdbcTemplate mysqlNamedParameterJdbcTemplate(@Qualifier("MysqlDataSource") DataSource dataSource) {
		return new NamedParameterJdbcTemplate(mysqlJdbcTemplate(dataSource));
	}
	
	@Primary
	@Bean(name="MySqlTransactionManager")
	public DataSourceTransactionManager mysqlDataSourceTransactionManager(@Qualifier("MysqlDataSource") DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}
	
}
