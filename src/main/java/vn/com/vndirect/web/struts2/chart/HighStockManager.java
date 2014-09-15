package vn.com.vndirect.web.struts2.chart;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import vn.com.vndirect.commons.flashchart.beans.FlashChartBean;
import vn.com.vndirect.commons.flashchart.beans.FlashPriceBean;
import vn.com.vndirect.commons.jfreechart.TAConstants;
import vn.com.web.commons.exception.FunctionalException;
import vn.com.web.commons.exception.SystemException;

/**
 * @author minh.nguyen
 * 
 */
@Component
public class HighStockManager implements IHighStockManager {
	private static Logger logger = Logger.getLogger(HighStockManager.class);

	private final String STR_DATE_TIME_FORMAT_DDMMYYYY = "dd/MM/yyyy";
	private final Long A_DAY_IN_MILISECOND = 86400000L;
	private final DateFormat formatter = new SimpleDateFormat(STR_DATE_TIME_FORMAT_DDMMYYYY);

	public HighStock generateHistoricalPrice(FlashChartBean flashChartBean) throws FunctionalException, SystemException {
		final String location = "generateJsonHistoricalPrice(flashChartBean:" + flashChartBean + ")";
		if (logger.isDebugEnabled())
			logger.debug(location + ":: BEGIN");

		if (flashChartBean == null) {
			throw new FunctionalException(location, "flashChartBean object is NULL or EMPTY ...");
		}

		final HighStock highStock = new HighStock();
		try {
			final JSONArray jsonArray = new JSONArray();
			if (flashChartBean.getPricesMap() != null) {
				final ArrayList<FlashPriceBean> values = (ArrayList<FlashPriceBean>) flashChartBean.getPricesMap().get(
						TAConstants.FlashChartParams.FlashChartParamValues.GET_HIST_PRICE);
				if (values != null && values.size() > 0) {
					// check 2 lastest data, if same day so remove one
					// record(the
					// lastest is union from sec_info)
					FlashPriceBean priceBean1 = values.get(values.size() - 1);
					FlashPriceBean priceBean2 = values.get(values.size() - 2);
					int sub = 0;
					if (priceBean1.getTransDate().getTime() == priceBean2.getTransDate().getTime()) {
						sub = 1;
					}
					JSONObject jsonObject;
					FlashPriceBean priceBean;
					Date date;
					for (int i = 0; i < values.size() - sub; i++) {
						priceBean = values.get(i);
						jsonObject = new JSONObject();
						date = formatter.parse(DateFormatUtils.format(priceBean.getTransDate(), STR_DATE_TIME_FORMAT_DDMMYYYY));
						jsonObject.put("transDate", date.getTime() + A_DAY_IN_MILISECOND);
						jsonObject.put("close", priceBean.getClose());
						jsonObject.put("high", priceBean.getHigh());
						jsonObject.put("low", priceBean.getLow());
						jsonObject.put("open", priceBean.getOpen());
						jsonObject.put("volume", priceBean.getVolume());
						jsonObject.put("event", priceBean.getEvent() == null ? "" : priceBean.getEvent());

						jsonArray.add(jsonObject);
					}
				}
			}

			highStock.setData(jsonArray);

			if (logger.isDebugEnabled())
				logger.debug(location + ":: END");
		} catch (Exception e) {
			logger.error(location, e);
			throw new SystemException(location, e);
		}

		return highStock;
	}

