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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;
import java.util.List;
import java.util.Optional;


@Controller
public class QuoteController {

    private final QuoteRepository quoteRepository;

    @Autowired
    public QuoteController(QuoteRepository quoteRepository) {
        this.quoteRepository = quoteRepository;
    }


    @GetMapping({"/"})
    public String index(Model model) {
        List<Quote> quotes = quoteRepository.findAll();

        model.addAttribute("quotes", quotes);

        return "index";
    }

    @RequestMapping(value = "/add")
    public String addQuote(Model model) {
        model.addAttribute("quote", new Quote());
        return "addQuote";
    }

    @RequestMapping(value = "/update/{id}")
    public ModelAndView update(@PathVariable("id") Long quoteId) {
        Optional<Quote> existsQuote = quoteRepository.findById(quoteId);
        ModelAndView mv = new ModelAndView("updateQuote");
        existsQuote.ifPresent(q -> mv.addObject("quote", q));
        return mv;
    }


    @PostMapping("/update/{id}")
    public View update(@PathVariable("id") Long quoteId, Quote quote, @AuthenticationPrincipal User author) {
        Optional<Quote> existsQuote = quoteRepository.findById(quoteId);
        if (!existsQuote.isPresent()) {
            return new RedirectView("/?error");
        }
        Quote updatedQuote = existsQuote.map(q -> {
            q.setAuthor(quote.getAuthor());
            q.setText(quote.getText());
            q.setAuthor(author);
            return q;
        }).get();
        quoteRepository.save(updatedQuote);
        return new RedirectView("/");
    }


    @PostMapping("/save")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ExceptionHandler(IOException.class)
    public String save(Quote quote, @AuthenticationPrincipal User author) {
        quote.setAuthor(author);
        quoteRepository.save(quote);
        return "redirect:/";
    }


    @PostMapping("/delete/{id}")
    public String deleteQuote(@PathVariable("id") Long quoteId, Model model) {
        quoteRepository.deleteById(quoteId);
        return "redirect:/";
    }


}

