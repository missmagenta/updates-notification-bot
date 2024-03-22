package edu.java.controller;

import edu.java.model.errorhandling.DefaultApiErrorResponse;
import edu.java.model.errorhandling.dto.ApiErrorResponse;
import edu.java.service.TgChatService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j

@DefaultApiErrorResponse(code = "400", description = "Некорректные параметры запроса")
@RequestMapping("/tg-chat")
@RestController
public class TgChatController {
    private final TgChatService chatService;

    public TgChatController(
        @Qualifier("jdbcTgChatService") TgChatService chatService) {
        this.chatService = chatService;
    }

    @Operation(summary = "Зарегистрировать чат")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200",
                     description = "Чат зарегистрирован",
                     content = @Content)
    })
    @PostMapping("/{id}")
    public void registerChat(@PathVariable("id") int id) {
        log.info("Chat is registered");
        chatService.register(id);
    }

    @Operation(summary = "Удалить чат")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200",
                     description = "Чат успешно удален"),
        @ApiResponse(responseCode = "404",
                     description = "Чат не существует",
                     content = @Content(
                         schema = @Schema(implementation = ApiErrorResponse.class),
                         mediaType = MediaType.APPLICATION_JSON_VALUE
                     ))
    })
    @DeleteMapping("/{id}")
    public void deleteChat(@PathVariable("id") int id) {
        log.info("Chat removed");
        chatService.unregister(id);
    }

}
