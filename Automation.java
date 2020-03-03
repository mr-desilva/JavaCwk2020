import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Automation extends Application {
    static final int SEATING_CAPACITY = 42;
    private String[] buttons = new String[SEATING_CAPACITY]; //array for button variable
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        BorderPane borderPane = new BorderPane();
        GridPane gridPane = new GridPane();
        GridPane gridPane1 = new GridPane();
        borderPane.setCenter(gridPane);
        borderPane.setTop(gridPane1);

        int bll=1;
        int row=0;
        int col=0;
        int buttontext = 1;
        String btnText ="1";
        ArrayList<String> buttonsD = new ArrayList<String>();
        for(int i=1;i<=SEATING_CAPACITY; i++){;
            Button btn = new Button(btnText);
            int index = 0;
            buttonsD.add(index,btn.getText());
            index++;
            buttontext++;
            btnText = Integer.toString(buttontext);
            gridPane.add(btn,row,col);
            col++;
            if (col>6){
                col=0;
                row=row+2;
            }
        }
        Button btnConfirm = new Button(" Confirm ");
        Button btnReset = new Button("   Reset  ");

        Label label = new Label("  Customer Name :");
        Label labe2 = new Label("  Total Count of Seats :");

        gridPane.add(btnConfirm, 14, 16);
        gridPane.add(btnReset, 14, 17);

        gridPane1.add(label, 0, 1);
        gridPane1.add(labe2, 0, 3);

        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setAlignment(Pos.CENTER);


        System.out.println(buttonsD);





        Scene scene = new Scene(borderPane, 600, 450);


        primaryStage.setTitle("Train Booking Menu");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
