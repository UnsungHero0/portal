package vn.com.vndirect.business;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.rpc.ServiceException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.code.ssm.api.ParameterValueKeyProvider;
import com.google.code.ssm.api.ReadThroughSingleCache;
import vn.com.vndirect.commons.utility.Constants;
import vn.com.vndirect.commons.utility.VNDirectWebUtilities;
import vn.com.vndirect.commons.vndsservice.VNDSServiceUtils;
import vn.com.vndirect.dao.impl.IfoBreakdownViewDAO;
import vn.com.vndirect.dao.impl.IfoCompanyIndustryViewDAO;
import vn.com.vndirect.dao.impl.IfoCompanyNameViewDAO;
import vn.com.vndirect.dao.impl.IfoCompanyOfficersViewDAO;
import vn.com.vndirect.dao.impl.IfoCompanyProfileViewDAO;
import vn.com.vndirect.dao.impl.IfoCompanySnapshotViewDAO;
import vn.com.vndirect.dao.impl.IfoExchangeCodeDAO;
import vn.com.vndirect.dao.impl.IfoFinancialHighlightViewDAO;
import vn.com.vndirect.dao.impl.IfoIndexCalcDAO;
import vn.com.vndirect.dao.impl.IfoInsiderTransactionViewDAO;
import vn.com.vndirect.dao.impl.IfoInvestorRightsViewDAO;
import vn.com.vndirect.dao.impl.IfoKeyRatioViewDAO;
import vn.com.vndirect.dao.impl.IfoMaijorHolderViewDAO;
import vn.com.vndirect.dao.impl.IfoSecIndexViewDAO;
import vn.com.vndirect.dao.impl.IfoSecPriceViewDAO;
import vn.com.vndirect.dao.impl.IfoSectorCodeDAO;
import vn.com.vndirect.dao.impl.IfoStockExchangeViewDAO;
import vn.com.vndirect.dao.impl.IfoValuationMeasuresViewDAO;
import vn.com.vndirect.domain.IfoBreakdownView;
import vn.com.vndirect.domain.IfoBreakdownViewId;
import vn.com.vndirect.domain.IfoCompany;
import vn.com.vndirect.domain.IfoCompanyNameView;
import vn.com.vndirect.domain.IfoCompanyNameViewId;
import vn.com.vndirect.domain.IfoCompanyOfficersViewId;
import vn.com.vndirect.domain.IfoCompanyProfileView;
import vn.com.vndirect.domain.IfoCompanyProfileViewId;
import vn.com.vndirect.domain.IfoEstimateView;
import vn.com.vndirect.domain.IfoExchangeCode;
import vn.com.vndirect.domain.IfoFinancialHighlightViewId;
import vn.com.vndirect.domain.IfoInsiderTransactionView;
import vn.com.vndirect.domain.IfoInsiderTransactionViewId;
import vn.com.vndirect.domain.IfoInvestorRightsViewId;
import vn.com.vndirect.domain.IfoKeyRatioViewId;
import vn.com.vndirect.domain.IfoMaijorHolderView;
import vn.com.vndirect.domain.IfoMaijorHolderViewId;
import vn.com.vndirect.domain.IfoSecIndexView;
import vn.com.vndirect.domain.IfoSecIndexViewId;
import vn.com.vndirect.domain.IfoSecPriceView;
import vn.com.vndirect.domain.IfoSectorCode;
import vn.com.vndirect.domain.IfoSectorCodeId;
import vn.com.vndirect.domain.IfoValuationMeasuresViewId;
import vn.com.vndirect.domain.extend.CompanySummary;
import vn.com.vndirect.domain.extend.CurrentCompanyForQuote;
import vn.com.vndirect.domain.extend.IfoCompanySnapshotViewExt;
import vn.com.vndirect.domain.extend.IfoIndexCalc;
import vn.com.vndirect.domain.extend.MarketOption;
import vn.com.vndirect.domain.extend.SearchIfoSecPrice;
import vn.com.vndirect.domain.extend.SearchSymbol;
import vn.com.vndirect.domain.extend.SecuritiesInfoForQuote;
import vn.com.vndirect.wsclient.AuthenticationHeader;
import vn.com.vndirect.wsclient.streamquotes.CompanyOverview;
import vn.com.vndirect.wsclient.streamquotes.IStreamQuotesServicePortType;
import vn.com.vndirect.wsclient.streamquotes.IntradayPriceSearch;
import vn.com.vndirect.wsclient.streamquotes.IntradayPriceSearchResult;
import vn.com.vndirect.wsclient.streamquotes.MarketOverview;
import vn.com.vndirect.wsclient.streamquotes.MarketOverviewSearch;
import vn.com.vndirect.wsclient.streamquotes.MarketOverviewSearchResult;
import vn.com.vndirect.wsclient.streamquotes.SecuritiesHighLight;
import vn.com.vndirect.wsclient.streamquotes.SecuritiesHighLightSearch;
import vn.com.vndirect.wsclient.streamquotes.SecuritiesHighLightSearchResult;
import vn.com.web.commons.domain.db.PagingInfo;
import vn.com.web.commons.domain.db.SearchResult;
import vn.com.web.commons.exception.FunctionalException;
import vn.com.web.commons.exception.SystemException;
import vn.com.web.commons.utility.DateUtils;

@SuppressWarnings({ "unchecked", "rawtypes" })
@Component
public class QuotesManager extends BaseManager implements IQuotesManager {

	private static Logger logger = Logger.getLogger(QuotesManager.class);
	private static final int EXPIRATION_TIME_FOR_CACHE = 600;
	@Autowired
	private IfoCompanySnapshotViewDAO ifoCompanySnapshotViewDAO;
	
	@Autowired
	private IfoCompanyNameViewDAO ifoCompanyNameViewDAO;
	
	@Autowired
	private IfoStockExchangeViewDAO ifoStockExchangeViewDAO;
	
	@Autowired
	private IfoCompanyIndustryViewDAO ifoCompanyIndustryViewDAO;
	
	@Autowired
	private IfoCompanyOfficersViewDAO ifoCompanyOfficersViewDAO;
	
	@Autowired
	private IfoSecIndexViewDAO ifoSecIndexViewDAO;
	
	@Autowired
	private IfoCompanyProfileViewDAO ifoCompanyProfileViewDAO;
	
	@Autowired
	private IfoValuationMeasuresViewDAO ifoValuationMeasuresViewDAO;
	
	@Autowired
	private IfoKeyRatioViewDAO ifoKeyRatioViewDAO;
	
	@Autowired
	private IfoFinancialHighlightViewDAO ifoFinancialHighlightViewDAO;
	
	@Autowired
	private IfoInvestorRightsViewDAO ifoInvestorRightsViewDAO;
	
	@Autowired
	private IfoBreakdownViewDAO ifoBreakdownViewDAO;
	
	@Autowired
	private IfoMaijorHolderViewDAO ifoMaijorHolderViewDAO;
	
	@Autowired
	private IfoInsiderTransactionViewDAO ifoInsiderTransactionViewDAO;
	
	@Autowired
	private IfoExchangeCodeDAO ifoExchangeCodeDAO;
	
	@Autowired
	private IfoSectorCodeDAO ifoSectorCodeDAO;
	
	@Autowired
	private IfoSecPriceViewDAO ifoSecPriceViewDAO;
	
	@Autowired
	private IfoIndexCalcDAO ifoIndexCalcDAO;

	/**
	 * @param ifoSecPriceViewDAO
	 *            the ifoSecPriceViewDAO to set
	 */
	public void setIfoSecPriceViewDAO(IfoSecPriceViewDAO ifoSecPriceViewDAO) {
		this.ifoSecPriceViewDAO = ifoSecPriceViewDAO;
	}

	/**
	 * @param ifoInsiderTransactionViewDAO
	 *            the ifoInsiderTransactionViewDAO to set
	 */
	public void setIfoInsiderTransactionViewDAO(IfoInsiderTransactionViewDAO ifoInsiderTransactionViewDAO) {
		this.ifoInsiderTransactionViewDAO = ifoInsiderTransactionViewDAO;
	}

	/**
	 * @param ifoMaijorHolderViewDAO
	 *            the ifoMaijorHolderViewDAO to set
	 */
	public void setIfoMaijorHolderViewDAO(IfoMaijorHolderViewDAO ifoMaijorHolderViewDAO) {
		this.ifoMaijorHolderViewDAO = ifoMaijorHolderViewDAO;
	}

	/**
	 * @param ifoBreakdownViewDAO
	 *            the ifoBreakdownViewDAO to set
	 */
	public void setIfoBreakdownViewDAO(IfoBreakdownViewDAO ifoBreakdownViewDAO) {
		this.ifoBreakdownViewDAO = ifoBreakdownViewDAO;
	}

	/**
	 * @param ifoCompanySnapshotViewDAO
	 *            the ifoCompanySnapshotViewDAO to set
	 */
	public void setIfoCompanySnapshotViewDAO(IfoCompanySnapshotViewDAO ifoCompanySnapshotViewDAO) {
		this.ifoCompanySnapshotViewDAO = ifoCompanySnapshotViewDAO;
	}

	/**
	 * @param ifoCompanyNameViewDAO
	 *            the ifoCompanyNameViewDAO to set
	 */
	public void setIfoCompanyNameViewDAO(IfoCompanyNameViewDAO ifoCompanyNameViewDAO) {
		this.ifoCompanyNameViewDAO = ifoCompanyNameViewDAO;
	}

