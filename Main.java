package com.example.company;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.Locale;

public class Main extends Application {
    Label info = new Label("");
    Label exceptionT = new Label("");
    //hard coded instances of Customer and Warehouse
    Customer[] customers = new Customer[5];
    Warehouse[] warehouses = new Warehouse[5];
    Product[] products = new Product[5];
    //ArrayList
    ArrayList<Product> productsList = new ArrayList<Product>();

    //labels and text fields
    Label nameL = new Label("Product name: ");
    TextField nameT = new TextField();
    Label obtainCostL = new Label("Obtain cost: ");
    TextField obtainCostT = new TextField();
    Label salePriceL = new Label("Sale price: ");
    TextField salePriceT = new TextField();
    Label purchasePriceL = new Label("Purchase price: ");
    TextField purchasePriceT = new TextField();
    Label stockCostL = new Label("Stock cost: ");
    TextField stockCostT = new TextField();
    Label typeL = new Label("Type (wind, string, unique, or other): ");
    TextField typeT = new TextField();

    public void start(Stage stage){
        //title, grid pane, and scene setup
        stage.setTitle("Grand Instrumentum");
        GridPane gridPane = new GridPane();
        gridPaneSetup(gridPane);
        Scene startScene = new Scene(gridPane, 600, 400);

        //label and buttons with event-handling
        info.setStyle("-fx-font: 18 arial;");
        info.setText("Product Management System");
        Button productSetup = new Button("Product Setup");
        productSetup.setOnAction(new SetupHandler(stage, startScene));
        Button productReport = new Button("Product Report");
        productReport.setOnAction(new ReportOut(stage, startScene));

        //add the label and buttons to the pane
        gridPane.add(info, 0, 0);
        gridPane.add(productSetup, 0, 1);
        gridPane.add(productReport, 0, 2);

        //set scene and show stage
        stage.setScene(startScene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    public void gridPaneSetup(GridPane gridPane){
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(10, 10, 10, 10));
    }

    class SetupHandler implements EventHandler<ActionEvent>{
        Stage stage;
        Scene scene;

        //method for passing the stage object
        SetupHandler(Stage stage, Scene scene){
            this.stage = stage;
            this.scene = scene;
        }

        public void handle(ActionEvent e){
            GridPane gridPane = new GridPane();
            gridPaneSetup(gridPane);
            Scene setupScene = new Scene(gridPane, 600, 400);
            Label title = new Label("Product Setup");
            title.setStyle("-fx-font: 18 arial;");
            exceptionT.setText("");

            //add submit button
            Button submit = new Button("Submit");
            submit.setOnAction(new SubmitHandler());

            //add clear button
            Button clear = new Button("Clear");
            clear.setOnAction(new ClearHandler());

            //add return button
            Button returnB = new Button("Return");
            returnB.setOnAction(new ReturnHandler(stage, scene));

            //add the elements to gridPane
            gridPane.add(title, 0, 0);
            gridPane.add(nameL, 0, 1);
            gridPane.add(nameT, 1, 1);
            gridPane.add(obtainCostL, 0, 2);
            gridPane.add(obtainCostT, 1, 2);
            gridPane.add(salePriceL, 0, 3);
            gridPane.add(salePriceT, 1, 3);
            gridPane.add(purchasePriceL, 0, 4);
            gridPane.add(purchasePriceT, 1, 4);
            gridPane.add(stockCostL, 0, 5);
            gridPane.add(stockCostT, 1, 5);
            gridPane.add(typeL, 0, 6);
            gridPane.add(typeT, 1, 6);
            gridPane.add(submit, 0, 8);
            gridPane.add(clear, 1, 8);
            gridPane.add(returnB, 2, 8);
            gridPane.add(exceptionT, 0, 9);

            stage.setScene(setupScene);

        }
    }

    //reportOut behavior
    class ReportOut implements EventHandler<ActionEvent> {
        Stage stage;
        Scene scene;

        //parameterized constructor
        ReportOut(Stage stage, Scene scene) {
            this.stage = stage;
            this.scene = scene;
        }

        public void handle(ActionEvent e) {
            //set up gridPane
            GridPane gridPane = new GridPane();
            gridPaneSetup(gridPane);
            Scene reportScene = new Scene(gridPane, 600, 400);

            //labels
            Label title = new Label("Run report by product type: ");
            title.setStyle("-fx-font: 18 arial;");
            Label productType = new Label("Product Type: ");

            //choice box
            ChoiceBox<String> choiceBox = new ChoiceBox<>();
            choiceBox.getItems().addAll("wind", "string", "unique", "other");

            //ReportRun button
            Button ReportRun = new Button("Run Report");
            ReportRun.setOnAction(new ReportRun(stage, scene, choiceBox));

            //add the elements to gridPane and display
            gridPane.add(title, 0, 0);
            gridPane.add(productType, 0, 2);
            gridPane.add(choiceBox, 0, 3);
            gridPane.add(ReportRun, 0, 4);
            stage.setScene(reportScene);

        }
    }

    public ObservableList getData(String option) {
        //clear arrayList and add new elements (products) to the arraylist if its type equals the selected option
        productsList.clear();
        for (int i=0; i<5; i++){
            if(products[i] == null){
                break;
            }
            else if(products[i].getType().toLowerCase().equals(option)){
                productsList.add(products[i]);
            }
        }
        return FXCollections.observableArrayList(productsList);
    }

    //reportOut behavior
    class ReportRun implements EventHandler<ActionEvent>{
        Stage stage;
        Scene scene;
        ChoiceBox<String> choiceBox;
        String option;

        //parameterized constructor
        ReportRun(Stage stage, Scene scene, ChoiceBox<String> choiceBox){
            this.stage = stage;
            this.scene = scene;
            this.choiceBox = choiceBox;
        }

        public void handle(ActionEvent e){
            //set up option and GUI elements
            option = choiceBox.getSelectionModel().getSelectedItem();
            GridPane gridPane = new GridPane();
            gridPaneSetup(gridPane);
            Scene reportScene = new Scene(gridPane, 900, 600);
            TableView productReport = new TableView<>();
            Label title = new Label("Product Report");
            title.setStyle("-fx-font: 18 arial;");
            ObservableList data = getData(option);

            //set up table
            productReport.prefHeightProperty().bind(gridPane.heightProperty());
            productReport.prefWidthProperty().bind(gridPane.widthProperty());

            // define table columns
            TableColumn productNameCol = new TableColumn("Product Name");
            TableColumn customerNameCol = new TableColumn("Customer Name");
            TableColumn customerSalesVolumeCol = new TableColumn("Customer Sales Volume");
            TableColumn warehouseNameCol = new TableColumn("Warehouse Name");
            TableColumn warehouseVolumeCol = new TableColumn("Warehouse Volume");
            TableColumn purchasePriceCol = new TableColumn("Purchase Price");
            TableColumn stockCostCol = new TableColumn("Stocking Cost");
            TableColumn salePriceCol = new TableColumn("Sale Price");

            //cell factories for data members
            productNameCol.setCellValueFactory(new PropertyValueFactory("productName"));
            customerNameCol.setCellValueFactory(new PropertyValueFactory("customerName"));
            customerSalesVolumeCol.setCellValueFactory(new PropertyValueFactory("customerSalesVolume"));
            warehouseNameCol.setCellValueFactory(new PropertyValueFactory("warehouseName"));
            warehouseVolumeCol.setCellValueFactory(new PropertyValueFactory("warehouseVolume"));
            purchasePriceCol.setCellValueFactory(new PropertyValueFactory("purchasePrice"));
            stockCostCol.setCellValueFactory(new PropertyValueFactory("stockCost"));
            salePriceCol.setCellValueFactory(new PropertyValueFactory("salePrice"));


            // set the data for the table
            productReport.setItems(data);
            // add the column definitions to the table
            productReport.getColumns().addAll(productNameCol, customerNameCol, customerSalesVolumeCol, warehouseNameCol,
                    warehouseVolumeCol, purchasePriceCol, stockCostCol, salePriceCol);

            //add return button
            Button returnB = new Button("Return");
            returnB.setOnAction(new ReturnHandler(stage, scene));

            //label, textfield, and buttom
            Label exportL = new Label("Enter location to write the file to: ");
            TextField exportT = new TextField();
            Button exportB = new Button("Export");
            exportB.setOnAction(new ExportHandler(exportT));

            //add elements to gridPane
            gridPane.add(title, 0, 0);
            gridPane.add(productReport, 0, 1);
            gridPane.add(returnB, 0, 2);
            gridPane.add(exportL, 0, 4);
            gridPane.add(exportT, 0, 5);
            gridPane.add(exportB, 0, 6);
            stage.setScene(reportScene);
        }
    }

    //export button event handler
    class ExportHandler implements EventHandler<ActionEvent>{
        TextField exportT;
        String pathname;

        ExportHandler(TextField exportT){
            this.exportT = exportT;
        }

        //handle method --> write the data to a .csv file
        public void handle(ActionEvent e){
            pathname = exportT.getText();
            try {
                writeExcel();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        //write excel method
        public void writeExcel() throws Exception {
            Writer writer = null;
            try {
                //create new file and write info if the certain product is not null
                File file = new File(pathname);
                file.createNewFile();
                writer = new BufferedWriter(new FileWriter(file));
                writer.write("Product Name" + "," + "Customer Name" + "," + "Customer Sales Volume" + "," +
                        "Warehouse Name" + "," + "Warehouse Volume" + "," + "Purchase Price" + "," +
                        "Stocking Cos" + "," + "Sale Price" + "\n");
                for (Product product : productsList) {
                    if(product != null){
                        String text = product.toString();
                        writer.write(text);
                    }
                }
                writer.flush();
                writer.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    //submit button event handler
    class SubmitHandler implements EventHandler<ActionEvent>{
        public void handle(ActionEvent e){
            customers[0] = new HighVolume("Adam Smith", 100);
            customers[1] = new LowVolume("Jane Johnson", 50);
            customers[2] = new HighVolume("Ben Chen", 40);
            customers[3] = new HighVolume("Jackie Bryan", 75);
            customers[4] = new LowVolume("Katy Lee", 300);

            warehouses[0] = new Regular("ABC Warehouse", 300);
            warehouses[1] = new TemperatureHumidity(70, 30, "Texas Warehouse", 500);
            warehouses[2] = new Regular("New York Warehouse", 200);
            warehouses[3] = new Regular("ETA Warehouse", 300);
            warehouses[4] = new TemperatureHumidity(50, 20, "GI Warehouse", 400);

            for (int i=0; i<5; i++){
                if(products[i] == null){
                    try {
                        //set product
                        products[i] = new Product(nameT.getText(), Double.parseDouble(obtainCostT.getText()),
                                Double.parseDouble(salePriceT.getText()), Double.parseDouble(purchasePriceT.getText()),
                                Double.parseDouble(stockCostT.getText()), typeT.getText(),
                                warehouses[i], customers[i]);
                        clearTFields();
                        exceptionT.setText("Successfully submitted!");
                        break;
                    }
                    catch(Exception ex){
                        exceptionT.setText("Valid input required!");
                        break;
                    }
                }
                //maximum size of 5 instances for arrays
                if(i==4){
                    exceptionT.setText("Max. capacity reached");
                }
            }
        }
    }

    //clear button event handler
    class ClearHandler implements EventHandler<ActionEvent>{
        public void handle(ActionEvent e){
            clearTFields();
            exceptionT.setText("");
        }
    }

    //return button event handler
    class ReturnHandler implements EventHandler<ActionEvent>{
        Stage stage = new Stage();
        Scene scene;

        //parameterized constructor
        ReturnHandler(Stage stage, Scene scene){
            this.stage = stage;
            this.scene = scene;
        }

        public void handle(ActionEvent e) {
            info.setText("Product Management System");
            stage.setScene(scene);
        }
    }

    //clearing all text fields
    public void clearTFields(){
        nameT.setText("");
        obtainCostT.setText("");
        salePriceT.setText("");
        purchasePriceT.setText("");
        stockCostT.setText("");
        typeT.setText("");
    }
}