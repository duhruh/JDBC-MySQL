package ucf.mysql.jdbc;

public class Riders {
	private String mRiderName;
	private String mTeamName;
	private String mNationality;
	private int mNumProWins;
	
	public void setRiderName(String mRiderName){
		this.mRiderName = mRiderName;
	}
	public void setTeamName(String mTeamName){
		this.mTeamName = mTeamName;
	}
	public void setNationality(String mNationality){
		this.mNationality = mNationality;
	}
	public void setNumProWins(int mNumProWins){
		this.mNumProWins = mNumProWins;
	}
	public String getRiderName(){
		return this.mRiderName;
	}
	public String getTeamName(){
		return this.mTeamName;
	}
	public String getNationality(){
		return this.mNationality;
	}
	public int getNumProWins(){
		return this.mNumProWins;
	}
}
