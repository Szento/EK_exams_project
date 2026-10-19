package app.config;

import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.javalin.Javalin;
import io.javalin.apibuilder.EndpointGroup;
import io.javalin.json.JavalinJackson;

public final class ApplicationConfig {
    private ApplicationConfig() {
    }

    public static Javalin createServer(EndpointGroup endpointGroup) {
        return Javalin.create(config -> {
            config.router.contextPath = "/api";
            config.router.apiBuilder(endpointGroup);

            JavalinJackson mapper = new JavalinJackson();
            mapper.updateMapper(objectMapper -> objectMapper.registerModule(new JavaTimeModule()));
            config.jsonMapper(mapper);
        });
    }
}
