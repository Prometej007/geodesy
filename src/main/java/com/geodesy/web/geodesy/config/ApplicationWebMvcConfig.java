package com.geodesy.web.geodesy.config;


import com.geodesy.web.geodesy.GeodesyApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by danul on 27.04.2017.
 */

@Configuration
@ComponentScan(basePackages = {"com.geodesy.web"})
@Import({GeodesyApplication.class})
public class ApplicationWebMvcConfig extends WebMvcConfigurerAdapter {
    String rootPath = System.getProperty("catalina.home");
    String[] PATH={
            "/static/",
            "/templates/",
            "file:/" + rootPath + "/resources/"

    };

    @Override
    public void addResourceHandlers(final ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/")
                .addResourceLocations(PATH);
        registry.addResourceHandler("/file/**")
                .addResourceLocations("file:/" + rootPath + "/resources/");
    }

}
