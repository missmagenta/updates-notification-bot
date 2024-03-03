package edu.java.model.errorhandling;

import edu.java.model.errorhandling.dto.ApiErrorResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.core.annotation.AliasFor;
import org.springframework.http.MediaType;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@ApiResponse(content = @Content(
    schema = @Schema(implementation = ApiErrorResponse.class),
    mediaType = MediaType.APPLICATION_JSON_VALUE))
public @interface DefaultApiErrorResponse {
    @AliasFor(annotation = ApiResponse.class, attribute = "code")
    String code();

    @AliasFor(annotation = ApiResponse.class, attribute = "description")
    String description();
}
