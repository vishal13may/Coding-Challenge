package main;

import java.util.List;

public class TrackInfo {
	private int trackId;
	private String trackDescription;
	private int totalMorningDuration;
	private int totalAfterNoonDuration;
	private int utilizedMorningDuration;
	private int utilizedAfterNoonDuration;
	private List<TalkInfo> morningTalkList;
	private List<TalkInfo> afternoonTalkList;
	
	public List<TalkInfo> getMorningTalkList() {
		return morningTalkList;
	}


	public void setMorningTalkList(List<TalkInfo> morningTalkList) {
		this.morningTalkList = morningTalkList;
	}


	public List<TalkInfo> getAfternoonTalkList() {
		return afternoonTalkList;
	}


	public void setAfternoonTalkList(List<TalkInfo> afternoonTalkList) {
		this.afternoonTalkList = afternoonTalkList;
	}


	public TrackInfo(int trackId ){
		this.trackId = trackId;
	}


	public int getTrackId() {
		return trackId;
	}


	public void setTrackId(int trackId) {
		this.trackId = trackId;
	}


	public String getTrackDescription() {
		return trackDescription;
	}


	public void setTrackDescription(String trackDescription) {
		this.trackDescription = trackDescription;
	}


	public int getTotalMorningDuration() {
		return totalMorningDuration;
	}


	public void setTotalMorningDuration(int totalMorningDuration) {
		this.totalMorningDuration = totalMorningDuration;
	}


	public int getTotalAfterNoonDuration() {
		return totalAfterNoonDuration;
	}


	public void setTotalAfterNoonDuration(int totalAfterNoonDuration) {
		this.totalAfterNoonDuration = totalAfterNoonDuration;
	}


	public int getUtilizedMorningDuration() {
		return utilizedMorningDuration;
	}


	public void setUtilizedMorningDuration(int utilizedMorningDuration) {
		this.utilizedMorningDuration = utilizedMorningDuration;
	}


	public int getUtilizedAfterNoonDuration() {
		return utilizedAfterNoonDuration;
	}


	public void setUtilizedAfterNoonDuration(int utilizedAfterNoonDuration) {
		this.utilizedAfterNoonDuration = utilizedAfterNoonDuration;
	}
	
	
	
	
	
}
