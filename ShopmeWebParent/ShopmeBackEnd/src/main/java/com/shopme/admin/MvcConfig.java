package com.shopme.admin;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.Path;
import java.nio.file.Paths;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String dirName = "user-photos";
        Path userPhotoDir = Paths.get(dirName);

        String userPhotosPath = userPhotoDir.toFile().getAbsolutePath();
        //The resulting userPhotosPath variable will contain the absolute path of the directory represented by userPhotoDir as a string.

        registry.addResourceHandler("/" + dirName + "/**")
                .addResourceLocations("file:/" + userPhotosPath + "/");
        //The "file:/" prefix indicates that the resources should be served from the local file system.
        //
        //Putting it all together, this configuration tells Spring MVC to handle requests matching the specified pattern ("/directoryName/") by
        // serving static resources from the local directory specified by userPhotosPath. This can be useful for serving images, stylesheets,
        // or other static content from a local directory within your application.
    }
}
