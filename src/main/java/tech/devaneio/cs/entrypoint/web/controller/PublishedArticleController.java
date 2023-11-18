package tech.devaneio.cs.entrypoint.web.controller;

import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import tech.devaneio.cs.entrypoint.web.payload.request.PublishArticlePayload;
import tech.devaneio.cs.entrypoint.web.payload.response.ArticlePayload;
import tech.devaneio.cs.entrypoint.web.query.PageableQuery;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RequestMapping("/v1/published-articles")
public interface PublishedArticleController {

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    Page<ArticlePayload> findAll(@Valid PageableQuery query);

    @GetMapping(path = "/{id}", produces = APPLICATION_JSON_VALUE)
    ArticlePayload findById(@PathVariable Long id);

    @PreAuthorize("hasRole('ADMINISTRATOR')")
    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    ArticlePayload publish(@RequestBody PublishArticlePayload payload);

}
