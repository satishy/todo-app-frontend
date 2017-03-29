package com.camellia.systems.todo.config;

import org.hsqldb.util.DatabaseManagerSwing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

@ComponentScan({ "com.camellia" })
@Configuration
public class SpringRootConfig {

	@Autowired
	DataSource dataSource;

	@Bean
	public NamedParameterJdbcTemplate getNamedParameterJdbcTemplate() {
		return new NamedParameterJdbcTemplate(dataSource);
	}

	@PostConstruct
	public void startDBManager() throws Exception {

		// hsqldb
		DatabaseManagerSwing.main(new String[]{"--url",
				"jdbc:hsqldb:mem:testdb", "--user", "sa", "--password", ""});


	}
}