package com.treasure.metadata.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.datastax.oss.driver.api.core.CqlSession;

@Configuration
public class CassandraConfig {

	@Value("${spring.data.cassandra.keyspace-name}")
	private String keyspaceName;

	public @Bean CqlSession session() {
		return CqlSession.builder().withKeyspace(this.keyspaceName).build();
	}

}
