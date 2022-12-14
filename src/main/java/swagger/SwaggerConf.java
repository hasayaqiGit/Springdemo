package swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

import static springfox.documentation.builders.PathSelectors.regex;

@EnableSwagger2
@Configuration
public class SwaggerConf {
    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("java"))
                .paths(regex("/api.*"))
                .build().apiInfo(metaInfo());

    }
    private ApiInfo metaInfo() {

        ApiInfo apiInfo=new ApiInfo("User Api", "User Api methods", "1.0", "Terms of Service", new Contact("Xinyu","","abc@gmail.com"), "License for Student Details ", "Url of student", Collections.EMPTY_LIST);

        return apiInfo;
    }
}
