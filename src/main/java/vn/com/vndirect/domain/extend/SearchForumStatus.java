/*--------------------------------------------------------------------------*
| Modification Logs:
| DATE          AUTHOR     DESCRIPTION
| ------------------------------------------------
| 3 Oct 2006      TungNQ     First Creation
 *--------------------------------------------------------------------------*/

package vn.com.vndirect.domain.extend;

import vn.com.vndirect.domain.IfoCompanyNameView;

/**
 * @author DucNM
 * 
 */
@SuppressWarnings("serial")
public class SearchForumStatus extends IfoCompanyNameView {

	private Long forumStatus;

	/**
	 * @return the forumStatus
	 */
	public Long getForumStatus() {
		return forumStatus;
	}

	/**
	 * @param forumStatus
	 *            the forumStatus to set
	 */
	public void setForumStatus(Long forumStatus) {
		this.forumStatus = forumStatus;
	}

}
