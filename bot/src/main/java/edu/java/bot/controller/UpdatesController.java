package edu.java.bot.controller;

import edu.java.model.errorhandling.dto.ApiErrorResponse;
import edu.java.bot.service.UpdateService;
import edu.java.request.LinkUpdateRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
public class UpdatesController {

    private final UpdateService updateService;

    @Operation(summary = "Отправить обновление")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200",
                     description = "Обновление обработано",
                     content = @Content),
        @ApiResponse(responseCode = "400",
                     description = "Некорректные параметры запроса",
                     content = @Content(schema = @Schema(implementation = ApiErrorResponse.class),
                                        mediaType = MediaType.APPLICATION_JSON_VALUE))
    })
    @PostMapping("/updates")
    public void sendUpdate(@Valid @RequestBody LinkUpdateRequest linkUpdateRequest) {
        updateService.sendUpdate(linkUpdateRequest);
    }
}
