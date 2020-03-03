import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class TrainBooking extends Application {
    static final int SEATING_CAPACITY = 42;
    private ArrayList<String> seats = new ArrayList<String>();  //array list for seats
    private HashMap<String, ArrayList> data = new HashMap<String, ArrayList>(); //hashmap to save username and booked seats
    private ArrayList<String> key = new ArrayList<String>();        //This array is created to get user values (seats numbers) from the harshmap

    private void GuiWindow(){
        //Button creation for seats
        Button btn1 = new Button(" 1 ");
        Button btn2 = new Button(" 2 ");
        Button btn3 = new Button(" 3 ");
        Button btn4 = new Button(" 4 ");
        Button btn5 = new Button(" 5 ");
        Button btn6 = new Button(" 6 ");
        Button btn7 = new Button(" 7 ");
        Button btn8 = new Button(" 8 ");
        Button btn9 = new Button(" 9 ");
        Button btn10 = new Button("10");
        Button btn11 = new Button("11");
        Button btn12 = new Button("12");
        Button btn13 = new Button("13");
        Button btn14 = new Button("14");
        Button btn15 = new Button("15");
        Button btn16 = new Button("16");
        Button btn17 = new Button("17");
        Button btn18 = new Button("18");
        Button btn19 = new Button("19");
        Button btn20 = new Button("20");
        Button btn21 = new Button("21");
        Button btn22 = new Button("22");
        Button btn23 = new Button("23");
        Button btn24 = new Button("24");
        Button btn25 = new Button("25");
        Button btn26 = new Button("26");
        Button btn27 = new Button("27");
        Button btn28 = new Button("28");
        Button btn29 = new Button("29");
        Button btn30 = new Button("30");
        Button btn31 = new Button("31");
        Button btn32 = new Button("32");
        Button btn33 = new Button("33");
        Button btn34 = new Button("34");
        Button btn35 = new Button("35");
        Button btn36 = new Button("36");
        Button btn37 = new Button("37");
        Button btn38 = new Button("38");
        Button btn39 = new Button("39");
        Button btn40 = new Button("40");
        Button btn41 = new Button("41");
        Button btn42 = new Button("42");

        Button btnConfirm = new Button(" Confirm ");
        Button btnReset = new Button("   Reset  ");

        Label label = new Label("  Customer Name :");
        Label labe2 = new Label("  Total Count of Seats :");

        //adding elements to the created panes
        BorderPane borderPane = new BorderPane();
        GridPane gridPane = new GridPane();
        GridPane gridPane1 = new GridPane();
        borderPane.setCenter(gridPane);
        borderPane.setTop(gridPane1);

        //Giving positions to the buttons
        gridPane.add(btn1, 0, 0);
        gridPane.add(btn2, 1, 0);
        gridPane.add(btn3, 2, 0);
        gridPane.add(btn4, 3, 0);
        gridPane.add(btn5, 4, 0);
        gridPane.add(btn6, 5, 0);
        gridPane.add(btn7, 0, 2);
        gridPane.add(btn8, 1, 2);
        gridPane.add(btn9, 2, 2);
        gridPane.add(btn10, 3, 2);
        gridPane.add(btn11, 4, 2);
        gridPane.add(btn12, 5, 2);
        gridPane.add(btn13, 0, 4);
        gridPane.add(btn14, 1, 4);
        gridPane.add(btn15, 2, 4);
        gridPane.add(btn16, 3, 4);
        gridPane.add(btn17, 4, 4);
        gridPane.add(btn18, 5, 4);
        gridPane.add(btn19, 0, 6);
        gridPane.add(btn20, 1, 6);
        gridPane.add(btn21, 2, 6);
        gridPane.add(btn22, 3, 6);
        gridPane.add(btn23, 4, 6);
        gridPane.add(btn24, 5, 6);
        gridPane.add(btn25, 0, 8);
        gridPane.add(btn26, 1, 8);
        gridPane.add(btn27, 2, 8);
        gridPane.add(btn28, 3, 8);
        gridPane.add(btn29, 4, 8);
        gridPane.add(btn30, 5, 8);
        gridPane.add(btn31, 0, 10);
        gridPane.add(btn32, 1, 10);
        gridPane.add(btn33, 2, 10);
        gridPane.add(btn34, 3, 10);
        gridPane.add(btn35, 4, 10);
        gridPane.add(btn36, 5, 10);
        gridPane.add(btn37, 0, 12);
        gridPane.add(btn38, 1, 12);
        gridPane.add(btn39, 2, 12);
        gridPane.add(btn40, 3, 12);
        gridPane.add(btn41, 4, 12);
        gridPane.add(btn42, 5, 12);

        gridPane.add(btnConfirm, 9, 16);
        gridPane.add(btnReset, 9, 17);

        gridPane1.add(label, 0, 1);
        gridPane1.add(labe2, 0, 3);


        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setAlignment(Pos.CENTER);

        //creating array for the onclick book action
        Button[] bt = {btn1, btn2, btn3, btn4, btn4, btn5, btn6, btn7, btn8, btn8, btn9, btn9, btn10, btn11, btn12, btn13, btn14, btn14, btn15, btn16, btn17, btn18, btn19, btn20, btn21, btn22, btn23, btn24, btn25, btn26, btn27, btn28, btn29, btn30, btn31, btn32, btn33, btn34, btn35, btn36, btn37, btn38, btn39, btn40, btn41, btn42};
        for (Button item : bt) {           //for each button inside the array it will perform the same onclick action
            item.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    item.setDisable(true);                      //making button disable after the click
                    item.setStyle("-fx-background-color: red"); //setting disable color to red after click
                    System.out.print(" " + item.getText());     //Giving concole output of the booked seats
                    seats.add(item.getText());                  //adding booked seats number to array
                    data.put("usrnme", seats);                 //putting username and the seats number arraylist to the hashmap
                }
            });
        }

        //confirm exit
        btnConfirm.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ((Node) event.getSource()).getScene().getWindow().hide();
            }
        });

        //Creating the scene
        Scene scene = new Scene(borderPane, 600, 450);

        Stage seatStage = new Stage();


        seatStage.setTitle("Train Booking Menu");
        seatStage.setScene(scene);
        seatStage.showAndWait();
    }

    public static void main(String[] args) {
        launch();
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.println("--Welcome to Train Ticket Booking System--");
            System.out.println("1.Enter (V) to view all the seats");
            System.out.println("2.Enter (E) to view empty seats");
            System.out.println("3.Enter (A) to add a customer");
            System.out.println("4.Enter (D) to delete a customer");
            System.out.println("5.Enter (F) to find a seat by customer name");
            System.out.println("6.Enter (O) to view ordered seats by customer name");
            System.out.println("7.Enter (S) to store data into a file");
            System.out.println("8.Enter (L) to load data from a file ");
            System.out.println("9/Enter (Q) to Quit the programme");
            System.out.print("Enter Selected Letter : ");
            String option = scanner.next();

            switch (option){
                case "V":
                case "v":
                    GuiWindow();
                    break;
                case "E":
                case "e":
                    //viewEmptySeats();
                case "A":
                case "a":
                    //addCustomer();
                case "D":
                case "d":
                    //deleteCustomer();
                case "F":
                case "f":
                    //findSeat();
                case "O":
                case "o":
                    //seatsOrder();
                case "S":
                case "s":
                    //storeDate();
                case "L":
                case "l":
                    //loadData();
                case "Q":
                case "q":
                    break;
            }
        }
    }
}

