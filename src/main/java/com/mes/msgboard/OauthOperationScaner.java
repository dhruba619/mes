package com.mes.msgboard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;

import com.fasterxml.classmate.TypeResolver;
import com.google.common.collect.Multimap;

import springfox.documentation.builders.ApiListingBuilder;
import springfox.documentation.builders.OperationBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiDescription;
import springfox.documentation.service.ApiListing;
import springfox.documentation.service.Operation;
import springfox.documentation.spring.web.plugins.DocumentationPluginsManager;
import springfox.documentation.spring.web.readers.operation.CachingOperationNameGenerator;
import springfox.documentation.spring.web.scanners.ApiDescriptionReader;
import springfox.documentation.spring.web.scanners.ApiListingScanner;
import springfox.documentation.spring.web.scanners.ApiListingScanningContext;
import springfox.documentation.spring.web.scanners.ApiModelReader;

public class OauthOperationScaner extends ApiListingScanner {

	@Autowired
    private TypeResolver typeResolver;

    @Autowired
    public OauthOperationScaner(ApiDescriptionReader apiDescriptionReader, ApiModelReader apiModelReader, DocumentationPluginsManager pluginsManager)
    {
        super(apiDescriptionReader, apiModelReader, pluginsManager);
    }

    @Override
    public Multimap<String, ApiListing> scan(ApiListingScanningContext context)
    {
        final Multimap<String, ApiListing> def = super.scan(context);

        final List<ApiDescription> apis = new LinkedList<>();

        final List<Operation> operations = new ArrayList<>();
        Set<String> tag = new HashSet<>();
        tag.add("oauth");
        
        operations.add(new OperationBuilder(new CachingOperationNameGenerator())
            .method(HttpMethod.POST)
            .uniqueId("login")
            .parameters(Arrays.asList(new ParameterBuilder()
                .name("username")
                .description("The username")
                .parameterType("form")            
                .type(typeResolver.resolve(String.class))
                .modelRef(new ModelRef("string"))
                .build(), 
                new ParameterBuilder()
                .name("password")
                .description("The password")
                .parameterType("form")            
                .type(typeResolver.resolve(String.class))
                .modelRef(new ModelRef("string"))
                .build(), 
                new ParameterBuilder()
                .name("grant_type")
                .description("the grant type").defaultValue("password")
                .parameterType("form")            
                .type(typeResolver.resolve(String.class))
                .modelRef(new ModelRef("string"))
                .build(), 
                new ParameterBuilder()
                .name("client_id")
                .description("client idetifier")
                .parameterType("form")            
                .type(typeResolver.resolve(String.class))
                .modelRef(new ModelRef("string"))
                .build(), 
                new ParameterBuilder()
                .name("client_secret")
                .description("the client secret")
                .parameterType("form")            
                .type(typeResolver.resolve(String.class))
                .modelRef(new ModelRef("string"))
                .build()))
            .summary("Get Oauth Token") 
            .notes("Here you can get oauth token").tags(tag).position(0)
            .build());
        
        apis.add(new ApiDescription("/oauth/token", "Authentication documentation", operations, false));

        def.put("authentication", new ApiListingBuilder(context.getDocumentationContext().getApiDescriptionOrdering())
            .apis(apis)
            .description("Custom authentication")
            .build());

        return def;
    }

}
