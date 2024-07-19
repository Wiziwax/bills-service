package org.interswitch.billsservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
//		(scanBasePackages = {"org.interswitch.billsservice", "org.interswitch.onboardingservice"})
@EnableDiscoveryClient
//@EnableJpaRepositories
@EnableFeignClients
//		(basePackages = {"org.interswitch.billsservice.Repositories", "org.interswitch.onboardingservice.Repositories"})
public class BillsServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BillsServiceApplication.class, args);
	}

}
