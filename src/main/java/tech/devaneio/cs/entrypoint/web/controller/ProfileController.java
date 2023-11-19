package tech.devaneio.cs.entrypoint.web.controller;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import tech.devaneio.cs.entrypoint.web.payload.request.UpdateUserPayload;
import tech.devaneio.cs.entrypoint.web.payload.response.UserPayload;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RequestMapping("/v1/profile")
public interface ProfileController {

    @PutMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    UserPayload update(@RequestBody UpdateUserPayload payload);

}
