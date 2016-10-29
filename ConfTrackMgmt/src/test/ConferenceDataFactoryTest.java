package test;

import static org.junit.Assert.*;

import java.text.ParseException;

import main.ConferenceDataFactory;
import main.TalkInfo;
import main.TrackInfo;

import org.junit.Test;

public class ConferenceDataFactoryTest {

	@Test
	public void testGetTrack() throws ParseException {

		TrackInfo expectedTrackInfo = new TrackInfo(1);
		expectedTrackInfo.setTotalAfterNoonDuration(240);
		expectedTrackInfo.setTotalMorningDuration(180);

		TrackInfo actualTrackInfo = ConferenceDataFactory.getTrack();
		assertNotNull("Unable to create new Track", actualTrackInfo);

		assertEquals(expectedTrackInfo.getTrackId(),
				actualTrackInfo.getTrackId());
		assertEquals(expectedTrackInfo.getTotalMorningDuration(),
				actualTrackInfo.getTotalMorningDuration());
		assertEquals(expectedTrackInfo.getTotalAfterNoonDuration(),
				actualTrackInfo.getTotalAfterNoonDuration());
		assertEquals(expectedTrackInfo.getUtilizedMorningDuration(),
				actualTrackInfo.getUtilizedMorningDuration());
		assertEquals(expectedTrackInfo.getUtilizedAfterNoonDuration(),
				actualTrackInfo.getUtilizedAfterNoonDuration());
	}

	@Test
	public void testGetTimeDifferenceInMinute() throws ParseException {
		String str1 = "12:00PM";
		String str2 = "9:00AM";
		int result = ConferenceDataFactory
				.getTimeDifferenceInMinute(str1, str2);
		assertEquals(180, result);
	}

	@Test
	public void testParseNumberInput() {
		String str = "Abcd efg 40Min";
		// String str1="Abd40 efg 20min";
		String result = ConferenceDataFactory.parseNumberInput(str);
		assertEquals("40", result);
	}


	@Test
	public void testCreateTalkInfo() throws Exception {
		String str = "Writing Fast Tests Against Enterprise Rails 60min";
		TalkInfo expectedTalkInfo = new TalkInfo(
				"Writing Fast Tests Against Enterprise Rails", 60);
		TalkInfo actualTalkInfo = ConferenceDataFactory.createTalkInfo(str);

		// String str1="Writing Fast Tests Against Enterprise Rails lightning";
		// TalkInfo expectedTalkInfo = new
		// TalkInfo("Writing Fast Tests Against Enterprise Rails",5);
		// TalkInfo actualTalkInfo= ConferenceDataFactory.createTalkInfo(str1);

		/*
		 * String str2="Writing 40Fast Tests Against Enterprise Rails 60min";
		 * //Invalid Input TalkInfo actualTalkInfo=
		 * ConferenceDataFactory.createTalkInfo(str2); TalkInfo
		 * expectedTalkInfo=null;
		 */

		assertEquals(expectedTalkInfo.getTalkDescription(),
				actualTalkInfo.getTalkDescription());
		assertEquals(expectedTalkInfo.getTalkDuration(),
				actualTalkInfo.getTalkDuration());

	}

}
