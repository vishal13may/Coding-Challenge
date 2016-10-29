package main;
import java.util.Comparator;


public class TalkInfoComparator implements Comparator<TalkInfo> {

	@Override
	public int compare(TalkInfo talkInfo1, TalkInfo talkInfo2) {
		if(talkInfo1.getTalkDuration() == talkInfo2.getTalkDuration()){
			return 0;
		}
		else if(talkInfo1.getTalkDuration()< talkInfo2.getTalkDuration()){
			return 1;
		}
		else{
			return -1;
		}
	}

	

}
