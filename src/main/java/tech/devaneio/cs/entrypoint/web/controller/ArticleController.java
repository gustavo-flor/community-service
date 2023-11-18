package tech.devaneio.cs.entrypoint.web.controller;

import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import tech.devaneio.cs.entrypoint.web.payload.request.CreateArticlePayload;
import tech.devaneio.cs.entrypoint.web.payload.request.UpdateArticlePayload;
import tech.devaneio.cs.entrypoint.web.payload.response.ArticlePayload;
import tech.devaneio.cs.entrypoint.web.query.PageableQuery;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@PreAuthorize("hasRole('ADMINISTRATOR')")
@RequestMapping("/v1/articles")
public interface ArticleController {

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    Page<ArticlePayload> findAll(@Valid PageableQuery query);

    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    ArticlePayload create(@RequestBody CreateArticlePayload payload);

    @PutMapping(path = "/{id}", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    ArticlePayload update(@PathVariable Long id, @RequestBody UpdateArticlePayload payload);

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteById(@PathVariable Long id);

}
