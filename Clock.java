import javafx.application.Application;
import javafx.stage.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.Scene;
import java.util.Calendar; 
import java.util.GregorianCalendar;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.text.Text;
import javafx.scene.layout.*;
import javafx.animation.*;
import javafx.event.*;
import javafx.util.Duration;
import javafx.geometry.Pos;

public class Clock extends Application 
{	
	Stage timeStage = new Stage();
	//Pane timePane = new Pane();
	ClockPane clock = new ClockPane();
	Scene timeScene = new Scene(clock, 300, 300);
	
	Stage countdownStage = new Stage();
	Pane countdownPane = new Pane();
	Scene countdownScene = new Scene(countdownPane, 300, 300);
	
	Stage alarmClockStage = new Stage();
	Pane alarmClockPane = new Pane();
	Scene alarmClockScene = new Scene(alarmClockPane, 300, 300);
	
	Label labShowCT = new Label("");
	TextField txtCountDown = new TextField("");
	Button butStartCT = new Button("Strat");
	Button butTU = new Button("Time's up");
	
	Timeline animation;

	// Override the start method in the Application class
	@Override
	public void start(Stage primaryStage)
	{
		Button butTime = new Button("Time");
		Button butCountdown = new Button("Countdown");
		Button butAlarmClock = new Button("Alarm clock");
		
		Pane clockPane = new Pane();
		
		butTime.setPrefSize(100,40);
		butTime.setLayoutX(100);
		butTime.setLayoutY(50);
		butCountdown.setPrefSize(100,40);
		butCountdown.setLayoutX(100);
		butCountdown.setLayoutY(140);
		butAlarmClock.setPrefSize(100,40);
		butAlarmClock.setLayoutX(100);
		butAlarmClock.setLayoutY(230);
		clockPane.getChildren().addAll(butTime, butCountdown, butAlarmClock);
		
		butTime.setOnMouseClicked(e -> {
			showTimeStage();
		});
		butCountdown.setOnMouseClicked(e -> {
			showCountdownStage();
		});
		butAlarmClock.setOnMouseClicked(e -> {
			showAlarmClockStage();
		});
		butStartCT.setOnMouseClicked(e -> {
			startCountdown();
		});
		butTU.setOnMouseClicked(e -> {
			butTU.setVisible(false);
			labShowCT.setVisible(false);
		});

		Scene scene = new Scene(clockPane, 300, 300);
		primaryStage.setTitle("Clock");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	void showTimeStage()
	{		
		GregorianCalendar calendar = new GregorianCalendar();
				
		EventHandler<ActionEvent> eventHandler = e -> {
			clock.setCurrentTime();
		};
		
		Timeline animation = new Timeline(
			new KeyFrame(Duration.millis(1000), eventHandler));
		animation.setCycleCount(Timeline.INDEFINITE);
		animation.play();
		timeStage.setTitle("Time");
		timeStage.setScene(timeScene);
		timeStage.show();		
	}
	
	void showCountdownStage()
	{
		txtCountDown.setAlignment(Pos.BASELINE_RIGHT);
		txtCountDown.setLayoutX(80);
		txtCountDown.setLayoutY(50);
		butStartCT.setPrefSize(80,40);
		butStartCT.setLayoutX(110);
		butStartCT.setLayoutY(125);
		labShowCT.setLayoutX(145);
		labShowCT.setLayoutY(200);
		labShowCT.setVisible(false);
		butTU.setPrefSize(300,300);
		butTU.setVisible(false);
		countdownPane.getChildren().addAll(txtCountDown, labShowCT, butStartCT, butTU);
		countdownStage.setTitle("Countdown");
		countdownStage.setScene(countdownScene);
		countdownStage.show();
	}
	
	void startCountdown()
	{
		labShowCT.setText(txtCountDown.getText());
		labShowCT.setVisible(true);
		if (animation == null)
		{
			animation = new Timeline(
				new KeyFrame(Duration.millis(1000), e ->
				{
					if (butTU.isVisible())
						return;
					if (Integer.valueOf(labShowCT.getText()) > 0)
						labShowCT.setText(String.valueOf(Integer.valueOf(labShowCT.getText()) - 1));
					else if (Integer.valueOf(labShowCT.getText()) == 0)
					{
						butTU.setVisible(true);
						labShowCT.setText("-1");
					}
				}));
			animation.setCycleCount(Timeline.INDEFINITE);
			animation.play();
		}
	}
	
	void showAlarmClockStage()
	{
		alarmClockStage.setTitle("AlarmClock");
		alarmClockStage.setScene(alarmClockScene);
		alarmClockStage.show();
	}

}
