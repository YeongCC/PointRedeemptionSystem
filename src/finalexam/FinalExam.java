/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalexam;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javafx.application.Application;
import javafx.event.ActionEvent;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 *
 * @author User
 */
public class FinalExam extends Application {

    private int sumItem, sumQuantity, sumPoint, totalAll;
    private static TextField id, q1 = new TextField(), q2 = new TextField(), q3 = new TextField();
    private int[] quantityGiftArray = new int[3];
    private Label n1, p1, t1, t2, t3, t4;
    private int item = 0, totalQuantity = 0, totalPoint = 0;
    private Button searchbtn, confirmbtn;
    private Label enterlength, enterwidth;
    private CheckBox annie = new CheckBox("Anniversary Cup(100pt) Quantity : "), jusco = new CheckBox("Jusco Voucher RM10(200pt) Quantity : "), umbrella = new CheckBox("Umbrella(100pt) Quantity : ");
    private GridPane center = new GridPane();
    private MemberCard card = null;
    private String[] itemName = {"Anniversary cup (100pt) ", "Jusco Voucher RM 10 (200pt) ", "Umbrella (100 pt)"};

    @Override
    public void start(Stage primaryStage) {
        BorderPane pane = new BorderPane();
        pane.setStyle("-fx-Background-color:lightblue");

        GridPane top = new GridPane();
        id = new TextField();
        Label m = new Label("Member card numbers : ");
        Label c = new Label("Customer name : ");
        Label a = new Label("Available point : ");
        n1 = new Label("---");
        p1 = new Label("---");
        searchbtn = new Button("Search");
        top.add(m, 0, 0);
        top.add(c, 0, 1);
        top.add(a, 0, 2);
        top.add(n1, 1, 1);
        top.add(p1, 1, 2);
        top.add(id, 1, 0);
        top.add(searchbtn, 3, 0);
        pane.setTop(top);
        top.setAlignment(Pos.TOP_LEFT);
        center = new GridPane();
        center.add(annie, 0, 0);
        center.add(jusco, 0, 1);
        center.add(umbrella, 0, 2);
        center.add(q1, 1, 0);
        center.add(q2, 1, 1);
        center.add(q3, 1, 2);
        pane.setCenter(center);
        center.setAlignment(Pos.CENTER_LEFT);
        center.setVisible(false);

        GridPane botton = new GridPane();
        Label ti = new Label("Total item: ");
        Label tq = new Label("Total quantity : ");
        Label tp = new Label("Total Point : ");
        Label rp = new Label("Remaining point : ");
        t1 = new Label();
        t2 = new Label();
        t3 = new Label();
        t4 = new Label();
        confirmbtn = new Button("Confirm");
        botton.add(ti, 0, 0);
        botton.add(tq, 0, 1);
        botton.add(tp, 0, 2);
        botton.add(rp, 0, 3);
        botton.add(t1, 1, 0);
        botton.add(t2, 1, 1);
        botton.add(t3, 1, 2);
        botton.add(t4, 1, 3);
        botton.add(confirmbtn, 4, 4);
        pane.setBottom(botton);
        botton.setAlignment(Pos.TOP_LEFT);

        Scene scene = new Scene(pane, 400, 250);
        primaryStage.setTitle("Point Redemption System");
        primaryStage.setScene(scene);
        primaryStage.show();

        searchbtn.setOnAction(e -> {
            try {
                String fileName = id.getText() + ".txt";
                Scanner input = new Scanner(new File(fileName));
                String name = input.next();
                String point = input.next();
                input.close();
                int Setpoint = Integer.parseInt(point);
                n1.setText(String.valueOf(name));
                p1.setText(String.valueOf(point));
                t4.setText(String.valueOf(point));
                card = new MemberCard(fileName, name);
                card.setPoint(Setpoint);
                center.setVisible(true);
            } catch (FileNotFoundException fnf) {
                JOptionPane.showMessageDialog(null, "File not found");
            }

        });

        annie.setOnAction(e -> updatePoint(e));
        jusco.setOnAction(e -> updatePoint(e));
        umbrella.setOnAction(e -> updatePoint(e));

        confirmbtn.setOnAction(e
                -> {
            String message = "Member card number : " + card.getCardnumber()
                    + "\nCard holder name : " + card.getMembercardhordlername()
                    + "\nReamaining point : " + card.getPoint()
                    + "\nRedeemption List : ";
            int itemNum = 1;
            for (int i = 0; i < quantityGiftArray.length; i++) {
                if (quantityGiftArray[i] > 0) {
                    message += "\n" + (itemNum++) + ")" + itemName[i] + " x " + quantityGiftArray[i];
                }
            }
            JOptionPane.showMessageDialog(null, message);
        }
        );

    }

    public void updatePoint(ActionEvent e) {

        try {
            int quantity = 0;
            int point = 0;
            int totalPoint = 0;
            if (e.getSource() == annie) {
                point = 100;
                if (annie.isSelected()) {
                    quantity = Integer.parseInt(q1.getText());
                    if (!addSelection(quantity, point, 0)) {
                        annie.setSelected(false);
                    }
                } else {
                    quantity = quantityGiftArray[0];
                    if (quantity > 0) {
                        removeSelection(quantity, point);
                    }
                    quantityGiftArray[0] = 0;
                }
            } else if (e.getSource() == jusco) {
                point = 200;
                if (jusco.isSelected()) {
                    quantity = Integer.parseInt(q2.getText());
                    if (!addSelection(quantity, point, 1)) {
                        jusco.setSelected(false);
                    }
                } else {
                    quantity = quantityGiftArray[1];
                    if (quantity > 0) {
                        removeSelection(quantity, point);
                    }
                    quantityGiftArray[1] = 0;
                }
            } else if (e.getSource() == umbrella) {
                point = 100;
                if (umbrella.isSelected()) {
                    quantity = Integer.parseInt(q3.getText());
                    if (!addSelection(quantity, point, 2)) {
                        umbrella.setSelected(false);
                    }
                } else {
                    quantity = quantityGiftArray[2];
                    if (quantity > 0) {
                        removeSelection(quantity, point);
                    }
                    quantityGiftArray[2] = 0;
                }

            }
            t1.setText(String.valueOf(item));
            t2.setText(String.valueOf(totalQuantity));
            t3.setText(String.valueOf(this.totalPoint));
        } catch (NumberFormatException nfe) {
            JOptionPane.showMessageDialog(null, "The quantity must be numeric number ! ");
        }
    }

    public boolean addSelection(int quantity, int point, int index) {
        int sum = quantity * point;
        System.out.println(sum);
        if (card.isRedeemable(sum)) {
            System.out.println("--" + sum);
            item++;
            totalQuantity += quantity;
            totalPoint += sum;
            card.redeemPoint(sum);

            quantityGiftArray[index] = quantity;
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "Insufficient point ! ");
        }
        return false;
    }

    public void removeSelection(int quantity, int point) {
        int sum = quantity * point;
        item--;
        totalQuantity -= quantity;
        totalPoint -= sum;
        card.setPoint(card.getPoint() + sum);
    }

    public static void main(String[] args) {
        launch(args);
    }

}
