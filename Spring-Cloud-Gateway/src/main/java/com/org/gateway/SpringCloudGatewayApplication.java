package com.org.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;

@SpringBootApplication
@EnableDiscoveryClient
public class SpringCloudGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudGatewayApplication.class, args);
	}

	@Bean
	public RouteLocator routerBuilder(RouteLocatorBuilder routeLocatorBuilder) {
		return routeLocatorBuilder.routes()
				.route("Employee-Service", r -> r.path("/employee/**").uri("http://localhost:8081/"))
				.route("Employee-Service", r -> r.path("/employee-address/**").uri("http://localhost:8081/"))
				.route("Address-Service", r -> r.path("/address/**").uri("lb://ADDRESS-SERVICE"))
				.build();
	}
}