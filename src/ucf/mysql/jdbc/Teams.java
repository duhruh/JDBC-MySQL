package ucf.mysql.jdbc;

public class Teams {
	private String mTeamName;
	private String mBikeName;
	private String mRegisteredNation;
	private String mManager;
	private int mNumRiders;
	
	public void setTeamName(String mTeamName){
		this.mTeamName = mTeamName;
	}
	public void setBikeName(String mBikeName){
		this.mBikeName = mBikeName;
	}
	public void setRegisteredNaiton(String mRegisteredNation){
		this.mRegisteredNation = mRegisteredNation;
	}
	public void setManager(String mManager){
		this.mManager = mManager;
	}
	public void setNumRiders(int mNumRiders){
		this.mNumRiders = mNumRiders;
	}
	public String getTeamName(){
		return this.mTeamName;
	}
	public String getBikeName(){
		return this.mBikeName;
	}
	public String getRegisteredNation(){
		return this.mRegisteredNation;
	}
	public String getManager(){
		return this.mManager;
	}
	public int getNumRiders(){
		return this.mNumRiders;
	}
}
