package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ConferenceDataFactory {
	private static int trackId = 1;

	// create Track Object
	public static TrackInfo getTrack() throws ParseException {
		TrackInfo trackInfo = new TrackInfo(trackId);
		trackInfo.setTotalMorningDuration(getTimeDifferenceInMinute(
				ConferenceConstant.LUNCH_START_TIME,
				ConferenceConstant.MORNING_SESSION_START_TIME));
		trackInfo.setTotalAfterNoonDuration(getTimeDifferenceInMinute(
				ConferenceConstant.NETWORK_EVENT_START_TIME,
				ConferenceConstant.AFTERNOON_SESSION_START_TIME));
		trackInfo.setMorningTalkList(new ArrayList<TalkInfo>());
		trackInfo.setAfternoonTalkList(new ArrayList<TalkInfo>());

		trackId++;

		return trackInfo;
	}

	// calculate the time difference in minutes.
	private static int getTimeDifferenceInMinute(String str1, String str2)
			throws ParseException {
		Calendar cal1 = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();
		DateFormat dateFormat = new SimpleDateFormat(
				ConferenceConstant.SIMPLE_DATE_FORMAT);
		long diffInMilliSeconds;
		long diffInMinutes = 0;
		cal1.setTime(dateFormat.parse(str1));
		cal2.setTime(dateFormat.parse(str2));
		diffInMilliSeconds = cal1.getTime().getTime()
				- cal2.getTime().getTime();
		diffInMinutes = TimeUnit.MILLISECONDS.toMinutes(diffInMilliSeconds + 1);

		return (int) diffInMinutes;

	}

	// Read the talks information from a file.
	public static List<TalkInfo> getTalkInfo() throws Exception {
		File file = new File(ConferenceConstant.INPUT_FILE);
		String talk;
		List<TalkInfo> talkInfoList = new ArrayList<TalkInfo>();
		FileReader fileReader = null;
		BufferedReader bufferedReader = null;
		try {
			fileReader = new FileReader(file);
			bufferedReader = new BufferedReader(fileReader);
			while ((talk = bufferedReader.readLine()) != null) {
				talkInfoList.add(createTalkInfo(talk));
			}
		} finally {
			if (bufferedReader != null) {
				bufferedReader.close();
			}
		}
		Collections.sort(talkInfoList, new TalkInfoComparator());
		return talkInfoList;
	}

	// create list of Talks and sort in descending order.
	private static TalkInfo createTalkInfo(String talk) throws Exception {

		String talkDescription = new String(talk.substring(0,
				talk.lastIndexOf(" ")));
		if (parseNumberInput(talkDescription) != null) {
			throw new Exception("Invalid Input");
		}
		TalkInfo talkInfo;
		String duration = new String(talk.substring(talk.lastIndexOf(" ") + 1));
		if (duration.equalsIgnoreCase(ConferenceConstant.LIGHTNING)) {
			talkInfo = new TalkInfo(talkDescription, 5);
		} else {
			talkInfo = new TalkInfo(talkDescription,
					Integer.parseInt(parseNumberInput(duration)));
		}
		return talkInfo;
	}

	// Extract the Number from a line.
	// Serves two purpose.
	// 1) To validate whether talk description contains number.
	// 2) To extract minute part.
	private static String parseNumberInput(String description) {
		Pattern pattern = Pattern.compile("\\d+");
		Matcher match = pattern.matcher(description);
		String result = null;
		if (match.find()) {
			result = match.group();
		} else {
			result = null;
		}

		return result;
	}
}
