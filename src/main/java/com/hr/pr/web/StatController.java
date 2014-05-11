package com.hr.pr.web;

import com.hr.pr.domain.Repository;
import com.hr.pr.domain.TokenAuth;
import com.hr.pr.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.SortedMap;

@Controller
@RequestMapping("/stat")
public class StatController {

    @Autowired
    private StatisticsService statisticsService;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public SortedMap<Integer, Integer> doStatistics(@RequestParam(required = true, value = "token") String token,
                                                    @RequestParam(required = true, value = "owner") String owner,
                                                    @RequestParam(required = true, value = "repository") String repository) {

        TokenAuth tokenAuthenticator = new TokenAuth(token);
        Repository repo = new Repository(owner, repository);

        return statisticsService.processStatistics(tokenAuthenticator, repo);
    }

}
