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
import com.google.gson.Gson;
import java.util.List;
import java.util.ArrayList;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/** Servlet that returns some example content. TODO: modify this file to handle comments data */
@WebServlet("/data")
public class DataServlet extends HttpServlet {
    private ArrayList<String> comments;
  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    comments = new ArrayList<>();
    comments.add("Make in your face like a samsung");
    comments.add("I took it and I ran for it");
    comments.add("Smile pearl white, shine so bright");
    
    String test = "Hello Gia-Huy Gonzalez!";
    String json = convertToJson(comments);

    //response.setContentType("text/html;");
    //response.getWriter().println(test);
        // Send the JSON as the response
    response.setContentType("application/json;");
    response.getWriter().println(json);
  }

  private String convertToJson(ArrayList quote) {
    String json = "{";
    json += "\"Lyric1\": ";
    json += "\"" + quote.get(0) + "\"";
    json += ", ";
    json += "\"Lyric2\": ";
    json += "\"" + quote.get(1) + "\"";
    json += ", ";
    json += "\"Lyric3\": ";
    json += "\""+ quote.get(2) + "\"";
    json += "}";
    return json;
  }
}
