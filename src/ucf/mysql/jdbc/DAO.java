package ucf.mysql.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;

import com.mysql.jdbc.MySQLConnection;
import com.mysql.jdbc.PreparedStatement;

public class DAO {
	private final String[] TABLES = {"riders","bikes","racewinners","teams"};
	private MySQLConnection mConnection;
	
	public DAO(MySQLConnection Connection){
		this.mConnection = Connection;
	}
//	public ArrayList<Object> runSelectN(String Query){
//		
//	}
//	public ArrayList<Object> runSelectAll(String Query){
//		ArrayList<Object> mObject = new ArrayList<Object>();
//		String[] mTable = Query.split("* from ", 1);
//		mTable = mTable[1].split(" ", 1);
//		
//		if(mTable[0] == "riders"){
//			return SelectRiders(new String[]{"ALL"},Query);
//		}else if(mTable[0] == "bikes"){
//			Bikes mBikes = new Bikes();
//		}else if(mTable[0] == "racewinners"){
//			RaceWinners mRaceWinners = new RaceWinners();
//		}else if(mTable[0] == "teams"){
//			Teams mTeams = new Teams();
//		}
//	}
	public ArrayList<Riders> getRiders(String[] mCollumns,String mQuery) throws SQLException{
		PreparedStatement mStatement = (PreparedStatement) this.mConnection.createStatement();
		ResultSet mResults = mStatement.executeQuery(mQuery);
		ArrayList<Riders> mRidersList = new ArrayList<Riders>();
		while(mResults.next()){
			Riders mRider = new Riders();
			if(mCollumns[0] == "ALL" || Arrays.asList(mCollumns).contains("nationality"))
				mRider.setNationality(mResults.getString("nationality"));
			if(mCollumns[0] == "ALL" || Arrays.asList(mCollumns).contains("num_pro_wins"))
				mRider.setNumProWins(mResults.getInt("num_pro_wins"));
			if(mCollumns[0] == "ALL" || Arrays.asList(mCollumns).contains("ridername"))
				mRider.setRiderName(mResults.getString("ridername"));
			if(mCollumns[0] == "ALL" || Arrays.asList(mCollumns).contains("teamname"))
				mRider.setTeamName(mResults.getString("teamname"));
			mRidersList.add(mRider);
		}
		return mRidersList;
	}
	public ArrayList<Teams> getTeams(String[] mCollumns,String mQuery) throws SQLException{
		PreparedStatement mStatement = (PreparedStatement) this.mConnection.createStatement();
		ResultSet mResults = mStatement.executeQuery(mQuery);
		ArrayList<Teams> mTeamsList = new ArrayList<Teams>();
		while(mResults.next()){
			Teams mTeams = new Teams();
			if(mCollumns[0] == "ALL" || Arrays.asList(mCollumns).contains("teamname"))
				mTeams.setTeamName(mResults.getString("teamname"));
			if(mCollumns[0] == "ALL" || Arrays.asList(mCollumns).contains("bikename"))
				mTeams.setBikeName(mResults.getString("bikename"));
			if(mCollumns[0] == "ALL" || Arrays.asList(mCollumns).contains("manager"))
				mTeams.setManager(mResults.getString("manager"));
			if(mCollumns[0] == "ALL" || Arrays.asList(mCollumns).contains("num_riders"))
				mTeams.setNumRiders(mResults.getInt("num_riders"));
			if(mCollumns[0] == "ALL" || Arrays.asList(mCollumns).contains("registered_nation"))
				mTeams.setRegisteredNaiton(mResults.getString("registered_nation"));
			mTeamsList.add(mTeams);
		}
		return mTeamsList;
	}
	public ArrayList<RaceWinners> getRaceWinners(String[] Collumns, String Query) throws SQLException{
		PreparedStatement mStatement = (PreparedStatement) this.mConnection.createStatement();
		ResultSet mResults = mStatement.executeQuery(Query);
		ArrayList<RaceWinners> mRaceWinnersList = new ArrayList<RaceWinners>();
		while(mResults.next()){
			RaceWinners mRaceWinners = new RaceWinners();
			if(Collumns[0] == "ALL" || Arrays.asList(Collumns).contains("racename"))
				mRaceWinners.setRaceName(mResults.getString("racename"));
			if(Collumns[0] == "ALL" || Arrays.asList(Collumns).contains("ridername"))
				mRaceWinners.setRiderName(mResults.getString("ridername"));
			if(Collumns[0] == "ALL" || Arrays.asList(Collumns).contains("distance"))
				mRaceWinners.setDistance(mResults.getInt("distance"));
			if(Collumns[0] == "ALL" || Arrays.asList(Collumns).contains("raceyear"))
				mRaceWinners.setRaceYear(mResults.getInt("raceyear"));
			if(Collumns[0] == "ALL" || Arrays.asList(Collumns).contains("winning_time"))
				mRaceWinners.setWinningTime(mResults.getTime("winning_time"));
			mRaceWinnersList.add(mRaceWinners);
		}
		return mRaceWinnersList;
		
	}
	public ArrayList<Bikes> getBikes(String[] Collumns, String Query) throws SQLException{
		PreparedStatement mStatement = (PreparedStatement) this.mConnection.createStatement();
		ResultSet mResults = mStatement.executeQuery(Query);
		ArrayList<Bikes> mBikeList = new ArrayList<Bikes>();
		while(mResults.next()){
			Bikes mBike = new Bikes();
			if(Collumns[0] == "ALL" || Arrays.asList(Collumns).contains("bikename"))
				mBike.setBikeName(mResults.getString("bikename"));
			if(Collumns[0] == "ALL" || Arrays.asList(Collumns).contains("cost"))
				mBike.setCost(mResults.getInt("cost"));
			if(Collumns[0] == "ALL" || Arrays.asList(Collumns).contains("country_of_origins"))
				mBike.setCountryOfOrigin(mResults.getString("country_of_origin"));
			mBikeList.add(mBike);
		}
		return mBikeList;
	}
}