	public HighStock generateSMA(FlashChartBean flashChartBean) throws FunctionalException, SystemException {
		final String location = "generateSMA(flashChartBean:" + flashChartBean + ")";
		if (logger.isDebugEnabled())
			logger.debug(location + ":: BEGIN");

		if (flashChartBean == null) {
			throw new FunctionalException(location, "flashChartBean object is NULL or EMPTY ...");
		}

		// begin set value
		final HighStock highStock = new HighStock();
		try {
			final ArrayList<JSONArray> listJsonArray = new ArrayList<JSONArray>();

			// period 1
			final ArrayList<FlashPriceBean> periodValues1 = (ArrayList<FlashPriceBean>) flashChartBean.getPricesMap().get(
					TAConstants.IndicatorParams.SMA.PERIOD_1);
			if (periodValues1 != null && periodValues1.size() > 0) {
				// check 2 lastest data, if same day so remove one record(the
				// lastest is union from sec_info)
				FlashPriceBean priceBean1 = periodValues1.get(periodValues1.size() - 1);
				FlashPriceBean priceBean2 = periodValues1.get(periodValues1.size() - 2);
				int sub = 0;
				if (priceBean1.getTransDate().getTime() == priceBean2.getTransDate().getTime()) {
					sub = 1;
				}

				listJsonArray.add(exportFlashPriceBeanToJsonArray(periodValues1, sub));
			}

			// period 2
			final ArrayList<FlashPriceBean> periodValues2 = (ArrayList<FlashPriceBean>) flashChartBean.getPricesMap().get(
					TAConstants.IndicatorParams.SMA.PERIOD_2);
			if (periodValues2 != null && periodValues2.size() > 0) {
				// check 2 lastest data, if same day so remove one record(the
				// lastest is union from sec_info)
				FlashPriceBean priceBean1 = periodValues2.get(periodValues2.size() - 1);
				FlashPriceBean priceBean2 = periodValues2.get(periodValues2.size() - 2);
				int sub = 0;
				if (priceBean1.getTransDate().getTime() == priceBean2.getTransDate().getTime()) {
					sub = 1;
				}

				listJsonArray.add(exportFlashPriceBeanToJsonArray(periodValues2, sub));
			}

			// period 3
			final ArrayList<FlashPriceBean> periodValues3 = (ArrayList<FlashPriceBean>) flashChartBean.getPricesMap().get(
					TAConstants.IndicatorParams.SMA.PERIOD_3);
			if (periodValues3 != null && periodValues3.size() > 0) {
				// check 2 lastest data, if same day so remove one record(the
				// lastest is union from sec_info)
				FlashPriceBean priceBean1 = periodValues3.get(periodValues3.size() - 1);
				FlashPriceBean priceBean2 = periodValues3.get(periodValues3.size() - 2);
				int sub = 0;
				if (priceBean1.getTransDate().getTime() == priceBean2.getTransDate().getTime()) {
					sub = 1;
				}

				listJsonArray.add(exportFlashPriceBeanToJsonArray(periodValues3, sub));
			}

			highStock.setListData(listJsonArray);

			if (logger.isDebugEnabled())
				logger.debug(location + ":: END");
		} catch (Exception e) {
			logger.error(location, e);
			throw new SystemException(location, e);
		}

		return highStock;
	}

