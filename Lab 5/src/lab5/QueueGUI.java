package lab5;

/****************************************** 
* @Author John Morrisett
* @since 3/12/2021
* Program: QueueGUI.java
* COMSC111L.51
* Professor Patterson
* Purpose:  Creates a GUI to display the contents of a queue.
* Implements the LinkedQueue class defined earlier.
*******************************************/

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import jsjf.LinkedQueue;


public class QueueGUI extends Application {
	//class that creates a gui for the a queue
	
	//gui elements
	Text toppingText = new Text("QueueGUI");
	Rectangle topping = new Rectangle(500, 40, Color.GREEN);
	StackPane header = new StackPane(topping, toppingText);
	
	TextField enterField = new TextField();
	Button addButton = new Button("Add item(s)");
	Button removeButton = new Button("Remove top item");
	VBox controlField = new VBox(20, enterField, addButton, removeButton);
	
	Rectangle controlBoxBackground = new Rectangle(200, 460, Color.WHITE);
	StackPane controlBox = new StackPane(controlBoxBackground, controlField);
	
	VBox dataBox = new VBox();
	Rectangle queueInfoBackground = new Rectangle(300, 460, Color.WHITE);
	StackPane queueInfo = new StackPane(queueInfoBackground, dataBox);
	
	HBox body = new HBox(queueInfo, controlBox);
	VBox GUI = new VBox(header, body);
	Pane pane = new Pane(GUI);
	Scene scene = new Scene(pane, 500, 500);

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		LinkedQueue<String> data = new LinkedQueue<String>();

		//setting style for gui elements
		queueInfo.setStyle("-fx-border-color:  Black");
		controlBox.setStyle("-fx-background-color: White; -fx-border-color:  Black");
		controlField.setAlignment(Pos.TOP_CENTER);
		
		//functionality for the pressed keys
		enterField.setOnKeyPressed(e->{
	         if (e.getCode() == KeyCode.ENTER) {
	        	 addSingle(data);
	         }
		});
		
		
        addButton.setOnAction(e-> {
        	addSingle(data);
        });
        
        
        removeButton.setOnAction(e->{
        	removeSingle(data);
        });
        
		
		primaryStage.setTitle("Queue GUI");
		primaryStage.setScene(scene);
		primaryStage.show();
		System.out.println("Brought to you by John Morrisett 3/11/2021");
	}
	
	
	public void removeSingle(LinkedQueue<String> data) {
		//removes a single element from the queue and displays the new elements
    	if (!data.isEmpty()) {
    		//make the new queue, and set up for the loop
        	data.dequeue();
        	dataBox.getChildren().clear();
        	int limit = data.size();
        	int count = 0;
        	
        	while (count < limit) {
        		//add back in the old values to the queue display
        		String value = data.dequeue();
        		data.enqueue(value);
        		
        		//alternate elements for visual style
        		Color color = Color.WHITE;
        		if (count % 2 == 0)
        			color = Color.GAINSBORO;
        		
        		addEntry(value, color);
        		count++;
        	}
    	} else {
    		//if there is nothing to remove, display an error message
    		dataBox.getChildren().clear();
    		addEntry("No data to remove", Color.RED);
    	}
    	
    	enterField.requestFocus();
	}
	
	
	public void addSingle(LinkedQueue<String> data) {
		//method that enqueues the textbox, and adds a new element to the display
		if (!enterField.getText().equals("")) {  //only add if textbox is not empty
	    	String entryText = enterField.getText();
	    	enterField.clear();
	    	if (data.isEmpty()) {
	    		//if an error message is displayed, remove the message
	    		dataBox.getChildren().clear();
	    	}
	    	data.enqueue(entryText);
	    	
	    	//alternate elements for visual style
	    	Color color = Color.WHITE;
	    	if (data.size() % 2 == 1)
	    		color = Color.GAINSBORO;
	    	addEntry(entryText, color);
		}
		System.out.println(data);
    	enterField.requestFocus();
	}
	
    public void addEntry(String data, Color color) {
    	//method that adds the entry to the display
    	Text text = new Text(data);
    	Rectangle entryBackground = new Rectangle(300, 30, color);
    	StackPane entry = new StackPane(entryBackground, text);
    	dataBox.getChildren().add(entry);
    	
    }
	
	public static void main(String args[]) {
		Application.launch(args);
	}

}