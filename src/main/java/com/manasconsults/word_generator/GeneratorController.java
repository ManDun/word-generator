package com.manasconsults.word_generator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

/**
 * This is a controller class that handles requests from  web to convert numbers to words.
 * This class calls GeneratorService and updates UI with response
 */

@Controller
public class GeneratorController {

    private static final Logger log = LoggerFactory.getLogger(GeneratorController.class);

    @Autowired
    private GeneratorService wordsGenerator;

    @GetMapping("/")
    public String home() {
        log.info("Loading home page");
        return "base";
    }

    @PostMapping("/convert")
    public String convert(
            @RequestParam("number") @NonNull BigDecimal number,
            Model model) throws Exception {

        log.info("Calling words generator to convert number to string: "+number);
        String error = null;
        String words = "";

        if(number == null || number.compareTo(BigDecimal.ZERO) < 0){
            log.error("Invalid input provided.");
            model.addAttribute("error", "Invalid input! Please enter a valid number.");
            return "base";
        }

        try{
            words = wordsGenerator.convertNumberToWord(number);
        } catch (Exception e) {
            error = e.getMessage();
            words = e.getMessage();
        }

        model.addAttribute("number", number);
        model.addAttribute("words", words);
        model.addAttribute("error", error);
        log.info("Loading page to display words.");
        return "base";
    }
}
