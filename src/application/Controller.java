package application;

import javafx.fxml.FXML;
import javafx.scene.control.ToolBar;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import message.parser.HTMLparser;
import message.urlreader.URLReader;
import org.jetbrains.annotations.Contract;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;


public class Controller {
    @FXML TextFlow textFlowStop1, textFlowStop2, textFlowStop3, textFlowStop4;
    @FXML Text headingStop1, headingStop2, headingStop3, headingStop4;
    @FXML ToolBar toolBar;

    @FXML
    public void initialize() throws IOException {
        loop();
    }

    private void loop() throws IOException {
        Properties properties = getProperties();
        headingStop1.setText(properties.getProperty("firstStop_Name") + ":");
        headingStop2.setText(properties.getProperty("secondStop_Name") + ":");
        headingStop3.setText(properties.getProperty("thirdStop_Name") + ":");
        headingStop4.setText(properties.getProperty("fourthStop_Name") + ":");

        Text text =
                new Text(optimizeLayout(HTMLparser.getInstance().parse(URLReader.getInstance().read(new URL(properties
                        .getProperty
                                ("firstStop_URL"))), "div")));
        text.setFill(Color.WHITE);
        text.setFont(Font.font("System", 24));
        textFlowStop1.getChildren().addAll(text);
    }

    @Contract(pure = true)
    private String optimizeLayout(String s) {
        return s;
    }


    private Properties getProperties() throws IOException {
        Properties properties = new Properties();
        BufferedInputStream stream = new BufferedInputStream(this.getClass().getResourceAsStream
                ("/properties/properties.prop"));
        properties.load(stream);
        stream.close();
        return properties;
    }

}
