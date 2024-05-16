package com.nd.tepia;

import java.io.File;
import java.io.PrintStream;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Timer;
import java.util.TimerTask;

public class TepiaApplication {

	public static Long usersCount;

	public static String formattedDate;

	public static void main(String[] args) {
		try {
			System.setErr(new PrintStream(new File("log\\log.txt")));
		} catch (Exception e) {
			e.printStackTrace();
		}

		SpringLoader.main(args);

		LocalDateTime initMoment = LocalDateTime.now();

		Timer mainLoop = new Timer();
		mainLoop.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				Duration timeIsRunning = Duration.between(initMoment, LocalDateTime.now());

				Long daysCount = timeIsRunning.toDaysPart();
				Integer hoursCount = timeIsRunning.toHoursPart();
				Integer minutesCount = timeIsRunning.toMinutesPart();
				Integer secondsCount = timeIsRunning.toSecondsPart();

				if(daysCount <= 0){
					formattedDate = String.format("%sh:%sm:%ss\n",
					((hoursCount <= 9) ? "0" + hoursCount.toString() : hoursCount),
					((minutesCount <= 9) ? "0" + minutesCount.toString() : minutesCount),
					((secondsCount <= 9) ? "0" + secondsCount.toString() : secondsCount));
				}else{
					formattedDate = String.format("%s days and %sh:%sm:%ss\n",
					((daysCount <= 9)? "0" + daysCount.toString() : daysCount),
					((hoursCount <= 9) ? "0" + hoursCount.toString() : hoursCount),
					((minutesCount <= 9) ? "0" + minutesCount.toString() : minutesCount),
					((secondsCount <= 9) ? "0" + secondsCount.toString() : secondsCount));
				}
				
			}
		}, 200, 1000);
	}

}
