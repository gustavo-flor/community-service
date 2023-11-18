package tech.devaneio.cs.entrypoint.web.controller;

import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import tech.devaneio.cs.entrypoint.web.payload.request.CreateUserPayload;
import tech.devaneio.cs.entrypoint.web.payload.request.UpdateUserPayload;
import tech.devaneio.cs.entrypoint.web.payload.request.UpdateUserRolePayload;
import tech.devaneio.cs.entrypoint.web.payload.response.UserPayload;
import tech.devaneio.cs.entrypoint.web.query.PageableQuery;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RequestMapping("/v1/users")
public interface UserController {

    @PreAuthorize("hasRole('ADMINISTRATOR')")
    @GetMapping(produces = APPLICATION_JSON_VALUE)
    Page<UserPayload> findAll(@Valid PageableQuery query);

    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    UserPayload create(@RequestBody CreateUserPayload payload);

    @PreAuthorize("hasRole('ADMINISTRATOR')")
    @PutMapping(path = "/{id}", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    UserPayload update(@PathVariable Long id, @RequestBody UpdateUserPayload payload);

    @PreAuthorize("hasRole('ADMINISTRATOR')")
    @PatchMapping(path = "/{id}/role", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    UserPayload updateRole(@PathVariable Long id, @RequestBody UpdateUserRolePayload payload);

}
