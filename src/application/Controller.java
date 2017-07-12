package application;

import javafx.fxml.FXML;
import javafx.scene.control.ToolBar;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import message.parser.HTMLparser;
import message.urlreader.URLReader;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Properties;


public class Controller {
    @FXML TextFlow textFlowStop1, textFlowStop2, textFlowStop3, textFlowStop4;
    @FXML Text headingStop1, headingStop2, headingStop3, headingStop4;
    @FXML ToolBar toolBar;
    Properties properties;
    @FXML
    public void initialize() throws IOException {
        System.out.println("test");
        properties = getProperties();
        Runnable task = () -> {
        	try{
        		while(true) {
        			loop(properties);
        			Thread.sleep(1000);
        		}
        	}catch(Exception e) {
        		e.printStackTrace();
        	}
    	};
    	headingStop1.setText(properties.getProperty("firstStop_Name") + ":");
        headingStop2.setText(properties.getProperty("secondStop_Name") + ":");
        headingStop3.setText(properties.getProperty("thirdStop_Name") + ":");
        headingStop4.setText(properties.getProperty("fourthStop_Name") + ":");
        Thread t = new Thread(task);
        t.start();
        //loop(properties);
    }

    private void loop(Properties properties) throws IOException {

        Text text = new Text(optimizeLayout(
                HTMLparser.getInstance()
                .parse(URLReader.getInstance()
                        .read(new URL(properties
                        .getProperty("firstStop_URL"))), "div")));
        text.setFill(Color.WHITE);
        text.setFont(Font.font("System", 24));
        textFlowStop1.getChildren().addAll(text);
        
        text = new Text(optimizeLayout(
                HTMLparser.getInstance()
                .parse(URLReader.getInstance()
                        .read(new URL(properties
                        .getProperty("secondStop_URL"))), "div")));
        text.setFill(Color.WHITE);
        text.setFont(Font.font("System", 24));
        textFlowStop2.getChildren().addAll(text);

        
        text = new Text(optimizeLayout(
                HTMLparser.getInstance()
                .parse(URLReader.getInstance()
                        .read(new URL(properties
                        .getProperty("thirdStop_URL"))), "div")));
        text.setFill(Color.WHITE);
        text.setFont(Font.font("System", 24));
        textFlowStop3.getChildren().addAll(text);

        
        text = new Text(optimizeLayout(
                HTMLparser.getInstance()
                .parse(URLReader.getInstance()
                        .read(new URL(properties
                        .getProperty("fourthStop_URL"))), "div")));
        text.setFill(Color.WHITE);
        text.setFont(Font.font("System", 24));
        textFlowStop4.getChildren().addAll(text);
    }


    private String optimizeLayout(String s) {
    	String out = Charset.forName("UTF-8").decode(Charset.defaultCharset().encode(s)).toString();
        System.out.println(out);
        return out;
    }


    private Properties getProperties() throws IOException {
        Properties properties = new Properties();
        BufferedInputStream stream = new BufferedInputStream(this.getClass().getResourceAsStream
                ("/properties/properties.prop"));
        properties.load(new InputStreamReader(stream, Charset.forName("UTF-8")));
        stream.close();
        return properties;
    }

}
