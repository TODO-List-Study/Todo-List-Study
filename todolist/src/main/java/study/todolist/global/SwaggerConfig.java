package study.todolist.global;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api(){ // Docket -> Swagger 설정의 핵심

        return new Docket(DocumentationType.OAS_30)
                .select() // Api selectorBuilder 생성
                .apis(RequestHandlerSelectors.basePackage("study.todolist.controller")) // API 패키지 경로 설정
                .paths(PathSelectors.ant("/todo/**")) // 특정 경로에 대해 문서화
//                .paths(PathSelectors.any()) // 모든 API 경로에 대해 문서화
                .build()
                .apiInfo(apiInfo()) // API 문서에 대한 정보 추가
                .securityContexts(Arrays.asList(securityContext()))
                .securitySchemes(Arrays.asList(apiKey()));
    }

    private ApiInfo apiInfo(){

        return new ApiInfoBuilder()
                .title("meeti API 문서")
                .description("공유 오피스 대여 서비스 플랫폼 meeti의 API에 대해 설명하는 문서입니다.")
                .version("1.0")
                .build();
    }

    private SecurityContext securityContext(){

        return SecurityContext.builder()
                .securityReferences(defaultAuth())
                .build();
    }

    private List<SecurityReference> defaultAuth(){

        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;

        return Arrays.asList(new SecurityReference("Authorization", authorizationScopes));
    }

    private ApiKey apiKey(){

        return new ApiKey("Authorization", "Authorization", "Header");
    }
}
