package app.config;

import app.exceptions.APIException;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.javalin.Javalin;
import io.javalin.apibuilder.EndpointGroup;
import io.javalin.json.JavalinJackson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.javalin.validation.ValidationException;

import java.util.Map;

public final class ApplicationConfig {
    private static final Logger logger = LoggerFactory.getLogger(ApplicationConfig.class);
    private ApplicationConfig() {
    }

    public static Javalin createServer(EndpointGroup endpointGroup) {
        Javalin app = Javalin.create(config -> {
            config.router.contextPath = "/api";
            config.router.apiBuilder(endpointGroup);
            config.requestLogger.http((ctx, ms) ->{
                logger.info("{} {} -> {} ({} ms)", ctx.method(), ctx.path(), ctx.status().getCode(), ms );
            });
            /*
            JavaTimeModule lets Jackson handle Java dates (Instant, LocalDate, LocalDateTime)
            WRITE_DATES_AS_TIMESTAMPS - disable: Dates are sent as readable Dates and Time ("2026-10-27T10:59:59Z")
            instead of a UNIX timestamp (12345678765678).
             */
            JavalinJackson mapper = new JavalinJackson();
            mapper.updateMapper(objectMapper -> objectMapper.registerModule(new JavaTimeModule())
                    .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS));
            config.jsonMapper(mapper);
        });

        app.exception(APIException.class, (e, ctx) ->{
            ctx.status(e.getCode());
            ctx.json(Map.of("status", e.getCode(), "msg", e.getMessage()));
        });

        app.exception(Exception.class, (e, ctx) ->{
            logger.error("Unhandled exception on {} {}", ctx.method(), ctx.path(), e);
            ctx.status(500);
            ctx.json(Map.of("status", 500, "msg", "Internal server error"));
        });

        app.exception(ValidationException.class, (e, ctx) ->{
            String msg = e.getErrors().values().iterator().next().get(0).getMessage();
            ctx.status(400);
            ctx.json(Map.of("status", 400, "msg", msg));
        });
        return app;
    }
}
