package vn.com.vndirect.web.struts2.portlet;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import vn.com.vndirect.domain.struts2.portlet.WorldIndexAJAXModel;
import vn.com.vndirect.worldmarket.WorldMarketManager;
import vn.com.vndirect.worldmarket.adapter.MapQuote;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@SuppressWarnings("serial")
public class WorldIndexAJAXAction extends ActionSupport implements ModelDriven<WorldIndexAJAXModel> {
	private static Logger logger = Logger.getLogger(WorldIndexAJAXAction.class);

	private WorldMarketManager worldMarketManager;
	private MapQuote mapQuoteYAH;
	private MapQuote mapQuoteGOO;

	private WorldIndexAJAXModel model = new WorldIndexAJAXModel();

	/**
	 * @param worldMarketManager
	 *            the worldMarketManager to set
	 */
	public void setWorldMarketManager(WorldMarketManager worldMarketManager) {
		this.worldMarketManager = worldMarketManager;
	}

	public WorldIndexAJAXModel getModel() {
		return model;
	}

	/**
	 *
	 */
	public String execute() {
		if (worldMarketManager != null) {
			List<String> lstSymbols = new ArrayList<String>();
			String market = model.getMarket();
			if (StringUtils.isNotBlank(market)) {
				market = market.trim().toUpperCase();
				if (mapQuoteGOO != null) {
					List<String> lstGOOSymbols = mapQuoteGOO.getMarketQuotes(market);
					if (lstGOOSymbols != null) {
						lstSymbols.addAll(lstGOOSymbols);
					}
				}
				if (mapQuoteYAH != null) {
					List<String> lstYAHSymbols = mapQuoteYAH.getMarketQuotes(market);
					if (lstYAHSymbols != null) {
						lstSymbols.addAll(lstYAHSymbols);
					}
				}
			}
			if (logger.isDebugEnabled()) {
				logger.debug("ALL lstSymbols: " + lstSymbols);
			}

			if (lstSymbols.size() == 0) {
				if (model.getSymbols() == null || model.getSymbols().length() == 0) {
					model.setLstWorldQuote(worldMarketManager.findAllWorldQuotes());
				} else {
					lstSymbols = new ArrayList<String>();
					StringTokenizer strToken = new StringTokenizer(model.getSymbols(), "+,;");
					while (strToken.hasMoreTokens()) {
						lstSymbols.add(strToken.nextToken());
					}
					if (logger.isDebugEnabled()) {
						logger.debug("execute()" + lstSymbols);
					}
					model.setLstWorldQuote(worldMarketManager.findWorldQuotes(lstSymbols));
				}
			} else {
				model.setLstWorldQuote(worldMarketManager.findWorldQuotes(lstSymbols));
			}
		}
		return SUCCESS;
	}

	/**
	 * @param mapQuote
	 *            the mapQuote to set
	 */
	public void setMapQuoteYAH(MapQuote mapQuoteYAH) {
		this.mapQuoteYAH = mapQuoteYAH;
	}

	/**
	 * @param mapQuote
	 *            the mapQuote to set
	 */
	public void setMapQuoteGOO(MapQuote mapQuoteGOO) {
		this.mapQuoteGOO = mapQuoteGOO;
	}
}