/**
 * 
 */
package ucf.mysql.jdbc;

/**
 * @author david
 *
 */
public class Bikes {
	private String mBikeName;
	private String mCountryOfOrigin;
	private int mCost;
	
	public void setBikeName(String mBikeName){
		this.mBikeName = mBikeName;
	}
	public void setCountryOfOrigin(String mCountryOfOrigin){
		this.mCountryOfOrigin = mCountryOfOrigin;
	}
	public void setCost(int mCost){
		this.mCost = mCost;
	}
	public String getBikeName(){
		return this.mBikeName;
	}
	public String getCountryOfOrigin(){
		return this.mCountryOfOrigin;
	}
	public int getCost(){
		return this.mCost;
	}
}
