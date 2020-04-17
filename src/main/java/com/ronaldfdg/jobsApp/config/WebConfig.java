package com.ronaldfdg.jobsApp.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

	@Value("${jobsApp.route.images}")
	private String routeImages;
	
	@Value("${jobsApp.route.cvs}")
	private String routeCvs;
	
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/logos/**").addResourceLocations("file:" + routeImages);
		registry.addResourceHandler("/curriculumns/**").addResourceLocations("file:" + routeCvs);
	}
	
}
