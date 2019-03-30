package aws.mitocode.spring;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {                                    
    @Bean
    public Docket api() { 
        return new Docket(DocumentationType.SWAGGER_2)  
          .select()                                  
          .apis(RequestHandlerSelectors.basePackage("aws.mitocode.spring.controller.api"))              
          .paths(PathSelectors.any())                          
          .build()
          .apiInfo(apiInfo());
    }
    
    private ApiInfo apiInfo() {
        return new ApiInfo(
          "RestApi GeoServicios", 
          "Servicios Rest para el curso Mitocode", 
          "API ToS", 
          "Terms of service", 
          new Contact("@wjma90", "https://twitter.com/wjma90", "w.marchanaranda@gmail.com"), 
          "License of API", "API license URL", Collections.emptyList());
   }
}