	public HighStock generateEMA(FlashChartBean flashChartBean) throws FunctionalException, SystemException {
		final String location = "generateEMA(flashChartBean:" + flashChartBean + ")";
		if (logger.isDebugEnabled())
			logger.debug(location + ":: BEGIN");

		if (flashChartBean == null) {
			throw new FunctionalException(location, "flashChartBean object is NULL or EMPTY ...");
		}

		// begin set value
		final HighStock highStock = new HighStock();
		try {
			final ArrayList<JSONArray> listJsonArray = new ArrayList<JSONArray>();

			// period 1
			final ArrayList<FlashPriceBean> periodValues1 = (ArrayList<FlashPriceBean>) flashChartBean.getPricesMap().get(
					TAConstants.IndicatorParams.EMA.PERIOD_1);
			if (periodValues1 != null && periodValues1.size() > 0) {
				// check 2 lastest data, if same day so remove one record(the
				// lastest is union from sec_info)
				FlashPriceBean priceBean1 = periodValues1.get(periodValues1.size() - 1);
				FlashPriceBean priceBean2 = periodValues1.get(periodValues1.size() - 2);
				int sub = 0;
				if (priceBean1.getTransDate().getTime() == priceBean2.getTransDate().getTime()) {
					sub = 1;
				}

				listJsonArray.add(exportFlashPriceBeanToJsonArray(periodValues1, sub));
			}

			// period 2
			final ArrayList<FlashPriceBean> periodValues2 = (ArrayList<FlashPriceBean>) flashChartBean.getPricesMap().get(
					TAConstants.IndicatorParams.EMA.PERIOD_2);
			if (periodValues2 != null && periodValues2.size() > 0) {
				// check 2 lastest data, if same day so remove one record(the
				// lastest is union from sec_info)
				FlashPriceBean priceBean1 = periodValues2.get(periodValues2.size() - 1);
				FlashPriceBean priceBean2 = periodValues2.get(periodValues2.size() - 2);
				int sub = 0;
				if (priceBean1.getTransDate().getTime() == priceBean2.getTransDate().getTime()) {
					sub = 1;
				}

				listJsonArray.add(exportFlashPriceBeanToJsonArray(periodValues2, sub));
			}

			// period 3
			final ArrayList<FlashPriceBean> periodValues3 = (ArrayList<FlashPriceBean>) flashChartBean.getPricesMap().get(
					TAConstants.IndicatorParams.EMA.PERIOD_3);
			if (periodValues3 != null && periodValues3.size() > 0) {
				// check 2 lastest data, if same day so remove one record(the
				// lastest is union from sec_info)
				FlashPriceBean priceBean1 = periodValues3.get(periodValues3.size() - 1);
				FlashPriceBean priceBean2 = periodValues3.get(periodValues3.size() - 2);
				int sub = 0;
				if (priceBean1.getTransDate().getTime() == priceBean2.getTransDate().getTime()) {
					sub = 1;
				}

				listJsonArray.add(exportFlashPriceBeanToJsonArray(periodValues3, sub));
			}

			highStock.setListData(listJsonArray);

			if (logger.isDebugEnabled())
				logger.debug(location + ":: END");
		} catch (Exception e) {
			logger.error(location, e);
			throw new SystemException(location, e);
		}

		return highStock;
	}

	public HighStock generateBBands(FlashChartBean flashChartBean) throws FunctionalException, SystemException {
		final String location = "generateBBands(flashChartBean:" + flashChartBean + ")";
		if (logger.isDebugEnabled())
			logger.debug(location + ":: BEGIN");

		if (flashChartBean == null) {
			throw new FunctionalException(location, "flashChartBean object is NULL or EMPTY ...");
		}

		// begin set value
		final HighStock highStock = new HighStock();
		try {
			JSONArray jsonArray = new JSONArray();
			JSONObject jsonObject;

			// period
			final ArrayList<FlashPriceBean> periodValues = (ArrayList<FlashPriceBean>) flashChartBean.getPricesMap().get(
					TAConstants.IndicatorParams.BBands.PERIOD);
			if (periodValues != null && periodValues.size() > 0) {
				// check 2 lastest data, if same day so remove one record(the
				// lastest is union from sec_info)
				FlashPriceBean priceBean1 = periodValues.get(periodValues.size() - 1);
				FlashPriceBean priceBean2 = periodValues.get(periodValues.size() - 2);
				int sub = 0;
				if (priceBean1.getTransDate().getTime() == priceBean2.getTransDate().getTime()) {
					sub = 1;
				}

				FlashPriceBean priceBean;
				Date date;
				for (int i = 0; i < periodValues.size() - sub; i++) {
					priceBean = periodValues.get(i);
					jsonObject = new JSONObject();
					date = formatter.parse(DateFormatUtils.format(priceBean.getTransDate(), STR_DATE_TIME_FORMAT_DDMMYYYY));
					jsonObject.put("transDate", date.getTime() + A_DAY_IN_MILISECOND);
					jsonObject.put("low", priceBean.getLow());
					jsonObject.put("high", priceBean.getHigh());

					jsonArray.add(jsonObject);
				}
			}

			highStock.setData(jsonArray);

			if (logger.isDebugEnabled())
				logger.debug(location + ":: END");
		} catch (Exception e) {
			logger.error(location, e);
			throw new SystemException(location, e);
		}

		return highStock;
	}

