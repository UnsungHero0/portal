package vn.com.vndirect.commons.utility;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;

/**
 * Create news url detail
 * 
 * @author minh.nguyen
 * 
 */
public class NewsUrlUtility {

	private static final String EVENT_NEWSTYPE = "Event";
	private static final String MAC_WORLD_NEWSTYPE = "MacWorld";
	private static final String MAC_VN_NEWSTYPE = "MacVN";
	private static final String EXPERT_NEWSTYPE = "Expert";
	private static final String DISCLOSURE_NEWSTYPE = "Disclousure"; // TODO : remember change to right spell (disclosure)
	private static final String VNDIRECT_NEWSTYPE = "VNDIRECT";
	private static final String DAILYREPORT = "DailyReport";
//	private static final String IPO = "IPO";
	private static final String CONTEXT_PATH = ServletActionContext.getRequest().getContextPath();

	public static String createUrl(String newsType, String newsHeader, Long newsId, String symbol) {
		StringBuilder url = new StringBuilder("");
		newsHeader = VNDirectWebUtilities.utf8ToAsciiUrl(StringUtils.trim(newsHeader));
		if (VNDIRECT_NEWSTYPE.equals(newsType)) {
			url.append(CONTEXT_PATH + "/vndirect/tin-vndirect/").append(newsHeader).append("-").append(newsId).append(".shtml");
		} else if (DISCLOSURE_NEWSTYPE.equals(newsType)) {
			url.append(CONTEXT_PATH + "/cong-bo-thong-tin/");
			url.append(StringUtils.isNotEmpty(symbol) ? symbol.toLowerCase() + "/" : "");
			url.append(newsHeader).append("-").append(newsId).append(".shtml");
		} else if (EXPERT_NEWSTYPE.equals(newsType)) {
			url.append(CONTEXT_PATH + "/y-kien-chuyen-gia/").append(newsHeader).append("-").append(newsId).append(".shtml");
		} else if (MAC_VN_NEWSTYPE.equals(newsType)) {
			url.append(CONTEXT_PATH + "/tin-trong-nuoc/").append(newsHeader).append("-").append(newsId).append(".shtml");
		} else if (MAC_WORLD_NEWSTYPE.equals(newsType)) {
			url.append(CONTEXT_PATH + "/tin-quoc-te/").append(newsHeader).append("-").append(newsId).append(".shtml");
		} else if (DAILYREPORT.equals(newsType)) {
			url.append(CONTEXT_PATH + "/nhan-dinh-thi-truong/").append(newsHeader).append("-").append(newsId).append(".shtml");
		} else if (EVENT_NEWSTYPE.equals(newsType)) {
			url.append(CONTEXT_PATH + "/news/company-event/").append(newsHeader).append("-").append(newsId).append(".shtml");
		} else if ("cong-bo-thong-tin".equals(newsType)) {
			url.append(CONTEXT_PATH + "/cong-bo-thong-tin/").append(newsHeader).append("-").append(newsId).append(".shtml");
		} else if ("IPO".equals(newsType)){
			url.append(CONTEXT_PATH + "/cong-bo-thong-tin/").append(newsHeader).append("-").append(newsId).append(".shtml");
		} else {
			url.append(CONTEXT_PATH + "/news/no-url/");
		} 

		return url.toString();
	}

	public static String createUrl(String pageUrl, String newsType, String newsHeader, Long newsId, String symbol) {
		StringBuilder url = new StringBuilder("");
		newsHeader = VNDirectWebUtilities.utf8ToAsciiUrl(StringUtils.trim(newsHeader));
		if (VNDIRECT_NEWSTYPE.equals(newsType)) {
			url.append(CONTEXT_PATH + "/vndirect/tin-vndirect/").append(newsHeader).append("-").append(newsId).append(".shtml");
		} else if (DISCLOSURE_NEWSTYPE.equals(newsType)) {
			url.append(CONTEXT_PATH + "/" + pageUrl + "/cong-bo-thong-tin/");
			url.append(StringUtils.isNotEmpty(symbol) ? symbol.toLowerCase() + "/" : "");
			url.append(newsHeader).append("-").append(newsId).append(".shtml");
		} else if (EXPERT_NEWSTYPE.equals(newsType)) {
			url.append(CONTEXT_PATH + "/y-kien-chuyen-gia/").append(newsHeader).append("-").append(newsId).append(".shtml");
		} else if (MAC_VN_NEWSTYPE.equals(newsType)) {
			url.append(CONTEXT_PATH + "/tin-trong-nuoc/").append(newsHeader).append("-").append(newsId).append(".shtml");
		} else if (MAC_WORLD_NEWSTYPE.equals(newsType)) {
			url.append(CONTEXT_PATH + "/tin-quoc-te/").append(newsHeader).append("-").append(newsId).append(".shtml");
		} else if (DAILYREPORT.equals(newsType)) {
			url.append(CONTEXT_PATH + "/nhan-dinh-thi-truong/").append(newsHeader).append("-").append(newsId).append(".shtml");
		} else if (EVENT_NEWSTYPE.equals(newsType)) {
			url.append(CONTEXT_PATH + "/" + pageUrl + "/tin-doanh-nghiep/").append(newsHeader).append("-").append(newsId).append(".shtml");
		} else {
			url.append(CONTEXT_PATH + "/news/no-url/");
		}

		return url.toString();
	}
}
