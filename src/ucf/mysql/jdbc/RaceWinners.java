package ucf.mysql.jdbc;

import java.sql.Time;

public class RaceWinners {
	private String mRaceName;
	private String mRiderName;
	private int mRaceYear;
	private int mDistance;
	private Time mWinningTime;
	
	public void setRaceName(String mRaceName){
		this.mRaceName = mRaceName;
	}
	public void setRiderName(String mRiderName){
		this.mRiderName = mRiderName;
	}
	public void setRaceYear(int mRaceYear){
		this.mRaceYear = mRaceYear;
	}
	public void setDistance(int mDistance){
		this.mDistance = mDistance;
	}
	public void setWinningTime(Time mWinningTime){
		this.mWinningTime = mWinningTime;
	}
	public String getRaceName(){
		return this.mRaceName;
	}
	public String getRiderName(){
		return this.mRiderName;
	}
	public int getRaceYear(){
		return this.mRaceYear;
	}
	public int getDistance(){
		return this.mDistance;
	}
	public Time getWinningTime(){
		return this.mWinningTime;
	}
}
