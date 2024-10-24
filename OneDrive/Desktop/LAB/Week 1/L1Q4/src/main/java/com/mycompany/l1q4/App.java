package com.mycompany.l1q4;

import javafx.application.Application; 
import javafx.collections.FXCollections;  
import javafx.collections.ObservableList; 
import javafx.scene.Group; 
import javafx.scene.Scene; 
import javafx.stage.Stage; 
import javafx.scene.chart.PieChart; 
         
public class App extends Application {  
   @Override 
   public void start(Stage stage) { 
      //Preparing ObservbleList object         
      ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
         new PieChart.Data("January 2016\nRM2500 ", 25), 
         new PieChart.Data("February 2016\nRM1600", 16), 
         new PieChart.Data("March 2016\nRM2000", 20), 
         new PieChart.Data("April 2016\nRM2700", 27),
         new PieChart.Data("May 2016\nRM3200", 32),
         new PieChart.Data("June 2016\nRM800", 8)); 
     
       
      //Creating a Pie chart 
      PieChart pieChart = new PieChart(pieChartData); 
              
      //Setting the title of the Pie chart 
      pieChart.setTitle("Total sales of product A"); 
       
      //setting the direction to arrange the data 
      pieChart.setClockwise(true); 
       
      //Setting the length of the label line 
      pieChart.setLabelLineLength(40); 

      //Setting the labels of the pie chart visible  
      pieChart.setLabelsVisible(true); 
       
      //Setting the start angle of the pie chart  
      pieChart.setStartAngle(180);     
         
      //Creating a Group object  
      Group root = new Group(pieChart); 
         
      //Creating a scene object 
      Scene scene = new Scene(root, 500, 400);  
      
      //Setting title to the Stage 
      stage.setTitle("Pie chart"); 
         
      //Adding scene to the stage 
      stage.setScene(scene); 
      stage.centerOnScreen();
         
      //Displaying the contents of the stage 
      stage.show();         
   }     
   public static void main(String args[]){ 
      launch(args); 
   } 
} 