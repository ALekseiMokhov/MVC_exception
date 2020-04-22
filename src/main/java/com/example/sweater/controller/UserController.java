package com.example.sweater.controller;

import com.example.sweater.domain.Role;
import com.example.sweater.domain.User;
import com.example.sweater.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserRepo userRepo;

    @GetMapping
    public String userList(Model model){
        model.addAttribute( "users",userRepo.findAll() );
        return "userlist";
    }
    @GetMapping  ("{user}")
    public String userEditForm(@PathVariable User user, Model model){
        model.addAttribute( "user",user )   ;
        model.addAttribute( "roles", Role.values() );
            return "useredit";
    }
    @PostMapping
    public String saveEditForm   (
            @RequestParam("userId")User user,
            @RequestParam String username,
            @RequestParam Map<String,String> form
           )
            {
        user.setUsername(username);
        Set <String> roles = Arrays.stream( Role.values() ).map( Role::name ).collect( Collectors.toSet() );
        user.getRoles().clear();
               for(String k: form.keySet())         {
                   if(roles.contains( k ))     {
                       user.getRoles().add( Role.valueOf( k ) )    ;
                   }
               }
        userRepo.save( user )   ;
        return "redirect:/user";
    }

}
