package com.google.codeu.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.codeu.data.Datastore;
import com.google.codeu.data.Message;
import com.google.gson.Gson;

import com.google.cloud.translate.Translate;
import com.google.cloud.translate.Translate.TranslateOption;
import com.google.cloud.translate.TranslateOptions;
import com.google.cloud.translate.Translation;

/**
 * Handles fetching all messages for the public feed.
 */
@WebServlet("/feed")
public class MessageFeedServlet extends HttpServlet {

  private Datastore datastore;

  @Override
  public void init() {
    datastore = new Datastore();
  }

  private void translateMessages(List<Message> messages, String targetLanguageCode) {
    Translate translate = TranslateOptions.getDefaultInstance().getService();

    for (Message message : messages) {
      String originalText = message.getText();

      Translation translation = translate.translate(originalText, TranslateOption.targetLanguage(targetLanguageCode));
      String translatedText = translation.getTranslatedText();

      message.setText(translatedText);
    }
  }

  /**
   * Responds with a JSON representation of Message data for all users.
   */
  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

    response.setContentType("application/json");

    List<Message> messages = datastore.getAllMessages();
    Gson gson = new Gson();
    String json = gson.toJson(messages);

    response.getWriter().println(json);
  }
}