	public HighStock generateMFI(FlashChartBean flashChartBean) throws FunctionalException, SystemException {
		final String location = "generateMFI(flashChartBean:" + flashChartBean + ")";
		if (logger.isDebugEnabled())
			logger.debug(location + ":: BEGIN");

		if (flashChartBean == null) {
			throw new FunctionalException(location, "flashChartBean object is NULL or EMPTY ...");
		}

		// begin set value
		final HighStock highStock = new HighStock();
		try {
			JSONArray jsonArray = new JSONArray();
			// period
			final ArrayList<FlashPriceBean> periodValues = (ArrayList<FlashPriceBean>) flashChartBean.getPricesMap().get(
					TAConstants.IndicatorParams.MFI.PERIOD);
			if (periodValues != null && periodValues.size() > 0) {
				// check 2 lastest data, if same day so remove one record(the
				// lastest is union from sec_info)
				FlashPriceBean priceBean1 = periodValues.get(periodValues.size() - 1);
				FlashPriceBean priceBean2 = periodValues.get(periodValues.size() - 2);
				int sub = 0;
				if (priceBean1.getTransDate().getTime() == priceBean2.getTransDate().getTime()) {
					sub = 1;
				}

				jsonArray = exportFlashPriceBeanToJsonArray(periodValues, sub);
			}

			highStock.setData(jsonArray);

			if (logger.isDebugEnabled())
				logger.debug(location + ":: END");
		} catch (Exception e) {
			logger.error(location, e);
			throw new SystemException(location, e);
		}

		return highStock;
	}

	public HighStock generateMACD(FlashChartBean flashChartBean) throws FunctionalException, SystemException {
		final String location = "generateMACD(flashChartBean:" + flashChartBean + ")";
		if (logger.isDebugEnabled())
			logger.debug(location + ":: BEGIN");

		if (flashChartBean == null) {
			throw new FunctionalException(location, "flashChartBean object is NULL or EMPTY ...");
		}

		// begin set value
		final HighStock highStock = new HighStock();
		try {
			JSONObject jsonObject;
			JSONArray jsonArray = new JSONArray();
			final ArrayList<FlashPriceBean> periodValues = (ArrayList<FlashPriceBean>) flashChartBean.getPricesMap().get(
					TAConstants.IndicatorParams.MACD.FAST_PERIOD);
			if (periodValues != null && periodValues.size() > 0) {
				// check 2 lastest data, if same day so remove one record(the
				// lastest is union from sec_info)
				FlashPriceBean priceBean1 = periodValues.get(periodValues.size() - 1);
				FlashPriceBean priceBean2 = periodValues.get(periodValues.size() - 2);
				int sub = 0;
				if (priceBean1.getTransDate().getTime() == priceBean2.getTransDate().getTime()) {
					sub = 1;
				}
				FlashPriceBean priceBean;
				Date date;
				for (int i = 0; i < periodValues.size() - sub; i++) {
					priceBean = periodValues.get(i);
					jsonObject = new JSONObject();
					date = formatter.parse(DateFormatUtils.format(priceBean.getTransDate(), STR_DATE_TIME_FORMAT_DDMMYYYY));
					jsonObject.put("transDate", date.getTime() + A_DAY_IN_MILISECOND);
					jsonObject.put("close", priceBean.getClose());
					jsonObject.put("high", priceBean.getHigh());

					jsonArray.add(jsonObject);
				}
			}

			highStock.setData(jsonArray);

			if (logger.isDebugEnabled())
				logger.debug(location + ":: END");
		} catch (Exception e) {
			logger.error(location, e);
			throw new SystemException(location, e);
		}

		return highStock;
	}

	public HighStock generatePSAR(FlashChartBean flashChartBean) throws FunctionalException, SystemException {
		final String location = "generatePSAR(flashChartBean:" + flashChartBean + ")";
		if (logger.isDebugEnabled())
			logger.debug(location + ":: BEGIN");

		if (flashChartBean == null) {
			throw new FunctionalException(location, "flashChartBean object is NULL or EMPTY ...");
		}

		// begin set value
		final HighStock highStock = new HighStock();
		try {
			JSONArray jsonArray = new JSONArray();
			final ArrayList<FlashPriceBean> periodValues = (ArrayList<FlashPriceBean>) flashChartBean.getPricesMap().get(
					TAConstants.IndicatorParams.PSAR.MAX_STEP_PERIOD);
			if (periodValues != null && periodValues.size() > 0) {
				// check 2 lastest data, if same day so remove one record(the
				// lastest is union from sec_info)
				FlashPriceBean priceBean1 = periodValues.get(periodValues.size() - 1);
				FlashPriceBean priceBean2 = periodValues.get(periodValues.size() - 2);
				int sub = 0;
				if (priceBean1.getTransDate().getTime() == priceBean2.getTransDate().getTime()) {
					sub = 1;
				}

				jsonArray = exportFlashPriceBeanToJsonArray(periodValues, sub);
			}

			highStock.setData(jsonArray);

			if (logger.isDebugEnabled())
				logger.debug(location + ":: END");
		} catch (Exception e) {
			logger.error(location, e);
			throw new SystemException(location, e);
		}

		return highStock;
	}

