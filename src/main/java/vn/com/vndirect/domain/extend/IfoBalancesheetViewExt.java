/*--------------------------------------------------------------------------*
| Modification Logs:
| DATE          AUTHOR     DESCRIPTION
| ------------------------------------------------
| 6 Nov 2006      TungNQ     First Creation
 *--------------------------------------------------------------------------*/

package vn.com.vndirect.domain.extend;

import vn.com.vndirect.domain.IfoBalancesheetView;

/**
 * @author DucNM
 * 
 */
@SuppressWarnings("serial")
public class IfoBalancesheetViewExt extends IfoBalancesheetView {

	private String strNumericValue;

	private int numberItem;

	public String getStrNumericValue() {
		return strNumericValue;
	}

	public void setStrNumericValue(String strNumericValue) {
		this.strNumericValue = strNumericValue;
	}

	public int getNumberItem() {
		return numberItem;
	}

	public void setNumberItem(int numberItem) {
		this.numberItem = numberItem;
	}

}
