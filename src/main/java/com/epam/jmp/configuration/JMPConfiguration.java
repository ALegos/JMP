package com.epam.jmp.configuration;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.http.MediaType;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;
import org.springframework.web.servlet.view.xml.MarshallingView;

@Configuration
@EnableWebMvc
@ComponentScan("com.epam.jmp")
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class JMPConfiguration extends WebMvcConfigurerAdapter {
	
	private static final String EXTENTION_PATH_PARAMETER = "mediaType";
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}
	
	@Override
	public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
		UrlBasedContentNegotiationStrategy urlBasedContentNegotiationStrategy = new UrlBasedContentNegotiationStrategy(
				MediaType.APPLICATION_JSON);
		// should be for using path parameter in process of resolving media type
		urlBasedContentNegotiationStrategy.setPathParameterName(EXTENTION_PATH_PARAMETER);
		
		//@formatter:off
		configurer.ignoreAcceptHeader(true)
			.favorParameter(true)
			.parameterName(EXTENTION_PATH_PARAMETER)
			.mediaType("json", MediaType.APPLICATION_JSON)
			.mediaType("xml", MediaType.APPLICATION_XML)
			.defaultContentTypeStrategy(urlBasedContentNegotiationStrategy);
		//@formatter:on
	}
	
	@Bean
	public ViewResolver contentNegotiatingViewResolver(ContentNegotiationManager manager) {
		ContentNegotiatingViewResolver resolver = new ContentNegotiatingViewResolver();
		resolver.setContentNegotiationManager(manager);
		
		// Define all possible view resolvers
		List<ViewResolver> resolvers = new ArrayList<>();
		
		resolvers.add(jsonViewResolver());
		resolvers.add(jaxb2MarshallingXmlViewResolver());
		resolvers.add(jspViewResolver());
		resolver.setViewResolvers(resolvers);
		return resolver;
	}
	
	@Bean
	public ViewResolver jaxb2MarshallingXmlViewResolver() {
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		// TODO define marshaling entities
		
		// marshaller.setClassesToBeBound(Pizza.class);
		return (viewName, locale) -> {
			MarshallingView view = new MarshallingView();
			view.setMarshaller(marshaller);
			return view;
		};
	}
	
	@Bean
	public ViewResolver jsonViewResolver() {
		return (viewName, locale) -> {
			MappingJackson2JsonView view = new MappingJackson2JsonView();
			view.setPrettyPrint(true);
			return view;
		};
	}
	
	@Bean
	public ViewResolver jspViewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/WEB-INF/views/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}
	
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
	
}