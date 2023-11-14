package tech.devaneio.cs.entrypoint.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import tech.devaneio.cs.core.service.UserService;
import tech.devaneio.cs.core.usecase.CreateUserUseCase;
import tech.devaneio.cs.entrypoint.web.payload.request.CreateUserPayload;
import tech.devaneio.cs.entrypoint.web.payload.response.UserPayload;
import tech.devaneio.cs.entrypoint.web.query.PageableQuery;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final CreateUserUseCase createUserUseCase;

    @PreAuthorize("hasRole('ADMINISTRATOR')")
    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public Page<UserPayload> findAll(@Valid final PageableQuery query) {
        return userService.findAll(query.pageRequest()).map(UserPayload::of);
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public UserPayload create(@RequestBody final CreateUserPayload payload) {
        final var input = payload.input();
        final var output = createUserUseCase.execute(input);
        return UserPayload.of(output.user());
    }

}
