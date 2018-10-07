/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epx;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.nio.charset.Charset;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *
 * @author honghung
 */
public class EpxMain extends Application {
// Application: code bằng javafx để tạo app
    private Socket client;

    private int minX = 30;
    private int minY = 30;
    private int width = 50;
    private int height = 50;
    private final int step = 5;
    private final String imageUrl = "http://allaboutwindowsphone.com/images/appicons/284761.png";

    @Override
    public void start(Stage theStage) throws Exception {
        client = new Socket("localhost", 6666);
        System.out.println("Kết nối thành công đến máy chủ.");
//        client.getInputStream();
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(client.getOutputStream(), Charset.forName("UTF-8")));
        theStage.setTitle("Canvas Example");
        Group root = new Group();
        Scene theScene = new Scene(root);
        theStage.setScene(theScene);

        Canvas canvas = new Canvas(700, 350);
        root.getChildren().add(canvas);

        GraphicsContext gc = canvas.getGraphicsContext2D();
        
        Image face = new Image(
                imageUrl,
                this.width, this.height, false, false);
        gc.drawImage(face, this.minX, this.minY);
        
        theScene.setOnKeyPressed((event) -> {
            if (null != event.getCode()) {
                switch (event.getCode()) {
                    case RIGHT:
                        gc.clearRect(this.minX, this.minY, this.width, this.height);
                        this.minX += this.step;
                        gc.drawImage(face, this.minX, this.minY);
                        break;
                    case LEFT:
                        gc.clearRect(this.minX, this.minY, this.width, this.height);
                        this.minX -= this.step;
                        gc.drawImage(face, this.minX, this.minY);
                        break;
                    case UP:
                        gc.clearRect(this.minX, this.minY, this.width, this.height);
                        this.minY -= this.step;
                        gc.drawImage(face, this.minX, this.minY);
                        break;
                    case DOWN:
                        gc.clearRect(this.minX, this.minY, this.width, this.height);
                        this.minY += this.step;
                        gc.drawImage(face, this.minX, this.minY);
                        break;
                    default:
                        break;
                }
                try {
                    bw.write(this.minX + "|" + this.minY);
                    bw.newLine();
                    bw.flush();
                } catch (IOException e) {
                    
                }

            }
        });
        theStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
