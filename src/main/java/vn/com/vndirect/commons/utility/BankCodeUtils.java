package vn.com.vndirect.commons.utility;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.ApplicationContextEvent;
import org.springframework.orm.hibernate3.SpringSessionContext;
import org.springframework.web.SpringServletContainerInitializer;
import org.springframework.web.context.support.WebApplicationContextUtils;

import vn.com.vndirect.commons.web.AppContext;
import vn.com.vndirect.commons.web.AppContextHolder;
import vn.com.vndirect.domain.struts2.common.BankBranch;
import vn.com.vndirect.domain.struts2.common.Banks;
import vn.com.web.commons.exception.SystemException;
import vn.com.web.commons.utility.SpringUtils;

public class BankCodeUtils {
	private static Logger logger = Logger.getLogger(BankCodeUtils.class);
	public static Map<String, List<BankBranch>> listBranches;
	public static List<Banks> listBanks;
	private static final String BRANCH_ITEM = "branchitem";
	private static final String BANK_CODE = "bankcode";
	private static final String BRANCH_CODE = "branchcode";
	private static final String BRANCH_NAME = "branchname";
	private static final String BANK_ITEM = "bankitem";
	private static final String SHORT_NAME = "shortname";
	private static final String BANK_NAME = "bankname";
	private String urlBanklList;
	private String urlBranchList;
	
	
	public void initConfigbankService(){
		try {
			initBranchConfig();
			initBankConfig();
		} catch (Exception e) {
			logger.error("initConfigbankService(): " + e.getMessage());
		} 
	}
	
	
	public void initBranchConfig() throws JDOMException, IOException{
		InputStream io = Thread.currentThread().getContextClassLoader().getResourceAsStream(urlBranchList);
		SAXBuilder builder = new SAXBuilder();
		Document dom = builder.build(io);
		listBranches = loadBranchesConfig(dom);
	}
	
	public void initBankConfig() throws JDOMException, IOException{
		InputStream io = Thread.currentThread().getContextClassLoader().getResourceAsStream(urlBanklList);
		SAXBuilder builder = new SAXBuilder();
		Document dom = builder.build(io);
		listBanks = loadBankConfig(dom);
	}
	
	private static Map<String, List<BankBranch>> loadBranchesConfig (Document dom) {
		Map<String, List<BankBranch>> result = new HashMap<String, List<BankBranch>>();
		List<Element> branchItems = dom.getRootElement().getChildren(BRANCH_ITEM);
		if(branchItems != null){
			String bankCode = StringUtils.EMPTY;
			String oldBankCode = StringUtils.EMPTY;
			String branchCode = StringUtils.EMPTY;
			String branchName = StringUtils.EMPTY;
			List<BankBranch> listBranchs = new ArrayList<BankBranch>();
			for(int i=0, j=branchItems.size(); i < j; i++){
				bankCode = branchItems.get(i).getAttributeValue(BANK_CODE);
				if (i == 0) {
					oldBankCode = branchItems.get(i).getAttributeValue(BANK_CODE);
					branchCode = branchItems.get(i).getAttributeValue(BRANCH_CODE);
					branchName = branchItems.get(i).getAttributeValue(BRANCH_NAME);
					listBranchs.add(new BankBranch(branchCode, branchName));
				} else {
					if(bankCode.equalsIgnoreCase(oldBankCode)){
						branchCode = branchItems.get(i).getAttributeValue(BRANCH_CODE);
						branchName = branchItems.get(i).getAttributeValue(BRANCH_NAME);
						listBranchs.add(new BankBranch(branchCode, branchName));
					} else {
						result.put(oldBankCode, listBranchs);
						listBranchs = new ArrayList<BankBranch>();
						oldBankCode = bankCode;
						branchCode = branchItems.get(i).getAttributeValue(BRANCH_CODE);
						branchName = branchItems.get(i).getAttributeValue(BRANCH_NAME);
						listBranchs.add(new BankBranch(branchCode, branchName));
					}
				}
				
				if(i == j-1){
					result.put(bankCode, listBranchs);
				}
			}
		}
		return result;
	}
	
	private static List<Banks> loadBankConfig(Document dom) throws JDOMException, IOException{
		List<Banks> result = new ArrayList<Banks>();
		List<Element> bankItems = dom.getRootElement().getChildren(BANK_ITEM);
		for(Element e : bankItems){
			result.add(new Banks(e.getAttributeValue(BANK_CODE), e.getAttributeValue(SHORT_NAME), e.getAttributeValue(BANK_NAME)));
		}
		return result;
	}
	
	public static Banks getBanksByCode(String bankCode){
		Banks result = null;
		for(Banks bank : listBanks) {
			if(bank.getBankCode().equals(bankCode)) {
				result = bank;
				break;
			}
		}
		return result;
	}
	
	public static BankBranch getBankBranches(String bankCode, String branchCode) {
		BankBranch branch = null;
		List<BankBranch> branches = listBranches.get(bankCode);
		for(BankBranch item : branches) {
			if(item.getBranchCode().equalsIgnoreCase(branchCode)) {
				branch = item;
				break;
			}
		}
		return branch;
	}


	public String getUrlBranchList() {
		return urlBranchList;
	}

	public void setUrlBranchList(String urlBranchList) {
		this.urlBranchList = urlBranchList;
	}


	public String getUrlBanklList() {
		return urlBanklList;
	}


	public void setUrlBanklList(String urlBanklList) {
		this.urlBanklList = urlBanklList;
	}
}
