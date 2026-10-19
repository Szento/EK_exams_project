package app.config;

import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.javalin.Javalin;
import io.javalin.json.JavalinJackson;

public final class ApplicationConfig {
    private ApplicationConfig() {
    }

    public static Javalin createServer() {
        return Javalin.create(config -> {
            config.router.contextPath = "/api";

            JavalinJackson mapper = new JavalinJackson();
            mapper.updateMapper(objectMapper -> objectMapper.registerModule(new JavaTimeModule()));
            config.jsonMapper(mapper);
        });
    }
}
