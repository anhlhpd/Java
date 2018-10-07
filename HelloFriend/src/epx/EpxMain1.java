/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epx;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
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
public class EpxMain1 extends Application {

    private Socket client;

    private int minX = 30;
    private int minY = 30;
    private int width = 50;
    private int height = 50;
    private final int step = 5;
    private final String imageUrl = "http://allaboutwindowsphone.com/images/appicons/284761.png";
    private GraphicsContext gc;
    private Image face;

    @Override
    public void start(Stage theStage) throws Exception {
        client = new Socket("localhost", 6666);
        System.out.println("Kết nối thành công đến máy chủ.");
//        client.getInputStream();

        theStage.setTitle("Canvas Example");
        Group root = new Group();
        Scene theScene = new Scene(root);
        theStage.setScene(theScene);

        Canvas canvas = new Canvas(700, 350);
        root.getChildren().add(canvas);

        gc = canvas.getGraphicsContext2D();
        face = new Image(
                imageUrl,
                this.width, this.height, false, false);
        gc.drawImage(face, this.minX, this.minY);
        theStage.show();
        GameThread gt = new GameThread();
        gt.start();
    }

    public static void main(String[] args) {
        launch(args);
    }

    class GameThread extends Thread {

        @Override
        public void run() {
            try {
                BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));
                while (true) {
                    System.out.println("Hêllo");
                    String content = br.readLine();
                    String[] points = content.split("\\|");
                    gc.clearRect(minX, minY, width, height);
                    minX = Integer.parseInt(points[0]);
                    minY = Integer.parseInt(points[1]);
                    gc.drawImage(face, minX, minY);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
