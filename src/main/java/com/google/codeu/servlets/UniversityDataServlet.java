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
 * Returns University data as a JSON array, e.g. [{"university": "Princeton University", "city":"Princeton", "state":"NJ", "rank":1, "udergraduates":5402, "lat": 40.3439, "lng": -74.6514}]
 */
@WebServlet("/university-data")
public class UniversityDataServlet extends HttpServlet {
    JsonArray UniversityLocationArray;

    @Override
    public void init() {
        UniversityLocationArray = new JsonArray();
        Gson gson = new Gson();
        Scanner scanner = new Scanner(getServletContext().getResourceAsStream("/WEB-INF/National-Universities-Rankings-Located.csv"));
        scanner.nextLine();
        while(scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] cells = line.split(",");
            
            System.out.println(line);
            
            String university = cells[0];
            String city = cells[1];
            String state = cells[2];
            int rank = Integer.parseInt(cells[3]);
            int undergraduates = Integer.parseInt(cells[4]);
            double lat = Double.parseDouble(cells[5]);
            double lng = Double.parseDouble(cells[6]);
            
            UniversityLocationArray.add(gson.toJsonTree(new UniversityLocation(university, city, state, rank, undergraduates, lat, lng)));
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
        String city;
        String state;
        int rank;
        int undergraduates;
        double lat;
        double lng;
    
        private UniversityLocation(String university, String city, String state, int rank, int undergraduates, double lat, double lng) {
            this.university = university;
            this.city = city;
            this.state = state;
            this.rank = rank;
            this.undergraduates = undergraduates;
            this.lat = lat;
            this.lng = lng;
        }
    }
}