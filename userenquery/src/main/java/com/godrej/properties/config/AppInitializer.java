package com.godrej.properties.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.godrej.properties.context.filter.SessionListener;

public class AppInitializer extends
        AbstractAnnotationConfigDispatcherServletInitializer {
//	private int maxUploadSizeInMb = 5 * 1024 * 1024; // 5 MB
   @Override
   protected Class<?>[] getRootConfigClasses() {
      return new Class[] { HibernateConfiguration.class };
   }
 
   @Override
   protected Class<?>[] getServletConfigClasses() {
      return new Class[] { WebMvcConfig.class };
   }
 
   @Override
   protected String[] getServletMappings() {
      return new String[] { "/" };
   }
   
   @Override
   public void onStartup(ServletContext servletContext) throws ServletException {
	   super.onStartup(servletContext);
       servletContext.addListener(new SessionListener());
   }
   
  /* @Override
   protected void customizeRegistration(ServletRegistration.Dynamic registration) {

       // upload temp file will put here
       File uploadDirectory = new File(System.getProperty("java.io.tmpdir"));

       // register a MultipartConfigElement
       MultipartConfigElement multipartConfigElement =
               new MultipartConfigElement(uploadDirectory.getAbsolutePath(),
                       maxUploadSizeInMb, maxUploadSizeInMb * 2, maxUploadSizeInMb / 2);

       registration.setMultipartConfig(multipartConfigElement);

   }*/
}