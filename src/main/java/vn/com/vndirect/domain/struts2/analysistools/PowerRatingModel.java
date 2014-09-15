package vn.com.vndirect.domain.struts2.analysistools;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import vn.com.vndirect.commons.utility.VNDirectWebUtilities;
import vn.com.vndirect.domain.BaseModel;
import vn.com.vndirect.domain.IfoPowerRatingBean;
import vn.com.vndirect.domain.extend.CurrentCompanyForQuote;

@SuppressWarnings("serial")
public class PowerRatingModel extends BaseModel {

	private List<IfoPowerRatingBean> topTenPrs;
	private List<IfoPowerRatingBean> upgradedTopTenPrs;
	private List<IfoPowerRatingBean> bottomTenPrs;
	private List<IfoPowerRatingBean> downgradedBottomTenPrs;
	private List<IfoPowerRatingBean> generalMarketPrs;

	private int todayRatedCodeNumber = 0;
	private double avgGeneralMarketPr = 0;
	private String mostRecentDate;

	private List<IfoPowerRatingBean> prs;

	private static String VNINDEX = "VNINDEX";
	private static String HNX = "HNX";

	private double firstLevel;
	private double secondLevel;
	private double thirdLevel;
	private double fourthLevel;
	private double fifthLevel;
	private double sixthLevel;

	private int level;
	private String watchSecCodeList;

	private List<IfoPowerRatingBean> levelPrs;

	private List<IfoPowerRatingBean> watchListPrs;

	private IfoPowerRatingBean currentCompanyPr;

	private int activeAccordIndex;

	private String symbol;

	private String existSymbol = "Y";

	private int generalRatingNumber;

	private CurrentCompanyForQuote currentComp;

	public List<IfoPowerRatingBean> getListOfPrs() {
		return prs;
	}

	public void setListOfPrs(List<IfoPowerRatingBean> prs) {
		this.prs = prs;
		generalRatingNumber = getGeneralRatingNumber();
	}

	private int getGeneralRatingNumber() {
		int result = 0;
		for (IfoPowerRatingBean item : prs) {
			if (result >= 2) {
				break;
			}
			if (VNINDEX.equalsIgnoreCase(item.getSymbol()) || HNX.equalsIgnoreCase(item.getSymbol())) {
				result++;
			}
		}
		return result;
	}

	public int getActiveAccordIndex() {
		return activeAccordIndex;
	}

	public void setActiveAccordIndex(int activeAccordIndex) {
		this.activeAccordIndex = activeAccordIndex;
	}

	public IfoPowerRatingBean getCurrentCompanyPr() {
		return currentCompanyPr;
	}

	public void initCurrentCompanyPr() {
		try {
			if (currentComp == null || currentComp.getSymbol() == null || prs == null) {
				return;
			}
			String currentSecCode = currentComp.getSymbol();
			for (IfoPowerRatingBean item : prs) {
				if (currentSecCode.equalsIgnoreCase(item.getSymbol())) {
					currentCompanyPr = item;
					break;
				}
			}
		} catch (Exception e) {
		}
	}

	public List<IfoPowerRatingBean> getWatchListPrs() {
		return watchListPrs;
	}

	private String getSecCodeFromCookie(String sample) {
		if (sample == null || sample.length() == 0)
			return "";
		int separatorPosition = sample.indexOf("|");
		if (separatorPosition > 0)
			return sample.substring(0, separatorPosition);
		else
			return "";
	}

	private String getRankFromCookie(String sample) {
		if (sample == null || sample.length() == 0)
			return "";
		int separatorPosition = sample.indexOf("|");
		if (separatorPosition > 0)
			return sample.substring(separatorPosition + 1);
		else
			return "";
	}

	public void setWatchListPrs() {

		if (watchSecCodeList.length() < 4) {
			watchListPrs = new ArrayList<IfoPowerRatingBean>();
			return;
		}

		List<String> secCodeList = Arrays.asList(watchSecCodeList.split(","));

		HashMap<String, String> secCodeMap = new HashMap<String, String>();
		for (String item : secCodeList) {
			secCodeMap.put(getRankFromCookie(item), getSecCodeFromCookie(item));

		}
		if (watchListPrs == null) {
			watchListPrs = new ArrayList<IfoPowerRatingBean>();
			List<IfoPowerRatingBean> tmpList = new ArrayList<IfoPowerRatingBean>();
			String tmpSecCode;
			for (int i = 1; i <= secCodeMap.size(); i++) {
				tmpSecCode = secCodeMap.get(String.valueOf(i));

				for (IfoPowerRatingBean item : prs) {
					if (item.getSymbol().equalsIgnoreCase(tmpSecCode)) {
						tmpList.add(item);
					}
				}
			}

			watchListPrs = tmpList;
		}
	}

