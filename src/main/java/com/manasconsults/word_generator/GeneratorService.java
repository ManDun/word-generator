package com.manasconsults.word_generator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * This is a service class that provides service to controller.
 * This class calls private methods to convert number to words
 */

@Service
public class GeneratorService {

    private static final Logger log = LoggerFactory.getLogger(GeneratorService.class);
    private static final String[] ones = {"", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten"};
    private static final String[] teens = {"eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen"};
    private static final String[] tens = {"twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety"};
    private static final String[] hundreds = {"hundred"};
    private static final String[] thousands = {"thousand"};

    public String convertNumberToWord(BigDecimal n) throws Exception {
        String generated_word;
        String generated_word_before_decimal;
        String generated_word_after_decimal;
        try {
            if (n.compareTo(BigDecimal.ZERO) > 0) {
                log.info("Converting "+ n+" to words:");
                // Split strings
                String n_string = String.valueOf(n);
                int decimal_index = n_string.indexOf(".");
                log.info("Decimal index at "+ decimal_index);

                if (decimal_index > 0)
                {
                    int integer_number = Integer.parseInt(n_string.substring(0, decimal_index));
                    int decimal_number = Integer.parseInt(n_string.substring(decimal_index+1));
                    if (integer_number > 0 && decimal_number > 0)
                    {
                        generated_word_before_decimal = wordsInThousands(integer_number);
                        generated_word_after_decimal = wordsInThousands(decimal_number);
                        generated_word = generated_word_before_decimal+" dollars and "+generated_word_after_decimal+" cents";
                    }
                    else if (decimal_number > 0){
                        generated_word = wordsInThousands(decimal_number)+" cents";
                    }

                    else
                        generated_word = wordsInThousands(integer_number)+" dollars";


                }
                else
                    generated_word = wordsInThousands(Integer.parseInt(n_string))+" dollars";

            }

            else if (n.compareTo(BigDecimal.ZERO) == 0)
                return "zero".toUpperCase()+" DOLLARS";
            else
                throw new IllegalArgumentException("Invalid input provided");

            return generated_word.toUpperCase();

        }
        catch (Exception e){
            log.error(e.getMessage());
            throw new Exception("Invalid input provided, please try again!");
        }

    }

    private String wordsInOnes(Integer n){
        log.info("Generating word in ones for "+n.toString());

        if (n <= 10)
            return (ones[n]);
        else
            throw new IllegalArgumentException("Invalid input provided");

    }

    private String wordsInTeens(Integer n){
        log.info("Generating word in teens for "+n.toString());

        if (n <= 19 && n> 10)
            return (teens[n-11]);
        else
            return wordsInOnes(n);

    }

    private String wordsInHundreds(Integer n){
        log.info("Generating word in hundreds for "+n.toString());

        if (n < 1000 && n >= 100){

            if (n % 100 > 0) {
                return ones[(n/100)]+" hundred and " + wordsInTens(n%100);
            }
            else
                return ones[(n/100)]+" hundred";
        }

        else
            return wordsInTens(n);

    }

    private String wordsInThousands(Integer n){
        log.info("Generating word in thousands for "+n.toString());

        if (n < 1000000 && n >= 1000){

            if (n % 1000 > 0){
                return wordsInHundreds(n/1000)+" thousand and "+wordsInHundreds(n%1000);
            }
            else
                return wordsInHundreds(n/1000)+" thousand";
        }
        else return wordsInHundreds(n);

    }

    private String wordsInTens(Integer n)
    {
        log.info("Generating word in tens for "+n.toString());
        String word_generated = "";

        if (n < 100 && n > 19){
            return (tens[(n/10) - 2]+ " " + ones[n % 10]).trim();
        }
        else
            return wordsInTeens(n);
    }
}
