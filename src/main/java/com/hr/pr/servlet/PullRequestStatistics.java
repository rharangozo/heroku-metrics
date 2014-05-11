package com.hr.pr.servlet;

import com.hr.pr.domain.Repository;
import com.hr.pr.domain.TokenAuth;
import com.hr.pr.service.StatisticsService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.SortedMap;

//TODO: remove this class
public class PullRequestStatistics extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String token = req.getParameter("token");
        
        String owner = req.getParameter("owner");
        String repository = req.getParameter("repository");

        if (token != null && owner != null && repository != null) {

            TokenAuth ta = new TokenAuth(token);
            Repository repo = new Repository(owner, repository);

            SortedMap<Integer, Integer> result = new StatisticsService().processStatistics(ta, repo);

            req.setAttribute("result", result);

        }

        req.getRequestDispatcher("/main.jsp").forward(req, resp);
    }
}