	public void setLevelPrs() {
		int max = 0;
		int min = 0;

		if (level == 0) {
			max = 11;
			min = 7;
		}
		if (level == 1) {
			max = 8;
			min = 4;
		}
		if (level == 2) {
			max = 5;
			min = 0;
		}
		if (level == 3) {
			max = 1;
			min = -4;
		}
		if (level == 4) {
			max = -3;
			min = -7;
		}
		if (level == 5) {
			max = -6;
			min = -11;
		}
		if (levelPrs == null) {
			levelPrs = new ArrayList<IfoPowerRatingBean>();

			List<IfoPowerRatingBean> tmpList = new ArrayList<IfoPowerRatingBean>();
			for (IfoPowerRatingBean item : prs) {
				if (!VNINDEX.equalsIgnoreCase(item.getSymbol()) && !HNX.equalsIgnoreCase(item.getSymbol())
				        && (Integer.valueOf(item.getMark()) < max) && (Integer.valueOf(item.getMark()) > min)) {
					tmpList.add(item);
				}
			}

			levelPrs = tmpList;

		}

	}

	public List<IfoPowerRatingBean> getLevelPrs() {
		int max = 0;
		int min = 0;

		if (level == 0) {
			max = 11;
			min = 7;
		}
		if (level == 1) {
			max = 8;
			min = 4;
		}
		if (level == 2) {
			max = 5;
			min = 0;
		}
		if (level == 3) {
			max = 1;
			min = -4;
		}
		if (level == 5) {
			max = -3;
			min = -7;
		}
		if (level == 6) {
			max = -6;
			min = -11;
		}
		if (levelPrs == null) {
			levelPrs = new ArrayList<IfoPowerRatingBean>();

			for (IfoPowerRatingBean item : prs) {
				if (!!VNINDEX.equalsIgnoreCase(item.getSymbol()) && !HNX.equalsIgnoreCase(item.getSymbol())
				        && (Integer.valueOf(item.getMark()) < max) && (Integer.valueOf(item.getMark()) > min)) {
					levelPrs.add(item);
				}
			}
		}
		return levelPrs;
	}

	public List<IfoPowerRatingBean> getTopTenPrs() {
		if (topTenPrs == null) {
			topTenPrs = new ArrayList<IfoPowerRatingBean>();
			for (IfoPowerRatingBean item : prs) {
				if (topTenPrs.size() >= 10) {
					break;
				}
				if (!VNINDEX.equalsIgnoreCase(item.getSymbol()) && !HNX.equalsIgnoreCase(item.getSymbol())) {
					topTenPrs.add(item);
				}
			}
		}
		return topTenPrs;
	}

	public List<IfoPowerRatingBean> getUpgradedTopTenPrs() {
		if (upgradedTopTenPrs == null) {
			upgradedTopTenPrs = new ArrayList<IfoPowerRatingBean>();

			List<UpgradedPRBean> tmpList = new ArrayList<UpgradedPRBean>();
			for (IfoPowerRatingBean item : prs) {
				if (!VNINDEX.equalsIgnoreCase(item.getSymbol()) && !HNX.equalsIgnoreCase(item.getSymbol())) {
					int tmpPr = 0;
					if (item.getMarkB4days() == null || item.getMarkB4days().equals("-"))
						tmpPr = 100;
					else
						tmpPr = Integer.valueOf(item.getMarkB4days());
					tmpList.add(new UpgradedPRBean(Integer.valueOf(item.getMark()) - tmpPr, Double.valueOf(
					        Double.valueOf(item.getPctPriceChange()) * 100).intValue(), item));
				}
			}
			Collections.sort(tmpList);

			for (int i = 0; i < 10 && i < tmpList.size(); i++) {
				upgradedTopTenPrs.add(tmpList.get(i).getBean());
			}
		}
		return upgradedTopTenPrs;
	}

