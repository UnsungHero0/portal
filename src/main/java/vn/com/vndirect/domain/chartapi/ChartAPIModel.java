package vn.com.vndirect.domain.chartapi;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@SuppressWarnings("serial")
public class ChartAPIModel implements Serializable {
	public interface DateStep {
		int _1DAY_STEP = 86400;
		int _5DAY_STEP = 5 * _1DAY_STEP;
		int _15DAY_STEP = 15 * _1DAY_STEP;
		int _1MONTH_STEP = 30 * _1DAY_STEP;
		int _3MONTH_STEP = 3 * _1MONTH_STEP;
		int _1YEAR_STEP = 12 * _1MONTH_STEP;
	}

	public final static int Y_AXIS_MARGIN = 5;
	public final static int Y_AXIS_SHOW_ITEM = 5;

	private long xAxisMin;
	private long xAxisMax;
	private long xAxisSteps = DateStep._1DAY_STEP;
	private long xAxisVisibleSteps;

	private long yAxisMin;
	private long yAxisMax;
	private long yAxisSteps;

	private List<IChartAPIData> datas = new ArrayList<IChartAPIData>();

	/**
	 *
	 */
	public void setDefaultValue() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);

		xAxisMax = (cal.getTimeInMillis() / 1000);

		cal.add(Calendar.MONTH, -1);
		xAxisMin = (cal.getTimeInMillis() / 1000);

		xAxisSteps = DateStep._1DAY_STEP;
		xAxisVisibleSteps = 15;

		yAxisMin = 0;
		yAxisMax = 30;
		yAxisSteps = 5;
	}

	/**
	 * @return the xAxisMin
	 */
	public long getxAxisMin() {
		return xAxisMin;
	}

	/**
	 * @param xAxisMin
	 *            the xAxisMin to set
	 */
	public void setxAxisMin(long xAxisMin) {
		this.xAxisMin = xAxisMin;
	}

	/**
	 * @return the xAxisMax
	 */
	public long getxAxisMax() {
		return xAxisMax;
	}

	/**
	 * @param xAxisMax
	 *            the xAxisMax to set
	 */
	public void setxAxisMax(long xAxisMax) {
		this.xAxisMax = xAxisMax;
	}

	/**
	 * @return the xAxisSteps
	 */
	public long getxAxisSteps() {
		return xAxisSteps;
	}

	/**
	 * @param xAxisSteps
	 *            the xAxisSteps to set
	 */
	public void setxAxisSteps(long xAxisSteps) {
		this.xAxisSteps = xAxisSteps;
	}

	/**
	 * @return the xAxisVisibleSteps
	 */
	public long getxAxisVisibleSteps() {
		return xAxisVisibleSteps;
	}

	/**
	 * @param xAxisVisibleSteps
	 *            the xAxisVisibleSteps to set
	 */
	public void setxAxisVisibleSteps(long xAxisVisibleSteps) {
		this.xAxisVisibleSteps = xAxisVisibleSteps;
	}

	/**
	 * @return the yAxisMin
	 */
	public long getyAxisMin() {
		return yAxisMin;
	}

	/**
	 * @param yAxisMin
	 *            the yAxisMin to set
	 */
	public void setyAxisMin(long yAxisMin) {
		this.yAxisMin = yAxisMin;
	}

	/**
	 * @return the yAxisMax
	 */
	public long getyAxisMax() {
		return yAxisMax;
	}

	/**
	 * @param yAxisMax
	 *            the yAxisMax to set
	 */
	public void setyAxisMax(long yAxisMax) {
		this.yAxisMax = yAxisMax;
	}

	/**
	 * @return the yAxisSteps
	 */
	public long getyAxisSteps() {
		return yAxisSteps;
	}

	/**
	 * @param yAxisSteps
	 *            the yAxisSteps to set
	 */
	public void setyAxisSteps(long yAxisSteps) {
		this.yAxisSteps = yAxisSteps;
	}

	/**
	 * @return the datas
	 */
	public List<IChartAPIData> getDatas() {
		return datas;
	}

	/**
	 * @param datas
	 *            the datas to set
	 */
	public void setDatas(List<IChartAPIData> datas) {
		this.datas = datas;
	}

	/**
	 * 
	 * @param addedData
	 */
	public void add(IChartAPIData addedData) {
		if (addedData != null) {
			datas = (datas == null ? new ArrayList<IChartAPIData>() : datas);
			datas.add(addedData);
		}
	}

	/**
	 * 
	 * @param addedDatas
	 */
	public void addAll(List<IChartAPIData> addedDatas) {
		if (addedDatas != null && addedDatas.size() > 0) {
			datas = (datas == null ? new ArrayList<IChartAPIData>() : datas);
			datas.addAll(addedDatas);
		}
	}

	/**
	 *
	 */
	public void calcData() {
		if (datas != null && datas.size() > 0) {
			long _xAxisMin = -1;
			long _xAxisMax = -1;
			long _xAxisVisibleSteps = 0;

			double _yAxisMin = -1;
			double _yAxisMax = -1;
			long _yAxisSteps = 0;

			long _timeInSec = 0;
			double _value = 0;
			for (IChartAPIData item : datas) {
				_timeInSec = item.getTransTimeInSec();
				_xAxisMin = (_xAxisMin < 0 ? _timeInSec : (_xAxisMin > _timeInSec ? _timeInSec : _xAxisMin));
				_xAxisMax = (_xAxisMax < _timeInSec ? _timeInSec : _xAxisMax);

				_value = item.getValue();
				_yAxisMin = (_yAxisMin < 0 ? _value : (_yAxisMin > _value ? _value : _yAxisMin));
				_yAxisMax = (_yAxisMax < _value ? _value : _yAxisMax);
			}

			// +++ add margin to y axis
			_yAxisMin = (_yAxisMin - Y_AXIS_MARGIN);
			_yAxisMin = (_yAxisMin < 0 ? 0 : _yAxisMin);
			_yAxisMax = (_yAxisMax + Y_AXIS_MARGIN);

			_yAxisSteps = this.calcYAxisSteps(_yAxisMin, _yAxisMax, Y_AXIS_SHOW_ITEM);

			_xAxisVisibleSteps = calcVisibleSteps(_xAxisMin, _xAxisMax);

			// +++ reassign
			this.xAxisMin = _xAxisMin;
			this.xAxisMax = _xAxisMax;
			this.xAxisSteps = DateStep._1DAY_STEP;
			this.xAxisVisibleSteps = _xAxisVisibleSteps;

			this.yAxisMin = (long) _yAxisMin;
			this.yAxisMax = (long) _yAxisMax;
			this.yAxisSteps = _yAxisSteps;
		}
	}

	/**
	 * 
	 * @param minValue
	 * @param maxValue
	 * @return
	 */
	private long calcVisibleSteps(double minValue, double maxValue) {
		double check = Math.abs(maxValue - minValue);

		if (check > DateStep._1YEAR_STEP) {
			return (DateStep._1YEAR_STEP / DateStep._1DAY_STEP);
		} else if (check > DateStep._3MONTH_STEP && check <= DateStep._1YEAR_STEP) {
			return (DateStep._3MONTH_STEP / DateStep._1DAY_STEP);
		} else if (check > DateStep._1MONTH_STEP && check <= DateStep._3MONTH_STEP) {
			return (DateStep._1MONTH_STEP / DateStep._1DAY_STEP);
		} else if (check > DateStep._15DAY_STEP && check <= DateStep._1MONTH_STEP) {
			return (DateStep._15DAY_STEP / DateStep._1DAY_STEP);
		} else if (check > DateStep._5DAY_STEP && check <= DateStep._15DAY_STEP) {
			return (DateStep._5DAY_STEP / DateStep._1DAY_STEP);
		} else {
			return (DateStep._1DAY_STEP / DateStep._1DAY_STEP);
		}
	}

	/**
	 * 
	 * @param minValue
	 * @param maxValue
	 * @param numDisplayedItems
	 * @return
	 */
	private long calcYAxisSteps(double minValue, double maxValue, int numDisplayedItems) {
		numDisplayedItems = (numDisplayedItems < Y_AXIS_SHOW_ITEM ? Y_AXIS_SHOW_ITEM : numDisplayedItems);
		return (long) ((maxValue - minValue) / numDisplayedItems);
	}
}
