package ru.itis.springbootdemo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.itis.springbootdemo.dto.UsersPage;
import ru.itis.springbootdemo.services.UsersService;

@Controller
public class SearchController {
    @Autowired
    public UsersService usersService;

    @ResponseBody
    @PostMapping("/users/search")
    public ResponseEntity<UsersPage> search(@RequestParam("size") Integer size,
                                            @RequestParam("page") Integer page,
                                            @RequestParam(value = "q", required = false) String query,
                                            @RequestParam(value = "sort", required = false) String sort,
                                              @RequestParam(value = "direction", required = false) String direction) {
        System.out.println("got request");
        return ResponseEntity.ok(usersService.search(size, page, query, sort, direction));
    }
    @GetMapping("/users/search")
    public String search(){
        return "search_users";
    }
}
