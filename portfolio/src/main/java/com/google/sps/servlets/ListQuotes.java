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

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.SortDirection;
import com.google.gson.Gson;
import com.google.sps.data.Task;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/** Servlet responsible for listing tasks. */
@WebServlet("/list-quotes")
public class ListQuotes extends HttpServlet {

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    // looks like its creating the query and sorting them in order
    Query query = new Query("Task").addSort("timestamp", SortDirection.DESCENDING);

    // getting the data from datastore
    DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();

    // getting the data ready for it to be shown
    PreparedQuery results = datastore.prepare(query);

    // creating array for the data
    List<Task> tasks = new ArrayList<>();

    // going through all the datas
    for (Entity entity : results.asIterable()) {
        // getting the id and key ?
      long id = entity.getKey().getId();
      // get the quotes from word quote
      String quote = (String) entity.getProperty("quote");
      // getting the time stamps
      long timestamp = (long) entity.getProperty("timestamp");

    // idk what this does honestly
      Task task = new Task(id, quote, timestamp);
      tasks.add(task);
    }

    // creating it into json so it can be put into html
    Gson gson = new Gson();

    response.setContentType("application/json;");
    response.getWriter().println(gson.toJson(tasks));
  }
}