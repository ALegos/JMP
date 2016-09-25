package com.epam.jmp.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.epam.jmp")
public class JMPConfiguration {// extends WebMvcConfigurerAdapter {

	// @Override
	// public void configureContentNegotiation(ContentNegotiationConfigurer
	// configurer) {
	// configurer.ignoreAcceptHeader(true).defaultContentType(MediaType.TEXT_HTML).mediaType("html",
	// MediaType.TEXT_HTML);
	// // .mediaType("json", MediaType.APPLICATION_JSON)
	// // .mediaType("xml", MediaType.APPLICATION_XML);
	// }
	//
	// @Bean
	// public ViewResolver
	// contentNegotiatingViewResolver(ContentNegotiationManager manager) {
	// ContentNegotiatingViewResolver resolver = new
	// ContentNegotiatingViewResolver();
	// resolver.setContentNegotiationManager(manager);
	//
	// // Define all possible view resolvers
	// List<ViewResolver> resolvers = new ArrayList<>();
	//
	// // resolvers.add(jsonViewResolver());
	// // resolvers.add(jaxb2MarshallingXmlViewResolver());
	// resolvers.add(jspViewResolver());
	//
	// resolver.setViewResolvers(resolvers);
	// return resolver;
	// }
	//
	// // @Bean
	// public ViewResolver jaxb2MarshallingXmlViewResolver() {
	// Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
	// // TODO define marshaling entities
	//
	// // marshaller.setClassesToBeBound(Pizza.class);
	// return (viewName, locale) -> {
	// MarshallingView view = new MarshallingView();
	// view.setMarshaller(marshaller);
	// return view;
	// };
	// }
	//
	// // @Bean
	// public ViewResolver jsonViewResolver() {
	// return (viewName, locale) -> {
	// MappingJackson2JsonView view = new MappingJackson2JsonView();
	// view.setPrettyPrint(true);
	// return view;
	// };
	// }

	@Bean
	public ViewResolver jspViewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/WEB-INF/views/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}

}