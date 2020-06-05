package com.flock.frule;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.resource.loader.StringResourceLoader;
import org.apache.velocity.runtime.resource.util.StringResourceRepository;

import java.io.StringWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class VelocityExample {

    public static void main(String[] args) {
        VelocityEngine velocityEngine = new VelocityEngine();
        velocityEngine.setProperty(Velocity.RESOURCE_LOADER, "string");
        velocityEngine.setProperty("resource.loader.string.class", StringResourceLoader.class.getName());
        velocityEngine.init();

// Add template to repository
        StringResourceRepository repository = StringResourceLoader.getRepository();
        repository.putStringResource("hello_world", " Hello { $payload.from.firstname }, { $payload.array[1]. }");

// Set parameters
        VelocityContext context = new VelocityContext();
        Map from = new HashMap<>();
        from.put("firstname","Anshul");
        from.put("lastname","Gutpa");


        Map payload = new HashMap<>();
        payload.put("from",from);
        payload.put("array",Arrays.asList(1,2,3));

        context.put("payload",payload);

// Process the template
        StringWriter writer = new StringWriter();
        velocityEngine.getTemplate("hello_world").merge( context, writer );
        System.out.println(writer.toString());
    }
}
