package application;

import java.io.FileInputStream;
import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class SeppoHovinGUI extends Application {

	//tätä käytetään record-napin kuvien vaihdossa
	private int recordIsPressed = 0;

    @Override
    public void start(Stage primaryStage) throws Exception {

    	//kuvat
    	ImageView noPress = new ImageView(new Image(new FileInputStream(
    			"images/none.png")));
    	ImageView cPress = new ImageView(new Image(new FileInputStream(
    			"images/c.png")));
    	ImageView dPress = new ImageView(new Image(new FileInputStream(
    			"images/d.png")));
    	ImageView ePress = new ImageView(new Image(new FileInputStream(
    			"images/e.png")));
    	ImageView fPress = new ImageView(new Image(new FileInputStream(
    			"images/f.png")));
    	ImageView gPress = new ImageView(new Image(new FileInputStream(
    			"images/g.png")));
    	ImageView aPress = new ImageView(new Image(new FileInputStream(
    			"images/a.png")));
    	ImageView hPress = new ImageView(new Image(new FileInputStream(
    			"images/h.png")));
    	ImageView c2Press = new ImageView(new Image(new FileInputStream(
    			"images/c2.png")));
    	ImageView maestro = new ImageView(new Image(new FileInputStream(
    			"images/maestro.png")));
    	ImageView play = new ImageView(new Image(new FileInputStream(
    			"images/play.png")));
    	ImageView stop = new ImageView(new Image(new FileInputStream(
    			"images/stop.png")));
    	ImageView record = new ImageView(new Image(new FileInputStream(
    			"images/record.png")));
    	ImageView playPressed = new ImageView(new Image(new FileInputStream(
    			"images/playPressed.png")));
    	ImageView stopPressed = new ImageView(new Image(new FileInputStream(
    			"images/stopPressed.png")));
    	ImageView recordPressed = new ImageView(new Image(new FileInputStream(
    			"images/recordPressed.png")));
    	/*ImageView delete = new ImageView(new Image(new FileInputStream(
    			"images/recordPressed.png")));
    	ImageView deletePressed = new ImageView(new Image(new FileInputStream(
    			"images/recordPressed.png")));*/

    	//muut panet tulee borderpaneen
    	BorderPane border = new BorderPane();
    	border.getChildren().add(maestro); //taustakuva

    	//listan gridi erikseen
    	GridPane listGrid = new GridPane();
    	ListView<String> list = new ListView<String>();
    	ObservableList<String> items =FXCollections.observableArrayList (
    		    "Ukko Nooa", "BumtsiBum intro", "mystiset bm tykitys urut", "Sonata Arctica synat");
    	list.setItems(items);
    	list.setPrefWidth(150);
    	list.setPrefHeight(250);
    	listGrid.setPadding(new Insets(25,0,25,25));
    	listGrid.setStyle("-fx-control-inner-background: #F39C12; ");
    	//tekstikenttä tallennetta varten
    	TextField songName = new TextField();
    	songName.setPromptText("Nimeä uusi nauhoite");
    	songName.setPrefWidth(150);
    	songName.getText();
    	listGrid.add(list, 0, 0);
    	listGrid.add(songName, 0, 1);

    	//nappigrid
    	GridPane grid = new GridPane();
    	grid.setHgap(10);
		grid.setVgap(10);
		//napit
    	Button playButton = new Button("",play);
    	playButton.setStyle("-fx-background-color: transparent;");
    	playButton.setMaxWidth(Double.MAX_VALUE);
    	playButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event){
				//Button mode1 = (Button) event.getSource();
				//mode1.setGraphic(playPressed);
				if (recordIsPressed == 0){
					playButton.setGraphic(playPressed);
					//vaihtaa napin ikonin takaisin 100ms jälkeen
					Timeline timeline = new Timeline(new KeyFrame(
							Duration.millis(100),
							ae -> playButton.setGraphic(play)));
					timeline.play();
				}
			}
		});
    	Button stopButton = new Button("",stop);
    	stopButton.setStyle("-fx-background-color: transparent;");
    	stopButton.setMaxWidth(Double.MAX_VALUE);
    	stopButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event){
				//Button mode2 = (Button) event.getSource();
				//mode2.setGraphic(stopPressed);
				if (recordIsPressed == 0) {
					stopButton.setGraphic(stopPressed);
					Timeline timeline = new Timeline(new KeyFrame(
							Duration.millis(100),
							ae -> stopButton.setGraphic(stop)));
					timeline.play();
				}
			}
		});
    	Button recordButton = new Button("",record);
    	recordButton.setStyle("-fx-background-color: transparent;");
    	recordButton.setMaxWidth(Double.MAX_VALUE);
    	recordButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event){
				//tarkistus vaihdetaanko on- vai off-ikoniin
				if (recordIsPressed == 1){
					//Button mode3 = (Button) event.getSource();
					//mode3.setGraphic(record);
					recordButton.setGraphic(record);
					recordIsPressed = 0;
					//tähän tulee varmaan ne toiminnot
				} else {
					//Button mode3 = (Button) event.getSource();
					//mode3.setGraphic(recordPressed);
					//uuden tallenteen nimi tallentuu kun nauhoitus aloitetaan
					if (!songName.getText().isEmpty()){
						items.add(songName.getText());
						songName.clear();
						recordButton.setGraphic(recordPressed);
						recordIsPressed = 1;
					} else {
						songName.setPromptText("Kenttä ei voi olla tyhjä!");
					}
				}
			}
		});
    	Button deleteButton = new Button("Delete");
    	deleteButton.setOnAction(new EventHandler<ActionEvent>() {
    		@Override
    		public void handle(ActionEvent event){
    			if (!list.getSelectionModel().isEmpty()){
    				items.remove(list.getSelectionModel().getSelectedItem());
    			}
    			list.getSelectionModel().clearSelection();
    		}
    	});
    	//asettelu
    	grid.setPadding(new Insets(25,25,25,25));
    	grid.setHgap(10);
		grid.setVgap(22);
    	grid.add(playButton, 0, 0);
    	grid.add(stopButton, 0, 1);
    	grid.add(recordButton, 0, 2);
    	grid.add(deleteButton, 0, 4);
    	grid.setPadding(new Insets(25,25,25,25));

    	//pianonäkymä
    	Pane piano = new StackPane();
        piano.setPadding(new Insets(200, 150, 0, 0));
    	piano.getChildren().add(noPress);

    	//panejen sijoitus
    	border.setLeft(listGrid);
    	border.setCenter(grid);
    	border.setRight(piano);

        Scene scene = new Scene(border, 1200, 800);
        primaryStage.setScene(scene);
        primaryStage.show();


        //napin painallus
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
            public void handle(KeyEvent event) {
                switch (event.getCode())
                {
                case S:
                	//jos painalluskuva on jo esillä ei tehdä mitään
                    if (piano.getChildren().contains(cPress)){
                    	break;
                    } else {
                    	piano.getChildren().add(cPress);
                    };
                	break;
                case D:
                	if (piano.getChildren().contains(dPress)){
                		break;
                    } else {
                    	piano.getChildren().add(dPress);
                    };
                	break;
                case F:
                	if (piano.getChildren().contains(ePress)){
                		break;
                    } else {
                    	piano.getChildren().add(ePress);
                    };
                	break;
                case G:
                	if (piano.getChildren().contains(fPress)){
                		break;
                    } else {
                    	piano.getChildren().add(fPress);
                    };
                	break;
                case H:
                	if (piano.getChildren().contains(gPress)){
                		break;
                    } else {
                    	piano.getChildren().add(gPress);
                    };
                	break;
                case J:
                	if (piano.getChildren().contains(aPress)){
                		break;
                    } else {
                    	piano.getChildren().add(aPress);
                    };
                	break;
                case K:
                	if (piano.getChildren().contains(hPress)){
                		break;
                    } else {
                    	piano.getChildren().add(hPress);
                    };
                	break;
                case L:
                	if (piano.getChildren().contains(c2Press)){
                		break;
                    } else {
                    	piano.getChildren().add(c2Press);
                    };
                	break;
                }
            }
        });

        //napin päästö
        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode())
                {
                case S:
                    piano.getChildren().remove(cPress);
                    break;
                case D:
                    piano.getChildren().remove(dPress);
                    break;
                case F:
                    piano.getChildren().remove(ePress);
                    break;
                case G:
                	piano.getChildren().remove(fPress);
                    break;
                case H:
                	piano.getChildren().remove(gPress);
                    break;
                case J:
                	piano.getChildren().remove(aPress);
                    break;
                case K:
                	piano.getChildren().remove(hPress);
                    break;
                case L:
                	piano.getChildren().remove(c2Press);
                    break;
                }
            }
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}