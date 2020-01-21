package com.lanwei.community.controller;

import com.lanwei.community.dto.AccessTokenDTO;
import com.lanwei.community.dto.GithubUser;
import com.lanwei.community.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthorizeController {

    @Autowired
    private GithubProvider githubProvider;

    @GetMapping("/callback")
    public String callback(@RequestParam(name="code") String code,
                           @RequestParam(name="state") String state){
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id("6b4723e25b498e5f4e73");
        accessTokenDTO.setClient_secret("4b4e9ad1f019bc6357544fc5208ad5fda7613f9e");
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri("http://localhost:8080/callback");
        accessTokenDTO.setState(state);
        String accesToken = githubProvider.getAccesToken(accessTokenDTO);
        System.out.println(accesToken+"!!!!");
        GithubUser user = githubProvider.getUser(accesToken);
        System.out.println(user.toString());
        System.out.println(user.getName());
        return "index";
    }

}
