package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import main.ConferenceData;
import main.TalkInfo;
import main.TrackInfo;

import org.junit.Test;

public class ConferenceDataTest {
	@Test
	public void testCalculateStartTime() {
		String expectedStartTime = "9:30AM";
		String actualStartTime = ConferenceData.calculateStartTime("M", 30);
		assertEquals(expectedStartTime, actualStartTime);
	}

	// Test entire GenerateConferenceData method.
	@Test
	public void testGenerateConferenceData() throws ParseException {
		List<TalkInfo> talkInfoList = new ArrayList<TalkInfo>();

		TalkInfo talkInfo1 = new TalkInfo("A", 150);
		talkInfo1.setStartTime("9:00AM");
		talkInfoList.add(talkInfo1);

		TalkInfo talkInfo2 = new TalkInfo("B", 150);
		talkInfo2.setStartTime("1:00PM");
		talkInfoList.add(talkInfo2);

		TalkInfo talkInfo3 = new TalkInfo("C", 80);
		talkInfo3.setStartTime("3:30PM");
		talkInfoList.add(talkInfo3);

		TalkInfo talkInfo4 = new TalkInfo("D", 60);
		talkInfo4.setStartTime("9:00AM");
		talkInfoList.add(talkInfo4);

		TalkInfo talkInfo5 = new TalkInfo("E", 30);
		talkInfo5.setStartTime("9:00AM");
		talkInfoList.add(talkInfo5);

		List<TrackInfo> expectedTrackInfoList = new ArrayList<TrackInfo>();

		TrackInfo track1 = new TrackInfo(1);
		List<TalkInfo> expectedMorningTalkInfo1 = new ArrayList<TalkInfo>();
		expectedMorningTalkInfo1.add(talkInfo1);
		expectedMorningTalkInfo1.add(talkInfo5);
		track1.setMorningTalkList(expectedMorningTalkInfo1);
		List<TalkInfo> expectedAfternoonTalkInfo1 = new ArrayList<TalkInfo>();
		expectedAfternoonTalkInfo1.add(talkInfo2);
		expectedAfternoonTalkInfo1.add(talkInfo3);
		track1.setAfternoonTalkList(expectedAfternoonTalkInfo1);

		TrackInfo track2 = new TrackInfo(2);
		List<TalkInfo> expectedMorningTalkInfo2 = new ArrayList<TalkInfo>();
		expectedMorningTalkInfo2.add(talkInfo4);
		track2.setMorningTalkList(expectedMorningTalkInfo2);

		expectedTrackInfoList.add(track1);
		expectedTrackInfoList.add(track2);

		List<TrackInfo> actualTrackInfoList = ConferenceData
				.generateConferenceData(talkInfoList);

		assertNotNull("No data Found", actualTrackInfoList);
		assertFalse(actualTrackInfoList.size() <= 0);

		for (int i = 0; i < actualTrackInfoList.size(); i++) {
			for (int j = 0; j < actualTrackInfoList.get(i).getMorningTalkList()
					.size(); j++) {
				assertEquals(expectedTrackInfoList.get(i).getMorningTalkList()
						.get(j).getStartTime(), actualTrackInfoList.get(i)
						.getMorningTalkList().get(j).getStartTime());

				assertEquals(expectedTrackInfoList.get(i).getMorningTalkList()
						.get(j).getTalkDescription(), actualTrackInfoList
						.get(i).getMorningTalkList().get(j)
						.getTalkDescription());

				assertEquals(expectedTrackInfoList.get(i).getMorningTalkList()
						.get(j).getTalkDuration(), actualTrackInfoList.get(i)
						.getMorningTalkList().get(j).getTalkDuration());
			}
			for (int k = 0; k < actualTrackInfoList.get(i)
					.getAfternoonTalkList().size(); k++) {
				assertEquals(expectedTrackInfoList.get(i).getMorningTalkList()
						.get(k).getStartTime(), actualTrackInfoList.get(i)
						.getMorningTalkList().get(k).getStartTime());

				assertEquals(expectedTrackInfoList.get(i).getMorningTalkList()
						.get(k).getTalkDescription(), actualTrackInfoList
						.get(i).getMorningTalkList().get(k)
						.getTalkDescription());

				assertEquals(expectedTrackInfoList.get(i).getMorningTalkList()
						.get(k).getTalkDuration(), actualTrackInfoList.get(i)
						.getMorningTalkList().get(k).getTalkDuration());
			}
		}
	}
}
