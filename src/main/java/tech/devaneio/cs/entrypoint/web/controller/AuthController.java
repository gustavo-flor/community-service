package tech.devaneio.cs.entrypoint.web.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import tech.devaneio.cs.entrypoint.web.payload.request.LoginPayload;

@RequestMapping("/v1/auth")
public interface AuthController {

    @PostMapping("/login")
    ResponseEntity<Void> login(@RequestBody LoginPayload payload);

}
