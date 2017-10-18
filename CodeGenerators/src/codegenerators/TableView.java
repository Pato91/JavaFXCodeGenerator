
package codegenerators;

import java.util.ArrayList;
import javafx.scene.web.HTMLEditor;


public class TableView {
    
    public HTMLEditor CIFBasis(String name,String t_height,ArrayList<ArrayList<String>> columns){
    
        final HTMLEditor htmlEditor = new HTMLEditor();
        htmlEditor.setId("htmlEditor");
        htmlEditor.setStyle(
                "-fx-font: 26 cambria;"
                + "-fx-border-color: brown; "
                + "-fx-border-style: dotted;"
                + "-fx-background-color:grey;"
                + "-fx-border-width: 2;"
        );
        
        StringBuilder WEB=new StringBuilder();
        WEB.append("<html>"
                + "<head>"
                + "<style type=" + "text/css" + ">"
                + "td.datacellone {"
                + "background-color: #5D5B59; color: white;}"
                + "div {"
                + "border: 5px solid black; position: center; width: 99%; background-color: yellowgreen;}"
                + "</style>"
                + "</head>"
                + "<body contentEditable=false> ");
        
        WEB.append("<br/><br/>");
        WEB.append("TableView<<label>"+name+"</label>Data> "+name+" = new TableView<>();<br/>");
        WEB.append("ObservableList<<label>"+name+"</label>Data> "+name+"Items = FXCollections.observableArrayList();<br/>");
        WEB.append(name+".setEditable(false);<br/>");
        WEB.append(name+".setPrefHeight("+t_height+");<br/>");
        WEB.append(name+".setItems("+name+"Items);<br/>");
        
        WEB.append("<br/><br/>");
        
        WEB.append(name+".getColumns().clear();<br/>");
        
        WEB.append("<br/><br/>");
        
        for(int i=0;i<columns.size();i++){
        WEB.append("TableColumn "+name+"_"+columns.get(i).get(0)+" = new TableColumn(\""+columns.get(i).get(0)+"\");<br/>");
        WEB.append(name+"_"+columns.get(i).get(0)+".setPrefWidth("+columns.get(i).get(1)+");<br/>");
        WEB.append(name+"_"+columns.get(i).get(0)+".setCellValueFactory(new PropertyValueFactory<<label>"+name+"</label>Data, String>(\""+columns.get(i).get(0)+"\"));<br/>");
        WEB.append(name+".getColumns().addAll("+name+"_"+columns.get(i).get(0)+");");
        WEB.append("<br/><br/>");
        }
        
        WEB.append("/*--------------------------"+name+" data class---------------------------*/");
        
        WEB.append("<br/><br/>");
        
        WEB.append("public class "+name+"Data {<br/>");
        
        WEB.append(name+"Data(");
        for(int i=0;i<columns.size();i++){
        
            if(i<(columns.size()-1)){
            WEB.append("String "+columns.get(i).get(0)+"x,");
            }
            if(i==(columns.size()-1)){
            WEB.append("String "+columns.get(i).get(0)+"x){<br/><br/>");
            }
        }
        for(int i=0;i<columns.size();i++){
        WEB.append(columns.get(i).get(0)+"= new SimpleStringProperty("+columns.get(i).get(0)+"x);<br/>");
        }
        WEB.append("}");
        WEB.append("<br/><br/>");
        
        for(int i=0;i<columns.size();i++){
        WEB.append("private StringProperty "+columns.get(i).get(0)+";<br/><br/>");
        WEB.append("public void set"+columns.get(i).get(0)+"(String value) {"+columns.get(i).get(0)+"Property().set(value);}<br/><br/>");
        
        WEB.append("public String get"+columns.get(i).get(0)+"() {return "+columns.get(i).get(0)+"Property().get();}<br/><br/>");
        
        WEB.append("public StringProperty "+columns.get(i).get(0)+"Property() {<br/>");
        WEB.append("if ("+columns.get(i).get(0)+" == null) {<br/>");
        WEB.append(columns.get(i).get(0)+" = new SimpleStringProperty(this, \""+columns.get(i).get(0)+"\");<br/>");
        WEB.append("}<br/>");
        WEB.append("return "+columns.get(i).get(0)+";<br/>");
        WEB.append("}<br/>");
        WEB.append("<br/><br/>");
        }
        
        WEB.append("}");
        
        
        htmlEditor.setHtmlText(WEB.toString());
        
        return htmlEditor;
    }
}
