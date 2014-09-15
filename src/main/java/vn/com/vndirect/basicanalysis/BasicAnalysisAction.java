package vn.com.vndirect.basicanalysis;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import vn.com.vndirect.business.ICommonManager;
import vn.com.vndirect.business.IListedMarketManager;
import vn.com.vndirect.business.IQuotesManager;
import vn.com.vndirect.commons.i18n.I18NUtility;
import vn.com.vndirect.commons.utility.Constants;
import vn.com.vndirect.commons.utility.VNDirectDateUtils;
import vn.com.vndirect.domain.extend.CurrentCompanyForQuote;
import vn.com.vndirect.domain.extend.FinanceReportForQuote;
import vn.com.vndirect.domain.extend.IfoBalanceSheetSearch;
import vn.com.vndirect.domain.extend.IfoCompanySnapshotViewExt;
import vn.com.web.commons.exception.FunctionalException;
import vn.com.web.commons.exception.SystemException;
import vn.com.web.commons.servercfg.ServerConfig;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import edu.emory.mathcs.backport.java.util.Collections;

@SuppressWarnings("serial")
public class BasicAnalysisAction extends ActionSupport implements ModelDriven<BasicAnalysisModel> {

	private BasicAnalysisModel model = new BasicAnalysisModel();

	@Autowired
	private IQuotesManager quotesManager;

	@Autowired
	private ICommonManager commonManager;

	@Autowired
	private IListedMarketManager listedMarketManager;