	public HighStock generateROC(FlashChartBean flashChartBean) throws FunctionalException, SystemException {
		final String location = "generateROC(flashChartBean:" + flashChartBean + ")";
		if (logger.isDebugEnabled())
			logger.debug(location + ":: BEGIN");

		if (flashChartBean == null) {
			throw new FunctionalException(location, "flashChartBean object is NULL or EMPTY ...");
		}

		// begin set value
		final HighStock highStock = new HighStock();
		try {
			JSONArray jsonArray = new JSONArray();
			final ArrayList<FlashPriceBean> periodValues = (ArrayList<FlashPriceBean>) flashChartBean.getPricesMap().get(
					TAConstants.IndicatorParams.ROC.PERIOD);
			if (periodValues != null && periodValues.size() > 0) {
				// check 2 lastest data, if same day so remove one record(the
				// lastest is union from sec_info)
				FlashPriceBean priceBean1 = periodValues.get(periodValues.size() - 1);
				FlashPriceBean priceBean2 = periodValues.get(periodValues.size() - 2);
				int sub = 0;
				if (priceBean1.getTransDate().getTime() == priceBean2.getTransDate().getTime()) {
					sub = 1;
				}

				jsonArray = exportFlashPriceBeanToJsonArray(periodValues, sub);
			}

			highStock.setData(jsonArray);

			if (logger.isDebugEnabled())
				logger.debug(location + ":: END");
		} catch (Exception e) {
			logger.error(location, e);
			throw new SystemException(location, e);
		}

		return highStock;
	}

	public HighStock generateRSI(FlashChartBean flashChartBean) throws FunctionalException, SystemException {
		final String location = "generateRSI(flashChartBean:" + flashChartBean + ")";
		if (logger.isDebugEnabled())
			logger.debug(location + ":: BEGIN");

		if (flashChartBean == null) {
			throw new FunctionalException(location, "flashChartBean object is NULL or EMPTY ...");
		}

		// begin set value
		final HighStock highStock = new HighStock();
		try {
			JSONArray jsonArray = new JSONArray();
			final ArrayList<FlashPriceBean> periodValues = (ArrayList<FlashPriceBean>) flashChartBean.getPricesMap().get(
					TAConstants.IndicatorParams.RSI.PERIOD);
			if (periodValues != null && periodValues.size() > 0) {
				// check 2 lastest data, if same day so remove one record(the
				// lastest is union from sec_info)
				FlashPriceBean priceBean1 = periodValues.get(periodValues.size() - 1);
				FlashPriceBean priceBean2 = periodValues.get(periodValues.size() - 2);
				int sub = 0;
				if (priceBean1.getTransDate().getTime() == priceBean2.getTransDate().getTime()) {
					sub = 1;
				}

				jsonArray = exportFlashPriceBeanToJsonArray(periodValues, sub);
			}

			highStock.setData(jsonArray);

			if (logger.isDebugEnabled())
				logger.debug(location + ":: END");
		} catch (Exception e) {
			logger.error(location, e);
			throw new SystemException(location, e);
		}

		return highStock;
	}

