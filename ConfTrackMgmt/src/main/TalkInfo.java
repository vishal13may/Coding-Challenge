package main;
public class TalkInfo {
	private String talkDescription;
	private int talkDuration;
	private String startTime;

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public TalkInfo(String talkDescription, int talkDuration) {
		this.talkDescription = talkDescription;
		this.talkDuration = talkDuration;
	}

	public String getTalkDescription() {
		return talkDescription;
	}

	public void setTalkDescription(String talkDescription) {
		this.talkDescription = talkDescription;
	}

	public int getTalkDuration() {
		return talkDuration;
	}

	public void setTalkDuration(int talkDuration) {
		this.talkDuration = talkDuration;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(startTime);
		builder.append(" " + talkDescription);
		builder.append(" " + talkDuration);

		return builder.toString();
	}

}
