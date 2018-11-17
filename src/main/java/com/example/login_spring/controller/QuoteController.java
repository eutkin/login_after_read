package com.example.login_spring.controller;


import com.example.login_spring.model.Quote;
import com.example.login_spring.model.User;
import com.example.login_spring.repository.QuoteRepository;

import edu.umd.cs.findbugs.classfile.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
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


   /* @PutMapping("/update/{id}")
    public String update(@PathVariable("id") Long quoteId, Quote quote) {
        Quote quote1 `=  quoteRepository.findById( quoteId).orElseThrow(() -> new ResourceNotFoundException("Quote", "id", quoteId));;
        quote1.setAuthor(quote.getAuthor());
        quote1.setText(quote.getText())
        quoteRepository.save(quote);
        Quote quoteupdate = quoteRepository.save(quote1);
        return "redirect:/ww";
    }

*/


    @PostMapping("/save")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ExceptionHandler(IOException.class)
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