	public HighStock generateSS(FlashChartBean flashChartBean) throws FunctionalException, SystemException {
		final String location = "generateSS(flashChartBean:" + flashChartBean + ")";
		if (logger.isDebugEnabled())
			logger.debug(location + ":: BEGIN");

		if (flashChartBean == null) {
			throw new FunctionalException(location, "flashChartBean object is NULL or EMPTY ...");
		}

		// begin set value
		final HighStock highStock = new HighStock();

		try {
			JSONArray jsonArray = new JSONArray();
			final ArrayList<FlashPriceBean> periodValues = (ArrayList<FlashPriceBean>) flashChartBean.getPricesMap().get(
					TAConstants.IndicatorParams.SS.K_PERIOD);
			if (periodValues != null && periodValues.size() > 0) {
				// check 2 lastest data, if same day so remove one record(the
				// lastest is union from sec_info)
				FlashPriceBean priceBean1 = periodValues.get(periodValues.size() - 1);
				FlashPriceBean priceBean2 = periodValues.get(periodValues.size() - 2);
				int sub = 0;
				if (priceBean1.getTransDate().getTime() == priceBean2.getTransDate().getTime()) {
					sub = 1;
				}
				JSONObject jsonObject;
				FlashPriceBean priceBean;
				Date date;
				for (int i = 0; i < periodValues.size() - sub; i++) {
					priceBean = (FlashPriceBean) periodValues.get(i);
					jsonObject = new JSONObject();
					date = formatter.parse(DateFormatUtils.format(priceBean.getTransDate(), STR_DATE_TIME_FORMAT_DDMMYYYY));
					jsonObject.put("transDate", date.getTime() + A_DAY_IN_MILISECOND);
					if (isInfinite(priceBean.getClose()) || isInfinite(priceBean.getHigh())) {
						continue;
					}
					jsonObject.put("close", priceBean.getClose());
					jsonObject.put("high", priceBean.getHigh());

					jsonArray.add(jsonObject);
				}
			}

			highStock.setData(jsonArray);

			if (logger.isDebugEnabled())
				logger.debug(location + ":: END");
		} catch (Exception e) {
			logger.error(location, e);
			throw new SystemException(location, e);
		}

		return highStock;
	}

	public HighStock generateFS(FlashChartBean flashChartBean) throws FunctionalException, SystemException {
		final String location = "generateFS(flashChartBean:" + flashChartBean + ")";
		if (logger.isDebugEnabled())
			logger.debug(location + ":: BEGIN");

		if (flashChartBean == null) {
			throw new FunctionalException(location, "flashChartBean object is NULL or EMPTY ...");
		}
		// begin set value
		final HighStock highStock = new HighStock();
		JSONArray jsonArray = new JSONArray();
		try {

			final ArrayList<FlashPriceBean> periodValues = (ArrayList<FlashPriceBean>) flashChartBean.getPricesMap().get(
					TAConstants.IndicatorParams.FS.K_PERIOD);
			if (periodValues != null && periodValues.size() > 0) {
				// check 2 lastest data, if same day so remove one record(the
				// lastest is union from sec_info)
				FlashPriceBean priceBean1 = periodValues.get(periodValues.size() - 1);
				FlashPriceBean priceBean2 = periodValues.get(periodValues.size() - 2);
				int sub = 0;
				if (priceBean1.getTransDate().getTime() == priceBean2.getTransDate().getTime()) {
					sub = 1;
				}

				JSONObject jsonObject;
				FlashPriceBean priceBean;
				Date date;
				for (int i = 0; i < periodValues.size() - sub; i++) {
					priceBean = periodValues.get(i);
					if (isInfinite(priceBean.getClose()) || isInfinite(priceBean.getHigh())) {
						continue;
					}
					jsonObject = new JSONObject();
					date = formatter.parse(DateFormatUtils.format(priceBean.getTransDate(), STR_DATE_TIME_FORMAT_DDMMYYYY));
					jsonObject.put("transDate", date.getTime() + A_DAY_IN_MILISECOND);
					jsonObject.put("close", priceBean.getClose());
					jsonObject.put("high", priceBean.getHigh());

					jsonArray.add(jsonObject);
				}
			}

			highStock.setData(jsonArray);

			if (logger.isDebugEnabled())
				logger.debug(location + ":: END");
		} catch (Exception e) {
			logger.error(location, e);
			throw new SystemException(location, e);
		}

		return highStock;
	}

