import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.*;
import java.util.*;

public class Train_Booking_System extends Application {
    static final int SEATING_CAPACITY = 42;                                                //maximum number of seats.
    private ArrayList<String> Booked_Colombo_to_Badulla = new ArrayList<String>();              //array list for store All Booked seats from colombo to badulla route.
    private ArrayList<String> Booked_Badulla_to_Colombo = new ArrayList<String>();              //array list for store All Booked seats from Badulla to colombo route.
    private HashMap<String, ArrayList<String>> CustomerBookings = new HashMap<>();         //HashMap tp store customer name and booked seats.
    private ArrayList<String> BookedSeatsFromHashMap = new ArrayList<String>();                 //Array list to get only specific Customer booked seats from the HashMap.
    private ArrayList<Button> GuiSeats = new ArrayList<Button>();                               //array list for Gui Button creation.
    private HashMap<String, String> CustomerId = new HashMap<>();                           //Hash Map to store Customer Id and the Customer name.
    private HashMap<String, String> CustomerIdRoute = new HashMap<>();                      //Hash Map to Store Customer Id and the Route.

    private void addSeats() {
        ArrayList<String> TempSeatBooking = new ArrayList<String>();                            //Temporary Array list to put seats and add to the Customer Bookings.

        //getting customer details (Customer name , customer Id , Route)
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please Enter the Customer Name :");
        String customer = scanner.nextLine();
        System.out.print("Enter the customer National ID number : ");
        String id = scanner.next();
        CustomerId.put(id, customer);                                                                                      //adding customer id and the customer name to the Hash Map
        while (true) {
            System.out.print("Please select the your journey\n 1.Colombo to Badulla\n 2.Badulla to Colombo : ");          //Giving display message to select the journey.
            String route = scanner.next();
            if (route.equals("1")) {                                                                                       //Getting Customer Route.
                route = "Colombo_to_Badulla";
                CustomerIdRoute.put(id, route);                                                                           //Storing Customer Route to the Hash Map.
                break;
            } else if (route.equals("2")) {
                route = "Badulla_to_Colombo";
                CustomerIdRoute.put(id, route);                                                                            //Storing Customer Route to the Hash Map.
                break;
            } else {                                                                                                         //for the invalid input it will display this error message.
                System.out.print("Invalid Select ! Please enter a valid input");
            }
        }


        System.out.print("Selected Seats from the Gui Window : ");                                                        //Console message while selecting seats from the Gui.

        //Brief detail to the Gui Window based on Entered Customer Details.
        Button btnConfirm = new Button(" Confirm ");
        Label label = new Label("\t      Train Booking Menu\n\n\t customer name : " + customer + "\n\t    NIC No. : " + id + "\n\tRoute : " + CustomerIdRoute.get(id));
        Label colorLabel = new Label("Booked");
        Rectangle rectangle = new Rectangle(15, 15);
        rectangle.setStyle("-fx-fill: #958feb");


        //adding elements to the created panes.
        BorderPane borderPane = new BorderPane();
        GridPane gridPaneLeft = new GridPane();
        GridPane gridPaneRight = new GridPane();
        GridPane gridPaneBottom = new GridPane();
        GridPane gridPaneTop = new GridPane();
        borderPane.setLeft(gridPaneLeft);
        borderPane.setRight(gridPaneRight);
        borderPane.setTop(gridPaneTop);
        borderPane.setBottom(gridPaneBottom);

        //Aligning Brief detail Section.
        gridPaneBottom.add(btnConfirm, 12, 1);              //confirm button
        gridPaneTop.add(label, 9, 1);                         //Heading Label
        gridPaneBottom.add(rectangle, 1, 1);                  //Map Legend Color
        gridPaneBottom.add(colorLabel, 2, 1);                 //Color Label

        //Aligning created panes.
        gridPaneLeft.setHgap(10);
        gridPaneLeft.setVgap(10);
        gridPaneLeft.setPadding(new Insets(60, 10, 100, 150));
        gridPaneRight.setHgap(10);
        gridPaneRight.setVgap(10);
        gridPaneBottom.setHgap(2);
        gridPaneRight.setPadding(new Insets(60, 150, 100, 10));
        gridPaneBottom.setPadding(new Insets(0, 150, 10, 150));
        gridPaneTop.setPadding(new Insets(10, 60, 0, 0));
        gridPaneRight.setAlignment(Pos.CENTER);
        gridPaneLeft.setAlignment(Pos.CENTER);
        gridPaneBottom.setAlignment(Pos.CENTER);
        gridPaneTop.setAlignment(Pos.CENTER);

        //Button creation for seats using loop
        int buttonCount = 0;                                                //to give the button number
        for (int row = 1; row <= 7; row++) {                                   //Giving maximum number of rows button limit.
            for (int column = 1; column <= 6; column++) {                  //Giving maximum number of column button limit.
                buttonCount++;                                             //Incrementing button count for the button name.
                Button button = new Button("" + buttonCount);           //Creating button.
                GuiSeats.add(button);                                      //adding buttons to the Main button array.(Data type is Javafx Buttons)
                button.setPrefSize(37, 30);           //setting a default size for each buttons.
                if (column < 4) {                                         //shifting left and write by the column number.(Starting from column number 0).
                    gridPaneLeft.add(button, column, row);
                } else if (column >= 4) {
                    gridPaneRight.add(button, column, row);
                }
            }
        }

        //setting a new scene
        Scene scene = new Scene(borderPane, 700, 700);         //Setting the default pane and giving the resolution.
        Stage seatStage = new Stage();


        switch (CustomerIdRoute.get(id)) {                                                                   //Switching the Displaying Gui component according to the Customer Route.
            case ("Colombo_to_Badulla"):
                for (Button eachButton : GuiSeats) {                                                         //Will perform the same action to the each button in the Main button array list.
                    boolean checkSeatsCB = Booked_Colombo_to_Badulla.contains(eachButton.getText());        //Checking whether any already booked seats are in the specific route using the route array list.
                    if (checkSeatsCB) {
                        eachButton.setDisable(true);                                                        //for already booked seats will set disable.(Cant Click the Button).
                        eachButton.setStyle("-fx-background-color: Blue");                                  //Making booked seat color as blue.
                    }
                    eachButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            eachButton.setDisable(true);                                                    //making button disable after the click.
                            eachButton.setStyle("-fx-background-color: red");                               //setting disable color to red after click.
                            System.out.print(" " + eachButton.getText());                                   //Giving console output of the booked seats.
                            Booked_Colombo_to_Badulla.add(eachButton.getText());                            //Adding the seat number to specific route array list.
                            TempSeatBooking.add(eachButton.getText());                                      //adding booked seat number to temporary array to pass the value to the HashMap.
                            CustomerBookings.put(customer, TempSeatBooking);                                //completing customer booking with name and booked seats.
                        }
                    });
                }
                break;
            case ("Badulla_to_Colombo"):
                for (Button eachButton : GuiSeats) {                                                         //Will perform the same action to the each button in the Main button array list.
                    boolean checkSeatsBC = Booked_Badulla_to_Colombo.contains(eachButton.getText());        //Checking whether any already booked seats are in the specific route using the route array list.
                    if (checkSeatsBC) {
                        eachButton.setDisable(true);                                                        //for already booked seats will set disable.(Cant Click the Button).
                        eachButton.setStyle("-fx-background-color: Blue");                                  //Making booked seat color as blue.
                    }
                    eachButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            eachButton.setDisable(true);                                                    //making button disable after the click.
                            eachButton.setStyle("-fx-background-color: red");                               //setting disable color to red after click.
                            System.out.print(" " + eachButton.getText());                                   //Giving console output of the booked seats.
                            Booked_Badulla_to_Colombo.add(eachButton.getText());                            //Adding the seat number to specific route array list.
                            TempSeatBooking.add(eachButton.getText());                                      //adding booked seat number to temporary array to pass the value to the HashMap.
                            CustomerBookings.put(customer, TempSeatBooking);                                //completing customer booking with name and booked seats.
                        }
                    });
                }

        }


        //Creating a event Handler to perform the exit of the Gui Window.
        btnConfirm.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                seatStage.close();                                           //closing the current stage.
            }
        });
        seatStage.setTitle("Train Booking Menu");                            //Giving window name.
        seatStage.setScene(scene);                                           //setting stage to the scene.
        seatStage.showAndWait();                                             //Showing and Holding window until completing the specific task.
    }


    private void viewSeats() {

        //Requesting which Route to display view seats.

        System.out.print("Please select the route \n 1.Colombo to Badulla\n 2.Badulla to Colombo : ");
        Scanner scanner = new Scanner(System.in);
        String viewRouteSeats = scanner.next();

        //Brief detail to the Gui Window based on Selected Route.
        Button btnDone = new Button(" Done ");
        Label label = new Label("\t      Train Booking Menu\n\t\t     View Seats");
        Label colorLabel = new Label("Booked");
        Rectangle rectangleBooked = new Rectangle(15, 15);
        rectangleBooked.setStyle("-fx-fill: #958feb");
        Rectangle rectangleFree = new Rectangle(15, 15);
        rectangleFree.setStyle("-fx-fill: #97cfa6");
        Label colorLabel2 = new Label(" Available");

        //adding elements to the created panes
        BorderPane borderPane = new BorderPane();
        GridPane gridPaneLeft = new GridPane();
        GridPane gridPaneRight = new GridPane();
        GridPane gridPaneBottom = new GridPane();
        GridPane gridPaneTop = new GridPane();
        borderPane.setLeft(gridPaneLeft);
        borderPane.setRight(gridPaneRight);
        borderPane.setTop(gridPaneTop);
        borderPane.setBottom(gridPaneBottom);


        //Aligning Brief detail Section.
        gridPaneBottom.add(btnDone, 35, 1);
        gridPaneTop.add(label, 9, 1);
        gridPaneBottom.add(rectangleBooked, 1, 1);
        gridPaneBottom.add(colorLabel, 2, 1);
        gridPaneBottom.add(rectangleFree, 8, 1);
        gridPaneBottom.add(colorLabel2, 9, 1);

        //Aligning created panes.
        gridPaneLeft.setHgap(10);
        gridPaneLeft.setVgap(10);
        gridPaneLeft.setPadding(new Insets(60, 10, 100, 150));
        gridPaneRight.setHgap(10);
        gridPaneRight.setVgap(10);
        gridPaneBottom.setHgap(2);
        gridPaneRight.setPadding(new Insets(60, 150, 100, 10));
        gridPaneBottom.setPadding(new Insets(0, 150, 10, 150));
        gridPaneTop.setPadding(new Insets(10, 60, 0, 0));
        gridPaneRight.setAlignment(Pos.CENTER);
        gridPaneLeft.setAlignment(Pos.CENTER);
        gridPaneBottom.setAlignment(Pos.CENTER);
        gridPaneTop.setAlignment(Pos.CENTER);

        //Button creation for seats using loop
        int buttonCount = 0;                                           //to give the button number.
        for (int row = 1; row <= 7; row++) {                              //Giving maximum number of rows button limit.
            for (int column = 1; column <= 6; column++) {              //Giving maximum number of column button limit.
                buttonCount++;                                         //Incrementing button count for the button name.
                Button button = new Button("" + buttonCount);     //Creating button.
                GuiSeats.add(button);                                  //adding buttons to the array
                button.setPrefSize(37, 30);        //setting a default size for each buttons
                if (column < 4) {                                      //shifting left and write by the column number
                    gridPaneLeft.add(button, column, row);
                } else if (column >= 4) {
                    gridPaneRight.add(button, column, row);
                }
            }
        }


        //setting a new pane
        Scene scene = new Scene(borderPane, 700, 700);
        Stage viewSeatStage = new Stage();


        for (Button eachButton : GuiSeats) {                                                    //for each button inside the array it will perform the same action.
            eachButton.setDisable(true);                                                        //Disabling all buttons for the View window.
            switch (viewRouteSeats) {                                                           //switching the view seats route according to the user input.
                case ("1"):
                    label.setText("\t      Train Booking Menu\n\t\t     View Seats\n\t route : Colombo to Badulla");            //Setting Brief Detail section according to the route.
                    boolean checkSeatsCB = Booked_Colombo_to_Badulla.contains(eachButton.getText());                            //Checking already booked seats in the specific route from the route array list.
                    if (checkSeatsCB) {
                        eachButton.setStyle("-fx-background-color: Blue");                                                      //making booked seats as blue color
                    } else {
                        eachButton.setStyle("-fx-background-color: green");                                                     //showing available seats as in green color.
                    }
                    break;
                case ("2"):
                    label.setText("\t      Train Booking Menu\n\t\t     View Seats\n\t route : Badulla to Colombo");            //Setting Brief Detail section according to the route.
                    boolean checkSeatsBC = Booked_Badulla_to_Colombo.contains(eachButton.getText());                            //Checking already booked seats in the specific route from the route array list.
                    if (checkSeatsBC) {
                        eachButton.setStyle("-fx-background-color: Blue");                                                      //making booked seats as blue color
                    } else {
                        eachButton.setStyle("-fx-background-color: green");                                                     //making booked seats as in green color.
                    }
                    break;
            }
        }

        //confirm exit
        btnDone.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                viewSeatStage.close();                                      //closing the current stage.
            }
        });

        viewSeatStage.setTitle("Train Booking Menu");                       //Giving window name.
        viewSeatStage.setScene(scene);                                      //setting stage to the scene.
        viewSeatStage.showAndWait();                                        //Showing and Holding window until completing the specific task.
    }


    private void viewEmptySeats() {

        //Requesting which Route to display empty seats.

        System.out.print("Please select the route \n 1.Colombo to Badulla\n 2.Badulla to Colombo : ");
        Scanner scanner = new Scanner(System.in);
        String EmptyRouteSeats = scanner.next();

        //Brief detail to the Gui Window
        Button btnConfirm = new Button(" Done ");
        Label label = new Label("\t      Train Booking Menu\n\t\t    Empty seats");

        //adding elements to the created panes
        BorderPane borderPane = new BorderPane();
        GridPane gridPaneLeft = new GridPane();
        GridPane gridPaneRight = new GridPane();
        GridPane gridPaneBottom = new GridPane();
        GridPane gridPaneTop = new GridPane();
        borderPane.setLeft(gridPaneLeft);
        borderPane.setRight(gridPaneRight);
        borderPane.setTop(gridPaneTop);
        borderPane.setBottom(gridPaneBottom);

        //Aligning Brief detail Section.
        gridPaneBottom.add(btnConfirm, 12, 1);
        gridPaneTop.add(label, 9, 1);

        //Aligning created panes.
        gridPaneLeft.setHgap(10);
        gridPaneLeft.setVgap(10);
        gridPaneLeft.setPadding(new Insets(60, 10, 100, 150));
        gridPaneRight.setHgap(10);
        gridPaneRight.setVgap(10);
        gridPaneBottom.setHgap(2);
        gridPaneRight.setPadding(new Insets(60, 150, 100, 10));
        gridPaneBottom.setPadding(new Insets(0, 150, 10, 150));
        gridPaneTop.setPadding(new Insets(10, 60, 0, 0));
        gridPaneRight.setAlignment(Pos.CENTER);
        gridPaneLeft.setAlignment(Pos.CENTER);
        gridPaneBottom.setAlignment(Pos.CENTER);
        gridPaneTop.setAlignment(Pos.CENTER);

        //Button creation for seats using loop
        int buttonCount = 0;                                            //to give the button number
        for (int row = 1; row <= 7; row++) {                               //Giving maximum number of rows button limit.
            for (int column = 1; column <= 6; column++) {               //Giving maximum number of column button limit.
                buttonCount++;                                          //Incrementing button count for the button name.
                Button button = new Button("" + buttonCount);      //Creating button.
                GuiSeats.add(button);                                   //adding buttons to the array
                button.setPrefSize(37, 30);         //setting a default size for each buttons
                if (column < 4) {                                       //shifting left and write by the column number
                    gridPaneLeft.add(button, column, row);
                } else if (column >= 4) {
                    gridPaneRight.add(button, column, row);
                }
            }
        }


        //setting new scene
        Scene scene = new Scene(borderPane, 700, 700);
        Stage emptySeatStage = new Stage();


        //creating array for the onclick book action
        for (Button eachButton : GuiSeats) {                                                        //for each button inside the array it will perform the same action
            eachButton.setDisable(true);                                                            //Disabling all buttons for the Empty Seats window.
            switch (EmptyRouteSeats) {
                case ("1"):
                    label.setText("\t      Train Booking Menu\n\t\t     Empty Seats\n\t route : Colombo to Badulla");           //Setting Brief Detail section according to the route.
                    boolean checkSeatsCB = Booked_Colombo_to_Badulla.contains(eachButton.getText());                            //Checking already booked seats in the specific route from the route array list.
                    if (checkSeatsCB) {
                        eachButton.setVisible(false);                                                                           //Will hide the booked seats.
                    }
                    break;
                case ("2"):
                    label.setText("\t      Train Booking Menu\n\t\t     Empty Seats\n\t route : Badulla to Colombo");           //Setting Brief Detail section according to the route.
                    boolean checkSeatsBC = Booked_Badulla_to_Colombo.contains(eachButton.getText());                            //Checking already booked seats in the specific route from the route array list.
                    if (checkSeatsBC) {
                        eachButton.setVisible(false);                                                                          //Will hide the booked seats.
                    }
                    break;
            }
        }

        //confirm exit
        btnConfirm.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                emptySeatStage.close();                             //closing the current stage.
            }
        });


        emptySeatStage.setTitle("Train Booking Menu");              //giving a window name.
        emptySeatStage.setScene(scene);                             //setting the scene to the stage.
        emptySeatStage.showAndWait();                               //Showing and Holding window until completing the specific task.
    }


    private void deleteCustomer() {
        System.out.print("Enter the Customer Name : ");                                 //Requesting Customer name.
        Scanner scanner = new Scanner(System.in);
        String delName = scanner.next();
        System.out.print("Enter the customer NIC id : ");                               //Requesting customer NIC ID.
        String delID = scanner.next();
        boolean matched = delName.equals(CustomerId.get(delID));                        //Checking given customer name and the NIC ID is matched and assigning the result as a boolean value.
        if (matched) {
            if (CustomerId.containsKey(delID)) {                                                //Checking whether Customer Name and the id is matched if only it will allow for further steps.
                BookedSeatsFromHashMap = CustomerBookings.get(CustomerId.get(delID));           //from Customer id it will get the Customer Name  and call the values (Booked seats) from the Customer bookings (booked seats) to a New array list for further delete process.
                deleteMenu:
                //To break the Switch case and the while loop.
                //Sub delete Menu.
                while ((true)) {
                    System.out.println("Customer " + delName + " has booked " + String.join(" ", BookedSeatsFromHashMap) + " seats for " + CustomerIdRoute.get(delID) + " route" + "\nSelect bellow option to delete: ");         //Displaying Customer Name + booked seats + route.
                    //Available delete options to the user.
                    System.out.println("\tTo Delete All booked Seats ENTER (1)");
                    System.out.println("\tTo Delete Seat by Seat ENTER (2)");
                    System.out.print("\tExit ENTER(3) : ");
                    int deleteOption = scanner.nextInt();
                    switch (deleteOption) {                                                         //Switching the Delete option.
                        case 1:                                                                     //deleting specific seats in colombo to badulla route array list.
                            CustomerBookings.remove(CustomerId.get(delID));                         //Getting Customer name for the given NIC ID and deleting complete record from the Customer Bookings HashMap.
                            if (CustomerIdRoute.get(delID).equals("Colombo_to_Badulla")) {          //finding what is the booked route for route array list deletion.
                                for (String del : BookedSeatsFromHashMap) {                         //for each element in the assigned BookedSeatsFromHashMap (key values from HashMap) which means booked seats.
                                    Booked_Colombo_to_Badulla.remove(del);                          //it will delete all specified seats in the Colombo to badulla route array list which is in the assigned seats array from the Hash Map.
                                }
                            } else {
                                for (String del : BookedSeatsFromHashMap) {                         //for each element in the assigned BookedSeatsFromHashMap (key values from HashMap) which means booked seats.
                                    Booked_Badulla_to_Colombo.remove(del);                          //it will delete all specified seats in the Badulla to Colombo route array list which is in the assigned seats array from the Hash Map.
                                }
                            }
                            CustomerIdRoute.remove(delID);                                         //deleting customer id and route from Hash Map.
                            CustomerId.remove(delID);                                              //deleting Customer id and name Hash Map.
                            break deleteMenu;                                                      //will exit the delete menu
                        case 2:
                            System.out.print("Enter the seat number to delete :");                      //Requesting Seat number to delete seat by seat.
                            String seatNumber = scanner.next();                                         //getting seat number which is want to delete
                            boolean seatsChecked = BookedSeatsFromHashMap.contains(seatNumber);         //checking whether entered seat is a valid seat or not from the assigned array list.
                            if (seatsChecked) {                                                         //if a valid seat.
                                BookedSeatsFromHashMap.remove(seatNumber);                              //removing seat number from the CustomerBookings (HashMap)
                                if (CustomerIdRoute.get(delID).equals("Colombo_to_Badulla")) {          //if the booked seat is for the colombo to badulla route
                                    Booked_Colombo_to_Badulla.remove(seatNumber);                       //will delete the seat from the route array list.
                                } else {
                                    Booked_Badulla_to_Colombo.remove(seatNumber);                       //if not will delete from the Badulla to Colombo route array list.
                                }
                                System.out.println("Seat number " + seatNumber + " deleted successful");        //Output message about the deleted record.
                                break;
                            } else {
                                System.out.print("Invalid seat Number");                                //Error message for the invalid seat number.
                            }
                        case 3:
                            break deleteMenu;                                                           //will exit the delete menu
                    }
                }
            }
        } else {
            System.out.print("Customer Name and Id did not matched !");                                 //Error message for the unmatched Customer Name and the NIC ID.
        }
    }

    private void findSeat() {

        //Requesting Customer Details.

        System.out.print("Enter the customer name to find the booked seats : ");
        Scanner sc = new Scanner(System.in);
        String findName = sc.nextLine();
        System.out.print("Enter the Customer " + findName + " NIC ID no. : ");
        String findID = sc.next();
        boolean matched = findName.equals(CustomerId.get(findID));                      //Checking given customer name and the NIC ID is matched and assigning the result as a boolean value.
        if (matched) {                                                                   //if both matched.
            System.out.println("Customer " + findName + " has booked  " + " " + String.join(" ", CustomerBookings.get(CustomerId.get(findID))) + " seats from " + CustomerIdRoute.get(findID));   //finding Customer booked seats from the CustomerBookings HashMap.(String.join is used to remove the Brackets in the array list).
        } else {
            System.out.print("Customer Name and Id did not matched !");                 //Error message for the unmatched Customer Name and the NIC ID.
        }
    }

    private void seatsOrder() {
        String tempName;                                                                                     //Temporary name for the sorting process
        String names[] = CustomerBookings.keySet().toArray(new String[0]);                                   //Getting customer names from the HashMap to a String Array.
        System.out.println("----Seats Successfully Sorted by Customer Name----");
        //Comparing Customer name With each other using ASCII values
        for (int firstName = 0; firstName < names.length; firstName++) {
            for (int secondName = firstName + 1; secondName < names.length; secondName++) {
                if (names[secondName].compareTo(names[firstName]) < 0) {                                     //Using CompareTo method to compare string.
                    tempName = names[firstName];
                    names[firstName] = names[secondName];
                    names[secondName] = tempName;
                }
            }
            //using string.join to remove the brackets
            System.out.print(" " + String.join(" ", CustomerBookings.get(names[firstName])));          //calling the values(seat numbers) from the sorted customer name.
        }
    }


    //File writing Method for HashMap contains String and a Array list.
    private void CustomerBookingFileWrite(HashMap<String, ArrayList<String>> hashMap, String filePath) {     //Passing the HashMap and the path for the file.
        FileWriter fileWriter;                                                                              //Creating a file writer.
        try {
            fileWriter = new FileWriter(filePath);                                                          //Passing the file path to the file writer.
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);                                 //Creating a buffer reader.
            String CustomerNames[] = hashMap.keySet().toArray(new String[0]);                               //Assigning all the Customer names in to a array list.
            for (String Customer : CustomerNames) {
                bufferedWriter.write(Customer + ":" + String.join(",", hashMap.get(Customer)));     //Writing name by name to the text file which is in the previously assigned name array list.
                bufferedWriter.newLine();                                                                   //name by name in a new line.
            }
            bufferedWriter.close();                                                                         //Closing the buffer writer.
            System.out.println("Successfully wrote to the file.");                                          //Console output message for the process completion.
        } catch (IOException e) {
            System.out.println("A n error occurred.");                                                      //Error message for interruptions during the file writing process.
        }
    }

    //File writing method for HashMap contains String and String.
    private void HashMapFileWrite(HashMap<String, String> hashMap, String filePath) {                        //Passing the HashMap and the path for the file.
        FileWriter fileWriter;                                                                              //Creating a file writer.
        try {
            fileWriter = new FileWriter(filePath);                                                          //Passing the file path to the file writer.
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);                                 //Creating a buffer reader.
            String CustomerNames[] = hashMap.keySet().toArray(new String[0]);                               //Assigning all the Customer names in to a array list.
            for (String Customer : CustomerNames) {
                bufferedWriter.write(Customer + ":" + String.join(",", hashMap.get(Customer)));     //Writing name by name to the text file which is in the previously assigned name array list.
                bufferedWriter.newLine();                                                                        //name by name in a new line.
            }
            bufferedWriter.close();                                                                          //Closing the buffer writer.
            System.out.println("Successfully wrote to the file.");                                           //Console output message for the process completion.
        } catch (IOException e) {
            System.out.println("A n error occurred.");                                                       //Error message for interruptions during the file writing process.
        }
    }

    //File writing method for Array list contains String.
    private void ArraylistFileWrite(ArrayList<String> arrayList, String filepath) {
        FileWriter fileWriter;                                                                              //Creating a file writer.
        try {
            fileWriter = new FileWriter(filepath);                                                          //Passing the file path to the file writer.
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);                                 //Creating a buffer reader.
            for (String seats : arrayList) {                                                                //for the each element in the String array list.
                bufferedWriter.write(seats);                                                                //will write the each seat to the text file.
                bufferedWriter.newLine();                                                                   //line by line.
            }
            bufferedWriter.close();                                                                         //Closing the buffer writer.
        } catch (IOException e) {
            System.out.println("A n error occurred.");                                                      //Error message for interruptions during the file writing process.
        }

    }

    //Store Data Method. Calling all above created method for file writing to the specific type of Array lists and HashMaps.
    private void storeDate() throws IOException {
        CustomerBookingFileWrite(CustomerBookings, "C:/Users/andre/Desktop/BB SUB/CustomerBookings.txt");
        HashMapFileWrite(CustomerId, "C:/Users/andre/Desktop/BB SUB/CustomerID.txt");
        HashMapFileWrite(CustomerIdRoute, "C:/Users/andre/Desktop/BB SUB/CustomerIDRoute.txt");
        ArraylistFileWrite(Booked_Badulla_to_Colombo, "C:/Users/andre/Desktop/BB SUB/BadulToColm.txt");
        ArraylistFileWrite(Booked_Colombo_to_Badulla, "C:/Users/andre/Desktop/BB SUB/ColmToBadul.txt");
    }


    //File reading method for Loading data into a  HashMap which contains String and a Array list.
    private void CustomerBookingLoad(HashMap<String, ArrayList<String>> hashMap, String filepath) throws IOException {
        String line;                                                                //String variable to store reading line value.
        BufferedReader reader = new BufferedReader(new FileReader(filepath));       //creating a new buffer reader and passing the file path.
        while ((line = reader.readLine()) != null)                                  //until for a empty line.
        {
            String[] lineIntoSplit;                                                         //Creating a array to store reading line.
            lineIntoSplit = line.split(":", 2);                                 //reading the line and spiting the string from ":" to two index positions.
            if (lineIntoSplit.length >= 2) {
                String key = lineIntoSplit[0];                                              //assigning o index position to a string to add as the key value for the Hash Map.
                String value = lineIntoSplit[1];                                            //assigning 1 index position to a string to add as the value for the Hash Map.
                ArrayList<String> tempArray = new ArrayList<>();                            //Temporary array list to add Value variable data to the Hash Map.
                tempArray.add(value);                                                       //Adding Values.
                hashMap.put(key, tempArray);                                                 //Inserting data to the Hash Map.
            }
        }
        reader.close();                                                                    //Closing the Reader.
    }

    //File reading method for Loading data into a  HashMap which contains String and String.
    private void HashMapFileLoad(HashMap<String, String> hashMap, String filePath) throws IOException {
        String line;                                                                        //String variable to store reading line value.
        BufferedReader reader = new BufferedReader(new FileReader(filePath));               //creating a new buffer reader and passing the file path.
        while ((line = reader.readLine()) != null)                                          //until for a empty line.
        {
            String[] lineIntoSplit;                                                         //Creating a array to store reading line.
            lineIntoSplit = line.split(":", 2);                                 //reading the line and spiting the string from ":" to two index positions.
            if (lineIntoSplit.length >= 2) {
                String key = lineIntoSplit[0];                                              //assigning o index position to a string to add as the key value for the Hash Map.
                String value = lineIntoSplit[1];                                            //assigning 1 index position to a string to add as the value for the Hash Map.
                hashMap.put(key, value);                                                     //Inserting data to the Hash Map.
            }
        }
        reader.close();                                                                     //Closing the Reader.
    }

    //File reading method for Loading data into a Array list which contains String.
    private void ArraylistFileLoad(ArrayList<String> arrayList, String filePath) throws IOException {
        String line;                                                                       //String variable to store reading line value.
        BufferedReader reader = new BufferedReader(new FileReader(filePath));              //creating a new buffer reader and passing the file path.
        while ((line = reader.readLine()) != null)                                         //until for a empty line.
        {
            arrayList.add(line);                                                           //Inserting data to array list.
        }
        reader.close();                                                                    //Closing the Reader.
    }

    //Load Data Method. Calling all above created method for file reading to the specific type of Array lists and HashMaps.
    private void loadData() throws IOException {
        CustomerBookingLoad(CustomerBookings, "C:/Users/andre/Desktop/BB SUB/CustomerBookings.txt");
        HashMapFileLoad(CustomerId, "C:/Users/andre/Desktop/BB SUB/CustomerID.txt");
        HashMapFileLoad(CustomerIdRoute, "C:/Users/andre/Desktop/BB SUB/CustomerIDRoute.txt");
        ArraylistFileLoad(Booked_Badulla_to_Colombo, "C:/Users/andre/Desktop/BB SUB/BadulToColm.txt");
        ArraylistFileLoad(Booked_Colombo_to_Badulla, "C:/Users/andre/Desktop/BB SUB/ColmToBadul.txt");
    }

    //Calling the Gui Classes.
    public static void main(String[] args) {
        launch();
    }


    @Override
    public void start(Stage primaryStage) throws Exception {                                            //calling the main menu method.
        Scanner scanner = new Scanner(System.in);
        menu:
        //To break the Main menu switch case and the while loop.
        while (true) {
            try {
                System.out.println("\n----------Welcome to Train Ticket Booking System----------");
                System.out.println("1.Enter (V) to view all the seats");
                System.out.println("2.Enter (E) to view empty seats");
                System.out.println("3.Enter (A) to add a customer");
                System.out.println("4.Enter (D) to delete a customer");
                System.out.println("5.Enter (F) to find a seat by customer name");
                System.out.println("6.Enter (O) to view ordered seats by customer name");
                System.out.println("7.Enter (S) to store data into a file");
                System.out.println("8.Enter (L) to load data from a file ");
                System.out.println("9/Enter (Q) to Quit the programme");
                System.out.print("Enter Selected Letter : ");                                             //Getting user option.
                String option = scanner.next();

                switch (option) {                                                                         //Switching user option.
                    case "V":
                    case "v":
                        viewSeats();                                                                     //calling Gui method to view the seats.
                        break;
                    case "E":
                    case "e":
                        viewEmptySeats();                                                               //calling Gui method to view the empty seats.
                        break;
                    case "A":
                    case "a":
                        addSeats();                                                                     //calling Gui method to book a seat.
                        break;
                    case "D":
                    case "d":
                        deleteCustomer();
                        break;
                    case "F":
                    case "f":
                        findSeat();                                                                     //calling method to find seat by user using console.
                        break;
                    case "O":
                    case "o":
                        seatsOrder();                                                                   //calling sorting method.
                        break;
                    case "S":
                    case "s":
                        storeDate();                                                                    //calling method to store data into a file.v
                        break;
                    case "L":
                    case "l":
                        loadData();                                                                     //calling method to load data from a external file.
                        break;
                    case "Q":
                    case "q":
                        break menu;                                                                     //quiting programme.
                }
            }catch (Exception e){
                System.out.println("Invalid input");                                                    //error message for invalid menu selection.
            }
        }
    }
}
