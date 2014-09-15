package vn.com.vndirect.web.struts2.chart;

import vn.com.vndirect.commons.flashchart.beans.FlashChartBean;
import vn.com.web.commons.exception.FunctionalException;
import vn.com.web.commons.exception.SystemException;

public interface IHighStockManager {
	public HighStock generateHistoricalPrice(FlashChartBean flashChartBean) throws FunctionalException, SystemException;

	public HighStock generateSMA(FlashChartBean flashChartBean) throws FunctionalException, SystemException;

	public HighStock generateEMA(FlashChartBean flashChartBean) throws FunctionalException, SystemException;

	public HighStock generateBBands(FlashChartBean flashChartBean) throws FunctionalException, SystemException;

	public HighStock generateMFI(FlashChartBean flashChartBean) throws FunctionalException, SystemException;

	public HighStock generateMACD(FlashChartBean flashChartBean) throws FunctionalException, SystemException;

	public HighStock generatePSAR(FlashChartBean flashChartBean) throws FunctionalException, SystemException;
	
	public HighStock generateROC(FlashChartBean flashChartBean) throws FunctionalException, SystemException;
	
	public HighStock generateRSI(FlashChartBean flashChartBean) throws FunctionalException, SystemException;
	
	/**
	 * Slow stochastic
	 */
	public HighStock generateSS(FlashChartBean flashChartBean) throws FunctionalException, SystemException;
	
	/**
	 * fast stochastic
	 */
	public HighStock generateFS(FlashChartBean flashChartBean) throws FunctionalException, SystemException;
	
	public HighStock generateVolumeMA(FlashChartBean flashChartBean) throws FunctionalException, SystemException;
	
	/**
	 * William %R
	 */
	public HighStock generateWR(FlashChartBean flashChartBean) throws FunctionalException, SystemException;
}
