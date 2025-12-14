package com.example.demo.controller;

import com.example.demo.model.Show;
import com.example.demo.model.ShowDTO;
import com.example.demo.model.Theatre;
import com.example.demo.service.ShowService;
import com.example.demo.service.TheatreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class  ShowController {

    private  final   ShowService showService;
    private final TheatreService theatreService;
    @PostMapping("screens/{screenId}/shows")
    public ResponseEntity<String>addShow(
            @PathVariable Integer screenId,
            @RequestBody ShowDTO showDTO,
            Principal principal) {

        String email = principal.getName(); // logged-in user
        return showService.addShow(screenId,showDTO, email);

    }

}
