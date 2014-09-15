/**
 * 
 */
package vn.com.vndirect.web.tag;

import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.log4j.Logger;

import vn.com.vndirect.commons.utility.I18nContext;
import vn.com.web.commons.domain.db.PagingInfo;
import vn.com.web.commons.domain.db.PagingInfoItem;

/**
 * @author tungnq.nguyen
 * 
 */
public class ShowPagingTag extends TagSupport implements IWebTag {
	private static final long serialVersionUID = -2901318144923775674L;

	private static Logger logger = Logger.getLogger(ShowPagingTag.class);

	private PagingInfo pagingInfo;
	private boolean usingURLForNav = true;
	private boolean usingPageOverTotal = false;
	private boolean usingLastPage = false;
	private String navAction;
	protected I18nContext i18nCtx = I18nContext.newInstance();

	/**
	 * @return the pagingInfo
	 */
	public PagingInfo getPagingInfo() {
		return pagingInfo;
	}

	/**
	 * @param pagingInfo
	 *            the pagingInfo to set
	 */
	public void setPagingInfo(PagingInfo pagingInfo) {
		this.pagingInfo = pagingInfo;
	}

	/**
	 * @return the usingURLForNav
	 */
	public boolean isUsingURLForNav() {
		return usingURLForNav;
	}

	/**
	 * @param usingURLForNav
	 *            the usingURLForNav to set
	 */
	public void setUsingURLForNav(boolean usingURLForNav) {
		this.usingURLForNav = usingURLForNav;
	}

	/**
	 * @return the navAction
	 */
	public String getNavAction() {
		return navAction;
	}

	/**
	 * @param navAction
	 *            the navAction to set
	 */
	public void setNavAction(String navAction) {
		this.navAction = navAction;
	}

	private String buildNavAction(String func, int idx) {
		if (usingURLForNav) {
			// https://..../page.shtml?index={idx}
			return (func + idx);
		} else {
			// javascript:navGoto({idx})
			return (func + "(" + idx + ")");
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.jsp.tagext.TagSupport#doStartTag()
	 */
	public int doStartTag() throws JspException {
		int currentPageIndex = pagingInfo.getIndexPage();
		long totalPage = pagingInfo.getTotalPage();

		StringBuffer strNavBuf = new StringBuffer();

		String func = "";
		if (usingURLForNav) {
			func = navAction;
		} else {
			func = "javascript:" + navAction;
		}
		
		// show current page/ total pages
		if(totalPage > 1 && usingPageOverTotal == true){
			strNavBuf.append(i18nCtx.getText("Commons.Page") + " " + currentPageIndex + "/" + totalPage + "&nbsp;");
		}
		
		// show first (<<)
		if (currentPageIndex > 3) {
			strNavBuf.append("<span class=\"back\">");
			strNavBuf.append("<a href=\"").append(buildNavAction(func, 1)).append("\">").append("<<").append("</a>");

			strNavBuf.append("</span>");
		}

		// +++ show previous
		if (currentPageIndex > 1) {
			strNavBuf.append("<span class=\"back\">");
			strNavBuf.append("<a href=\"").append(buildNavAction(func, currentPageIndex - 1)).append("\">").append("<").append("</a>");

			strNavBuf.append("</span>");
		}

		// +++ show list items
		// <span class="index"><a href="#">1</a><a href="#">2</a><a href="#">3</a><a href="#">4</a><a href="#">5</a></span>

		strNavBuf.append("<span class=\"index\">");

		// +++ show ...
		if (currentPageIndex > 3) {
			strNavBuf.append(" ... ");
		}

		List<PagingInfoItem> listPagingItem = pagingInfo.getPagingItems();
		int pageIndex = 0;
		for (PagingInfoItem pagingInfoItem : listPagingItem) {
			pageIndex = pagingInfoItem.getIndex();
			if ((currentPageIndex == totalPage && (currentPageIndex == pageIndex + 4 || currentPageIndex == pageIndex + 3)) || (currentPageIndex == totalPage - 1 && currentPageIndex == pageIndex + 3)) {
				strNavBuf.append("<a href=\"").append(this.buildNavAction(func, pageIndex)).append("\" >").append(pagingInfoItem.getText()).append("</a>");
			}

			if (currentPageIndex - pageIndex < 3 && currentPageIndex - pageIndex > -3) {
				if (currentPageIndex == pageIndex) {
					/*
					 * strNavBuf.append("<a href=\"javascript:void(0);\" style=\"color:#8A8A8A\">") .append("<b>").append(pagingInfoItem.getText()).append("</b>") .append("</a>");
					 */
					strNavBuf.append("<a href=\"javascript:void(0);\" style=\"color:#8A8A8A\">").append("<b>").append(pagingInfoItem.getText()).append("</b>").append("</a>");

				} else {
					strNavBuf.append("<a href=\"").append(this.buildNavAction(func, pageIndex)).append("\" >").append(pagingInfoItem.getText()).append("</a>");
				}
			}

			if ((currentPageIndex == 1 && (pageIndex == 4 || pageIndex == 5)) || (currentPageIndex == 2 && pageIndex == 5)) {
				strNavBuf.append("<a href=\"").append(this.buildNavAction(func, pageIndex)).append("\" >").append(pagingInfoItem.getText()).append("</a>");

			}

			// if(pagingInfoItem.isSelected()) {
			// strNavBuf.append("<a href=\"javascript:void(0);\"")
			// .append("<b>")
			// .append(pagingInfoItem.getText())
			// .append("</b></a>");
			// } else {
			// strNavBuf.append("><a href=\"").append(this.buildNavAction(func, pagingInfoItem.getIndex())).append("\" >")
			// .append(pagingInfoItem.getText())
			// .append("</a>");
			// }
		}

		// +++ show ...
		if (totalPage > (currentPageIndex + 2)) {
			strNavBuf.append(" ... ");
		}

		strNavBuf.append("</span>");

		// +++ show next
		if (totalPage > currentPageIndex) {
			strNavBuf.append("<span class=\"next\">");
			strNavBuf.append("<a href=\"").append(buildNavAction(func, currentPageIndex + 1)).append("\">").append(">").append("</a>");
			strNavBuf.append("</span>");
		}
		
		// show last (>>)
		if (totalPage - 3 >= currentPageIndex && usingLastPage == true) {
			strNavBuf.append("<span class=\"next\">");
			strNavBuf.append("<a href=\"").append(buildNavAction(func, (int) totalPage)).append("\">").append(">>").append("</a>");
			strNavBuf.append("</span>");
		}

		try {
			pageContext.getOut().print(strNavBuf.toString());
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.debug("doStartTag()", e);
			}
		}
		return SKIP_BODY;
	}
	
	public boolean isUsingPageOverTotal() {
		return usingPageOverTotal;
	}

	public void setUsingPageOverTotal(boolean usingPageOverTotal) {
		this.usingPageOverTotal = usingPageOverTotal;
	}

	public boolean isUsingLastPage() {
		return usingLastPage;
	}

	public void setUsingLastPage(boolean usingLastPage) {
		this.usingLastPage = usingLastPage;
	}
}