	/**
	 * @param ifoStockExchangeViewDAO
	 *            the ifoStockExchangeViewDAO to set
	 */
	public void setIfoStockExchangeViewDAO(IfoStockExchangeViewDAO ifoStockExchangeViewDAO) {
		this.ifoStockExchangeViewDAO = ifoStockExchangeViewDAO;
	}

	/**
	 * @param ifoCompanyIndustryViewDAO
	 *            the ifoCompanyIndustryViewDAO to set
	 */
	public void setIfoCompanyIndustryViewDAO(IfoCompanyIndustryViewDAO ifoCompanyIndustryViewDAO) {
		this.ifoCompanyIndustryViewDAO = ifoCompanyIndustryViewDAO;
	}

	/**
	 * @param ifoCompanyOfficersViewDAO
	 *            the ifoCompanyOfficersViewDAO to set
	 */
	public void setIfoCompanyOfficersViewDAO(IfoCompanyOfficersViewDAO ifoCompanyOfficersViewDAO) {
		this.ifoCompanyOfficersViewDAO = ifoCompanyOfficersViewDAO;
	}

	/**
	 * @param ifoSecIndexViewDAO
	 *            the ifoSecIndexViewDAO to set
	 */
	public void setIfoSecIndexViewDAO(IfoSecIndexViewDAO ifoSecIndexViewDAO) {
		this.ifoSecIndexViewDAO = ifoSecIndexViewDAO;
	}

	/**
	 * @param ifoCompanyProfileViewDAO
	 *            the ifoCompanyProfileViewDAO to set
	 */
	public void setIfoCompanyProfileViewDAO(IfoCompanyProfileViewDAO ifoCompanyProfileViewDAO) {
		this.ifoCompanyProfileViewDAO = ifoCompanyProfileViewDAO;
	}

	/**
	 * @param ifoValuationMeasuresViewDAO
	 *            the ifoValuationMeasuresViewDAO to set
	 */
	public void setIfoValuationMeasuresViewDAO(IfoValuationMeasuresViewDAO ifoValuationMeasuresViewDAO) {
		this.ifoValuationMeasuresViewDAO = ifoValuationMeasuresViewDAO;
	}

	/**
	 * @param ifoKeyRatioViewDAO
	 *            the ifoKeyRatioViewDAO to set
	 */
	public void setIfoKeyRatioViewDAO(IfoKeyRatioViewDAO ifoKeyRatioViewDAO) {
		this.ifoKeyRatioViewDAO = ifoKeyRatioViewDAO;
	}

	/**
	 * @param ifoFinancialHighlightViewDAO
	 *            the ifoFinancialHighlightViewDAO to set
	 */
	public void setIfoFinancialHighlightViewDAO(IfoFinancialHighlightViewDAO ifoFinancialHighlightViewDAO) {
		this.ifoFinancialHighlightViewDAO = ifoFinancialHighlightViewDAO;
	}

	/**
	 * @param ifoInvestorRightsViewDAO
	 *            the ifoInvestorRightsViewDAO to set
	 */
	public void setIfoInvestorRightsViewDAO(IfoInvestorRightsViewDAO ifoInvestorRightsViewDAO) {
		this.ifoInvestorRightsViewDAO = ifoInvestorRightsViewDAO;
	}

	/**
	 * @param ifoSectorCodeDAO
	 */
	public void setIfoSectorCodeDAO(IfoSectorCodeDAO ifoSectorCodeDAO) {
		this.ifoSectorCodeDAO = ifoSectorCodeDAO;
	}

	/**
	 * @param ifoExchangeDAO
	 */
	public void setIfoExchangeCodeDAO(IfoExchangeCodeDAO ifoExchangeCodeDAO) {
		this.ifoExchangeCodeDAO = ifoExchangeCodeDAO;
	}

	public void setIfoIndexCalcDAO(IfoIndexCalcDAO ifoIndexCalcDAO) {
		this.ifoIndexCalcDAO = ifoIndexCalcDAO;
	}

