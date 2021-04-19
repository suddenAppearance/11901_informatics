package ru.itis.springbootdemo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.springbootdemo.dto.UsersPage;
import ru.itis.springbootdemo.services.UsersService;

@RestController
public class SearchController {
    @Autowired
    public UsersService usersService;

    @GetMapping("/users/search")
    public ResponseEntity<UsersPage> search(@RequestParam("size") Integer size,
                                            @RequestParam("page") Integer page,
                                            @RequestParam(value = "q", required = false) String query,
                                            @RequestParam(value = "sort", required = false) String sort,
                                            @RequestParam(value = "direction", required = false) String direction) {
        return ResponseEntity.ok(usersService.search(size, page, query, sort, direction));
    }
}
