package com.example.demo.controller;

import com.example.demo.model.Show;
import com.example.demo.model.Theatre;
import com.example.demo.service.ShowService;
import com.example.demo.service.TheatreService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class  ShowController {

    private  final   ShowService showService;
    private final TheatreService theatreService;
    @PostMapping("/theatres/{theatreId}/screens/{screenId}/shows")
    public String addShow(
            @PathVariable Integer theatreId,
            @PathVariable Integer screenId,
            @RequestBody Show show,
            Principal principal) {

        String email = principal.getName(); // logged-in user
        showService.addShow(theatreId, screenId,show, email);
        return "New show has been added";
    }

}
