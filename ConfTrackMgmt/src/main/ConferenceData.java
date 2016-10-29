package main;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

//This class contains static method to generate 
//conference data.i.e Scheduled list of tasks
//and other private static methods to help schedule the talks.
public class ConferenceData {

	// This method try to fit the talks in first available track.
	// If there is no any track then add that talk in new track.
	// All talks are sorted in descending order of their duration in minute.
	public static List<TrackInfo> generateConferenceData(
			List<TalkInfo> talkInfoList) throws ParseException {
		List<TrackInfo> trackInfoList = new ArrayList<TrackInfo>();
		TrackInfo trackInfoObj = ConferenceDataFactory.getTrack();
		trackInfoList.add(trackInfoObj);
		boolean isNewTrack = true;

		for (TalkInfo talkInfo : talkInfoList) {
			for (TrackInfo trackInfo : trackInfoList) {
				isNewTrack = true;
				// check for morning Session
				if (talkInfo.getTalkDuration() <= (trackInfo
						.getTotalMorningDuration() - trackInfo
						.getUtilizedMorningDuration())) {
					talkInfo.setStartTime(ConferenceData.calculateStartTime(
							ConferenceConstant.MORNING_SESSION,
							trackInfo.getUtilizedMorningDuration()));
					trackInfo.getMorningTalkList().add(talkInfo);
					int time = trackInfo.getUtilizedMorningDuration()
							+ talkInfo.getTalkDuration();
					trackInfo.setUtilizedMorningDuration(time);
					isNewTrack = false;
					break;
				}
				// check for afternoon session
				else if (talkInfo.getTalkDuration() <= (trackInfo
						.getTotalAfterNoonDuration() - trackInfo
						.getUtilizedAfterNoonDuration())) {
					talkInfo.setStartTime(ConferenceData.calculateStartTime(
							ConferenceConstant.AFTERNOON_SESSION,
							trackInfo.getUtilizedAfterNoonDuration()));
					trackInfo.getAfternoonTalkList().add(talkInfo);
					int time = trackInfo.getUtilizedAfterNoonDuration()
							+ talkInfo.getTalkDuration();
					trackInfo.setUtilizedAfterNoonDuration(time);
					isNewTrack = false;
					break;
				}
			}
			// Get New Track and add talk in morning session.
			if (isNewTrack) {
				trackInfoObj = ConferenceDataFactory.getTrack();
				talkInfo.setStartTime(ConferenceData.calculateStartTime(
						ConferenceConstant.MORNING_SESSION,
						trackInfoObj.getUtilizedMorningDuration()));

				trackInfoObj.getMorningTalkList().add(talkInfo);

				int time = trackInfoObj.getUtilizedMorningDuration()
						+ talkInfo.getTalkDuration();
				trackInfoObj.setUtilizedMorningDuration(time);
				trackInfoList.add(trackInfoObj);
				isNewTrack = false;
			}
		}

		return trackInfoList;
	}

	// calculate start time for talk.
	private static String calculateStartTime(String type, int duration) throws ParseException {
		DateFormat dateFormat = new SimpleDateFormat(
				ConferenceConstant.SIMPLE_DATE_FORMAT);
		Calendar afterNoonSeesion = Calendar.getInstance();
		Calendar morningSeesion = Calendar.getInstance();
		String startTime = null;

		morningSeesion.setTime(dateFormat
				.parse(ConferenceConstant.MORNING_SESSION_START_TIME));
		afterNoonSeesion.setTime(dateFormat
				.parse(ConferenceConstant.AFTERNOON_SESSION_START_TIME));
		if (type.equals(ConferenceConstant.MORNING_SESSION)) {
			morningSeesion.set(Calendar.MINUTE, duration);
			startTime = dateFormat.format(morningSeesion.getTime());
		} else {
			afterNoonSeesion.set(Calendar.MINUTE, duration);
			startTime = dateFormat.format(afterNoonSeesion.getTime());
		}
		return startTime;

	}

	// Entry point.
	public static void main(String[] args) {

		List<TalkInfo> talkInfoList;
		try {
			talkInfoList = ConferenceDataFactory.getTalkInfo();
			List<TrackInfo> trackInfoList = generateConferenceData(talkInfoList);
			for (TrackInfo trackInfo : trackInfoList) {
				System.out.println("Track :" + trackInfo.getTrackId());
				for (TalkInfo morningTalk : trackInfo.getMorningTalkList()) {
					System.out.println(morningTalk);
				}
				for (TalkInfo afternoonTalk : trackInfo.getAfternoonTalkList()) {
					System.out.println(afternoonTalk);
				}
			}
		} catch (ParseException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}
}
