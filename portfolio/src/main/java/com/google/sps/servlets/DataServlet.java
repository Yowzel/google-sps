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
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.gson.Gson;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/** Servlet that returns some example content. TODO: modify this file to handle comments data */
@WebServlet("/new-comments")
public class DataServlet extends HttpServlet {

  @Override
  // This gets the post from index.html and stores the data
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
    // this is going to save the users inputed string
    String quote = request.getParameter("quote");
    
    // I guess i'll keep this incase I need it?
    // just save when the users posted this
    long timestamp = System.currentTimeMillis();

    // creates entity which is needed to store into datastore
    Entity taskEntity = new Entity("Task");
    
    // puts the information into entity
    taskEntity.setProperty("quote", quote);
    taskEntity.setProperty("timestamp", timestamp);

    // creates the datastore, which is where i'm going to store users quotes
    DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
    // puts the users quote/comment into datastore
    datastore.put(taskEntity);

    // redirect the page and this will trigger the onload show it can show the comments/quotes
    response.sendRedirect("/index.html");
  }
}
