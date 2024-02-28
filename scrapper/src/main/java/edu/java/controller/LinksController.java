package edu.java.controller;

import edu.java.dto.request.AddLinkRequest;
import edu.java.dto.request.RemoveLinkRequest;
import edu.java.dto.response.LinkResponse;
import edu.java.dto.response.ListLinkResponse;
import edu.java.model.errorhandling.ApiErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@ApiErrorResponse(code = "400", description = "Некорректные параметры запроса")
@RequestMapping("/links")
@RestController
public class LinksController {

    public static final String TG_CHAT_ID = "Tg-Chat-Id";

    @Operation(summary = "Получить все отслеживаемые ссылки")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200",
                     description = "Ссылки успешно получены",
                     content = @Content(
                         schema = @Schema(implementation = ListLinkResponse.class),
                         mediaType = MediaType.APPLICATION_JSON_VALUE
                     ))
    })
    @GetMapping
    public void getTrackedLinks(@RequestHeader(TG_CHAT_ID) int id) {

    }


    @Operation(summary = "Добавить отслеживание ссылки")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200",
                     description = "Ссылка успешно добавлена",
                     content = @Content(
                         schema = @Schema(implementation = LinkResponse.class),
                         mediaType = MediaType.APPLICATION_JSON_VALUE
                     ))
    })
    @PostMapping
    public void addLinkToTrack(
        @RequestHeader(TG_CHAT_ID) int id,
        @Valid @RequestBody AddLinkRequest addLinkRequest
        ) {

    }

    @Operation(summary = "Убрать отслеживание ссылки")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200",
                     description = "Ссылка успешно убрана",
                     content = @Content(
                         schema = @Schema(implementation = LinkResponse.class),
                         mediaType = MediaType.APPLICATION_JSON_VALUE)),
        @ApiResponse(responseCode = "404",
                     description = "Ссылка не найдена")
    })
    @DeleteMapping
    public void deleteLink(
        @RequestHeader(TG_CHAT_ID) int id,
        @Valid @RequestBody RemoveLinkRequest removeLinkRequest
    ) {

    }
}
