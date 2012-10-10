package ucf.mysql.jdbc;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;

import com.mysql.jdbc.MySQLConnection;
import com.mysql.jdbc.PreparedStatement;

public class DAO {
	private final String[] TABLES = {"riders","bikes","racewinners","teams"};
	private final String[] KEYWORDS ={
			"ADD","ALL","ALTER","ANALYZE","AND","AS","ASC","ASENSITIVE","BEFORE"
			,"BETWEEN","BIGINT","BINARY","BLOB","BOTH","BY","CALL","CASCADE","CASE"
			,"CHANGE","CHAR","CHARACTER","CHECK","COLLATE","COLUMN","CONDITION","CONSTRAINT"
			,"CONTINUE","CONVERT","CREATE","CROSS","CURRENT_DATE","CURRENT_TIME","CURRENT_TIMESTAMP"
			,"CURRENT_USER","CURSOR","DATABASE","DATABASES","DAY_HOUR","DAY_MICROSECOND","DAY_MINUTE"
			,"DAY_SECOND","DEC","DECIMAL","DECLARE","DEFAULT","DELAYED","DELETE","DESC","DESCRIBE"
			,"DETERMINISTIC","DISTINCT","DISTINCTROW","DIV","DOUBLE","DROP","DUAL","EACH","ELSE","ELSEIF"
			,"ENCLOSED","ESCAPED","EXISTS","EXIT","EXPLAIN","FALSE","FETCH","FLOAT","FLOAT4","FLOAT8"
			,"FOR","FORCE","FOREIGN","FROM","FULLTEXT","GRANT","GROUP","HAVING","HIGH_PRIORITY"
			,"HOUR_MICROSECOND","HOUR_MINUTE","HOUR_SECOND","IF","IGNORE","IN","INDEX","INFILE","INNER"
			,"INOUT","INSENSITIVE","INSERT","INT","INT1","INT2","INT3","INT4","INT8","INTEGER","INTERVAL"
			,"INTO","IS","ITERATE","JOIN","KEY","KEYS","KILL","LEADING","LEAVE","LEFT","LIKE","LIMIT"
			,"LINES","LOAD","LOCALTIME","LOCALTIMESTAMP","LOCK","LONG","LONGBLOB","LONGTEXT","LOOP"
			,"LOW_PRIORITY","MATCH","MEDIUMBLOB","MEDIUMINT","MEDIUMTEXT","MIDDLEINT","MINUTE_MICROSECOND"
			,"MINUTE_SECOND","MOD","MODIFIES","NATURAL","NOT","NO_WRITE_TO_BINLOG","NULL","NUMERIC"
			,"ON","OPTIMIZE","OPTION","OPTIONALLY","OR","ORDER","OUT","OUTER","OUTFILE","PRECISION"
			,"PRIMARY","PROCEDURE","PURGE","READ","READS","REAL","REFERENCES","REGEXP","RELEASE","RENAME"
			,"REPEAT","REPLACE","REQUIRE","RESTRICT","RETURN","REVOKE","RIGHT","RLIKE","SCHEMA","SCHEMAS"
			,"SECOND_MICROSECOND","SELECT","SENSITIVE","SEPARATOR","SET","SHOW","SMALLINT","SONAME"
			,"SPATIAL","SPECIFIC","SQL","SQLEXCEPTION","SQLSTATE","SQLWARNING","SQL_BIG_RESULT"
			,"SQL_CALC_FOUND_ROWS","SQL_SMALL_RESULT","SSL","STARTING","STRAIGHT_JOIN","TABLE"
			,"TERMINATED","THEN","TINYBLOB","TINYINT","TINYTEXT","TO","TRAILING","TRIGGER","TRUE"
			,"UNDO","UNION","UNIQUE","UNLOCK","UNSIGNED","UPDATE","USAGE","USE","USING","UTC_DATE"
			,"UTC_TIME","UTC_TIMESTAMP","VALUES","VARBINARY","VARCHAR","VARCHARACTER","VARYING","WHEN"
			,"WHERE","WHILE","WITH","WRITE","XOR","YEAR_MONTH","ZEROFILL"
	};
	private MySQLConnection mConnection;
	private ResultSetMetaData mMetaData;
	private Vector<String> mColumns;
	
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
	public Vector<Vector<String>> runQuery(String mQuery) throws SQLException{
		Vector<Vector<String>> mResults = new Vector<Vector<String>>();
		Statement mStatement = (Statement) this.mConnection.createStatement();
		ResultSet mResultSet = mStatement.executeQuery(mQuery);
		
		mMetaData = mResultSet.getMetaData();
		int mNumColumns = mMetaData.getColumnCount();
		mColumns = new Vector<String>();
		for(int i = 1; i <= mNumColumns; i++){
			mColumns.add(mMetaData.getColumnName(i));
		}
		
		while(mResultSet.next()){
			Vector<String> mRow = new Vector<String>();
			for(int i = 1; i <= mNumColumns; i++){
				mRow.add(mResultSet.getString(i));
			}
			mResults.add(mRow);
		}
		return mResults;
	}
	public int runUpdate(String mQuery) throws SQLException{
		Statement mStatement = this.mConnection.createStatement();
		return mStatement.executeUpdate(mQuery);
	}
	public Vector<String> getColumns() throws SQLException{
		return this.mColumns;
	}
	public Vector<Riders> getRider(Vector<String> mCollumns,String mQuery) throws SQLException{
		Statement mStatement = (Statement) this.mConnection.createStatement();
		ResultSet mResults = mStatement.executeQuery(mQuery);
		Vector<Riders> mRidersList = new Vector<Riders>();
		while(mResults.next()){
			Riders mRider = new Riders();
			if(mCollumns.get(0) == "ALL" || mCollumns.contains("nationality"))
				mRider.setNationality(mResults.getString("nationality"));
			if(mCollumns.get(0) == "ALL" || mCollumns.contains("num_pro_wins"))
				mRider.setNumProWins(mResults.getInt("num_pro_wins"));
			if(mCollumns.get(0) == "ALL" || mCollumns.contains("ridername"))
				mRider.setRiderName(mResults.getString("ridername"));
			if(mCollumns.get(0) == "ALL" || mCollumns.contains("teamname"))
				mRider.setTeamName(mResults.getString("teamname"));
			mRidersList.add(mRider);
		}
		return mRidersList;
	}
	public ArrayList<Teams> getTeams(ArrayList<String> mCollumns,String mQuery) throws SQLException{
		PreparedStatement mStatement = (PreparedStatement) this.mConnection.createStatement();
		ResultSet mResults = mStatement.executeQuery(mQuery);
		ArrayList<Teams> mTeamsList = new ArrayList<Teams>();
		while(mResults.next()){
			Teams mTeams = new Teams();
			if(mCollumns.get(0) == "ALL" || mCollumns.contains("teamname"))
				mTeams.setTeamName(mResults.getString("teamname"));
			if(mCollumns.get(0) == "ALL" || mCollumns.contains("bikename"))
				mTeams.setBikeName(mResults.getString("bikename"));
			if(mCollumns.get(0) == "ALL" || mCollumns.contains("manager"))
				mTeams.setManager(mResults.getString("manager"));
			if(mCollumns.get(0) == "ALL" || mCollumns.contains("num_riders"))
				mTeams.setNumRiders(mResults.getInt("num_riders"));
			if(mCollumns.get(0) == "ALL" || mCollumns.contains("registered_nation"))
				mTeams.setRegisteredNaiton(mResults.getString("registered_nation"));
			mTeamsList.add(mTeams);
		}
		return mTeamsList;
	}
	public ArrayList<RaceWinners> getRaceWinners(ArrayList<String> Collumns, String Query) throws SQLException{
		PreparedStatement mStatement = (PreparedStatement) this.mConnection.createStatement();
		ResultSet mResults = mStatement.executeQuery(Query);
		ArrayList<RaceWinners> mRaceWinnersList = new ArrayList<RaceWinners>();
		while(mResults.next()){
			RaceWinners mRaceWinners = new RaceWinners();
			if(Collumns.get(0) == "ALL" || Collumns.contains("racename"))
				mRaceWinners.setRaceName(mResults.getString("racename"));
			if(Collumns.get(0) == "ALL" || Collumns.contains("ridername"))
				mRaceWinners.setRiderName(mResults.getString("ridername"));
			if(Collumns.get(0) == "ALL" || Collumns.contains("distance"))
				mRaceWinners.setDistance(mResults.getInt("distance"));
			if(Collumns.get(0) == "ALL" || Collumns.contains("raceyear"))
				mRaceWinners.setRaceYear(mResults.getInt("raceyear"));
			if(Collumns.get(0) == "ALL" || Collumns.contains("winning_time"))
				mRaceWinners.setWinningTime(mResults.getTime("winning_time"));
			mRaceWinnersList.add(mRaceWinners);
		}
		return mRaceWinnersList;
		
	}
	public ArrayList<Bikes> getBikes(ArrayList<String> Collumns, String Query) throws SQLException{
		PreparedStatement mStatement = (PreparedStatement) this.mConnection.createStatement();
		ResultSet mResults = mStatement.executeQuery(Query);
		ArrayList<Bikes> mBikeList = new ArrayList<Bikes>();
		while(mResults.next()){
			Bikes mBike = new Bikes();
			if(Collumns.get(0) == "ALL" || Collumns.contains("bikename"))
				mBike.setBikeName(mResults.getString("bikename"));
			if(Collumns.get(0) == "ALL" || Collumns.contains("cost"))
				mBike.setCost(mResults.getInt("cost"));
			if(Collumns.get(0) == "ALL" || Collumns.contains("country_of_origins"))
				mBike.setCountryOfOrigin(mResults.getString("country_of_origin"));
			mBikeList.add(mBike);
		}
		return mBikeList;
	}
	public boolean isKeyword(String mKeyword){
		return Arrays.asList(KEYWORDS).contains(mKeyword.toUpperCase());
	}
	public boolean isTable(String mTable){
		return Arrays.asList(TABLES).contains(mTable);
	}
}
