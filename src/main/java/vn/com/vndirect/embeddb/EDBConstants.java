package vn.com.vndirect.embeddb;

public abstract class EDBConstants {
	public interface Tables {
		String COMPANY_CALC_VIEW = "COMPANY_CALC_VIEW";
		String COMPANY_ITEM_CALC_VIEW = "COMPANY_ITEM_CALC_VIEW";

		String INDUSTRY_CALC_VIEW = "INDUSTRY_CALC_VIEW";
		String INDUSTRY_ITEM_CALC_VIEW = "INDUSTRY_ITEM_CALC_VIEW";

		String ITEM_CODE_REF = "ITEM_CODE_REF";
		String SECTOR_CALC_VIEW = "SECTOR_CALC_VIEW";
		String STOCK_EXCHANGE = "STOCK_EXCHANGE";

		String DB_EMBED_CONFIG = "DB_EMBED_CONFIG";

		interface Temp {
			String INDUSTRY_ITEM_CALC_VIEW = "INDUSTRY_ITEM_CALC_VIEW_TEMP";
			String COMPANY_ITEM_CALC_VIEW = "COMPANY_ITEM_CALC_VIEW_TEMP";
		}
	}

	/**
	 * 
	 * @author Blue9Frog
	 * 
	 */
	public interface DBEmbedConfig {
		public interface GROUP_CODE {
			String SYS_CFG = "SYS_CFG";
		}

		public interface ITEM_CODE {
			String INIT_DB = "INIT_DB";
		}
	}

	public interface GROUP_CODE {
		String INDUSTRY_ITEM_CALC_VIEW = "INDUSTRY_ITEM";
		String COMPANY_ITEM_CALC_VIEW = "COMPANY_ITEM";
		String INDUSTRY_CALC_VIEW = "INDUSTRY";
		String SECTOR_CALC_VIEW = "SECTOR";
	}

	/**
	 * 
	 * @author tungnq
	 * 
	 */
	public enum MappingItems {
		COMPANY_ID("company_id", "company_id", "company_id"), SYMBOL("symbol", "symbol", "symbol"), INDUSTRY_CODE("industry_code", "industry_code", "industry_code"), SECTOR_CODE("sector_code",
				"sector_code", "sector_code"), EXCHANGE_CODE("exchange_code", "exchange_code", "exchange_code"), COMPANY_NAME("company_name", "company_name", "company_name"), COMPANY_FULL_NAME(
				"company_full_name", "company_full_name", "company_full_name"), ABB_NAME("abb_name", "abb_name", "abb_name"), FIRST_TRADING_DATE("first_trading_date", "first_trading_date",
				"first_trading_date"), EXCHANGE_NAME("exchange_name", "exchange_name", "exchange_name"), INDUSTRY_GROUP_CODE("industry_group_code", "industry_group_code", "industry_group_code"), SECTOR_GROUP_CODE(
				"sector_group_code", "sector_group_code", "sector_group_code"), ITEM_NAME("item_name", "item_name", "item_name"), LOCALE("locale", "locale", "locale"),

		CLOSE_PRICE("close_price", "close_price", "close_price"), PCT_ABOVE_52_WEEK_LOW("pct_above_52_week_low", "pct_above_52_week_low", "pct_above_52_week_low"), PCT_BELOW_52_WEEK_HIGH(
				"pct_below_52_week_high", "pct_below_52_week_high", "pct_below_52_week_high"), VS_SMA_13_DAY("vs_sma_13_day", "vs_sma_13_day", "vs_sma_13_day"), VS_SMA_50_DAY("vs_sma_50_day",
				"vs_sma_50_day", "vs_sma_50_day"),

