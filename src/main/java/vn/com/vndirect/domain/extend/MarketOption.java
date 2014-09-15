/*--------------------------------------------------------------------------*
| Modification Logs:
| DATE          AUTHOR     DESCRIPTION
| ------------------------------------------------
| Oct 21, 2006      TungNQ     First Creation
 *--------------------------------------------------------------------------*/

package vn.com.vndirect.domain.extend;

import java.util.HashMap;
import java.util.Map;

import vn.com.vndirect.domain.BaseBean;

/**
 * @author tungnq
 * 
 */
@SuppressWarnings( { "unchecked", "serial" })
public class MarketOption extends BaseBean {
	public static String ACTIVE = "ACTIVE";
	public static String PERCENT_GAINERS = "PER_GAINERS";
	public static String PERCENT_LOSERS = "PER_LOSERS";
	public static String PRICE_GAINERS = "PRI_GAINERS";
	public static String PRICE_LOSERS = "PRI_LOSERS";

	private static Map mapNames = new HashMap();

	static {
		mapNames.put(ACTIVE, "Actives");
		mapNames.put(PERCENT_GAINERS, "% Gainers");
		mapNames.put(PERCENT_LOSERS, "% Losers ");
		mapNames.put(PRICE_GAINERS, "$ Gainer");
		mapNames.put(PRICE_LOSERS, "$ Losers");
	};

	private String option = ACTIVE;
	private boolean forHastc = true;
	private boolean forHostc = true;
	private boolean forVn30 = true;
	private boolean forHnx30 = true;
	private boolean forOtc = true;
	private boolean forUpCom = true;

	public boolean isForUpCom() {
		return forUpCom;
	}

	public void setForUpCom(boolean forUpCom) {
		this.forUpCom = forUpCom;
	}

	/**
	 * @return the option
	 */
	public String getOption() {
		return option;
	}

	/**
	 * @param option
	 *            the option to set
	 */
	public void setOption(String option) {
		this.option = option;
	}

	public String getOptionName() {
		String str = (String) mapNames.get(option);
		if (str == null) {
			option = ACTIVE;
			str = (String) mapNames.get(option);
		}
		return str;
	}

	public String toString() {
		return "MarketOption-[option:" + option + "]";
	}

	/**
	 * @return the forHastc
	 */
	public boolean isForHastc() {
		return forHastc;
	}

	/**
	 * @param forHastc
	 *            the forHastc to set
	 */
	public void setForHastc(boolean forHastc) {
		this.forHastc = forHastc;
	}

	/**
	 * @return the forHostc
	 */
	public boolean isForHostc() {
		return forHostc;
	}

	/**
	 * @param forHostc
	 *            the forHostc to set
	 */
	public void setForHostc(boolean forHostc) {
		this.forHostc = forHostc;
	}

	/**
	 * @return the forOtc
	 */
	public boolean isForOtc() {
		return forOtc;
	}

	/**
	 * @param forOtc
	 *            the forOtc to set
	 */
	public void setForOtc(boolean forOtc) {
		this.forOtc = forOtc;
	}

	public boolean isForVn30() {
		return forVn30;
	}

	public boolean isForHnx30() {
		return forHnx30;
	}

	public void setForVn30(boolean forVn30) {
		this.forVn30 = forVn30;
	}

	public void setForHnx30(boolean forHnx30) {
		this.forHnx30 = forHnx30;
	}
}
