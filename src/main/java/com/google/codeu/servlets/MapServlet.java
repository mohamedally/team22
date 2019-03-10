package com.google.codeu.servlets;

import java.io.IOException;
import java.util.Scanner;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
import com.google.gson.JsonArray;

/**
 * Returns University data as a JSON array, e.g. [{"lat": 38.4404675, "lng": -122.7144313}]
 */
@WebServlet("/University-data")
public class UniversityDataServlet extends HttpServlet {
    JsonArray UniversityLocationArray;

    @Override
    public void init() {
        UniversityLocationArray = new JsonArray();
        Gson gson = new Gson();
        Scanner scanner = new Scanner(getServletContext().getResourceAsStream("/WEB-INF/National-Universities-Rankings-Located.csv"));
        while(scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] cells = line.split(",");
            
            String university = cells[0];
            double lat = Double.parseDouble(cells[7]);
            double lng = Double.parseDouble(cells[8]);
            
            UniversityLocationArray.add(gson.toJsonTree(new UniversityLocation(university, lat, lng)));
        }
        scanner.close();
    }
    
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");  
        response.getOutputStream().println(UniversityLocationArray.toString());
    }
    
    private static class UniversityLocation{
        String university;
        double lat;
        double lng;
    
        private UniversityLocation(String university, double lat, double lng) {
            this.university = university;
            this.lat = lat;
            this.lng = lng;
        }
    }
}