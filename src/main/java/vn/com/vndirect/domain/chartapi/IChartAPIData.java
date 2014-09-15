package vn.com.vndirect.domain.chartapi;

public interface IChartAPIData {
	/**
	 * 
	 * @return
	 */
	public long getTransTime();

	/**
	 * 
	 * @return
	 */
	public long getTransTimeInSec();

	/**
	 *
	 */
	public double getValue();
}
