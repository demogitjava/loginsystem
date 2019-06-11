package de.jsoft;

import org.h2.tools.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.sql.SQLException;

@EnableAutoConfiguration
@SpringBootApplication
@ComponentScan(basePackages = "de.jsoft")
public class UserRegistrationSystemApplication {

	//@PersistenceContext(name = "default")
	//private EntityManager em;

	public static void main(String[] args)
	{
		startH2Server();
		SpringApplication.run(UserRegistrationSystemApplication.class, args);
	}

	private static void startH2Server()
	{
		try {
			Server h2Server = Server.createTcpServer().start();
			if (h2Server.isRunning(true))
			{
				System.out.print("H2 server was started and is running.");
			} else {
				throw new RuntimeException("Could not start H2 server.");
			}
		} catch (SQLException e) {
			throw new RuntimeException("Failed to start H2 server: ", e);
		}
	}

	@Bean
	public DataSource dataSource(){
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("org.h2.Driver");
		dataSource.setUrl("jdbc:h2:tcp://localhost/~/demodb");
		dataSource.setUsername( "admin" );
		dataSource.setPassword( "jj78mvpr52k1" );
		return dataSource;
	}


}