	public HighStock generateVolumeMA(FlashChartBean flashChartBean) throws FunctionalException, SystemException {
		final String location = "generateVolumeMA(flashChartBean:" + flashChartBean + ")";
		if (logger.isDebugEnabled())
			logger.debug(location + ":: BEGIN");

		if (flashChartBean == null) {
			throw new FunctionalException(location, "flashChartBean object is NULL or EMPTY ...");
		}
		// begin set value
		final HighStock highStock = new HighStock();

		try {
			JSONArray jsonArray = new JSONArray();
			final ArrayList<FlashPriceBean> periodValues = (ArrayList<FlashPriceBean>) flashChartBean.getPricesMap().get(
					TAConstants.IndicatorParams.VolumeMA.MA_PERIOD);
			if (periodValues != null && periodValues.size() > 0) {
				// check 2 lastest data, if same day so remove one record(the
				// lastest is union from sec_info)
				FlashPriceBean priceBean1 = periodValues.get(periodValues.size() - 1);
				FlashPriceBean priceBean2 = periodValues.get(periodValues.size() - 2);
				int sub = 0;
				if (priceBean1.getTransDate().getTime() == priceBean2.getTransDate().getTime()) {
					sub = 1;
				}

				jsonArray = exportFlashPriceBeanToJsonArray(periodValues, sub);
			}

			highStock.setData(jsonArray);

			if (logger.isDebugEnabled())
				logger.debug(location + ":: END");
		} catch (Exception e) {
			logger.error(location, e);
			throw new SystemException(location, e);
		}

		return highStock;
	}

	public HighStock generateWR(FlashChartBean flashChartBean) throws FunctionalException, SystemException {
		final String location = "generateWR(flashChartBean:" + flashChartBean + ")";
		if (logger.isDebugEnabled())
			logger.debug(location + ":: BEGIN");

		if (flashChartBean == null) {
			throw new FunctionalException(location, "flashChartBean object is NULL or EMPTY ...");
		}
		// begin set value
		final HighStock highStock = new HighStock();
		try {
			JSONArray jsonArray = new JSONArray();
			final ArrayList<FlashPriceBean> periodValues = (ArrayList<FlashPriceBean>) flashChartBean.getPricesMap().get(
					TAConstants.IndicatorParams.WilliamsR.MA_PERIOD);
			if (periodValues != null && periodValues.size() > 0) {
				// check 2 lastest data, if same day so remove one record(the
				// lastest is union from sec_info)
				FlashPriceBean priceBean1 = periodValues.get(periodValues.size() - 1);
				FlashPriceBean priceBean2 = periodValues.get(periodValues.size() - 2);
				int sub = 0;
				if (priceBean1.getTransDate().getTime() == priceBean2.getTransDate().getTime()) {
					sub = 1;
				}

				jsonArray = exportFlashPriceBeanToJsonArray(periodValues, sub);
			}

			highStock.setData(jsonArray);

			if (logger.isDebugEnabled())
				logger.debug(location + ":: END");
		} catch (Exception e) {
			logger.error(location, e);
			throw new SystemException(location, e);
		}

		return highStock;
	}

	/**
	 * use for filter [transDate], [close]
	 * 
	 * @throws ParseException
	 */
	private JSONArray exportFlashPriceBeanToJsonArray(List<FlashPriceBean> flashPriceBeans, int sub) throws ParseException {
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObject;
		FlashPriceBean priceBean;
		Date date;
		for (int i = 0; i < flashPriceBeans.size() - sub; i++) {
			priceBean = flashPriceBeans.get(i);
			if (isInfinite(priceBean.getClose()) || isInfinite(priceBean.getHigh())) {
				continue;
			}
			jsonObject = new JSONObject();
			date = formatter.parse(DateFormatUtils.format(priceBean.getTransDate(), STR_DATE_TIME_FORMAT_DDMMYYYY));
			jsonObject.put("transDate", date.getTime() + A_DAY_IN_MILISECOND);
			jsonObject.put("close", priceBean.getClose());

			jsonArray.add(jsonObject);
		}

		return jsonArray;
	}

	private boolean isInfinite(Double value) {
		return Double.isInfinite(value == null ? 0.0 : value);
	}
}