		BETA("mapping.beta", "51007", "f51007"), MARKET_CAP("mapping.market_cap", "51003", "f51003"), STOCK_PRICE_VS_INDEX_4_WEEKS("mapping.stock_price_vs_index_4_weeks", "1000007", "f1000007"), STOCK_PRICE_VS_INDEX_13_WEEKS(
				"mapping.stock_price_vs_index_13_weeks", "1000008", "f1000008"), STOCK_PRICE_VS_INDEX_26_WEEKS("mapping.stock_price_vs_index_26_weeks", "1000009", "f1000009"), STOCK_PRICE_VS_INDEX_52_WEEKS(
				"mapping.stock_price_vs_index_52_weeks", "1000010", "f1000010"), PE_RATIO("mapping.pe_ratio", "51009", "f51009"), PEG_RATIO("mapping.peg_ratio", "51010", "f51010"), PROFIT_MARGIN(
				"mapping.profit_margin", "53004", "f53004"), PRICE_SALE_RATIO("mapping.price_sale_ratio", "51011", "f51011"), PRICE_BOOK_RATIO("mapping.price_book_ratio", "51012", "f51012"), ROE(
				"mapping.roe", "52002", "f52002"), ROA("mapping.roa", "52001", "f52001"), EPS_GROWTH_MANUAL("mapping.eps_growth_manual", "53009", "f53009"), REVERNUE_GROWTH_MANUAL(
				"mapping.revernue_growth_manual", "53030", "f53030"), EPS_GROWTH_QUARTERLY("mapping.eps_growth_quarterly", "53039", "f53039"), REVERNUE_GROWTH_QUARTERLY(
				"mapping.revernue_growth_quarterly", "53033", "f53033"), DIVIDENT_GROWTH_5_YEARS("mapping.divident_growth_5_years", "53003", "f53003"), DIVIDENT_YEALD("mapping.divident_yeald",
				"51005", "f51005"), DIVIDENT_YEALD_4_INDUSTRY("mapping.divident_yeald_4_industry", "81005", "f81005"), _52_WEEK_HIGH("mapping.52_week_high", "51001", "f51001"), _52_WEEK_LOW(
				"mapping.52_week_low", "51002", "f51002"), _1_DAYS_CHG("mapping.1_days_chg", "1000001", "f1000001"), _5_DAYS_CHG("mapping.5_days_chg", "1000002", "f1000002"), _52_WEEK_CHG(
				"mapping.52_week_chg", "1000006", "f1000006"), PRICE_CHG_4_WEEKS("mapping.price_chg_4_weeks", "1000003", "f1000003"), HOSE_INDEX_4_WEEKS("mapping.hose_index_4_weeks", "1000003",
				"f1000003"), HASTC_INDEX_4_WEEKS("mapping.hastc_index_4_weeks", "1000003", "f1000003"), EPS_GROWTH_CFY("mapping.eps_growth_cfy", "53014", "f53014"), SALES_4_COMPANY(
				"mapping.sales_4_company", "21001", "f21001"), INCOME_4_COMPANY_12_MONTHS("mapping.income_4_company_12_months", "23000", "f23000"), SALES_4_INDUSTRY_12_MONTHS(
				"mapping.sales_4_industry_12_months", "81011", "f81011"), INCOME_4_INDUSTRY_12_MONTHS("mapping.income_4_industry_12_months", "83000", "f83000"), INCOME_GROWTH_4_COMPANY_12_MONTHS(
				"mapping.income_growth_4_company_12_months", "53050", "f53050"), SALES_GROWTH_4_COMPANY_12_MONTHS("mapping.sales_growth_4_company_12_months", "53030", "f53030"), INCOME_GROWTH_4_INDUSTRY_12_MONTHS(
				"mapping.income_growth_4_industry_12_months", "83050", "f83050"), SALES_GROWTH_4_INDUSTRY_12_MONTHS("mapping.sales_growth_4_industry_12_months", "83030", "f83030"), NET_PROFIT_MARGIN_4_COMPANY(
				"mapping.net_profit_margin_4_company", "53006", "f53006"), NET_PROFIT_MARGIN_4_INDUSTRY("mapping.net_profit_margin_4_industry", "83006", "f83006"), DEB_EQUIRY_RATIO_4_COMPANY(
				"mapping.deb_equiry_ratio_4_company", "56002", "f56002"), DEB_EQUIRY_RATIO_4_INDUSTRY("mapping.deb_equiry_ratio_4_industry", "86002", "f86002"), STOCK_PERFORMANCE_3_MONTHS_INDUSTRY(
				"mapping.stock_performance_3_months_industry", "1000014", "f1000014"), STOCK_PERFORMANCE_6_MONTHS_INDUSTRY("mapping.stock_performance_6_months_industry", "1000015", "f1000015"), STOCK_PERFORMANCE_12_MONTHS_INDUSTRY(
				"mapping.stock_performance_12_months_industry", "1000016", "f1000016"), FIRST_RESISTANCE_FOR_COMPANY("mapping.first_resistance_for_company", "1000012", "f1000012"), SECOND_RESISTANCE_FOR_COMPANY(
				"mapping.second_resistance_for_company", "1000013", "f1000013"), MARKET_CAP_4_INDUSTRY("mapping.market_cap_4_industry", "81003", "f81003"), ASSETS("mapping.assets", "82700", "f82700"), EQUITIES(
				"mapping.equities", "84000", "f84000"), ROA_4_INDUSTRY("mapping.roa_4_industry", "82001", "f82001"), ROE_4_INDUSTRY("mapping.roe_4_industry", "82002", "f82002"), EPS("mapping.eps",
				"53007", "f53007"), PRICE_EARNING_RATION("mapping.price_earning_ration", "81006", "f81006"), _3_MO_PRICE_CHANGE("mapping.3_mo_price_change", "1000004", "f1000004"), _6_MO_PRICE_CHANGE(
				"mapping.6_mo_price_change", "1000005", "f1000005"), EBITDA("mapping.ebitda", "53011", "f53011"), SHARE_OUTSTANDING("mapping.share_outstanding", "51004", "f51004"), AVG_VOLUME_10_DAYS(
				"mapping.avg_volume_10_days", "51016", "f51016"), SMA_13("mapping.sma_13", "1000011", "f1000011"), SMA_50("mapping.sma_50", "1000012", "f1000012"), SMA_200("mapping.sma_200",
				"1000013", "f1000013"), PE("mapping.pe", "51006", "f51006"), ASSETS_4_COMPANY("mapping.assets_4_company", "12700", "f12700"), EQUITIES_4_COMPANY("mapping.equities_4_company", "14100",
				"f14100");

		// +++ mapping key
		public String mappingKey;
		public String itemCode;
		public String fieldName;

		MappingItems(String mappingKey, String itemCode, String fieldName) {
			this.fieldName = fieldName;
			this.itemCode = itemCode;
			this.mappingKey = mappingKey;
		}
	};
}
