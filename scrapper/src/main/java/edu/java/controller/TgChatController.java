package edu.java.controller;

import edu.java.model.errorhandling.ApiErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@ApiErrorResponse(code = "400", description = "Некорректные параметры запроса")
@RequestMapping("/tg-chat")
public class TgChatController {

    @Operation(summary = "Зарегистрировать чат")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200",
                     description = "Чат зарегистрирован",
                     content = @Content)
    })
    @PostMapping("/{id}")
    public void registerChat(@PathVariable("id") int id) {

    }

    @Operation(summary = "Удалить чат")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200",
                     description = "Чат успешно удален"),
        @ApiResponse(responseCode = "404",
                     description = "Чат не существует",
                     content = @Content)
    })
    @DeleteMapping("/{id}")
    public void deleteChat(@PathVariable("id") int id) {

    }

}
