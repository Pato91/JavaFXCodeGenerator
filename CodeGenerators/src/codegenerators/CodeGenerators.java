
package codegenerators;

import java.util.ArrayList;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebView;
import javafx.stage.Popup;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class CodeGenerators extends Application {
    ///system varaibles
    BorderPane root = new BorderPane();
    final Screen screen = Screen.getPrimary();
    Rectangle2D bounds = screen.getVisualBounds();

    double gH = bounds.getHeight();
    double gW = bounds.getWidth();
    
    WebView midView=new WebView();
    
    Stage stage=new Stage();
    
    @Override
    public void start(Stage primaryStage) {
        
        root.setId("root");
        
        Label elementL = new Label("Node: ");
        elementL.setId("th2");
        
        //the list of all elements  that the code generator works on.
        final ComboBox element = new ComboBox();
        element.setPrefWidth(gW / 6);
        final ObservableList elementlist = FXCollections.observableArrayList("Table View","ListView");
        element.setItems(elementlist);
        
        //button to trigger off the process of node creation
        Button rec=new Button("Generate");
        rec.setMinWidth(0.1*0.9*gW);
        
        //hbox holding the header components of the software
        HBox top=new HBox();
        top.setAlignment(Pos.CENTER);
        top.setSpacing(5);
        top.setPadding(new Insets(5, 5, 5, 5));
        top.getChildren().addAll(elementL,element,rec);
        
        //the middle view in which the code is populated
        VBox middle=new VBox();
        middle.setAlignment(Pos.TOP_CENTER);
        middle.setSpacing(5);
        middle.setPadding(new Insets(0, 5, 5, 5));
        middle.getChildren().addAll(midView);
        
        root.setTop(top);
        root.setCenter(middle);
        
        Scene scene = new Scene(root, 300, 250);
        
        primaryStage.setTitle("Omuba");
        primaryStage.setScene(scene);
        primaryStage.setX(bounds.getMinX());
        primaryStage.setY(bounds.getMinY());
        primaryStage.setWidth(bounds.getWidth());
        primaryStage.setHeight(bounds.getHeight());
        scene.getStylesheets().add(CodeGenerators.class.getResource("styles.css").toExternalForm());
        
        primaryStage.show();
        
        stage=primaryStage;
        
        rec.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(element.getSelectionModel().getSelectedIndex()==0){
                tableview();
                }
            }
        });
    }
    
    public void tableview() {
        
        Label tableVL = new Label("Table Properties");
        tableVL.setId("th2");
        
        ///the text fields
        Label tnameL = new Label("Table Name");
        tnameL.setId("th2");
        
        TextField tnameF=new TextField();
        tnameF.setPrefWidth(150);
        //tnameF.setId("th2");
        
        ///the text fields
        Label heightL = new Label("Height");
        heightL.setId("th2");
        
        TextField heightLF=new TextField();
        heightLF.setPrefWidth(60);
        //heightLF.setId("th2");
        
        HBox topfield=new HBox();
        topfield.setSpacing(2);
        topfield.setPadding(new Insets(5, 5, 5, 5));
        topfield.setAlignment(Pos.CENTER);
        topfield.getChildren().addAll(tnameL,tnameF,heightL,heightLF);

        Label ViewHeader = new Label("Columns");
        ViewHeader.setId("th2");

        final javafx.scene.control.TableView<othersTable> others = new javafx.scene.control.TableView<>();
        others.setId("prodt");
        final ObservableList<othersTable> datax = FXCollections.observableArrayList();

        others.setPrefHeight(0.3 * gH);
        others.setEditable(false);

        TableColumn columnName = new TableColumn("columnName");
        columnName.setPrefWidth(0.2 * gW);
        columnName.setCellValueFactory(new PropertyValueFactory<othersTable, String>("columnName"));

        TableColumn columnHeight = new TableColumn("columnHeight");
        columnHeight.setPrefWidth(0.2 * gW);
        columnHeight.setCellValueFactory(new PropertyValueFactory<othersTable, String>("columnHeight"));

        others.getColumns().addAll(columnName, columnHeight);
        others.setItems(datax);
        
        ///the text fields
        Label tnameLc = new Label("Column Name");
        tnameLc.setId("th2");
        
        TextField tnameFc=new TextField();
        tnameFc.setPrefWidth(150);
        //tnameFc.setId("th2");
        
        ///the text fields
        Label heightLc = new Label("Height");
        heightLc.setId("th2");
        
        TextField heightLFc=new TextField();
        heightLFc.setPrefWidth(60);
        //heightLFc.setId("th2");
        
        Button addc=new Button("Add");
        
        HBox topfieldc=new HBox();
        topfieldc.setSpacing(2);
        topfieldc.setAlignment(Pos.CENTER);
        topfieldc.getChildren().addAll(tnameLc,tnameFc,heightLc,heightLFc,addc);
        
        Button proceed=new Button("Proceed!");
        
        Button cancel=new Button("Cancel");
        
        HBox lowbuts=new HBox();
        lowbuts.setSpacing(2);
        lowbuts.setAlignment(Pos.CENTER);
        lowbuts.getChildren().addAll(cancel,proceed);
        
        //the middle view in which the code is populated
        VBox middle=new VBox();
        middle.setAlignment(Pos.TOP_CENTER);
        middle.setSpacing(5);
        middle.setId("tooltip");
        middle.setPadding(new Insets(0, 5, 5, 5));
        middle.getChildren().addAll(topfield, ViewHeader, others,topfieldc, lowbuts);
        
        Popup gen=new Popup();
        gen.getContent().add(middle);
        gen.show(stage);

        addc.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                if(!"".equals(tnameFc.getText())&&!"".equals(heightLFc.getText())){
                datax.add(new othersTable(tnameFc.getText(),heightLFc.getText()));
                tnameFc.clear();
                //heightLFc.clear();
                }
            }
        });
        
        cancel.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                gen.hide();
            }
        });

        proceed.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                
                ArrayList<ArrayList<String>> columns=new ArrayList<>();
                
                for(int i=0;i<datax.size();i++){
                
                    ArrayList<String> items=new ArrayList<>();
                    
                    items.add(datax.get(i).getcolumnName());
                    items.add(datax.get(i).getcolumnHeight());
                    
                    columns.add(items);
                }

                TableView gettable = new TableView();
                midView.getEngine().loadContent(gettable.CIFBasis(tnameF.getText(),heightLF.getText(),columns).getHtmlText());

                gen.hide();
            }
        });
    }
    
    public void Listview() {
        
        Label tableVL = new Label("List Properties");
        tableVL.setId("th2");
        
        ///the text fields
        Label tnameL = new Label("List Name");
        tnameL.setId("th2");
        
        TextField tnameF=new TextField();
        tnameF.setPrefWidth(150);
        //tnameF.setId("th2");
        
        ///the text fields
        Label heightL = new Label("Height");
        heightL.setId("th2");
        
        TextField heightLF=new TextField();
        heightLF.setPrefWidth(60);
        //heightLF.setId("th2");
        
        HBox topfield=new HBox();
        topfield.setSpacing(2);
        topfield.setPadding(new Insets(5, 5, 5, 5));
        topfield.setAlignment(Pos.CENTER);
        topfield.getChildren().addAll(tnameL,tnameF,heightL,heightLF);

        Label ViewHeader = new Label("Columns");
        ViewHeader.setId("th2");

        final javafx.scene.control.TableView<othersTable> others = new javafx.scene.control.TableView<>();
        others.setId("prodt");
        final ObservableList<othersTable> datax = FXCollections.observableArrayList();

        others.setPrefHeight(0.3 * gH);
        others.setEditable(false);

        TableColumn columnName = new TableColumn("columnName");
        columnName.setPrefWidth(0.2 * gW);
        columnName.setCellValueFactory(new PropertyValueFactory<othersTable, String>("columnName"));

        TableColumn columnHeight = new TableColumn("columnHeight");
        columnHeight.setPrefWidth(0.2 * gW);
        columnHeight.setCellValueFactory(new PropertyValueFactory<othersTable, String>("columnHeight"));

        others.getColumns().addAll(columnName, columnHeight);
        others.setItems(datax);
        
        ///the text fields
        Label tnameLc = new Label("Column Name");
        tnameLc.setId("th2");
        
        TextField tnameFc=new TextField();
        tnameFc.setPrefWidth(150);
        //tnameFc.setId("th2");
        
        ///the text fields
        Label heightLc = new Label("Height");
        heightLc.setId("th2");
        
        TextField heightLFc=new TextField();
        heightLFc.setPrefWidth(60);
        //heightLFc.setId("th2");
        
        Button addc=new Button("Add");
        
        HBox topfieldc=new HBox();
        topfieldc.setSpacing(2);
        topfieldc.setAlignment(Pos.CENTER);
        topfieldc.getChildren().addAll(tnameLc,tnameFc,heightLc,heightLFc,addc);
        
        Button proceed=new Button("Proceed!");
        
        Button cancel=new Button("Cancel");
        
        HBox lowbuts=new HBox();
        lowbuts.setSpacing(2);
        lowbuts.setAlignment(Pos.CENTER);
        lowbuts.getChildren().addAll(cancel,proceed);
        
        //the middle view in which the code is populated
        VBox middle=new VBox();
        middle.setAlignment(Pos.TOP_CENTER);
        middle.setSpacing(5);
        middle.setId("tooltip");
        middle.setPadding(new Insets(0, 5, 5, 5));
        middle.getChildren().addAll(topfield, ViewHeader, others,topfieldc, lowbuts);
        
        Popup gen=new Popup();
        gen.getContent().add(middle);
        gen.show(stage);

        addc.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                if(!"".equals(tnameFc.getText())&&!"".equals(heightLFc.getText())){
                datax.add(new othersTable(tnameFc.getText(),heightLFc.getText()));
                tnameFc.clear();
                //heightLFc.clear();
                }
            }
        });
        
        cancel.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                gen.hide();
            }
        });

        proceed.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                
                ArrayList<ArrayList<String>> columns=new ArrayList<>();
                
                for(int i=0;i<datax.size();i++){
                
                    ArrayList<String> items=new ArrayList<>();
                    
                    items.add(datax.get(i).getcolumnName());
                    items.add(datax.get(i).getcolumnHeight());
                    
                    columns.add(items);
                }

                TableView gettable = new TableView();
                midView.getEngine().loadContent(gettable.CIFBasis(tnameF.getText(),heightLF.getText(),columns).getHtmlText());

                gen.hide();
            }
        });
    }

    public class othersTable {

            othersTable(String columnNamex, String columnHeightx) {

                columnName = new SimpleStringProperty(columnNamex);
                columnHeight = new SimpleStringProperty(columnHeightx);
                
            }

            
            private StringProperty columnName;

            public void setcolumnName(String value) {
                columnNameProperty().set(value);
            }

            public String getcolumnName() {
                return columnNameProperty().get();
            }

            public StringProperty columnNameProperty() {
                if (columnName == null) {
                    columnName = new SimpleStringProperty(this, "columnName");
                }
                return columnName;
            }

            private StringProperty columnHeight;

            public void setcolumnHeight(String value) {
                columnHeightProperty().set(value);
            }

            public String getcolumnHeight() {
                return columnHeightProperty().get();
            }

            public StringProperty columnHeightProperty() {
                if (columnHeight == null) {
                    columnHeight = new SimpleStringProperty(this, "columnHeight");
                }
                return columnHeight;
            }

        }

    public static void main(String[] args) {
        launch(args);
    }
    
}
