// Copyright 2019 Google LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     https://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.google.sps.servlets;

import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.*;

/** Servlet that returns some example content. TODO: modify this file to handle comments data */
@WebServlet("/data")
public class DataServlet extends HttpServlet {
  //to store quotes
  ArrayList<String> messages= new ArrayList<String>();
  //to store names and comments

  Hashtable<String, String> h = new Hashtable<String, String>(); 

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {   
    // add messages to list
    messages.add("Whatever you do, do it well. -- Walt Disney");
    messages.add("Simplicity is the ultimate sophistication. -- Leonardo da Vinci");
    messages.add("Yesterday you said tomorrow. Just do it. -- Nike");

    // Convert the messages to JSON
    String json = convertToJson(messages);

    // Send the JSON as the response
    response.setContentType("application/json;");
    response.getWriter().println(json);

    
  }

 /**
   * Converts a the ArrayList instance into a JSON string using the Gson library. 
   */
  private String convertToJson(ArrayList messages) {
    Gson gson = new Gson();
    String json = gson.toJson(messages);
    return json;
  }


@Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
    // Get the input from the form.
    String name= getParameter(request, "name", "");
    String comment = getParameter(request,"text-input", "");
    h.put(name, comment);

    // Respond with the result.
    response.setContentType("text/html;");
    response.getWriter().print(name);
    response.getWriter().print(" left the comment: ");
    response.getWriter().println(comment);

    // Redirect back to the HTML page.
    //response.sendRedirect("/index.html");

  }

  /**
   * @return the request parameter, or the default value if the parameter
   *         was not specified by the client
   */
  private String getParameter(HttpServletRequest request, String name, String defaultValue) {
    String value = request.getParameter(name);
    if (value == null) {
      return defaultValue;
    }
    return value;
  }
}
