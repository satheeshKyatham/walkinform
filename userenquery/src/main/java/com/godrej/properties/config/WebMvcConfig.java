package com.godrej.properties.config;

import javax.annotation.PostConstruct;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = { "com.godrej.properties.*"})
public class WebMvcConfig implements WebMvcConfigurer {
	@Bean
	public TilesConfigurer tilesConfigurer(){
	    TilesConfigurer tilesConfigurer = new TilesConfigurer();
	    tilesConfigurer.setDefinitions(new String[] {"/WEB-INF/views/**/tiles.xml"});
	    tilesConfigurer.setCheckRefresh(true);
	    return tilesConfigurer;
	}
	
	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		TilesViewResolver viewResolver = new TilesViewResolver();
		registry.viewResolver(viewResolver);
	}
	
   /*@Bean
   public InternalResourceViewResolver resolver() {
      InternalResourceViewResolver resolver = new InternalResourceViewResolver();
      resolver.setViewClass(JstlView.class);
      resolver.setPrefix("/WEB-INF/views/");
      resolver.setSuffix(".jsp");
      return resolver;
   }*/
 
   @Bean
   public MessageSource messageSource() {
      ResourceBundleMessageSource source = new ResourceBundleMessageSource();
      source.setBasename("messages");
      return source;
   }
 
   @Override
   public Validator getValidator() {
      LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();
      validator.setValidationMessageSource(messageSource());
      return validator;
   }
   
   @Bean
   public MultipartResolver multipartResolver() {
      CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
      multipartResolver.setMaxUploadSize(10485760); // 10MB
      multipartResolver.setMaxUploadSizePerFile(10485760); // 1MB
      return multipartResolver;
   }
   
   @Override
	 public void addResourceHandlers(final ResourceHandlerRegistry registry) {
	        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	        registry.addResourceHandler("/static/**").addResourceLocations("/static/");
	    }
	 
}