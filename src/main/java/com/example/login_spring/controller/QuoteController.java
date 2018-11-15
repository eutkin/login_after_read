package com.example.login_spring.controller;


import com.example.login_spring.model.Quote;
import com.example.login_spring.model.User;
import com.example.login_spring.repository.QuoteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
public class QuoteController {

    private final QuoteRepository quoteRepository;

    @Autowired
    public QuoteController(QuoteRepository quoteRepository) {
        this.quoteRepository = quoteRepository;
    }


    @GetMapping({"/", "/ww"})
    public String index(Model model) {
        List<Quote> quotes = quoteRepository.findAll();

        model.addAttribute("quotes", quotes);

        return "index";
    }

    @RequestMapping(value = "add")
    public String addQuote(Model model) {
        model.addAttribute("quote", new Quote());
        return "addQuote";
    }

/*

    @PostMapping("/update")
    public String update(Quote quote, @AuthenticationPrincipal User author) {
        quote.setAuthor(author);
        quoteRepository.save(quote);
        return "redirect:/ww";
    }

*/



    @PostMapping("/save")
    @ResponseStatus(value=HttpStatus.NOT_FOUND, reason="No such Order")
    public String save(Quote quote, @AuthenticationPrincipal User author) {
        quote.setAuthor(author);
        quoteRepository.save(quote);
        return "redirect:/ww";
    }


    @PostMapping("/delete/{id}")
    public String deleteQuote(@PathVariable("id") Long quoteId, Model model) {
        quoteRepository.deleteById(quoteId);
        return "redirect:/ww";
    }


}

