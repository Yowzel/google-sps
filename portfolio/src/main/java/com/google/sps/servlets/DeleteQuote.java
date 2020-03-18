
package com.google.sps.servlets;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/** Servlet responsible for deleting tasks. */
@WebServlet("/delete-quote")
public class DeleteQuote extends HttpServlet {

  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

    //getting the id of the quote/comment that wants to be deleted
    long id = Long.parseLong(request.getParameter("id"));

    // creating a key for it
    Key taskEntityKey = KeyFactory.createKey("Task", id);
    // acessing the datastore
    DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
    //deleting the item from the datastore
    datastore.delete(taskEntityKey);
  }
}