	@Override
	public String execute() throws Exception {

		try {
			PasicAnalysis analysis = analysis(model.getSymbol());

			model.setPasicAnalysis(analysis);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return SUCCESS;
	}

	public String analysisByCanslim() throws Exception {

		String sym = model.getSymbol();
		double acceptableLastQuarter = Double.valueOf(model.getAcceptableLastQuarter());
		double acceptableSameQuarterLastYear = Double.valueOf(model.getAcceptableSameQuarterLastYear());

		PasicAnalysis analysis = new PasicAnalysis();
		CurrentCompanyForQuote companyQuote = quotesManager.quickSearchQuotes(sym, "VN");
		
		boolean result = false;
		if (companyQuote != null) {
			boolean passByEPS = checkByCanslimEPS(acceptableLastQuarter, acceptableSameQuarterLastYear, companyQuote);
			if(passByEPS){
				// TODO check loi nhuan
				List<String> incomeList = this.getIncomeList();
				
				result = true;
			}
		}
		
		model.setIsGoodEPSByCanslim(result);

		return SUCCESS;
	}

	private boolean checkByCanslimEPS(double acceptableLastQuarter, double acceptableSameQuarterLastYear,
			CurrentCompanyForQuote companyQuote) throws SystemException, FunctionalException {
		boolean result = false;
		FinanceReportForQuote quote = getFinanceList(companyQuote);
		if (quote != null) {
			List<Double> listEPS = toEPS(quote, getShareOutstanding(companyQuote));
			Double currentEPS = listEPS.get(0);
			double lastQuarterEPSRatio = ((currentEPS - listEPS.get(1)) / listEPS.get(1)) * 100;
			double sameQuarterLastYearEPSRatio = ((currentEPS - listEPS.get(4)) / listEPS.get(4)) * 100;
			result = (lastQuarterEPSRatio >= acceptableLastQuarter)
					&& (sameQuarterLastYearEPSRatio >= acceptableSameQuarterLastYear);
		}
		return result;
	}

	public String viewYearlyIncome() {
		try {
			List<String> incomeList = this.getIncomeList();
			PasicAnalysis analysis = new PasicAnalysis();
			analysis.setIncomeList(incomeList);

			model.setPasicAnalysis(analysis);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return SUCCESS;
	}

	public String viewHNXSymbols() {

		Object[] symbols = BasicAnalysisUtils.getListHnxSymbol().keySet().toArray();
		List<String> symbolList = new ArrayList<String>();
		for (int i = 0; i < symbols.length; i++) {
			symbolList.add((String) symbols[i]);
		}

		Collections.sort(symbolList);

		model.setHnxSymbols(symbolList);

		return SUCCESS;
	}

	public String viewHOSESymbols() {

		Object[] symbols = BasicAnalysisUtils.getListHoseSymbol().keySet().toArray();
		List<String> symbolList = new ArrayList<String>();
		for (int i = 0; i < symbols.length; i++) {
			symbolList.add((String) symbols[i]);
		}

		Collections.sort(symbolList);

		model.setHoseSymbols(symbolList);

		return SUCCESS;
	}

	private PasicAnalysis analysis(String sym) throws FunctionalException, SystemException {
		PasicAnalysis analysis = new PasicAnalysis();
		CurrentCompanyForQuote companyQuote = quotesManager.quickSearchQuotes(sym, "VN");
		if (companyQuote != null) {
			analysis.setCompanyQuote(companyQuote);
			FinanceReportForQuote quote = getFinanceList(companyQuote);
			if (quote != null) {
				List<Double> listEPS = toEPS(quote, getShareOutstanding(companyQuote));

				DecimalFormat df = new DecimalFormat("#.##");
				Double currentEPS = listEPS.get(0);
				List<String> epsIncreasePercentList = new ArrayList<String>();
				List<String> earningList = new ArrayList<String>();
				epsIncreasePercentList.add("0");
				earningList.add(df.format(currentEPS));
				for (int i = 1; i < listEPS.size(); i++) {
					epsIncreasePercentList.add(df.format(toIncreasePercent(currentEPS, listEPS.get(i))));
					earningList.add(df.format(listEPS.get(i)));
				}
				analysis.setEpsIncreasePercentList(epsIncreasePercentList);
				analysis.setEarningList(earningList);

				List<String> earningHeadList = new ArrayList<String>();
				earningHeadList.add(quote.getStrFiscalDate1());
				earningHeadList.add(quote.getStrFiscalDate2());
				earningHeadList.add(quote.getStrFiscalDate3());
				earningHeadList.add(quote.getStrFiscalDate4());
				earningHeadList.add(quote.getStrFiscalDate5());
				analysis.setEarningHeadList(earningHeadList);
			}
		}

		return analysis;
	}

	private Double getShareOutstanding(CurrentCompanyForQuote companyQuote) throws FunctionalException, SystemException {
		IfoCompanySnapshotViewExt ifoComSnapshotViewExt = quotesManager.getCompanySnapshotInfo(companyQuote);

		Double shareOutstanding = ifoComSnapshotViewExt.getId().getShareOutstanding();

		return shareOutstanding;
	}

	private FinanceReportForQuote getFinanceList(CurrentCompanyForQuote companyQuote) throws SystemException, FunctionalException {

		IfoBalanceSheetSearch searchObject = createCommonBalanceSheetSearch(companyQuote);

		return getIncomeAfterTax(searchObject);
	}

	private FinanceReportForQuote getIncomeAfterTax(IfoBalanceSheetSearch searchObject) throws FunctionalException,
			SystemException {
		List ifoIncomeList = listedMarketManager.getIncomeViewInfo(searchObject);
		FinanceReportForQuote quote = null;
		for (int i = 0; i < ifoIncomeList.size(); i++) {
			quote = (FinanceReportForQuote) ifoIncomeList.get(i);
			if ("Lợi nhuận sau thuế thu nhập doanh nghiệp".equals(quote.getItemName())) {
				break;
			}
			quote = null;
		}

		return quote;
	}

	private String getYearlyIncomeAfterTax(IfoBalanceSheetSearch searchObject) throws FunctionalException, SystemException {

		FinanceReportForQuote quote = getIncomeAfterTax(searchObject);
		if (quote == null) {
			return "";
		}

		Double result = (toDouble(StringUtils.isNotEmpty(quote.getStrNumericValue1()) ? quote.getStrNumericValue1() : "0")
				+ toDouble(StringUtils.isNotEmpty(quote.getStrNumericValue2()) ? quote.getStrNumericValue2() : "0")
				+ toDouble(StringUtils.isNotEmpty(quote.getStrNumericValue3()) ? quote.getStrNumericValue3() : "0") + toDouble(StringUtils
				.isNotEmpty(quote.getStrNumericValue4()) ? quote.getStrNumericValue4() : "0")) / 1000000D;

		DecimalFormat df = new DecimalFormat();

		return df.format(result);
	}

	/**
	 * Vốn chủ sở hữu
	 */
	private FinanceReportForQuote getOwnerEqity(IfoBalanceSheetSearch searchObject) throws FunctionalException, SystemException {
		List financeInfoList = listedMarketManager.getBalancesheetViewInfo(searchObject);
		FinanceReportForQuote quote = null;
		for (int i = 0; i < financeInfoList.size(); i++) {
			quote = (FinanceReportForQuote) financeInfoList.get(i);
			if ("Vốn chủ sở hữu".equals(quote.getItemName())) {
				break;
			}
			quote = null;
		}

		return quote;
	}

	private IfoBalanceSheetSearch createCommonBalanceSheetSearch(CurrentCompanyForQuote companyQuote) throws FunctionalException,
			SystemException {
		HttpServletRequest request = ServletActionContext.getRequest();
		IfoBalanceSheetSearch searchObject = new IfoBalanceSheetSearch();
		searchObject.setCompanyId(companyQuote.getCompanyId());
		searchObject.setLocale(I18NUtility.getCurrentLocale(request.getSession()));

		List listYear = listedMarketManager.fiscalYearListIncomeStatement(searchObject);
		if (listYear == null || listYear.size() == 0) {
			listYear.add(VNDirectDateUtils.getCurrentYear());
		}
		searchObject.setFiscalYear((String) listYear.get(0));

		if (searchObject.getFiscalQuarter() == null || searchObject.getFiscalQuarter().trim().length() == 0) {
			searchObject.setFiscalQuarter(listedMarketManager.fiscalQuarterBalancesheet(searchObject));
		}

		String reportType;
		if ("IN_YEAR".equals(searchObject.getFiscalQuarter())) {
			reportType = ServerConfig.getOnlineValue(Constants.IServerConfig.ReportType.ANNUAL);
			searchObject.setAnnual(true);
		} else {
			reportType = ServerConfig.getOnlineValue(Constants.IServerConfig.ReportType.QUARTER);
		}
		searchObject.setReportType(reportType);

		return searchObject;
	}

	private List<String> getROEList() throws Exception {

		List<String> roeList = new ArrayList<String>();

		CurrentCompanyForQuote companyQuote = quotesManager.quickSearchQuotes(model.getSymbol(), "VN");
		IfoBalanceSheetSearch searchObject = createCommonBalanceSheetSearch(companyQuote);
		searchObject.setFiscalQuarter("Q4");

		try {
			searchObject.setFiscalYear("2013");
			roeList.add(getROE(searchObject));

			searchObject.setFiscalYear("2012");
			roeList.add(getROE(searchObject));

			searchObject.setFiscalYear("2011");
			roeList.add(getROE(searchObject));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return roeList;
	}

	private List<String> getIncomeList() throws Exception {

		List<String> incomeList = new ArrayList<String>();

		CurrentCompanyForQuote companyQuote = quotesManager.quickSearchQuotes(model.getSymbol(), "VN");
		IfoBalanceSheetSearch searchObject = createCommonBalanceSheetSearch(companyQuote);
		searchObject.setFiscalQuarter("Q4");

		try {
			searchObject.setFiscalYear("2013");
			incomeList.add(getYearlyIncomeAfterTax(searchObject));

			searchObject.setFiscalYear("2012");
			incomeList.add(getYearlyIncomeAfterTax(searchObject));

			searchObject.setFiscalYear("2011");
			incomeList.add(getYearlyIncomeAfterTax(searchObject));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return incomeList;
	}

	private String getROE(IfoBalanceSheetSearch searchObject) throws FunctionalException, SystemException {
		FinanceReportForQuote quote = null;
		quote = this.getIncomeAfterTax(searchObject);
		double totalIncome = toDouble(StringUtils.isNotEmpty(quote.getStrNumericValue1()) ? quote.getStrNumericValue1() : "0")
				+ toDouble(StringUtils.isNotEmpty(quote.getStrNumericValue2()) ? quote.getStrNumericValue2() : "0")
				+ toDouble(StringUtils.isNotEmpty(quote.getStrNumericValue3()) ? quote.getStrNumericValue3() : "0")
				+ toDouble(StringUtils.isNotEmpty(quote.getStrNumericValue4()) ? quote.getStrNumericValue4() : "0");

		quote = this.getOwnerEqity(searchObject);
		double averageEquity = (toDouble(StringUtils.isNotEmpty(quote.getStrNumericValue1()) ? quote.getStrNumericValue1() : "0")
				+ toDouble(StringUtils.isNotEmpty(quote.getStrNumericValue2()) ? quote.getStrNumericValue2() : "0")
				+ toDouble(StringUtils.isNotEmpty(quote.getStrNumericValue3()) ? quote.getStrNumericValue3() : "0") + toDouble(StringUtils
				.isNotEmpty(quote.getStrNumericValue4()) ? quote.getStrNumericValue4() : "0")) / 4;

		DecimalFormat df = new DecimalFormat("#.##");
		String roe = df.format(totalIncome / averageEquity * 100);

		return roe;
	}

	public String viewROE() {
		try {
			List<String> roeList = this.getROEList();
			PasicAnalysis analysis = new PasicAnalysis();
			analysis.setRoeList(roeList);

			model.setPasicAnalysis(analysis);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return SUCCESS;
	}

	private Double toIncreasePercent(Double curr, Double old) {
		return (curr - old) / old * 100;
	}

	private List<Double> toEPS(FinanceReportForQuote quote, double s) {
		List<Double> listEPS = new ArrayList<Double>();

		Double ep1 = toDouble(StringUtils.isNotEmpty(quote.getStrNumericValue1()) ? quote.getStrNumericValue1() : "0");
		Double ep2 = toDouble(StringUtils.isNotEmpty(quote.getStrNumericValue2()) ? quote.getStrNumericValue2() : "0");
		Double ep3 = toDouble(StringUtils.isNotEmpty(quote.getStrNumericValue3()) ? quote.getStrNumericValue3() : "0");
		Double ep4 = toDouble(StringUtils.isNotEmpty(quote.getStrNumericValue4()) ? quote.getStrNumericValue4() : "0");
		Double ep5 = toDouble(StringUtils.isNotEmpty(quote.getStrNumericValue5()) ? quote.getStrNumericValue5() : "0");
		listEPS.add(ep1 / s);
		listEPS.add(ep2 / s);
		listEPS.add(ep3 / s);
		listEPS.add(ep4 / s);
		listEPS.add(ep5 / s);

		return listEPS;
	}

	private Double toDouble(String s) {
		return Double.valueOf(clearQuote(s)) * 1000000;
	}

	private String clearQuote(String s) {
		if (s.contains(",")) {
			s = s.replace(",", "");
		}

		return s;
	}

	public static void main(String[] args) {
		String s = "1,2324";
		BasicAnalysisAction action = new BasicAnalysisAction();
		System.out.println(action.clearQuote(s));
	}

	@Override
	public BasicAnalysisModel getModel() {
		return model;
	}

}
