package filecompression;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * GUI to display the results of file compression.
 */
public class FileCompressionGUI extends Application {

    @Override
    public void start(Stage primaryStage) {
        Button btn = new Button();
        btn.setText("Compress File");
        TextField tf = new TextField(); //file input to compress
        
        //Make image
        ImageView iv = new ImageView();
        iv.setFitHeight(200);
        iv.setFitWidth(200);
        
        //Size output
        Text tSizeBefore = new Text("Length Before:");
        Text tSizeAfter = new Text("Length After:");
        TextField tfSizeBefore = new TextField();
        TextField tfSizeAfter = new TextField();

        //area to display string input and compressed output
        Text tStringBefore = new Text("Bits Before:");
        Text tStringAfter = new Text("Bits After:");
        TextArea taBefore = new TextArea();
        TextArea taAfter = new TextArea();
        
        //Compress button event handler
        btn.setOnAction((ActionEvent event) -> {
            try {
                //load and read file
                String fileName = tf.getText();
                
                String s = readBinaryFileToString(fileName);
                tfSizeBefore.setText("" + s.length());
                
                //Image image = new Image(fileName);
                Image image = new Image(new FileInputStream(fileName));
                iv.setImage(image);
                
                //Display orignial string
                String first100bytes = "";
                for (int i = 0; i < 100; i++) {
                    first100bytes += s.substring(i * 8, (i + 1) * 8) + " ";
                    if ((i + 1) % 10 == 0) {
                        first100bytes += "\n";
                    }
                }
                taBefore.setText(first100bytes);
                
                //Compress string
                String compressed = FileCompression.iterativeCompress(s);
                
                tfSizeAfter.setText("" + compressed.length());
                
                //Display compressed string
                String first1000chars = "";
                for (int i = 0; i < 10; i++) {
                    if (compressed.length() > (i + 1) * 100) {
                        first1000chars += compressed.substring(i * 100, (i + 1) * 100) + "\n";
                    }
                }
                taAfter.setText(first1000chars);
            } catch (IOException ex) {
                System.out.println(ex.toString());
            }
        });

        //Layout components
        GridPane root = new GridPane();
        root.setHgap(10);
        root.setVgap(10);
        root.setAlignment(Pos.CENTER);
        root.add(btn, 0, 0);
        root.add(tf, 1, 0);
        root.add(iv, 0, 1, 2, 1);
        root.add(tStringBefore, 0, 2);
        root.add(taBefore, 0, 3, 2, 1);
        root.add(tSizeBefore, 0, 4);
        root.add(tfSizeBefore, 1, 4);
        root.add(tStringAfter, 0, 5);
        root.add(taAfter, 0, 6, 2, 1);
        root.add(tSizeAfter, 0, 7);
        root.add(tfSizeAfter, 1, 7);

        Scene scene = new Scene(root, 600, 800);

        primaryStage.setTitle("File Compression Lab");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /** Read fileName as a binary file and return a String of 1s and 0s*/
    private static String readBinaryFileToString(String fileName) throws FileNotFoundException, IOException {

        boolean endOfFile = false; 

        // Open as a binary file.
        DataInputStream inputFile = new DataInputStream(new FileInputStream(fileName));

        String result = "";
        int b;
        
        int count = 0;

        // Read data from the file.
        while (!endOfFile) {
            try {
                b = inputFile.readUnsignedByte();
                System.out.println("next");
                System.out.println(b);
                System.out.println(Integer.toBinaryString(b));
                count++;
                result += Integer.toBinaryString(b);

            } catch (EOFException e) {
                endOfFile = true;
            }
        }
        System.out.println("byte numbers: " + count);

        // Close the file.
        inputFile.close();
        return result;

    }

    /** Approximate the size that a compressed string would require.  1 byte for each 
     * count + 1 bit for the value = 9 bits per block.
     */
     private int approximateSize(String s) {
        String[] sArray = s.split(" ");
        return (sArray.length/2)*9;
    }
        
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