	public List<IfoPowerRatingBean> getBottomTenPrs() {
		if (bottomTenPrs == null) {
			bottomTenPrs = new ArrayList<IfoPowerRatingBean>();
			IfoPowerRatingBean aTmpBean;
			for (int i = prs.size() - 1; i >= 0; i--) {
				if (bottomTenPrs.size() >= 10) {
					break;
				}
				aTmpBean = prs.get(i);
				if (!VNINDEX.equalsIgnoreCase(aTmpBean.getSymbol()) && !HNX.equalsIgnoreCase(aTmpBean.getSymbol())) {
					bottomTenPrs.add(aTmpBean);
				}
			}
		}
		return bottomTenPrs;
	}

	// this class is used for comparing
	private class UpgradedPRBean implements Comparable<UpgradedPRBean> {

		int deltaMark;
		IfoPowerRatingBean bean;
		int priceChange;

		public UpgradedPRBean(int deltaMark, int priceChange, IfoPowerRatingBean bean) {
			super();
			this.deltaMark = deltaMark;
			this.priceChange = priceChange;
			this.bean = bean;
		}

		public int compareTo(UpgradedPRBean o) {
			return (o.deltaMark - this.deltaMark) * 10000 + (o.priceChange - this.priceChange);
		}

		public IfoPowerRatingBean getBean() {
			return bean;
		}
	}

	// this class is used for comparing
	private class DowngradedPRBean implements Comparable<DowngradedPRBean> {

		int deltaMark;
		IfoPowerRatingBean bean;
		int priceChange;

		public DowngradedPRBean(int deltaMark, int priceChange, IfoPowerRatingBean bean) {
			super();
			this.deltaMark = deltaMark;
			this.priceChange = priceChange;
			this.bean = bean;
		}

		public int compareTo(DowngradedPRBean o) {
			return (this.deltaMark - o.deltaMark) * 10000 + (this.priceChange - o.priceChange);

		}

		public IfoPowerRatingBean getBean() {
			return bean;
		}
	}

	public List<IfoPowerRatingBean> getDowngradedBottomTenPrs() {

		if (downgradedBottomTenPrs == null) {
			downgradedBottomTenPrs = new ArrayList<IfoPowerRatingBean>();
			List<DowngradedPRBean> tmpList = new ArrayList<DowngradedPRBean>();

			for (IfoPowerRatingBean item : prs) {
				if (!VNINDEX.equalsIgnoreCase(item.getSymbol()) && !HNX.equalsIgnoreCase(item.getSymbol())) {
					int tmpPr = 0;
					if (item.getMarkB4days() == null || item.getMarkB4days().equals("-")) {
						tmpPr = -100;
					} else {
						tmpPr = Integer.valueOf(item.getMarkB4days());
					}
					tmpList.add(new DowngradedPRBean(Integer.valueOf(item.getMark()) - tmpPr, Double.valueOf(
					        Double.valueOf(item.getPctPriceChange()) * 100).intValue(), item));
				}
			}
			Collections.sort(tmpList);

			for (int i = 0; i < 10 && i < tmpList.size(); i++) {
				downgradedBottomTenPrs.add(tmpList.get(i).getBean());
			}
		}
		return downgradedBottomTenPrs;
	}

	public List<IfoPowerRatingBean> getGeneralMarketPrs() {
		if (generalMarketPrs == null) {
			generalMarketPrs = new ArrayList<IfoPowerRatingBean>();
			for (IfoPowerRatingBean item : prs) {
				if (generalMarketPrs.size() == 2) {
					break;
				}
				if (VNINDEX.equalsIgnoreCase(item.getSymbol()) || HNX.equalsIgnoreCase(item.getSymbol())) {
					generalMarketPrs.add(item);
				}
			}
		}
		return generalMarketPrs;
	}

	public int getTodayRatedCodeNumber() {
		if (todayRatedCodeNumber == 0)
			todayRatedCodeNumber = prs.size() - 2 > 0 ? prs.size() - 2 : 0;
		return todayRatedCodeNumber;

	}

	public double getAvgGeneralMarketPr() {
		return avgGeneralMarketPr;
	}

	public String getStrAvgGeneralMarketPr() {
		if (avgGeneralMarketPr == 0) {
			double sum = 0;
			int index = 0;
			for (IfoPowerRatingBean item : prs) {
				if (!VNINDEX.equalsIgnoreCase(item.getSymbol()) && !HNX.equalsIgnoreCase(item.getSymbol())) {
					sum = sum + Double.valueOf(item.getMark());
					index++;
				}
			}
			if (index > 0)
				avgGeneralMarketPr = sum / index;
		}
		return VNDirectWebUtilities.getStrDoubleWithScale2(avgGeneralMarketPr);
	}

