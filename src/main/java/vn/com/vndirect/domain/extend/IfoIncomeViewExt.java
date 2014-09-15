/*--------------------------------------------------------------------------*
| Modification Logs:
| DATE          AUTHOR     DESCRIPTION
| ------------------------------------------------
| 6 Nov 2006      TungNQ     First Creation
 *--------------------------------------------------------------------------*/

package vn.com.vndirect.domain.extend;

import vn.com.vndirect.domain.IfoIncomeView;

/**
 * @author DucNM
 * 
 */
@SuppressWarnings("serial")
public class IfoIncomeViewExt extends IfoIncomeView {

	String strNumericValue;

	public String getStrNumericValue() {
		return strNumericValue;
	}

	public void setStrNumericValue(String strNumericValue) {
		this.strNumericValue = strNumericValue;
	}
}