	public IntradayPriceSearchResult searchIntradayPrice(IntradayPriceSearch intradayPriceSearch) throws FunctionalException,
	        SystemException {
		final String LOCATION = "searchIntradayPrice(" + "intradayPriceSearch: " + intradayPriceSearch + ")";
		if (logger.isDebugEnabled())
			logger.debug(LOCATION + ":: BEGIN");

		try {

			IStreamQuotesServicePortType iStreamQuotesService = getIStreamQuotesServicePortType();
			IntradayPriceSearchResult result = iStreamQuotesService.searchIntradayPrice(this.getVndsAuthenticationHeader(),
			        intradayPriceSearch);
			VNDSServiceUtils.processMessageStatus(result == null ? null : result.getMsgStatus());
			if (logger.isDebugEnabled())
				logger.debug(LOCATION + ":: END");
			return result;
		} catch (RemoteException rex) {
			logger.error(LOCATION, rex);
			throw new FunctionalException(LOCATION, rex.getMessage(), Constants.ErrorKeys.IDG.REMOTE_ERROR,
			        new String[] { rex.getMessage() });
		} catch (ServiceException srvex) {
			logger.error(LOCATION, srvex);
			throw new FunctionalException(LOCATION, srvex.getMessage(), Constants.ErrorKeys.IDG.REMOTE_ERROR,
			        new String[] { srvex.getMessage() });
		} catch (FunctionalException fex) {
			logger.error(LOCATION, fex);
			throw fex;
		} catch (Exception ex) {
			logger.error(LOCATION, ex);
			throw new FunctionalException(LOCATION, Constants.ErrorKeys.Commons.SYSTEM_ERROR);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * vn.com.vndirect.business.QuotesManager#getCompanySnapshotHighLights(vn
	 * .com.vndirect.domain.extend.CurrentCompanyForQuote)
	 */
	public SecuritiesInfoForQuote getCompanySnapshotHighLights(AuthenticationHeader header, CurrentCompanyForQuote criteriaObject)
	        throws FunctionalException, SystemException {
		final String LOCATION = "getCompanySnapshotHighLights(header:" + header + ", criteriaObject:" + criteriaObject + ")";
		if (logger.isDebugEnabled())
			logger.debug(LOCATION + ":: BEGIN");
		try {
			if (criteriaObject == null || criteriaObject.getCurrentExchangeCode() == null || criteriaObject.getSymbol() == null) {
				throw new SystemException(LOCATION, "criteriaObject is NULL or EMPTY... :: criteriaObject:" + criteriaObject);
			}

			String HASTC = VNDirectWebUtilities.getHASTCExchange();
			String OTC = VNDirectWebUtilities.getOTCExchange();

			IStreamQuotesServicePortType iStreamQuotesService = getIStreamQuotesServicePortType();
			SecuritiesHighLightSearch securitiesHighLightSearch = new SecuritiesHighLightSearch();
			securitiesHighLightSearch.setListSymbol(new String[] { criteriaObject.getSymbol() });

			SecuritiesHighLightSearchResult securitiesHighLightSearchResult = iStreamQuotesService.searchSecuritiesHighLight(
			        header, securitiesHighLightSearch);
			VNDSServiceUtils.processMessageStatus(securitiesHighLightSearchResult == null ? null
			        : securitiesHighLightSearchResult.getMsgStatus());

			SecuritiesInfoForQuote securitiesInfoForQuote = new SecuritiesInfoForQuote();

			if (securitiesHighLightSearchResult != null && securitiesHighLightSearchResult.getListSecuritiesHighLight() != null) {
				SecuritiesHighLight[] securitiesHighLight = securitiesHighLightSearchResult.getListSecuritiesHighLight();
				if (securitiesHighLight != null && securitiesHighLight.length > 0) {
					if (OTC.equalsIgnoreCase(securitiesHighLight[0].getExchangeCode())) {
						securitiesInfoForQuote.setOtcInfoList(Arrays.asList(securitiesHighLight[0].getOtcInfoList()));
					} else {
						vn.com.vndirect.wsclient.streamquotes.SecInfo secInfo = securitiesHighLight[0].getSecInfo();
						// set securitiesInfo
						securitiesInfoForQuote.setStrReferencePrice(VNDirectWebUtilities.getStrDoubleWithScale(
						        secInfo.getBasicPrice(), 1));
						securitiesInfoForQuote.setStrCeilingPrice(VNDirectWebUtilities.getStrDoubleWithScale(
						        secInfo.getCeilingPrice(), 1));
						securitiesInfoForQuote.setStrFloorPrice(VNDirectWebUtilities.getStrDoubleWithScale(
						        secInfo.getFloorPrice(), 1));
						securitiesInfoForQuote.setStrOpenPrice(VNDirectWebUtilities.getStrDoubleWithScale(secInfo.getOpenPrice(),
						        1));
						securitiesInfoForQuote.setStrClosePrice(VNDirectWebUtilities.getStrDoubleWithScale(
						        secInfo.getClosePrice(), 1));
						securitiesInfoForQuote.setStrAveragePrice(VNDirectWebUtilities.getStrDoubleWithScale(
						        secInfo.getAveragePrice(), 1));
						securitiesInfoForQuote.setStrNetVolumn(VNDirectWebUtilities.getStrDoubleWithScale(
						        secInfo.getTotalTradingQtty(), 0));

						// securitiesInfoForQuote.setCurrentPrice(VNDirectWebUtilities.getDoubleValue(secInfo.getCurrentPrice()));
						securitiesInfoForQuote.setCurrentPrice(VNDirectWebUtilities.getDoubleValue(secInfo.getCurrentIndex()));

						double priceChange, percentChange;

						if (HASTC.equalsIgnoreCase(securitiesHighLight[0].getExchangeCode())) {
							priceChange = VNDirectWebUtilities.getDoubleValue(secInfo.getChgIndex());
							percentChange = VNDirectWebUtilities.getDoubleValue(secInfo.getPctIndex());

							securitiesInfoForQuote.setStrTodayChange(VNDirectWebUtilities.getStrDoubleWithScale(priceChange, 1));

							double averagePrice = VNDirectWebUtilities.getDoubleValue(secInfo.getAveragePrice());
							double basicPrice = VNDirectWebUtilities.getDoubleValue(secInfo.getBasicPrice());
							if (averagePrice < basicPrice) {
								securitiesInfoForQuote.setColourStyle(1);
							} else if (averagePrice > basicPrice) {
								securitiesInfoForQuote.setColourStyle(2);
							}

							String percent = VNDirectWebUtilities.getStrDoubleWithScale2(percentChange);
							percent = percent == null ? "" : percent.trim();
							if (percent.length() > 0) {
								// percent = "(" + percent + "%)";
								securitiesInfoForQuote.setStrTodayChangePercent(percent);
							}
							// +++ TungNQ: add 24Mar2007
							securitiesInfoForQuote.setTodayChangePrice(percentChange);
							// ---
						} else {
							priceChange = VNDirectWebUtilities.getDoubleValue(secInfo.getChgIndex());
							percentChange = VNDirectWebUtilities.getDoubleValue(secInfo.getPctIndex());

							securitiesInfoForQuote.setStrTodayChange(VNDirectWebUtilities.getStrDoubleWithScale(
							        VNDirectWebUtilities.roundDouble(priceChange, 1), 1));

							double currentPrice = VNDirectWebUtilities.getDoubleValue(secInfo.getCurrentPrice());
							double priorClosePrice = VNDirectWebUtilities.getDoubleValue(secInfo.getPriorClosePrice());
							if (currentPrice < priorClosePrice) {
								securitiesInfoForQuote.setColourStyle(1);
							} else if (currentPrice > priorClosePrice) {
								securitiesInfoForQuote.setColourStyle(2);
							}

							String percent = VNDirectWebUtilities.getStrDoubleWithScale2(percentChange);
							percent = percent == null ? "" : percent.trim();
							if (percent.length() > 0) {
								// percent = "(" + percent + "%)";
								securitiesInfoForQuote.setStrTodayChangePercent(percent);
							}
							// +++ TungNQ: add 24Mar2007
							securitiesInfoForQuote.setTodayChangePrice(percentChange);
							// ---
						}

						securitiesInfoForQuote
						        .setStrBidP1(VNDirectWebUtilities.getStrDoubleWithScale(secInfo.getBidPrice01(), 1));
						securitiesInfoForQuote.setStrBidQ1(VNDirectWebUtilities.getStrDoubleWithScale(secInfo.getBidQtty01(), 0));
						securitiesInfoForQuote
						        .setStrBidP2(VNDirectWebUtilities.getStrDoubleWithScale(secInfo.getBidPrice02(), 1));
						securitiesInfoForQuote.setStrBidQ2(VNDirectWebUtilities.getStrDoubleWithScale(secInfo.getBidQtty02(), 0));
						securitiesInfoForQuote
						        .setStrBidP3(VNDirectWebUtilities.getStrDoubleWithScale(secInfo.getBidPrice03(), 1));
						securitiesInfoForQuote.setStrBidQ3(VNDirectWebUtilities.getStrDoubleWithScale(secInfo.getBidQtty03(), 0));

						securitiesInfoForQuote.setStrAskP1(VNDirectWebUtilities.getStrDoubleWithScale(secInfo.getOfferPrice01(),
						        1));
						securitiesInfoForQuote
						        .setStrAskQ1(VNDirectWebUtilities.getStrDoubleWithScale(secInfo.getOfferQtty01(), 0));
						securitiesInfoForQuote.setStrAskP2(VNDirectWebUtilities.getStrDoubleWithScale(secInfo.getOfferPrice02(),
						        1));
						securitiesInfoForQuote
						        .setStrAskQ2(VNDirectWebUtilities.getStrDoubleWithScale(secInfo.getOfferQtty02(), 0));
						securitiesInfoForQuote.setStrAskP3(VNDirectWebUtilities.getStrDoubleWithScale(secInfo.getOfferPrice03(),
						        1));
						securitiesInfoForQuote
						        .setStrAskQ3(VNDirectWebUtilities.getStrDoubleWithScale(secInfo.getOfferQtty03(), 0));

						// double totalVolume = 0;
						// totalVolume +=
						// VNDirectWebUtilities.getDoubleValue(secInfo.getBidQtty01());
						// totalVolume +=
						// VNDirectWebUtilities.getDoubleValue(secInfo.getBidQtty02());
						// totalVolume +=
						// VNDirectWebUtilities.getDoubleValue(secInfo.getBidQtty03());
						// totalVolume +=
						// VNDirectWebUtilities.getDoubleValue(secInfo.getOfferQtty01());
						// totalVolume +=
						// VNDirectWebUtilities.getDoubleValue(secInfo.getOfferQtty02());
						// totalVolume +=
						// VNDirectWebUtilities.getDoubleValue(secInfo.getOfferQtty03());
						securitiesInfoForQuote.setStrTotalVolumn(VNDirectWebUtilities.getStrDoubleWithScale(
						        secInfo.getTotalTradingQtty(), 0));

						securitiesInfoForQuote.setStrRecentTransactionP(VNDirectWebUtilities.getStrDoubleWithScale(
						        secInfo.getMatchPrice(), 1));
						securitiesInfoForQuote.setStrRecentTransactionQ(VNDirectWebUtilities.getStrDoubleWithScale(
						        secInfo.getMatchQtty(), 0));

						securitiesInfoForQuote.setStrClosedP(VNDirectWebUtilities.getStrDoubleWithScale(
						        secInfo.getCurrentPrice(), 1));
						securitiesInfoForQuote.setStrClosedQ(VNDirectWebUtilities.getStrDoubleWithScale(secInfo.getCurrentQtty(),
						        0));

						securitiesInfoForQuote.setStrTransactedVolume(VNDirectWebUtilities.getStrDoubleWithScale(
						        secInfo.getMatchQtty(), 0));

						securitiesInfoForQuote.setStrBidVolume(VNDirectWebUtilities.getStrDoubleWithScale(secInfo.getBidQtty01(),
						        0));
						securitiesInfoForQuote.setStrAskVolume(VNDirectWebUtilities.getStrDoubleWithScale(
						        secInfo.getOfferQtty01(), 0));

						securitiesInfoForQuote.setStrHighestPrice(VNDirectWebUtilities.getStrDoubleWithScale(
						        secInfo.getHighestPrice(), 1));
						securitiesInfoForQuote.setStrLowestPrice(VNDirectWebUtilities.getStrDoubleWithScale(
						        secInfo.getLowestPrice(), 1));

						// set secInfoDetails
						securitiesInfoForQuote.setStrSeesion1P(VNDirectWebUtilities.getStrDoubleWithScale(
						        securitiesHighLight[0].getSecSeesion1P(), 1));
						securitiesInfoForQuote.setStrSeesion1Q(VNDirectWebUtilities.getStrDoubleWithScale(
						        securitiesHighLight[0].getSecSeesion1Q(), 1));
						securitiesInfoForQuote.setStrSeesion2P(VNDirectWebUtilities.getStrDoubleWithScale(
						        securitiesHighLight[0].getSecSeesion2P(), 1));
						securitiesInfoForQuote.setStrSeesion2Q(VNDirectWebUtilities.getStrDoubleWithScale(
						        securitiesHighLight[0].getSecSeesion2Q(), 1));

						// update ATO, ATC
						// String HOSTC =
						// VNDirectWebUtilities.getHOSTCExchange();
						String floorCode = "";
						if (VNDirectWebUtilities.getHOSTCFloorCode() != null) {
							floorCode = VNDirectWebUtilities.getHOSTCFloorCode()[0];
						}
						floorCode = (floorCode == null ? "" : floorCode);
						if (floorCode.equalsIgnoreCase(secInfo.getFloorCode())) {
							// if
							// (HOSTC.equalsIgnoreCase(securitiesHighLight[0].getExchangeCode()))
							// {
							String session = "P";
							Date currentTime = DateUtils.getCurrentDate();
							if (VNDirectWebUtilities.getHOSTCMarketSessionTimeP1().isBetween(currentTime)) {
								session = "P1";
							} else if (VNDirectWebUtilities.getHOSTCMarketSessionTimeP2().isBetween(currentTime)) {
								session = "P2";
							} else if (VNDirectWebUtilities.getHOSTCMarketSessionTimeP3().isBetween(currentTime)) {
								session = "P3";
							}

							double bidP1 = VNDirectWebUtilities.getDoubleValue(secInfo.getBidPrice01());
							double bidQ1 = VNDirectWebUtilities.getDoubleValue(secInfo.getBidQtty01());
							double offerP1 = VNDirectWebUtilities.getDoubleValue(secInfo.getOfferPrice01());
							double offerQ1 = VNDirectWebUtilities.getDoubleValue(secInfo.getOfferQtty01());

							if ("P1".equalsIgnoreCase(session)) {
								if (bidP1 == 0 && bidQ1 != 0) {
									securitiesInfoForQuote.setStrBidP1("ATO");
								}
								if (offerP1 == 0 && offerQ1 != 0) {
									securitiesInfoForQuote.setStrAskP1("ATO");
								}
							} else if ("P3".equalsIgnoreCase(session)) {
								if (bidP1 == 0 && bidQ1 != 0) {
									securitiesInfoForQuote.setStrBidP1("ATC");
								}
								if (offerP1 == 0 && offerQ1 != 0) {
									securitiesInfoForQuote.setStrAskP1("ATC");
								}
							}
						}
					}
				}
			}
			if (logger.isDebugEnabled())
				logger.debug(LOCATION + ":: END");
			return securitiesInfoForQuote;
		} catch (RemoteException rex) {
			logger.error(LOCATION, rex);
			throw new FunctionalException(LOCATION, rex.getMessage(), Constants.ErrorKeys.IDG.REMOTE_ERROR,
			        new String[] { rex.getMessage() });
		} catch (ServiceException srvex) {
			logger.error(LOCATION, srvex);
			throw new FunctionalException(LOCATION, srvex.getMessage(), Constants.ErrorKeys.IDG.REMOTE_ERROR,
			        new String[] { srvex.getMessage() });
		} catch (FunctionalException fex) {
			logger.error(LOCATION, fex);
			throw fex;
		} catch (Exception ex) {
			logger.error(LOCATION, ex);
			throw new FunctionalException(LOCATION, Constants.ErrorKeys.Commons.SYSTEM_ERROR);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * vn.com.vndirect.business.IQuotesManager#getCompanySnapshotHighLightsForChart
	 * (vn.com.vndirect.wsclient.AuthenticationHeader,
	 * vn.com.vndirect.domain.extend.CurrentCompanyForQuote)
	 */
	public SecuritiesInfoForQuote getCompanySnapshotHighLightsForChart(AuthenticationHeader header,
	        CurrentCompanyForQuote criteriaObject) throws FunctionalException, SystemException {
		final String LOCATION = "getCompanySnapshotHighLightsForChart(header:" + header + ", criteriaObject:" + criteriaObject
		        + ")";
		if (logger.isDebugEnabled())
			logger.debug(LOCATION + ":: BEGIN");
		try {
			if (criteriaObject == null || criteriaObject.getCurrentExchangeCode() == null || criteriaObject.getSymbol() == null) {
				throw new SystemException(LOCATION, "criteriaObject is NULL or EMPTY... :: criteriaObject:" + criteriaObject);
			}

			String HOSTC = VNDirectWebUtilities.getHOSTCExchange();
			String VN30 = VNDirectWebUtilities.getVN30Exchange();
			String HNX30 = VNDirectWebUtilities.getHNX30Exchange();
			String HASTC = VNDirectWebUtilities.getHASTCExchange();

			String[] listSymbol = new String[0];

			SecuritiesInfoForQuote securitiesInfoForQuote = new SecuritiesInfoForQuote();

			if (HOSTC.equalsIgnoreCase(criteriaObject.getCurrentExchangeCode())) {
				if (VN30.equalsIgnoreCase(criteriaObject.getSymbol())) {
					listSymbol = new String[] { VNDirectWebUtilities.getVN30Index() };
				} else {
					listSymbol = new String[] { VNDirectWebUtilities.getHOSTCIndex() };
				}
			} else if (HASTC.equalsIgnoreCase(criteriaObject.getCurrentExchangeCode())) {
				if (HNX30.equalsIgnoreCase(criteriaObject.getSymbol())) {
					listSymbol = new String[] { VNDirectWebUtilities.getHNX30Index() };
				} else {
					listSymbol = new String[] { VNDirectWebUtilities.getHASTCIndex() };
				}
			} else {
				return securitiesInfoForQuote;
			}
			IStreamQuotesServicePortType iStreamQuotesService = getIStreamQuotesServicePortType();
			SecuritiesHighLightSearch securitiesHighLightSearch = new SecuritiesHighLightSearch();
			securitiesHighLightSearch.setListSymbol(listSymbol);

			SecuritiesHighLightSearchResult securitiesHighLightSearchResult = iStreamQuotesService.searchSecuritiesHighLight(
			        header, securitiesHighLightSearch);
			VNDSServiceUtils.processMessageStatus(securitiesHighLightSearchResult == null ? null
			        : securitiesHighLightSearchResult.getMsgStatus());

			if (securitiesHighLightSearchResult != null && securitiesHighLightSearchResult.getListSecuritiesHighLight() != null) {
				SecuritiesHighLight[] securitiesHighLight = securitiesHighLightSearchResult.getListSecuritiesHighLight();
				if (securitiesHighLight.length > 0) {
					vn.com.vndirect.wsclient.streamquotes.MarketInfo element = securitiesHighLight[0].getMarketInfo();
					securitiesInfoForQuote.setCurrentPrice(VNDirectWebUtilities.getDoubleValue(element.getMarketIndex()));
					securitiesInfoForQuote.setStrTransactionPrice(VNDirectWebUtilities.getStrDoubleWithScale(
					        element.getTotalValue(), 0));
					securitiesInfoForQuote.setStrTransactionVolume(VNDirectWebUtilities.getStrDoubleWithScale(
					        element.getTotalQtty(), 0));
					securitiesInfoForQuote.setStrMarketIndex(VNDirectWebUtilities.getStrDoubleWithScale(element.getMarketIndex(),
					        1));

					securitiesInfoForQuote
					        .setStrTodayChange(VNDirectWebUtilities.getStrDoubleWithScale(element.getChgIndex(), 1));
					securitiesInfoForQuote.setStrTodayChangePercent(VNDirectWebUtilities.getStrDoubleWithScale2(element
					        .getPctIndex()));

					// +++ TungNQ: add 24Mar2007
					securitiesInfoForQuote.setTodayChangePrice(VNDirectWebUtilities.getDoubleValue(element.getPctIndex()));
					// ---

					securitiesInfoForQuote.setStr52WeekHighestPrice(VNDirectWebUtilities.getStrDoubleWithScale(
					        securitiesHighLight[0].getPrice52WeekMarketHighest(), 1));
					securitiesInfoForQuote.setStr52WeekLowestPrice(VNDirectWebUtilities.getStrDoubleWithScale(
					        securitiesHighLight[0].getPrice52WeekMarketLowest(), 1));

				}
			}

			if (logger.isDebugEnabled())
				logger.debug(LOCATION + ":: END");
			return securitiesInfoForQuote;
		} catch (RemoteException rex) {
			logger.error(LOCATION, rex);
			throw new FunctionalException(LOCATION, rex.getMessage(), Constants.ErrorKeys.IDG.REMOTE_ERROR,
			        new String[] { rex.getMessage() });
		} catch (ServiceException srvex) {
			logger.error(LOCATION, srvex);
			throw new FunctionalException(LOCATION, srvex.getMessage(), Constants.ErrorKeys.IDG.REMOTE_ERROR,
			        new String[] { srvex.getMessage() });
		} catch (FunctionalException fex) {
			logger.error(LOCATION, fex);
			throw fex;
		} catch (Exception ex) {
			logger.error(LOCATION, ex);
			throw new FunctionalException(LOCATION, Constants.ErrorKeys.Commons.SYSTEM_ERROR);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * vn.com.vndirect.business.IQuotesManager#getCompanySnapshotInfo(vn.com
	 * .vndirect.domain.extend.CurrentCompanyForQuote)
	 */
	public IfoCompanySnapshotViewExt getCompanySnapshotInfo(CurrentCompanyForQuote criteriaObject) throws FunctionalException,
	        SystemException {
		final String LOCATION = "getCompanySnapshotInfo(criteriaObject:" + criteriaObject + ")";
		if (logger.isDebugEnabled())
			logger.debug(LOCATION + ":: BEGIN");

		if (criteriaObject == null || criteriaObject.getCompanyId() == null || criteriaObject.getCurrentExchangeCode() == null
		        || criteriaObject.getSymbol() == null) {
			throw new SystemException(LOCATION, "criteriaObject is NULL or EMPTY... :: criteriaObject:" + criteriaObject);
		}

		try {
			IfoCompanySnapshotViewExt obj = ifoCompanySnapshotViewDAO.findByCurrentCompany(criteriaObject);
			if (logger.isDebugEnabled())
				logger.debug(LOCATION + ":: END");
			return obj;
		} catch (SystemException sysex) {
			throw sysex;
		} catch (Exception ex) {
			logger.error(LOCATION, ex);
			throw new FunctionalException(LOCATION, Constants.ErrorKeys.Commons.SYSTEM_ERROR);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * vn.com.vndirect.business.IQuotesManager#getCurrentCompanyForQuote(vn.
	 * com.vndirect.domain.IfoCompanyNameViewId)
	 */
	public CurrentCompanyForQuote getCurrentCompanyForQuote(IfoCompanyNameViewId ifoCompanyNameViewId)
	        throws FunctionalException, SystemException {
		final String LOCATION = "getCurrentCompanyForQuote(ifoCompanyNameViewId:" + ifoCompanyNameViewId + ")";
		if (logger.isDebugEnabled())
			logger.debug(LOCATION + ":: BEGIN");
		if (ifoCompanyNameViewId == null || ifoCompanyNameViewId.getCompanyId() == null) {
			throw new SystemException(LOCATION, "Parameters are NULL...");
		}

		try {
			IfoCompanyNameView companyNameObj = ifoCompanyNameViewDAO.findByCompanyId(ifoCompanyNameViewId);
			if (logger.isDebugEnabled())
				logger.debug("+++>>> companyNameObj:" + companyNameObj);

			long companyId = ifoCompanyNameViewId.getCompanyId().longValue();
			List listIfoStockExchange = ifoStockExchangeViewDAO.findByCompanyId(companyId);
			if (logger.isDebugEnabled())
				logger.debug("+++>>> listIfoStockExchange:" + listIfoStockExchange);

			CurrentCompanyForQuote currentObj = new CurrentCompanyForQuote(companyNameObj, listIfoStockExchange);

			// List listIfoCompanyIndustryView =
			// ifoCompanyIndustryViewDAO.findByCompanyId(companyId,
			// ifoCompanyNameViewId.getLocale());
			List listIfoCompanyIndustryView = ifoCompanyIndustryViewDAO.findByCompanyId(companyId, null);
			if (logger.isDebugEnabled())
				logger.debug("+++>>> listIfoCompanyIndustryView:" + listIfoCompanyIndustryView);
			currentObj.setListIfoCompanyIndustryView(listIfoCompanyIndustryView);

			if (logger.isDebugEnabled())
				logger.debug(LOCATION + ":: END");
			return currentObj;
		} catch (SystemException sysex) {
			throw sysex;
		} catch (Exception ex) {
			logger.error(LOCATION, ex);
			throw new FunctionalException(LOCATION, Constants.ErrorKeys.Commons.SYSTEM_ERROR);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see vn.com.vndirect.business.QuotesManager#searchIntradayPrice(vn.com.
	 * clientservices.domain.AuthenticationHeader,
	 * vn.com.clientservices.domain.quotes.IntradayPriceSearch)
	 */
	public IntradayPriceSearchResult searchIntradayPrice(AuthenticationHeader header, IntradayPriceSearch intradayPriceSearch)
	        throws FunctionalException, SystemException {
		final String LOCATION = "searchIntradayPrice(" + header + "intradayPriceSearch: " + intradayPriceSearch + ")";
		if (logger.isDebugEnabled())
			logger.debug(LOCATION + ":: BEGIN");

		if (header == null || intradayPriceSearch == null) {
			throw new SystemException(LOCATION, "Input data is NULL...");
		}
		try {
			IStreamQuotesServicePortType iStreamQuotesService = getIStreamQuotesServicePortType();
			IntradayPriceSearchResult result = iStreamQuotesService.searchIntradayPrice(header, intradayPriceSearch);
			VNDSServiceUtils.processMessageStatus(result == null ? null : result.getMsgStatus());
			if (logger.isDebugEnabled())
				logger.debug(LOCATION + ":: END");
			return result;
		} catch (RemoteException rex) {
			logger.error(LOCATION, rex);
			throw new FunctionalException(LOCATION, rex.getMessage(), Constants.ErrorKeys.IDG.REMOTE_ERROR,
			        new String[] { rex.getMessage() });
		} catch (ServiceException srvex) {
			logger.error(LOCATION, srvex);
			throw new FunctionalException(LOCATION, srvex.getMessage(), Constants.ErrorKeys.IDG.REMOTE_ERROR,
			        new String[] { srvex.getMessage() });
		} catch (FunctionalException fex) {
			logger.error(LOCATION, fex);
			throw fex;
		} catch (Exception ex) {
			logger.error(LOCATION, ex);
			throw new FunctionalException(LOCATION, Constants.ErrorKeys.Commons.SYSTEM_ERROR);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * vn.com.vndirect.business.IQuotesManager#getCompanyProfile(vn.com.vndirect
	 * .domain.IfoCompany)
	 */
	public Map getCompanyProfile(IfoCompany ifoCompany) throws FunctionalException, SystemException {
		final String LOCATION = "getCompanyProfile(ifoCompany:" + ifoCompany + ")";
		if (logger.isDebugEnabled())
			logger.debug(LOCATION + ":: BEGIN");

		Map result = new HashMap();

		if (ifoCompany == null || ifoCompany.getCompanyId() == null) {
			throw new SystemException(LOCATION, "ifoCompany is null");
		}

		try {
			IfoCompanyNameViewId ifoCompanyNameViewId = new IfoCompanyNameViewId();
			ifoCompanyNameViewId.setCompanyId(ifoCompany.getCompanyId());

			IfoCompanyNameView ifoCompanyNameView = ifoCompanyNameViewDAO.findByJustCompanyId(ifoCompanyNameViewId);

			if (ifoCompanyNameView != null) {
				ifoCompanyNameViewId = ifoCompanyNameView.getId();
			}

			result.put(Constants.COMPANY_PROFILE_ITEMS.COMPANY_NAME_VIEW, ifoCompanyNameViewId);

			IfoCompanyOfficersViewId ifoCompanyOfficersViewId = new IfoCompanyOfficersViewId();
			ifoCompanyOfficersViewId.setCompanyId(ifoCompany.getCompanyId());
			ifoCompanyOfficersViewId.setLocale(ifoCompany.getLocale());
			List officersList = ifoCompanyOfficersViewDAO.findByCompanyId(ifoCompanyOfficersViewId);

			result.put(Constants.COMPANY_PROFILE_ITEMS.COMPANY_OFFICE_VIEW, officersList);

			IfoSecIndexViewId ifoSecIndexViewId = new IfoSecIndexViewId();
			ifoSecIndexViewId.setSymbol(ifoCompanyNameViewId.getSymbol());
			IfoSecIndexView ifoSecIndexView = ifoSecIndexViewDAO.findBySymbol(ifoSecIndexViewId);

			if (ifoSecIndexView != null) {
				ifoSecIndexViewId = ifoSecIndexView.getId();
			}

			result.put(Constants.COMPANY_PROFILE_ITEMS.SEC_INDEX, ifoSecIndexViewId);

			IfoCompanyProfileViewId ifoCompanyProfileViewId = new IfoCompanyProfileViewId();
			ifoCompanyProfileViewId.setCompanyId(ifoCompany.getCompanyId());
			ifoCompanyProfileViewId.setLocale(ifoCompany.getLocale());
			IfoCompanyProfileView ifoCompanyProfileView = ifoCompanyProfileViewDAO.findByCompanyId(ifoCompanyProfileViewId);

			if (ifoCompanyProfileView != null) {
				ifoCompanyProfileViewId = ifoCompanyProfileView.getId();
			}

			result.put(Constants.COMPANY_PROFILE_ITEMS.COMPANY_PROFILE_VIEW, ifoCompanyProfileViewId);

			if (logger.isDebugEnabled())
				logger.debug(LOCATION + ":: END");
			return result;
		} catch (Exception e) {
			logger.error(LOCATION, e);
			throw new SystemException(LOCATION, e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * vn.com.vndirect.business.IQuotesManager#getKeyStatic(vn.com.vndirect.
	 * domain.IfoCompanyNameViewId)
	 */
	public Map getKeyStatic(IfoCompanyNameViewId ifoCompanyNameViewId) throws FunctionalException, SystemException {
		final String LOCATION = "getKeyStatic(ifoCompanyNameViewId:" + ifoCompanyNameViewId + ")";
		if (logger.isDebugEnabled())
			logger.debug(LOCATION + ":: BEGIN");

		Map result = new HashMap();

		if (ifoCompanyNameViewId == null || ifoCompanyNameViewId.getCompanyId() == null) {
			throw new SystemException(LOCATION, "ifoCompanyNameViewId is null");
		}

		try {

			IfoValuationMeasuresViewId ifoValuationMeasuresViewId = new IfoValuationMeasuresViewId();
			ifoValuationMeasuresViewId.setCompanyId(ifoCompanyNameViewId.getCompanyId());
			ifoValuationMeasuresViewId.setLocale(ifoCompanyNameViewId.getLocale());

			List ifoValuationMeasuresViewList = ifoValuationMeasuresViewDAO.findByCompanyId(ifoValuationMeasuresViewId);

			result.put(Constants.KEY_STATIC_ITEMS.VALUATION_MEASURES, ifoValuationMeasuresViewList);

			IfoKeyRatioViewId ifoKeyRatioViewId = new IfoKeyRatioViewId();
			ifoKeyRatioViewId.setCompanyId(ifoCompanyNameViewId.getCompanyId());
			ifoKeyRatioViewId.setLocale(ifoCompanyNameViewId.getLocale());
			List ifoKeyRatioViewList = ifoKeyRatioViewDAO.findByCompanyId(ifoKeyRatioViewId);

			result.put(Constants.KEY_STATIC_ITEMS.KEY_RATIO, ifoKeyRatioViewList);

			IfoFinancialHighlightViewId ifoFinancialHighlightViewId = new IfoFinancialHighlightViewId();
			ifoFinancialHighlightViewId.setCompanyId(ifoCompanyNameViewId.getCompanyId());
			ifoFinancialHighlightViewId.setLocale(ifoCompanyNameViewId.getLocale());
			List ifoFinancialHighlightViewList = ifoFinancialHighlightViewDAO.findByCompanyId(ifoFinancialHighlightViewId);

			if (ifoFinancialHighlightViewList == null) {
				// update for new required 9-Nov-06
				ifoFinancialHighlightViewList = new ArrayList();
			}

			result.put(Constants.KEY_STATIC_ITEMS.FINANCIAL_HIGHLIGHT, ifoFinancialHighlightViewList);

			IfoInvestorRightsViewId ifoInvestorRightsViewId = new IfoInvestorRightsViewId();
			ifoInvestorRightsViewId.setCompanyId(ifoCompanyNameViewId.getCompanyId());
			ifoInvestorRightsViewId.setLocale(ifoCompanyNameViewId.getLocale());
			List ifoInvestorRightsViewIdList = ifoInvestorRightsViewDAO.findByCompanyId(ifoInvestorRightsViewId);

			result.put(Constants.KEY_STATIC_ITEMS.TRADING_INFORMATION, ifoInvestorRightsViewIdList);

			if (logger.isDebugEnabled())
				logger.debug(LOCATION + ":: END");
			return result;
		} catch (Exception e) {
			logger.error(LOCATION, e);
			throw new SystemException(LOCATION, e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * vn.com.vndirect.business.IQuotesManager#getIfoEstimateView(vn.com.vndirect
	 * .domain.IfoEstimateView)
	 */
	public IfoEstimateView getIfoEstimateView(IfoEstimateView ifoEstimateView) throws FunctionalException, SystemException {
		final String LOCATION = "getIncomeViewInfo(IfoEstimateView:" + ifoEstimateView + ")";
		if (logger.isDebugEnabled())
			logger.debug(LOCATION + ":: BEGIN");

		if (ifoEstimateView == null || ifoEstimateView.getCompanyId() == 0) {
			throw new SystemException(LOCATION, "ifoEstimateView is NULL or EMPTY...");
		}
		try {
			IfoEstimateView searchResults = ifoValuationMeasuresViewDAO.getIfoEstimateView(ifoEstimateView);
			if (logger.isDebugEnabled())
				logger.debug(LOCATION + ":: END");
			return searchResults;
		} catch (SystemException sysex) {
			throw sysex;
		} catch (Exception e) {
			logger.error(LOCATION, e);
			throw new SystemException(LOCATION, e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * vn.com.vndirect.business.IQuotesManager#getCompanyBreakdown(vn.com.vndirect
	 * .domain.IfoCompanyNameViewId)
	 */
	public IfoBreakdownViewId getCompanyBreakdown(IfoCompanyNameViewId ifoCompanyNameViewId) throws FunctionalException,
	        SystemException {
		final String LOCATION = "getCompanyBreakdown(ifoCompanyNameViewId:" + ifoCompanyNameViewId + ")";
		if (logger.isDebugEnabled()) {
			logger.debug(LOCATION + ":: BEGIN");
		}
		if (ifoCompanyNameViewId == null || ifoCompanyNameViewId.getCompanyId() == null) {
			throw new SystemException(LOCATION, "ifoCompanyNameViewId is null");
		}
		try {
			if (logger.isDebugEnabled()) {
				logger.debug(LOCATION + ":: END");
			}
			IfoBreakdownViewId ifoBreakdownViewId = new IfoBreakdownViewId();
			ifoBreakdownViewId.setCompanyId(ifoCompanyNameViewId.getCompanyId());

			IfoBreakdownView ifoBreakdownView = ifoBreakdownViewDAO
			        .findByCompanyId(ifoBreakdownViewId.getCompanyId().longValue());

			if (ifoBreakdownView != null) {
				ifoBreakdownViewId = ifoBreakdownView.getId();
			}
			return ifoBreakdownViewId;
		} catch (Exception e) {
			logger.error(LOCATION, e);
			throw new SystemException(LOCATION, e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * vn.com.vndirect.business.IQuotesManager#getMajorHolders(vn.com.vndirect
	 * .domain.IfoCompanyNameViewId)
	 */
	public Map<String, Object> getMajorHolders(IfoCompanyNameViewId ifoCompanyNameViewId) throws FunctionalException,
	        SystemException {
		final String LOCATION = "getMajorHolders(ifoCompanyNameViewId:" + ifoCompanyNameViewId + ")";
		if (logger.isDebugEnabled()) {
			logger.debug(LOCATION + ":: BEGIN");
		}

		Map<String, Object> result = new HashMap<String, Object>();

		if (ifoCompanyNameViewId == null || ifoCompanyNameViewId.getCompanyId() == null) {
			throw new SystemException(LOCATION, "ifoCompanyNameViewId is null");
		}

		try {
			IfoBreakdownViewId ifoBreakdownViewId = new IfoBreakdownViewId();
			ifoBreakdownViewId.setCompanyId(ifoCompanyNameViewId.getCompanyId());

			IfoBreakdownView ifoBreakdownView = ifoBreakdownViewDAO
			        .findByCompanyId(ifoBreakdownViewId.getCompanyId().longValue());

			if (ifoBreakdownView != null) {
				ifoBreakdownViewId = ifoBreakdownView.getId();
			}

			result.put(Constants.MAJOR_HOLDERS_ITEMS.BREAK_DOWN, ifoBreakdownViewId);

			IfoMaijorHolderViewId ifoMaijorHolderViewId = new IfoMaijorHolderViewId();
			ifoMaijorHolderViewId.setCompanyId(ifoCompanyNameViewId.getCompanyId());
			ifoMaijorHolderViewId.setLocale(ifoCompanyNameViewId.getLocale());
			List<IfoMaijorHolderView> ifoMaijorHolderViewList = ifoMaijorHolderViewDAO.findByCompanyId(ifoMaijorHolderViewId);

			result.put(Constants.MAJOR_HOLDERS_ITEMS.MAJOR_HOLDERS, ifoMaijorHolderViewList);

			if (logger.isDebugEnabled()) {
				logger.debug(LOCATION + ":: END");
			}
			return result;
		} catch (Exception e) {
			logger.error(LOCATION, e);
			throw new SystemException(LOCATION, e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * vn.com.vndirect.business.IQuotesManager#getInsiderTransactions(vn.com
	 * .vndirect.domain.IfoCompanyNameViewId)
	 */
	public List<IfoInsiderTransactionView> getInsiderTransactions(IfoCompanyNameViewId ifoCompanyNameViewId)
	        throws FunctionalException, SystemException {
		final String LOCATION = "getInsiderTransactions(ifoCompanyNameViewId:" + ifoCompanyNameViewId + ")";
		if (logger.isDebugEnabled())
			logger.debug(LOCATION + ":: BEGIN");

		if (ifoCompanyNameViewId == null || ifoCompanyNameViewId.getCompanyId() == null) {
			throw new SystemException(LOCATION, "ifoCompanyNameViewId is null");
		}

		try {
			IfoInsiderTransactionViewId ifoInsiderTransactionViewId = new IfoInsiderTransactionViewId();
			ifoInsiderTransactionViewId.setCompanyId(ifoCompanyNameViewId.getCompanyId());
			ifoInsiderTransactionViewId.setLocale(ifoCompanyNameViewId.getLocale());
			List<IfoInsiderTransactionView> ifoInsiderTransactionViewList = ifoInsiderTransactionViewDAO
			        .findByCompanyId(ifoInsiderTransactionViewId);

			if (logger.isDebugEnabled())
				logger.debug(LOCATION + ":: END");
			return ifoInsiderTransactionViewList;
		} catch (Exception e) {
			logger.error(LOCATION, e);
			throw new SystemException(LOCATION, e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * vn.com.vndirect.business.IQuotesManager#quickSearchQuotes(java.lang.String
	 * , java.lang.String)
	 */
	@ReadThroughSingleCache(namespace = "QuotesManager.quickSearchQuotes@", expiration = EXPIRATION_TIME_FOR_CACHE)
	public CurrentCompanyForQuote quickSearchQuotes(@ParameterValueKeyProvider final String symbol, String locale) throws FunctionalException, SystemException {
		final String LOCATION = "quickSearchQuotes(symbol:" + symbol + ")";
		if (logger.isDebugEnabled())
			logger.debug(LOCATION + ":: BEGIN");

		if (symbol == null) {
			throw new SystemException(LOCATION, "symbol is null");
		}
		try {
			IfoCompanyNameView ifoCompName = ifoCompanyNameViewDAO.findBySymbol(symbol);

			CurrentCompanyForQuote currentObj = null;
			if (ifoCompName != null && ifoCompName.getId() != null) {
				IfoCompanyNameViewId ifoCompanyNameViewId = ifoCompName.getId();
				long companyId = ifoCompanyNameViewId.getCompanyId().longValue();

				List listIfoStockExchange = ifoStockExchangeViewDAO.findByCompanyId(companyId);
				if (logger.isDebugEnabled())
					logger.debug("+++>>> listIfoStockExchange:" + listIfoStockExchange);
				currentObj = new CurrentCompanyForQuote(ifoCompName, listIfoStockExchange);

				List listIfoCompanyIndustryView = ifoCompanyIndustryViewDAO.findByCompanyId(companyId, null);
				if (logger.isDebugEnabled())
					logger.debug("+++>>> listIfoCompanyIndustryView:" + listIfoCompanyIndustryView);
				currentObj.setListIfoCompanyIndustryView(listIfoCompanyIndustryView);

				// /++++
				String[] lstIndex = VNDirectWebUtilities.getIndexList();
				if (lstIndex != null && lstIndex.length > 0) {
					for (String idx : lstIndex) {
						if (idx.equalsIgnoreCase(symbol)) {
							currentObj.setExchange(true);
							break;
						}
					}
				}
			}

			if (logger.isDebugEnabled())
				logger.debug(LOCATION + ":: END");
			return currentObj;
		} catch (SystemException sysex) {
			throw sysex;
		} catch (Exception e) {
			logger.error(LOCATION, e);
			throw new SystemException(LOCATION, e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see vn.com.vndirect.business.IQuotesManager#getAllIfoExchange()
	 */
	public List<IfoExchangeCode> getAllIfoExchange() throws FunctionalException, SystemException {
		final String LOCATION = "getAllIfoExchange()";
		if (logger.isDebugEnabled())
			logger.debug(LOCATION + ":: BEGIN");
		try {
			List<IfoExchangeCode> lst = ifoExchangeCodeDAO.findAllExchangeCode();
			if (logger.isDebugEnabled())
				logger.debug(LOCATION + ":: END");
			return lst;
		} catch (SystemException sysex) {
			throw sysex;
		} catch (Exception e) {
			logger.error(LOCATION, e);
			throw new SystemException(LOCATION, e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * vn.com.vndirect.business.IQuotesManager#getAllIfoSectorCode(vn.com.vndirect
	 * .domain.IfoSectorCodeId)
	 */
	public List<IfoSectorCode> getAllIfoSectorCode(IfoSectorCodeId searchObj) throws FunctionalException, SystemException {
		final String LOCATION = "getAllIfoSectorCode()";
		if (logger.isDebugEnabled())
			logger.debug(LOCATION + ":: BEGIN");
		try {
			List<IfoSectorCode> lst = ifoSectorCodeDAO.find(searchObj);
			if (logger.isDebugEnabled())
				logger.debug(LOCATION + ":: END");
			return lst;
		} catch (SystemException sysex) {
			throw sysex;
		} catch (Exception e) {
			logger.error(LOCATION, e);
			throw new SystemException(LOCATION, e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * vn.com.vndirect.business.IQuotesManager#searchSymbol(vn.com.vndirect.
	 * domain.extend.SearchSymbol, vn.com.web.commons.domain.db.PagingInfo)
	 */
	public SearchResult searchSymbol(SearchSymbol searchObj, PagingInfo pagingInfo) throws FunctionalException, SystemException {
		final String LOCATION = "searchSymbol(searchObj:" + searchObj + " --- pagingInfo:" + pagingInfo + ")";
		if (logger.isDebugEnabled())
			logger.debug(LOCATION + ":: BEGIN");

		if (searchObj == null) {
			throw new SystemException(LOCATION, "ifoCompany is NULL...");
		}
		try {
			SearchResult result = ifoCompanyNameViewDAO.searchStockExchange(searchObj, pagingInfo);

			if (logger.isDebugEnabled())
				logger.debug(LOCATION + ":: END");
			return result;
		} catch (SystemException sysex) {
			throw sysex;
		} catch (Exception e) {
			logger.error(LOCATION, e);
			throw new SystemException(LOCATION, e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * vn.com.vndirect.business.IQuotesManager#getMostActiveCompany(vn.com.vndirect
	 * .domain.extend.MarketOption)
	 */
	public Map<String, List<CompanySummary>> getMostActiveCompany(MarketOption marketOption) throws FunctionalException,
	        SystemException {
		final String LOCATION = "getMostActiveCompany(marketOption:" + marketOption + ")";
		if (logger.isDebugEnabled())
			logger.debug(LOCATION + ":: BEGIN");
		try {
			marketOption = (marketOption == null ? new MarketOption() : marketOption);

			MarketOverviewSearch marketOverviewSearch = new MarketOverviewSearch();
			marketOverviewSearch.setForHastc(Boolean.valueOf(marketOption.isForHastc()));
			marketOverviewSearch.setForHostc(Boolean.valueOf(marketOption.isForHostc()));
			marketOverviewSearch.setForUpcom(Boolean.valueOf(marketOption.isForUpCom()));
			marketOverviewSearch.setSearchOption(marketOption.getOption());

			MarketOverviewSearchResult marketOverviewSearchResult = this.getIStreamQuotesServicePortType().searchMarketOverview(
			        getVndsAuthenticationHeader(), marketOverviewSearch);
			VNDSServiceUtils.processMessageStatus(marketOverviewSearchResult == null ? null : marketOverviewSearchResult
			        .getMsgStatus());

			Map<String, List<CompanySummary>> results = new HashMap<String, List<CompanySummary>>();
			if (marketOverviewSearchResult != null) {
				MarketOverview marketOverview;

				// process HASTC
				marketOverview = marketOverviewSearchResult.getHastcOverview();
				if (marketOverview != null) {
					results.put(Constants.MarketSummary.LIST_HASTC_COMPANY_SUMMARY,
					        processMostActiveData(marketOverview.getListCompanyOverview()));
				}

				// process HOSTC
				marketOverview = marketOverviewSearchResult.getHostcOverview();
				if (marketOverview != null) {
					results.put(Constants.MarketSummary.LIST_HOSTC_COMPANY_SUMMARY,
					        processMostActiveData(marketOverview.getListCompanyOverview()));
				}

				// process UPCOM
				marketOverview = marketOverviewSearchResult.getUpcomOverview();
				if (marketOverview != null) {
					results.put(Constants.MarketSummary.LIST_UPCOM_COMPANY_SUMMARY,
					        processMostActiveData(marketOverview.getListCompanyOverview()));
				}
			}

			return results;
		} catch (FunctionalException fex) {
			throw fex;
		} catch (Exception e) {
			logger.error(LOCATION, e);
			throw new FunctionalException(LOCATION, Constants.ErrorKeys.Commons.SYSTEM_ERROR);
		}
	}

	/**
	 * @param companyOverview
	 * @return
	 * @throws RemoteException
	 * @throws Exception
	 */
	private List<CompanySummary> processMostActiveData(CompanyOverview[] companyOverview) throws RemoteException, Exception {
		int size;
		List<CompanySummary> data = new ArrayList<CompanySummary>();
		List<String> indexData = new ArrayList<String>();

		size = companyOverview == null ? 0 : companyOverview.length;
		for (int i = 0; i < size; i++) {
			data.add(convertCompanyOverviewToCompanySummary(companyOverview[i]));
			indexData.add(companyOverview[i].getSymbol());
		}
		IntradayPriceSearch intradayPriceSearch = new IntradayPriceSearch();
		intradayPriceSearch.setListSymbols((String[]) indexData.toArray(new String[indexData.size()]));

		IntradayPriceSearchResult result = this.getIStreamQuotesServicePortType().searchIntradayPrice(
		        getVndsAuthenticationHeader(), intradayPriceSearch);

		vn.com.vndirect.wsclient.streamquotes.SecInfo[] secInfoArray = result.getListSecInfo();
		size = (secInfoArray == null ? 0 : secInfoArray.length);
		int index;
		CompanySummary _companySummary;
		vn.com.vndirect.wsclient.streamquotes.SecInfo secInfo;
		for (int i = 0; i < size; i++) {
			secInfo = secInfoArray[i];
			index = indexData.indexOf(secInfo.getCode());
			_companySummary = (CompanySummary) data.get(index);
			_companySummary.setHighestPrice(VNDirectWebUtilities.getDoubleValue(secInfo.getHighestPrice()));
			_companySummary.setLowestPrice(VNDirectWebUtilities.getDoubleValue(secInfo.getLowestPrice()));
			data.set(index, _companySummary);
		}
		return data;
	}

	/**
	 * 
	 * @param companyOverview
	 * @return CompanySummary
	 */
	private CompanySummary convertCompanyOverviewToCompanySummary(CompanyOverview companyOverview) {
		CompanySummary _companySummary = new CompanySummary();
		if (companyOverview != null) {
			_companySummary.setBasicPrice(VNDirectWebUtilities.getDoubleValue(companyOverview.getBasicPrice()));
			_companySummary.setLastPrice(VNDirectWebUtilities.getDoubleValue(companyOverview.getLastPrice()));
			_companySummary.setPercentChange(VNDirectWebUtilities.getDoubleValue(companyOverview.getPercentChange()));
			_companySummary.setPriceChange(VNDirectWebUtilities.getDoubleValue(companyOverview.getPriceChange()));
			_companySummary.setSecPrice(VNDirectWebUtilities.getDoubleValue(companyOverview.getSecPrice()));
			_companySummary.setVolume(VNDirectWebUtilities.getDoubleValue(companyOverview.getVolume()));
			_companySummary.setCompanyName(companyOverview.getCompanyName());
			_companySummary.setSymbol(companyOverview.getSymbol());
		}
		return _companySummary;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * vn.com.vndirect.business.IQuotesManager#getMarketOverview(vn.com.vndirect
	 * .domain.extend.MarketOption)
	 */
	public Map<String, Object> getMarketOverview(MarketOption marketOption) throws FunctionalException, SystemException {
		final String LOCATION = "getMarketOverview(marketOption:" + marketOption + ")";
		if (logger.isDebugEnabled()) {
			logger.debug(LOCATION + ":: BEGIN");
		}
		try {
			marketOption = (marketOption == null ? new MarketOption() : marketOption);

			MarketOverviewSearch marketOverviewSearch = new MarketOverviewSearch();
			marketOverviewSearch.setForHastc(Boolean.valueOf(marketOption.isForHastc()));
			marketOverviewSearch.setForHostc(Boolean.valueOf(marketOption.isForHostc()));
			marketOverviewSearch.setForVn30(Boolean.valueOf(marketOption.isForVn30()));
			marketOverviewSearch.setForHnx30(Boolean.valueOf(marketOption.isForHnx30()));
			marketOverviewSearch.setForOtc(Boolean.valueOf(marketOption.isForOtc()));
			marketOverviewSearch.setForUpcom(Boolean.valueOf(marketOption.isForUpCom()));
			marketOverviewSearch.setSearchOption(marketOption.getOption());

			MarketOverviewSearchResult marketOverviewSearchResult = this.getIStreamQuotesServicePortType().searchMarketOverview(
			        getVndsAuthenticationHeader(), marketOverviewSearch);

			VNDSServiceUtils.processMessageStatus(marketOverviewSearchResult == null ? null : marketOverviewSearchResult
			        .getMsgStatus());

			Map<String, Object> results = new HashMap<String, Object>();
			if (marketOverviewSearchResult != null) {
				int i, size;
				CompanyOverview[] companyOverview;
				Collection<CompanySummary> list;
				MarketOverview marketOverview;

				// process HASTC
				marketOverview = marketOverviewSearchResult.getHastcOverview();
				if (marketOverview != null) {
					results.put(Constants.MarketSummary.HASTC_MARKET_INFO, marketOverview.getMarketInfo());
					companyOverview = (marketOverview.getListCompanyOverview() == null ? null : marketOverview
					        .getListCompanyOverview());
					size = (companyOverview == null ? 0 : companyOverview.length);
					list = new ArrayList<CompanySummary>();
					for (i = 0; i < size; i++) {
						list.add(convertCompanyOverviewToCompanySummary(companyOverview[i]));
					}
					results.put(Constants.MarketSummary.LIST_HASTC_COMPANY_SUMMARY, list);
				}

				// process HOSTC
				marketOverview = marketOverviewSearchResult.getHostcOverview();
				if (marketOverview != null) {
					results.put(Constants.MarketSummary.HOSTC_MARKET_INFO, marketOverview.getMarketInfo());
					companyOverview = (marketOverview.getListCompanyOverview() == null ? null : marketOverview
					        .getListCompanyOverview());
					size = (companyOverview == null ? 0 : companyOverview.length);
					list = new ArrayList<CompanySummary>();
					for (i = 0; i < size; i++) {
						list.add(convertCompanyOverviewToCompanySummary(companyOverview[i]));
					}
					results.put(Constants.MarketSummary.LIST_HOSTC_COMPANY_SUMMARY, list);
				}

				// process VN30
				marketOverview = marketOverviewSearchResult.getVn30Overview();
				if (marketOverview != null) {
					results.put(Constants.MarketSummary.VN30_MARKET_INFO, marketOverview.getMarketInfo());
				}

				// process HNX30
				marketOverview = marketOverviewSearchResult.getHnx30Overview();
				if (marketOverview != null) {
					results.put(Constants.MarketSummary.HNX30_MARKET_INFO, marketOverview.getMarketInfo());
				}

				// process OTC
				marketOverview = marketOverviewSearchResult.getOtcOverview();
				if (marketOverview != null) {
					results.put(Constants.MarketSummary.OTC_MARKET_INFO, marketOverview.getMarketInfo());
					companyOverview = (marketOverview.getListCompanyOverview() == null ? null : marketOverview
					        .getListCompanyOverview());
					size = (companyOverview == null ? 0 : companyOverview.length);
					list = new ArrayList<CompanySummary>();
					for (i = 0; i < size; i++) {
						list.add(convertCompanyOverviewToCompanySummary(companyOverview[i]));
					}
					results.put(Constants.MarketSummary.LIST_OTC_COMPANY_SUMMARY, list);
				}

				// process UPCOM
				marketOverview = marketOverviewSearchResult.getUpcomOverview();
				if (marketOverview != null) {
					results.put(Constants.MarketSummary.UPCOM_MARKET_INFO, marketOverview.getMarketInfo());
					companyOverview = (marketOverview.getListCompanyOverview() == null ? null : marketOverview
					        .getListCompanyOverview());
					size = (companyOverview == null ? 0 : companyOverview.length);
					list = new ArrayList<CompanySummary>();
					for (i = 0; i < size; i++) {
						list.add(convertCompanyOverviewToCompanySummary(companyOverview[i]));
					}
					results.put(Constants.MarketSummary.LIST_UPCOM_COMPANY_SUMMARY, list);
				}
			}

			return results;
		} catch (FunctionalException fex) {
			throw fex;
		} catch (Exception e) {
			logger.error(LOCATION, e);
			throw new FunctionalException(LOCATION, Constants.ErrorKeys.Commons.SYSTEM_ERROR);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * vn.com.vndirect.business.IQuotesManager#quickSearchQuotes(java.lang.String
	 * , java.lang.String)
	 */
//	public CurrentCompanyForQuote quickSearchQuotes(String symbol, String locale) throws FunctionalException, SystemException {
//		final String LOCATION = "quickSearchQuotes(symbol:" + symbol + ", locale:" + locale + ")";
//		if (logger.isDebugEnabled())
//			logger.debug(LOCATION + ":: BEGIN");
//
//		if (symbol == null) {
//			throw new SystemException(LOCATION, "symbol is null");
//		}
//		try {
//			IfoCompanyNameView ifoCompName = ifoCompanyNameViewDAO.findBySymbol(symbol);
//
//			CurrentCompanyForQuote currentObj = null;
//			if (ifoCompName != null && ifoCompName.getId() != null) {
//				IfoCompanyNameViewId ifoCompanyNameViewId = ifoCompName.getId();
//				long companyId = ifoCompanyNameViewId.getCompanyId().longValue();
//
//				// IfoCompanyNameView companyNameObj =
//				// ifoCompanyNameViewDAO.findByCompanyId(ifoCompanyNameViewId);
//				// if (logger.isDebugEnabled())
//				// logger.debug("+++>>> companyNameObj:" + companyNameObj);
//
//				List listIfoStockExchange = ifoStockExchangeViewDAO.findByCompanyId(companyId);
//				if (logger.isDebugEnabled())
//					logger.debug("+++>>> listIfoStockExchange:" + listIfoStockExchange);
//
//				// currentObj = new CurrentCompanyForQuote(companyNameObj,
//				// listIfoStockExchange);
//				currentObj = new CurrentCompanyForQuote(ifoCompName, listIfoStockExchange);
//
//				// List listIfoCompanyIndustryView =
//				// ifoCompanyIndustryViewDAO.findByCompanyId(companyId, locale);
//				List listIfoCompanyIndustryView = ifoCompanyIndustryViewDAO.findByCompanyId(companyId, null);
//				if (logger.isDebugEnabled())
//					logger.debug("+++>>> listIfoCompanyIndustryView:" + listIfoCompanyIndustryView);
//				currentObj.setListIfoCompanyIndustryView(listIfoCompanyIndustryView);
//			}
//
//			if (logger.isDebugEnabled())
//				logger.debug(LOCATION + ":: END");
//			return currentObj;
//		} catch (SystemException sysex) {
//			throw sysex;
//		} catch (Exception e) {
//			logger.error(LOCATION, e);
//			throw new SystemException(LOCATION, e);
//		}
//	}

	public SearchResult<IfoSecPriceView> searchStockPrices(SearchIfoSecPrice searchObj, PagingInfo pagingInfo)
	        throws SystemException, FunctionalException {
		final String LOCATION = "searchStockPrices(searchObj:" + searchObj + ", searchObj:" + searchObj + ")";
		logger.debug(LOCATION + ":: BEGIN");

		if (searchObj == null) {
			throw new FunctionalException(LOCATION, "chartInfo or searchIfoSecPrice object is NULL or EMPTY ...");
		}

		try {
			SearchResult<IfoSecPriceView> listIfoSecPriceView = ifoSecPriceViewDAO.findStockPrices(searchObj, pagingInfo);

			logger.debug(LOCATION + ":: END - size: " + (listIfoSecPriceView == null ? 0 : listIfoSecPriceView.size()));
			return listIfoSecPriceView;
		} catch (SystemException e) {
			throw e;
		} catch (Exception e) {
			logger.error(LOCATION, e);
			throw new SystemException(LOCATION, e);
		}
	}

	public SearchResult<IfoIndexCalc> searchIndexOfSectorAndIndustry(IfoIndexCalc ifoIndexCalc) throws FunctionalException,
	        SystemException {
		final String LOCATION = "searchIndexOfSectorAndIndustry(ifoIndexCalc)";
		logger.debug(LOCATION + ":: BEGIN");

		if (ifoIndexCalc == null || ifoIndexCalc.getIndexCode() == null || ifoIndexCalc.getIndexCode().length() == 0) {
			throw new FunctionalException(LOCATION, "ifoIndexCalc object is NULL or EMPTY ...");
		}

		try {
			ifoIndexCalc.setItemCode("1000017");
			SearchResult<IfoIndexCalc> listIfoIndexCalc = ifoIndexCalcDAO.searchIndexCalcByItemAndIndexCode(ifoIndexCalc);

			logger.debug(LOCATION + ":: END - size: " + (listIfoIndexCalc == null ? 0 : listIfoIndexCalc.size()));
			return listIfoIndexCalc;
		} catch (SystemException e) {
			throw e;
		} catch (Exception e) {
			logger.error(LOCATION, e);
			throw new SystemException(LOCATION, e);
		}
	}

	public List fiscalYearList(IfoEstimateView ifoEstimateView) throws FunctionalException, SystemException {
		final String LOCATION = "fiscalYearList(" + ifoEstimateView + ")";
		if (logger.isDebugEnabled())
			logger.debug(LOCATION + ":: BEGIN");

		if (ifoEstimateView == null || Long.valueOf(ifoEstimateView.getCompanyId()) == null) {
			throw new SystemException(LOCATION, "criteriaObject is NULL or EMPTY...");
		}
		try {
			List results = ifoValuationMeasuresViewDAO.fiscalYearList(ifoEstimateView);

			if (logger.isDebugEnabled())
				logger.debug(LOCATION + ":: END");
			return results;
		} catch (SystemException sysex) {
			throw sysex;
		} catch (Exception e) {
			logger.error(LOCATION, e);
			throw new SystemException(LOCATION, e);
		}
	}
}