	// =======================================================================================

	public String getMostRecentDate() {
		if (prs.size() == 0)
			return "";
		if (mostRecentDate == null)
			mostRecentDate = prs.get(0).getStrTransDate();
		return mostRecentDate;
	}

	public double getFirstLevel() {

		if (prs.size() <= 2)
			return 0;
		if (firstLevel == 0) {
			double sum = 0;
			for (IfoPowerRatingBean item : prs) {
				if (!VNINDEX.equalsIgnoreCase(item.getSymbol()) && !HNX.equalsIgnoreCase(item.getSymbol())
				        && Integer.valueOf(item.getMark()) > 7) {
					sum++;
				}
			}
			firstLevel = sum * 100 / (prs.size() - generalRatingNumber);

		}
		return firstLevel;
	}

	public double getSecondLevel() {
		if (prs.size() <= 2)
			return 0;
		if (secondLevel == 0) {
			double sum = 0;
			for (IfoPowerRatingBean item : prs) {
				if (!VNINDEX.equalsIgnoreCase(item.getSymbol()) && !HNX.equalsIgnoreCase(item.getSymbol())
				        && Integer.valueOf(item.getMark()) > 4 && Integer.valueOf(item.getMark()) < 8) {
					sum++;
				}
			}
			secondLevel = sum * 100 / (prs.size() - generalRatingNumber);
		}
		return secondLevel;
	}

	public double getThirdLevel() {
		if (prs.size() <= 2)
			return 0;
		if (thirdLevel == 0) {
			double sum = 0;
			for (IfoPowerRatingBean item : prs) {
				if (!VNINDEX.equalsIgnoreCase(item.getSymbol()) && !HNX.equalsIgnoreCase(item.getSymbol())
				        && Integer.valueOf(item.getMark()) > 0 && Integer.valueOf(item.getMark()) < 5) {
					sum++;
				}
			}
			thirdLevel = sum * 100 / (prs.size() - generalRatingNumber);
		}
		return thirdLevel;
	}

	public double getFourthLevel() {
		if (prs.size() <= 2)
			return 0;
		if (fourthLevel == 0) {
			double sum = 0;
			for (IfoPowerRatingBean item : prs) {
				if (!VNINDEX.equalsIgnoreCase(item.getSymbol()) && !HNX.equalsIgnoreCase(item.getSymbol())
				        && Integer.valueOf(item.getMark()) > -4 && Integer.valueOf(item.getMark()) < 1) {
					sum++;
				}
			}
			fourthLevel = sum * 100 / (prs.size() - generalRatingNumber);
		}
		return fourthLevel;
	}

	public double getFifthLevel() {
		if (prs.size() <= 2)
			return 0;
		if (fifthLevel == 0) {
			double sum = 0;
			for (IfoPowerRatingBean item : prs) {
				if (!VNINDEX.equalsIgnoreCase(item.getSymbol()) && !HNX.equalsIgnoreCase(item.getSymbol())
				        && Integer.valueOf(item.getMark()) > -7 && Integer.valueOf(item.getMark()) < -3) {
					sum++;
				}
			}
			fifthLevel = sum * 100 / (prs.size() - generalRatingNumber);
		}
		return fifthLevel;
	}

	public double getSixthLevel() {
		if (prs.size() <= 2)
			return 0;
		if (sixthLevel == 0) {
			double sum = 0;
			for (IfoPowerRatingBean item : prs) {
				if (!VNINDEX.equalsIgnoreCase(item.getSymbol()) && !HNX.equalsIgnoreCase(item.getSymbol())
				        && Integer.valueOf(item.getMark()) < -6) {
					sum++;
				}
			}
			sixthLevel = sum * 100 / (prs.size() - generalRatingNumber);
		}
		return sixthLevel;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getWatchSecCodeList() {
		return watchSecCodeList;
	}

	public void setWatchSecCodeList(String watchSecCodeList) {
		this.watchSecCodeList = watchSecCodeList;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getExistSymbol() {
		return existSymbol;
	}

	public void setExistSymbol(String existSymbol) {
		this.existSymbol = existSymbol;
	}

	public CurrentCompanyForQuote getCurrentComp() {
		return currentComp;
	}

	public void setCurrentComp(CurrentCompanyForQuote currentComp) {
		this.currentComp = currentComp;
	}

}
