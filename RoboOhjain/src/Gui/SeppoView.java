package Gui;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Timer;

import Controller.SeppoControl;
import Controller.SeppoControl_IF;
import Model.Note;
import Model.SeppoModel;
import Model.SeppoModel_IF;
import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class SeppoView extends Application implements SeppoView_IF {

	private SeppoControl_IF kontrolleri;
	private File folder = new File("C:\\Users\\Jesme\\workspace\\RoboOhjain\\Songs\\");
	private File[] listOfFiles = folder.listFiles();
	private ObservableList<String> items;
	ListView<String> list;
	private String newSong; // uuden nauhoitteen nimi
	static DataOutputStream out;
	private int recordIsPressed = 0; // monitoroidaan onko nauhoitus käynnissä
	private int playIsPressed = 0; // monitoroidaan onko toisto käynnissä
	public String whichNote;
	public int duration;
	Timer timer = new Timer();

	ArrayList<Note> song = new ArrayList<Note>();
	Note note;

	@Override
	public void init() {
		SeppoModel_IF model = new SeppoModel();
		kontrolleri = new SeppoControl(this, model);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		// SongBank song = new SongBank();
		kontrolleri.connect();

		// kuvat
		ImageView noPress = new ImageView(new Image(new FileInputStream("images/none.png")));
		ImageView cPress = new ImageView(new Image(new FileInputStream("images/c.png")));
		ImageView dPress = new ImageView(new Image(new FileInputStream("images/d.png")));
		ImageView ePress = new ImageView(new Image(new FileInputStream("images/e.png")));
		ImageView fPress = new ImageView(new Image(new FileInputStream("images/f.png")));
		ImageView gPress = new ImageView(new Image(new FileInputStream("images/g.png")));
		ImageView aPress = new ImageView(new Image(new FileInputStream("images/a.png")));
		ImageView hPress = new ImageView(new Image(new FileInputStream("images/h.png")));
		ImageView c2Press = new ImageView(new Image(new FileInputStream("images/c2.png")));
		ImageView maestro = new ImageView(new Image(new FileInputStream("images/maestro.png")));
		ImageView play = new ImageView(new Image(new FileInputStream("images/play.png")));
		ImageView stop = new ImageView(new Image(new FileInputStream("images/stop.png")));
		ImageView record = new ImageView(new Image(new FileInputStream("images/record.png")));
		ImageView delete = new ImageView(new Image(new FileInputStream("images/delete.png")));
		ImageView playPressed = new ImageView(new Image(new FileInputStream("images/playPressed.png")));
		ImageView stopPressed = new ImageView(new Image(new FileInputStream("images/stopPressed.png")));
		ImageView recordPressed = new ImageView(new Image(new FileInputStream("images/recordPressed.png")));
		ImageView deletePressed = new ImageView(new Image(new FileInputStream("images/recordPressed.png")));
		ImageView playError = new ImageView(new Image(new FileInputStream("images/playError.png")));
		ImageView stopError = new ImageView(new Image(new FileInputStream("images/stopError.png")));
		ImageView recordError = new ImageView(new Image(new FileInputStream("images/recordError.png")));
		ImageView deleteError = new ImageView(new Image(new FileInputStream("images/deleteError.png")));

		// muut panet tulee borderpaneen
		BorderPane border = new BorderPane();
		border.getChildren().add(maestro); // taustakuva

		// listan gridi erikseen
		GridPane listGrid = new GridPane();
		this.list = new ListView<String>();
		this.items = FXCollections.observableArrayList();
		// lisää filut items-listaan
		listList();
		list.setItems(items);
		list.setPrefWidth(150);
		list.setPrefHeight(250);
		listGrid.setPadding(new Insets(25, 0, 25, 25));
		listGrid.setStyle("-fx-control-inner-background: #F39C12; " + "-fx-selection-bar: purple;");
		// tekstikenttä tallennetta varten
		TextField songName = new TextField();
		songName.setPromptText("Nimeä uusi nauhoite");
		songName.setPrefWidth(150);
		songName.getText();
		listGrid.add(list, 0, 0);
		listGrid.add(songName, 0, 1);

		// nappigrid
		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		// napit
		Button playButton = new Button("", play);
		playButton.setStyle("-fx-background-color: transparent;");
		playButton.setMaxWidth(Double.MAX_VALUE);
		playButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				// jos record on pohjassa, ei voi käyttää muita nappeja
				if (recordIsPressed == 0) {
					if (!list.getSelectionModel().isEmpty()) {
						playButton.setGraphic(playPressed);
						songName.setPromptText("Toistetaan...");

						kontrolleri.sendInt(1);

						kontrolleri.sendSong();
						System.out.println("Soittaaa");
						playIsPressed = 1;

						// jos ei ole valittu kappaletta
					} else {
						songName.setPromptText("Ei valintaa");
						playButton.setGraphic(playError);
						Timeline timeline = new Timeline(
								new KeyFrame(Duration.millis(100), ae -> playButton.setGraphic(play)));
						timeline.play();
						timeline = new Timeline(new KeyFrame(Duration.millis(2000),
								ae -> songName.setPromptText("Nimeä uusi nauhoite")));
						timeline.play();
					}
				} else {
					// vilkuttaa nappia mahdollisimman epäoptimoidulla tavalla
					songName.setPromptText("Nauhoitus käynnissä!");
					Timeline timeline = new Timeline(
							new KeyFrame(Duration.millis(2000), ae -> songName.setPromptText("Nauhoitetaan...")));
					timeline.play();
					playButton.setGraphic(playError);
					timeline = new Timeline(new KeyFrame(Duration.millis(100), ae -> playButton.setGraphic(play)));
					timeline.play();
				}
			}
		});

		Button stopButton = new Button("", stop);
		stopButton.setStyle("-fx-background-color: transparent;");
		stopButton.setMaxWidth(Double.MAX_VALUE);
		stopButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {

				if (recordIsPressed == 0) {

					if (playIsPressed == 1) {

						kontrolleri.sendInt(0);

						stopButton.setGraphic(stopPressed);
						Timeline timeline = new Timeline(
								new KeyFrame(Duration.millis(200), ae -> stopButton.setGraphic(stop)));
						timeline.play();
						playButton.setGraphic(play);
						playIsPressed = 0;
						songName.setPromptText("Nimeä uusi nauhoite");
					} else {
						songName.setPromptText("Ei valintaa");
						stopButton.setGraphic(stopError);
						Timeline timeline = new Timeline(
								new KeyFrame(Duration.millis(100), ae -> stopButton.setGraphic(stop)));
						timeline.play();
						timeline = new Timeline(new KeyFrame(Duration.millis(2000),
								ae -> songName.setPromptText("Nimeä uusi nauhoite")));
						timeline.play();
					}
				} else {
					// vilkuttaa nappia mahdollisimman epäoptimoidulla tavalla
					songName.setPromptText("Nauhoitus käynnissä!");
					Timeline timeline = new Timeline(
							new KeyFrame(Duration.millis(2000), ae -> songName.setPromptText("Nauhoitetaan...")));
					timeline.play();
					stopButton.setGraphic(stopError);
					timeline = new Timeline(new KeyFrame(Duration.millis(100), ae -> stopButton.setGraphic(stop)));
					timeline.play();
				}
			}
		});

		Button recordButton = new Button("", record);
		recordButton.setStyle("-fx-background-color: transparent;");
		recordButton.setMaxWidth(Double.MAX_VALUE);
		recordButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				// tarkistus vaihdetaanko on- vai off-ikoniin
				if (recordIsPressed == 1) {
					recordButton.setGraphic(record);
					recordIsPressed = 0;

					createSong(newSong);
					items.add(newSong);

					// uuden kappaleen luonti tapahtuu kun nauhoitus loppuu

					songName.setPromptText("Kappale tallennettu!");
					Timeline timeline = new Timeline(
							new KeyFrame(Duration.millis(2000), ae -> songName.setPromptText("Nimeä uusi nauhoite")));
					timeline.play();

				} else {
					if (!songName.getText().isEmpty()) {

						// toiston aikana ei voi nauhoittaa uutta kappaletta
						if (playIsPressed == 1) {
							recordButton.setGraphic(recordError);
							Timeline timeline = new Timeline(
									new KeyFrame(Duration.millis(100), ae -> recordButton.setGraphic(record)));
							timeline.play();
							songName.setPromptText("Toisto käynnissä!");
						} else {
							// uuden kappaleen nimi otetaan talteen
							// myöhemmäksi
							newSong = songName.getText();
							songName.clear();
							recordButton.setGraphic(recordPressed);
							songName.setPromptText("Nauhoitetaan...");
							recordIsPressed = 1;

						}
					} else {
						if (playIsPressed == 1) {
							recordButton.setGraphic(recordError);
							Timeline timeline = new Timeline(
									new KeyFrame(Duration.millis(100), ae -> recordButton.setGraphic(record)));
							timeline.play();
							songName.setPromptText("Toisto käynnissä!");
							timeline = new Timeline(
									new KeyFrame(Duration.millis(2000), ae -> songName.setPromptText("Toistetaan...")));
							timeline.play();
							songName.setPromptText("Toisto käynnissä!");
						} else {
							recordButton.setGraphic(recordError);
							Timeline timeline = new Timeline(
									new KeyFrame(Duration.millis(100), ae -> recordButton.setGraphic(record)));
							timeline.play();
							songName.setPromptText("Anna ensin nimi!");
							timeline = new Timeline(new KeyFrame(Duration.millis(2000),
									ae -> songName.setPromptText("Nimeä uusi nauhoite")));
							timeline.play();
						}
					}
				}
			}
		});

		Button deleteButton = new Button("", delete);
		deleteButton.setStyle("-fx-background-color: transparent;");
		deleteButton.setMaxWidth(Double.MAX_VALUE);
		deleteButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if (recordIsPressed == 0) {
					if (!list.getSelectionModel().isEmpty()) {
						if (playIsPressed == 1) {
							deleteButton.setGraphic(deleteError);
							Timeline timeline = new Timeline(
									new KeyFrame(Duration.millis(100), ae -> deleteButton.setGraphic(delete)));
							timeline.play();
							songName.setPromptText("Toisto käynnissä!");
							timeline = new Timeline(
									new KeyFrame(Duration.millis(2000), ae -> songName.setPromptText("Toistetaan...")));
							timeline.play();
						} else {
							deleteButton.setGraphic(deletePressed);
							Timeline timeline = new Timeline(
									new KeyFrame(Duration.millis(200), ae -> deleteButton.setGraphic(delete)));

							timeline.play();

							kontrolleri.deleteFile();

							items.remove(list.getSelectionModel().getSelectedItem());

							list.getSelectionModel().clearSelection();
							songName.setPromptText("Kappale poistettu!");
							list.getSelectionModel().clearSelection();
							timeline = new Timeline(new KeyFrame(Duration.millis(2000),
									ae -> songName.setPromptText("Nime� uusi nauhoite")));
							timeline.play();
						}
					} else {
						if (playIsPressed == 1) {
							deleteButton.setGraphic(deleteError);
							Timeline timeline = new Timeline(
									new KeyFrame(Duration.millis(100), ae -> deleteButton.setGraphic(delete)));
							timeline.play();
							songName.setPromptText("Toisto käynnissä!");
							timeline = new Timeline(
									new KeyFrame(Duration.millis(2000), ae -> songName.setPromptText("Toistetaan...")));
							timeline.play();
						} else {
							songName.setPromptText("Ei valintaa");
							deleteButton.setGraphic(deleteError);
							Timeline timeline = new Timeline(
									new KeyFrame(Duration.millis(100), ae -> deleteButton.setGraphic(delete)));
							timeline.play();
							timeline = new Timeline(new KeyFrame(Duration.millis(2000),
									ae -> songName.setPromptText("Nimeä uusi nauhoite")));
							timeline.play();
						}
					}
				} else {
					// vilkuttaa nappia mahdollisimman epäoptimoidulla tavalla
					songName.setPromptText("Nauhoitus käynnissä!");
					Timeline timeline = new Timeline(
							new KeyFrame(Duration.millis(2000), ae -> songName.setPromptText("Nauhoitetaan...")));
					timeline.play();
					deleteButton.setGraphic(deleteError);
					timeline = new Timeline(new KeyFrame(Duration.millis(100), ae -> deleteButton.setGraphic(delete)));
					timeline.play();
				}

			}
		});
		// asettelu
		grid.setPadding(new Insets(25, 25, 25, 25));
		grid.setHgap(10);
		grid.setVgap(17);
		grid.add(playButton, 0, 0);
		grid.add(stopButton, 0, 1);
		grid.add(recordButton, 0, 2);
		grid.add(deleteButton, 0, 3);
		grid.setPadding(new Insets(25, 25, 25, 25));

		// pianonäkymä
		Pane piano = new StackPane();
		piano.setPadding(new Insets(220, 140, 0, 0));
		piano.getChildren().add(noPress);

		// panejen sijoitus
		border.setLeft(listGrid);
		border.setCenter(grid);
		border.setRight(piano);

		Scene scene = new Scene(border, 1180, 790);
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();


		// napin painallus
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				switch (event.getCode()) {
				case S:
					// jos painalluskuva on jo esillä ei tehdä mitään
					if (piano.getChildren().contains(cPress)) {
						break;
					} else {
						piano.getChildren().add(cPress);

						kontrolleri.sendInt(2);

						kontrolleri.timerStart();

					}
					;
					break;
				case D:
					if (piano.getChildren().contains(dPress)) {
						break;
					} else {
						piano.getChildren().add(dPress);

						kontrolleri.sendInt(3);

						kontrolleri.timerStart();

					}
					;
					break;
				case F:
					if (piano.getChildren().contains(ePress)) {
						break;
					} else {
						piano.getChildren().add(ePress);
						kontrolleri.sendInt(4);

						kontrolleri.timerStart();
					}
					;
					break;
				case G:
					if (piano.getChildren().contains(fPress)) {
						break;
					} else {
						piano.getChildren().add(fPress);
						kontrolleri.sendInt(5);
						kontrolleri.timerStart();
					}
					;
					break;
				case H:
					if (piano.getChildren().contains(gPress)) {
						break;
					} else {
						piano.getChildren().add(gPress);
						kontrolleri.sendInt(6);
						kontrolleri.timerStart();
					}
					;
					break;
				case J:
					if (piano.getChildren().contains(aPress)) {
						break;
					} else {
						piano.getChildren().add(aPress);
						kontrolleri.sendInt(7);

						kontrolleri.timerStart();
					}
					;
					break;
				case K:
					if (piano.getChildren().contains(hPress)) {
						break;
					} else {
						piano.getChildren().add(hPress);
						kontrolleri.sendInt(8);

						kontrolleri.timerStart();
					}
					;
					break;
				case L:
					if (piano.getChildren().contains(c2Press)) {
						break;
					} else {
						piano.getChildren().add(c2Press);
						kontrolleri.sendInt(9);
						kontrolleri.timerStart();

					}
					;
					break;
				}
			}
		});

		// napin päästö
		scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				switch (event.getCode()) {
				case S:
					piano.getChildren().remove(cPress);
					kontrolleri.sendInt(10);

					kontrolleri.timerStop();
					note = new Note('c', (int) kontrolleri.getTime());
					System.out.println(note.getKey() + note.getDelay());

					if (recordIsPressed == 1){
						song.add(note);
					}

					break;
				case D:
					piano.getChildren().remove(dPress);
					kontrolleri.sendInt(10);

					kontrolleri.timerStop();
					note = new Note('d', (int) kontrolleri.getTime());
					System.out.println(note.getKey() + note.getDelay());

					if (recordIsPressed == 1){
						song.add(note);
					}

					break;
				case F:
					piano.getChildren().remove(ePress);
					kontrolleri.sendInt(10);

					kontrolleri.timerStop();
					note = new Note('e', (int) kontrolleri.getTime());
					System.out.println(note.getKey() + note.getDelay());

					if (recordIsPressed == 1){
						song.add(note);
					}

					break;
				case G:
					piano.getChildren().remove(fPress);
					kontrolleri.sendInt(10);

					kontrolleri.timerStop();
					note = new Note('f', (int) kontrolleri.getTime());
					System.out.println(note.getKey() + note.getDelay());

					if (recordIsPressed == 1){
						song.add(note);
					}

					break;
				case H:
					piano.getChildren().remove(gPress);
					kontrolleri.sendInt(10);

					kontrolleri.timerStop();
					note = new Note('g', (int) kontrolleri.getTime());
					System.out.println(note.getKey() + note.getDelay());

					if (recordIsPressed == 1){
						song.add(note);
					}

					break;
				case J:
					piano.getChildren().remove(aPress);
					kontrolleri.sendInt(10);

					kontrolleri.timerStop();
					note = new Note('a', (int) kontrolleri.getTime());
					System.out.println(note.getKey() + note.getDelay());

					if (recordIsPressed == 1){
						song.add(note);
					}

					break;
				case K:
					piano.getChildren().remove(hPress);
					kontrolleri.sendInt(10);

					kontrolleri.timerStop();
					note = new Note('h', (int) kontrolleri.getTime());
					System.out.println(note.getKey() + note.getDelay());

					if (recordIsPressed == 1){
						song.add(note);
					}

					break;
				case L:
					piano.getChildren().remove(c2Press);
					kontrolleri.sendInt(10);

					kontrolleri.timerStop();
					note = new Note('v', (int) kontrolleri.getTime());
					System.out.println(note.getKey() + note.getDelay());

					if (recordIsPressed == 1){
						song.add(note);
					}

					break;
				}
			}
		});
	}

	// lisää filut items-listaan
	public void listList() {
		for (int i = 0; i < listOfFiles.length; i++) {
			items.add(listOfFiles[i].getName());
		}
	}

	// poistaa kappaleen directorysta

	public String selectedFilepath() {
		String filepath = "C:\\Users\\Jesme\\workspace\\RoboOhjain\\Songs\\"
				+ (list.getSelectionModel().getSelectedItem());
		return filepath;
	}


	public void createSong(String songname){

		try {
			FileOutputStream out = new FileOutputStream("C:\\Users\\jesme\\workspace\\RoboOhjain\\Songs\\" + songname);
			ObjectOutputStream oos = new ObjectOutputStream(out);
			oos.writeObject(song);
			oos.close();
		} catch (Exception e) {
			System.out.println("Kappaleen tallennus ep�onnistui!!");
			e.printStackTrace();
		}
		System.out.println("Tallennus onnistui!");
		song.clear();
	}


	/*
	 * public Note createNote(char c, long delay) { Note note = new Note(c,
	 * (int) delay); return note; }
	 *
	 * public ArrayList<Note> createSong(Note note) { song.add(note); return
	 * song; }
	 *
	 * public void clearSong() { song.clear(); }
	 */
	public static void main(String[] args) {

		launch(args);

	}
}