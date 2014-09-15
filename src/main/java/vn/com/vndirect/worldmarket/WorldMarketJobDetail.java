package vn.com.vndirect.worldmarket;

import org.apache.log4j.Logger;

public class WorldMarketJobDetail {
	private static Logger logger = Logger.getLogger(WorldMarketJobDetail.class);

	private WorldMarketManager worldMarketManager;

	/**
	 * @param worldMarketManager
	 *            the worldMarketManager to set
	 */
	public void setWorldMarketManager(WorldMarketManager worldMarketManager) {
		this.worldMarketManager = worldMarketManager;
	}

	public void checkWorldIndex() {
		if (worldMarketManager != null) {
			try {
				if (logger.isDebugEnabled()) {
					logger.debug("checkWorldIndex().........");
				}
				worldMarketManager.checkWorldIndex();
			} catch (Exception e) {
				logger.error("checkWorldIndex() - " + e);
			}
		}
	}

}
