package com.qa.ToDoListAPI;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

// A @Configuration class is used to supply beans to the application context
// and configure the application
@Configuration
public class ApplicationConfiguration {

	// A new bean is created on every request for this ModelMapper bean
	@Bean
	@Scope("prototype")
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
}
