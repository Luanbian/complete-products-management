package study.project.Productmanagement;

import org.flywaydb.core.Flyway;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ProductManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductManagementApplication.class, args);
	}
	@Bean
	public Flyway flyway() {
		Flyway flyway = Flyway.configure().dataSource("jdbc:postgresql://localhost:5432/products", "luan", "02122001").load();
		flyway.migrate();
		return flyway;
	}
}