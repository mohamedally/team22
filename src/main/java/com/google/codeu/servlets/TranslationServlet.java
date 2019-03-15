package com.google.codeu.servlets;

import com.google.cloud.translate.Translate;
import com.google.cloud.translate.Translate.TranslateOption;
import com.google.cloud.translate.TranslateOptions;
import com.google.cloud.translate.Translation;
import com.google.gson.Gson;
import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/translations")
public class TranslationServlet extends HttpServlet{
    
    private String test_translate(){
        Translate translate = TranslateOptions.getDefaultInstance().getService();

        String originalText = "Hello world";

        Translation translation =
        translate.translate(originalText, TranslateOption.targetLanguage("es"));
        String translatedText = translation.getTranslatedText();

        return translatedText;
    }
    
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    
        response.setContentType("application/json");
        
        Gson gson = new Gson();
        String json = gson.toJson(test_translate());
    
        response.getWriter().println(json);
    }
    
    
}
