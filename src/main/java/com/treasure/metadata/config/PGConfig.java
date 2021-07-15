package com.treasure.metadata.config;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableJpaRepositories(
		entityManagerFactoryRef = "entityManager",
		transactionManagerRef = "transactionManager",
		basePackages = {
				"com.treasure.metadata.repository.pg"
		}
)
@EntityScan(basePackages = {"com.treasure.metadata.model.pg"})
public class PGConfig {

	@Value("${spring.datasource.url}")
	private String dataSourceUrl;

	@Value("${spring.datasource.username}")
	private String username;

	@Value("${spring.datasource.password}")
	private String password;

	@Value("${spring.datasource.driver-class-name}")
	private String driverClass;


	@Bean(name = "pgDatasource")
	@Primary
	public DataSource dataSource() {
		return DataSourceBuilder.create()
				.driverClassName(this.driverClass)
				.url(this.dataSourceUrl)
				.username(this.username)
				.password(this.password)
				.build();
	}

    @Bean(name="entityManager")
    @Primary
    public LocalContainerEntityManagerFactoryBean getEntityManager() throws SQLException {
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setDatabase(Database.POSTGRESQL);
        vendorAdapter.setGenerateDdl(false);

        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(vendorAdapter);
        factory.setPackagesToScan("com.treasure.metadata.model.pg");
        factory.setDataSource(this.dataSource());

        return factory;
    }

	@Bean(name="transactionManager")
	@Primary
	public PlatformTransactionManager transactionManager() throws SQLException {
		JpaTransactionManager txManager = new JpaTransactionManager();
		txManager.setDataSource(this.dataSource());
		txManager.setEntityManagerFactory(this.getEntityManager().getObject());
		return txManager;
	}

}
