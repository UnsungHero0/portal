/*--------------------------------------------------------------------------*
 | Modification Logs:
 | DATE        AUTHOR      DESCRIPTION
 | ------------------------------------------------
 | Mar 4, 2008   TungNQ     First generate code
 *--------------------------------------------------------------------------*/

package vn.com.vndirect.domain;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import vn.com.vndirect.commons.i18n.I18NUtility;
import vn.com.vndirect.wsclient.tradingideas.SaveSearch;
import vn.com.web.commons.utility.ObjectEncoder;
import vn.com.web.commons.utility.Validation;

/**
 * @author tungnq.nguyen
 * 
 */

@SuppressWarnings( { "unchecked", "serial" })
public class SearchStockScreenerBean implements Serializable {

	private String asset_class_1;
	private String asset_class_2;
	private String asset_class_3;
	private String asset_class_4;
	private String component_1;
	private String component_2;
	private String component_3;
	private String sector;
	private String industry;
	private String share_price_from;
	private String share_price_to;
	private String price_change_from;
	private String price_change_1;
	private String price_change_2;
	private String price_change_3;
	private String price_change_4;
	private String price_change_5;
	private String price_performance_1;
	private String price_performance_2;
	private String price_performance_3;
	private String price_performance_4;
	private String price_performance_5;
	private String price_performance_6;
	private String over_1;
	private String over_2;
	private String over_3;
	private String average_volume_from;
	private String average_volume_to;
	private String pe_ratio_1;
	private String pe_ratio_2;
	private String pe_ratio_3;
	private String pe_ratio_4;
	private String pe_ratio_5;
	private String pe_ratio_6;
	private String pe_ratio_7;
	private String peg_1;
	private String peg_2;
	private String profit_margin_1;
	private String profit_margin_2;
	private String profit_margin_3;
	private String profit_margin_4;
	private String profit_margin_5;
	private String profit_margin_6;
	private String profit_margin_and_1;
	private String profit_margin_and_2;
	private String price_sale_ratio_1;
	private String price_sale_ratio_2;
	private String price_sale_ratio_3;
	private String price_sale_ratio_4;
	private String price_sale_ratio_5;
	private String price_sale_ratio_6;
	private String price_sale_ratio_7;
	private String price_book_ratio_1;
	private String price_book_ratio_2;
	private String price_book_ratio_3;
	private String price_book_ratio_4;
	private String price_book_ratio_5;
	private String return_equity_1;
	private String return_equity_2;
	private String return_equity_3;
	private String return_equity_4;
	private String return_equity_5;
	private String return_equity_and_1;
	private String return_equity_and_2;
	private String return_asset_1;
	private String return_asset_2;
	private String return_asset_3;
	private String return_asset_4;
	private String return_asset_5;
	private String return_asset_and_1;
	private String return_asset_and_2;
	private String eps_growth_annual_1;
	private String eps_growth_annual_2;
	private String eps_growth_annual_3;
	private String eps_growth_annual_4;
	private String eps_growth_annual_5;
	private String eps_growth_annual_6;
	private String eps_growth_annual_7;
	private String eps_growth_annual_8;
	private String revenue_growth_annual_1;
	private String revenue_growth_annual_2;
	private String revenue_growth_annual_3;
	private String revenue_growth_annual_4;
	private String revenue_growth_annual_5;
	private String revenue_growth_annual_6;
	private String revenue_growth_annual_7;
	private String revenue_growth_annual_8;
	private String eps_growth_quarterly_1;
	private String eps_growth_quarterly_2;
	private String eps_growth_quarterly_3;
	private String eps_growth_quarterly_4;
	private String eps_growth_quarterly_5;
	private String eps_growth_quarterly_6;
	private String eps_growth_quarterly_7;
	private String eps_growth_quarterly_8;
	private String revenue_growth_quarterly_1;
	private String revenue_growth_quarterly_2;
	private String revenue_growth_quarterly_3;
	private String revenue_growth_quarterly_4;
	private String revenue_growth_quarterly_5;
	private String revenue_growth_quarterly_6;
	private String revenue_growth_quarterly_7;
	private String revenue_growth_quarterly_8;
	private String dividend_growth_5_year_1;
	private String dividend_growth_5_year_2;
	private String dividend_growth_5_year_3;
	private String dividend_growth_5_year_4;
	private String dividend_growth_5_year_5;
	private String dividend_yield_1;
	private String dividend_yield_2;
	private String dividend_yield_3;
	private String dividend_yield_4;
	private String dividend_yield_5;
	private String dividend_yield_6;
	private String dividend_yield_and_1;
	private String dividend_yield_and_2;
	private String _52_week_high;
	private String _52_week_high_1;
	private String _52_week_low;
	private String _52_week_low_1;
	private String price_closes_sam_1;
	private String price_closes_sam_2;
	private String price_closes_sam_3;
	private String price_closes_sam_4;
	private String price_closes_sam_5;
	private String price_closes_sam_6;
	private String price_closes_sam_7;
	private String price_closes_sam_8;
	private String above_below_1;
	private String above_below_2;
	private String _13_day_sma_1;
	private String _13_day_sma_2;

	// TODO
	// Count result
	private String cnt_asset_class;
	private String cnt_component;
	private String cnt_sector_industry;
	private String cnt_g1_c_result;
	private String cnt_share_price;
	private String cnt_price_change;
	private String cnt_price_performance;
	private String cnt_average_volume;
	private String cnt_g2_c_result;
	private String cnt_pe_ratio;
	private String cnt_peg;
	private String cnt_profit_margin;
	private String cnt_price_sale_ratio;
	private String cnt_price_book_ratio;
	private String cnt_return_equity;
	private String cnt_return_asset;
	private String cnt_g3_c_result;
	private String cnt_eps_growth_annual;
	private String cnt_revenue_growth_annual;
	private String cnt_eps_growth_quarterly;
	private String cnt_revenue_growth_quarterly;
	private String cnt_dividend_growth_5_year;
	private String cnt_dividend_yield;
	private String cnt_g4_c_result;
	private String cnt_52_week_high;
	private String cnt_52_week_low;
	private String cnt_price_closes_sam;
	private String cnt_13_day_sma;
	private String cnt_g5_c_result;
	private String cnt_all_c;
	private String cnt_all_c_result;

	// TODO
	// Sort by field:
	private String sortOrder;
	private String sortField;
	private String saveSearchName;
	private Long saveSearchId;

	public String getAsset_class_1() {
		return asset_class_1;
	}

	public void setAsset_class_1(String asset_class_1) {
		this.asset_class_1 = asset_class_1;
	}

	public String getAsset_class_2() {
		return asset_class_2;
	}

	public void setAsset_class_2(String asset_class_2) {
		this.asset_class_2 = asset_class_2;
	}

	public String getAsset_class_3() {
		return asset_class_3;
	}

	public void setAsset_class_3(String asset_class_3) {
		this.asset_class_3 = asset_class_3;
	}

	public String getComponent_1() {
		return component_1;
	}

	public void setComponent_1(String component_1) {
		this.component_1 = component_1;
	}

	public String getComponent_2() {
		return component_2;
	}

	public void setComponent_2(String component_2) {
		this.component_2 = component_2;
	}

	public String getComponent_3() {
		return component_3;
	}

	public void setComponent_3(String component_3) {
		this.component_3 = component_3;
	}

	public String getSector() {
		return sector;
	}

	public void setSector(String sector) {
		this.sector = sector;
	}

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	public String getShare_price_from() {
		return share_price_from;
	}

	public void setShare_price_from(String share_price_from) {
		this.share_price_from = share_price_from;
	}

	public String getShare_price_to() {
		return share_price_to;
	}

	public void setShare_price_to(String share_price_to) {
		this.share_price_to = share_price_to;
	}

	public String getPrice_change_from() {
		return price_change_from;
	}

	public void setPrice_change_from(String price_change_from) {
		this.price_change_from = price_change_from;
	}

	public String getPrice_change_1() {
		return price_change_1;
	}

	public void setPrice_change_1(String price_change_1) {
		this.price_change_1 = price_change_1;
	}

	public String getPrice_change_2() {
		return price_change_2;
	}

	public void setPrice_change_2(String price_change_2) {
		this.price_change_2 = price_change_2;
	}

	public String getPrice_change_3() {
		return price_change_3;
	}

	public void setPrice_change_3(String price_change_3) {
		this.price_change_3 = price_change_3;
	}

	public String getPrice_change_4() {
		return price_change_4;
	}

	public void setPrice_change_4(String price_change_4) {
		this.price_change_4 = price_change_4;
	}

	public String getPrice_change_5() {
		return price_change_5;
	}

	public void setPrice_change_5(String price_change_5) {
		this.price_change_5 = price_change_5;
	}

	public String getPrice_performance_1() {
		return price_performance_1;
	}

	public void setPrice_performance_1(String price_performance_1) {
		this.price_performance_1 = price_performance_1;
	}

	public String getPrice_performance_2() {
		return price_performance_2;
	}

	public void setPrice_performance_2(String price_performance_2) {
		this.price_performance_2 = price_performance_2;
	}

	public String getPrice_performance_3() {
		return price_performance_3;
	}

	public void setPrice_performance_3(String price_performance_3) {
		this.price_performance_3 = price_performance_3;
	}

	public String getPrice_performance_4() {
		return price_performance_4;
	}

	public void setPrice_performance_4(String price_performance_4) {
		this.price_performance_4 = price_performance_4;
	}

	public String getPrice_performance_5() {
		return price_performance_5;
	}

	public void setPrice_performance_5(String price_performance_5) {
		this.price_performance_5 = price_performance_5;
	}

	public String getPrice_performance_6() {
		return price_performance_6;
	}

	public void setPrice_performance_6(String price_performance_6) {
		this.price_performance_6 = price_performance_6;
	}

	public String getOver_1() {
		return over_1;
	}

	public void setOver_1(String over_1) {
		this.over_1 = over_1;
	}

	public String getOver_2() {
		return over_2;
	}

	public void setOver_2(String over_2) {
		this.over_2 = over_2;
	}

	public String getOver_3() {
		return over_3;
	}

	public void setOver_3(String over_3) {
		this.over_3 = over_3;
	}

	public String getAverage_volume_from() {
		return average_volume_from;
	}

	public void setAverage_volume_from(String average_volume_from) {
		this.average_volume_from = average_volume_from;
	}

	public String getAverage_volume_to() {
		return average_volume_to;
	}

	public void setAverage_volume_to(String average_volume_to) {
		this.average_volume_to = average_volume_to;
	}

	public String getPe_ratio_1() {
		return pe_ratio_1;
	}

	public void setPe_ratio_1(String pe_ratio_1) {
		this.pe_ratio_1 = pe_ratio_1;
	}

	public String getPe_ratio_2() {
		return pe_ratio_2;
	}

	public void setPe_ratio_2(String pe_ratio_2) {
		this.pe_ratio_2 = pe_ratio_2;
	}

	public String getPe_ratio_3() {
		return pe_ratio_3;
	}

	public void setPe_ratio_3(String pe_ratio_3) {
		this.pe_ratio_3 = pe_ratio_3;
	}

	public String getPe_ratio_4() {
		return pe_ratio_4;
	}

	public void setPe_ratio_4(String pe_ratio_4) {
		this.pe_ratio_4 = pe_ratio_4;
	}

	public String getPe_ratio_5() {
		return pe_ratio_5;
	}

	public void setPe_ratio_5(String pe_ratio_5) {
		this.pe_ratio_5 = pe_ratio_5;
	}

	public String getPe_ratio_6() {
		return pe_ratio_6;
	}

	public void setPe_ratio_6(String pe_ratio_6) {
		this.pe_ratio_6 = pe_ratio_6;
	}

	public String getPeg_1() {
		return peg_1;
	}

	public void setPeg_1(String peg_1) {
		this.peg_1 = peg_1;
	}

	public String getPeg_2() {
		return peg_2;
	}

	public void setPeg_2(String peg_2) {
		this.peg_2 = peg_2;
	}

	public String getProfit_margin_1() {
		return profit_margin_1;
	}

	public void setProfit_margin_1(String profit_margin_1) {
		this.profit_margin_1 = profit_margin_1;
	}

	public String getProfit_margin_2() {
		return profit_margin_2;
	}

	public void setProfit_margin_2(String profit_margin_2) {
		this.profit_margin_2 = profit_margin_2;
	}

	public String getProfit_margin_3() {
		return profit_margin_3;
	}

	public void setProfit_margin_3(String profit_margin_3) {
		this.profit_margin_3 = profit_margin_3;
	}

	public String getProfit_margin_4() {
		return profit_margin_4;
	}

	public void setProfit_margin_4(String profit_margin_4) {
		this.profit_margin_4 = profit_margin_4;
	}

	public String getProfit_margin_5() {
		return profit_margin_5;
	}

	public void setProfit_margin_5(String profit_margin_5) {
		this.profit_margin_5 = profit_margin_5;
	}

	public String getProfit_margin_6() {
		return profit_margin_6;
	}

	public void setProfit_margin_6(String profit_margin_6) {
		this.profit_margin_6 = profit_margin_6;
	}

	public String getProfit_margin_and_1() {
		return profit_margin_and_1;
	}

	public void setProfit_margin_and_1(String profit_margin_and_1) {
		this.profit_margin_and_1 = profit_margin_and_1;
	}

	public String getProfit_margin_and_2() {
		return profit_margin_and_2;
	}

	public void setProfit_margin_and_2(String profit_margin_and_2) {
		this.profit_margin_and_2 = profit_margin_and_2;
	}

	public String getPrice_sale_ratio_1() {
		return price_sale_ratio_1;
	}

	public void setPrice_sale_ratio_1(String price_sale_ratio_1) {
		this.price_sale_ratio_1 = price_sale_ratio_1;
	}

	public String getPrice_sale_ratio_2() {
		return price_sale_ratio_2;
	}

	public void setPrice_sale_ratio_2(String price_sale_ratio_2) {
		this.price_sale_ratio_2 = price_sale_ratio_2;
	}

	public String getPrice_sale_ratio_3() {
		return price_sale_ratio_3;
	}

	public void setPrice_sale_ratio_3(String price_sale_ratio_3) {
		this.price_sale_ratio_3 = price_sale_ratio_3;
	}

	public String getPrice_sale_ratio_4() {
		return price_sale_ratio_4;
	}

	public void setPrice_sale_ratio_4(String price_sale_ratio_4) {
		this.price_sale_ratio_4 = price_sale_ratio_4;
	}

	public String getPrice_sale_ratio_5() {
		return price_sale_ratio_5;
	}

	public void setPrice_sale_ratio_5(String price_sale_ratio_5) {
		this.price_sale_ratio_5 = price_sale_ratio_5;
	}

	public String getPrice_sale_ratio_6() {
		return price_sale_ratio_6;
	}

	public void setPrice_sale_ratio_6(String price_sale_ratio_6) {
		this.price_sale_ratio_6 = price_sale_ratio_6;
	}

	public String getPrice_sale_ratio_7() {
		return price_sale_ratio_7;
	}

	public void setPrice_sale_ratio_7(String price_sale_ratio_7) {
		this.price_sale_ratio_7 = price_sale_ratio_7;
	}

	public String getPrice_book_ratio_1() {
		return price_book_ratio_1;
	}

	public void setPrice_book_ratio_1(String price_book_ratio_1) {
		this.price_book_ratio_1 = price_book_ratio_1;
	}

	public String getPrice_book_ratio_2() {
		return price_book_ratio_2;
	}

	public void setPrice_book_ratio_2(String price_book_ratio_2) {
		this.price_book_ratio_2 = price_book_ratio_2;
	}

	public String getPrice_book_ratio_3() {
		return price_book_ratio_3;
	}

	public void setPrice_book_ratio_3(String price_book_ratio_3) {
		this.price_book_ratio_3 = price_book_ratio_3;
	}

	public String getPrice_book_ratio_4() {
		return price_book_ratio_4;
	}

	public void setPrice_book_ratio_4(String price_book_ratio_4) {
		this.price_book_ratio_4 = price_book_ratio_4;
	}

	public String getReturn_equity_1() {
		return return_equity_1;
	}

	public void setReturn_equity_1(String return_equity_1) {
		this.return_equity_1 = return_equity_1;
	}

	public String getReturn_equity_2() {
		return return_equity_2;
	}

	public void setReturn_equity_2(String return_equity_2) {
		this.return_equity_2 = return_equity_2;
	}

	public String getReturn_equity_3() {
		return return_equity_3;
	}

	public void setReturn_equity_3(String return_equity_3) {
		this.return_equity_3 = return_equity_3;
	}

	public String getReturn_equity_4() {
		return return_equity_4;
	}

	public void setReturn_equity_4(String return_equity_4) {
		this.return_equity_4 = return_equity_4;
	}

	public String getReturn_equity_and_1() {
		return return_equity_and_1;
	}

	public void setReturn_equity_and_1(String return_equity_and_1) {
		this.return_equity_and_1 = return_equity_and_1;
	}

	public String getReturn_equity_and_2() {
		return return_equity_and_2;
	}

	public void setReturn_equity_and_2(String return_equity_and_2) {
		this.return_equity_and_2 = return_equity_and_2;
	}

	public String getReturn_asset_1() {
		return return_asset_1;
	}

	public void setReturn_asset_1(String return_asset_1) {
		this.return_asset_1 = return_asset_1;
	}

	public String getReturn_asset_2() {
		return return_asset_2;
	}

	public void setReturn_asset_2(String return_asset_2) {
		this.return_asset_2 = return_asset_2;
	}

	public String getReturn_asset_3() {
		return return_asset_3;
	}

	public void setReturn_asset_3(String return_asset_3) {
		this.return_asset_3 = return_asset_3;
	}

	public String getReturn_asset_4() {
		return return_asset_4;
	}

	public void setReturn_asset_4(String return_asset_4) {
		this.return_asset_4 = return_asset_4;
	}

	public String getEps_growth_annual_1() {
		return eps_growth_annual_1;
	}

	public void setEps_growth_annual_1(String eps_growth_annual_1) {
		this.eps_growth_annual_1 = eps_growth_annual_1;
	}

	public String getEps_growth_annual_2() {
		return eps_growth_annual_2;
	}

	public void setEps_growth_annual_2(String eps_growth_annual_2) {
		this.eps_growth_annual_2 = eps_growth_annual_2;
	}

	public String getEps_growth_annual_3() {
		return eps_growth_annual_3;
	}

	public void setEps_growth_annual_3(String eps_growth_annual_3) {
		this.eps_growth_annual_3 = eps_growth_annual_3;
	}

	public String getEps_growth_annual_4() {
		return eps_growth_annual_4;
	}

	public void setEps_growth_annual_4(String eps_growth_annual_4) {
		this.eps_growth_annual_4 = eps_growth_annual_4;
	}

	public String getEps_growth_annual_5() {
		return eps_growth_annual_5;
	}

	public void setEps_growth_annual_5(String eps_growth_annual_5) {
		this.eps_growth_annual_5 = eps_growth_annual_5;
	}

	public String getEps_growth_annual_6() {
		return eps_growth_annual_6;
	}

	public void setEps_growth_annual_6(String eps_growth_annual_6) {
		this.eps_growth_annual_6 = eps_growth_annual_6;
	}

	public String getEps_growth_annual_7() {
		return eps_growth_annual_7;
	}

	public void setEps_growth_annual_7(String eps_growth_annual_7) {
		this.eps_growth_annual_7 = eps_growth_annual_7;
	}

	public String getEps_growth_annual_8() {
		return eps_growth_annual_8;
	}

	public void setEps_growth_annual_8(String eps_growth_annual_8) {
		this.eps_growth_annual_8 = eps_growth_annual_8;
	}

	public String getRevenue_growth_annual_1() {
		return revenue_growth_annual_1;
	}

	public void setRevenue_growth_annual_1(String revenue_growth_annual_1) {
		this.revenue_growth_annual_1 = revenue_growth_annual_1;
	}

	public String getRevenue_growth_annual_2() {
		return revenue_growth_annual_2;
	}

	public void setRevenue_growth_annual_2(String revenue_growth_annual_2) {
		this.revenue_growth_annual_2 = revenue_growth_annual_2;
	}

	public String getRevenue_growth_annual_3() {
		return revenue_growth_annual_3;
	}

	public void setRevenue_growth_annual_3(String revenue_growth_annual_3) {
		this.revenue_growth_annual_3 = revenue_growth_annual_3;
	}

	public String getRevenue_growth_annual_4() {
		return revenue_growth_annual_4;
	}

	public void setRevenue_growth_annual_4(String revenue_growth_annual_4) {
		this.revenue_growth_annual_4 = revenue_growth_annual_4;
	}

	public String getRevenue_growth_annual_5() {
		return revenue_growth_annual_5;
	}

	public void setRevenue_growth_annual_5(String revenue_growth_annual_5) {
		this.revenue_growth_annual_5 = revenue_growth_annual_5;
	}

	public String getRevenue_growth_annual_6() {
		return revenue_growth_annual_6;
	}

	public void setRevenue_growth_annual_6(String revenue_growth_annual_6) {
		this.revenue_growth_annual_6 = revenue_growth_annual_6;
	}

	public String getRevenue_growth_annual_7() {
		return revenue_growth_annual_7;
	}

	public void setRevenue_growth_annual_7(String revenue_growth_annual_7) {
		this.revenue_growth_annual_7 = revenue_growth_annual_7;
	}

	public String getRevenue_growth_annual_8() {
		return revenue_growth_annual_8;
	}

	public void setRevenue_growth_annual_8(String revenue_growth_annual_8) {
		this.revenue_growth_annual_8 = revenue_growth_annual_8;
	}

	public String getEps_growth_quarterly_1() {
		return eps_growth_quarterly_1;
	}

	public void setEps_growth_quarterly_1(String eps_growth_quarterly_1) {
		this.eps_growth_quarterly_1 = eps_growth_quarterly_1;
	}

	public String getEps_growth_quarterly_2() {
		return eps_growth_quarterly_2;
	}

	public void setEps_growth_quarterly_2(String eps_growth_quarterly_2) {
		this.eps_growth_quarterly_2 = eps_growth_quarterly_2;
	}

	public String getEps_growth_quarterly_3() {
		return eps_growth_quarterly_3;
	}

	public void setEps_growth_quarterly_3(String eps_growth_quarterly_3) {
		this.eps_growth_quarterly_3 = eps_growth_quarterly_3;
	}

	public String getEps_growth_quarterly_4() {
		return eps_growth_quarterly_4;
	}

	public void setEps_growth_quarterly_4(String eps_growth_quarterly_4) {
		this.eps_growth_quarterly_4 = eps_growth_quarterly_4;
	}

	public String getEps_growth_quarterly_5() {
		return eps_growth_quarterly_5;
	}

	public void setEps_growth_quarterly_5(String eps_growth_quarterly_5) {
		this.eps_growth_quarterly_5 = eps_growth_quarterly_5;
	}

	public String getEps_growth_quarterly_6() {
		return eps_growth_quarterly_6;
	}

	public void setEps_growth_quarterly_6(String eps_growth_quarterly_6) {
		this.eps_growth_quarterly_6 = eps_growth_quarterly_6;
	}

	public String getEps_growth_quarterly_7() {
		return eps_growth_quarterly_7;
	}

	public void setEps_growth_quarterly_7(String eps_growth_quarterly_7) {
		this.eps_growth_quarterly_7 = eps_growth_quarterly_7;
	}

	public String getEps_growth_quarterly_8() {
		return eps_growth_quarterly_8;
	}

	public void setEps_growth_quarterly_8(String eps_growth_quarterly_8) {
		this.eps_growth_quarterly_8 = eps_growth_quarterly_8;
	}

	public String getRevenue_growth_quarterly_1() {
		return revenue_growth_quarterly_1;
	}

	public void setRevenue_growth_quarterly_1(String revenue_growth_quarterly_1) {
		this.revenue_growth_quarterly_1 = revenue_growth_quarterly_1;
	}

	public String getRevenue_growth_quarterly_2() {
		return revenue_growth_quarterly_2;
	}

	public void setRevenue_growth_quarterly_2(String revenue_growth_quarterly_2) {
		this.revenue_growth_quarterly_2 = revenue_growth_quarterly_2;
	}

	public String getRevenue_growth_quarterly_3() {
		return revenue_growth_quarterly_3;
	}

	public void setRevenue_growth_quarterly_3(String revenue_growth_quarterly_3) {
		this.revenue_growth_quarterly_3 = revenue_growth_quarterly_3;
	}

	public String getRevenue_growth_quarterly_4() {
		return revenue_growth_quarterly_4;
	}

	public void setRevenue_growth_quarterly_4(String revenue_growth_quarterly_4) {
		this.revenue_growth_quarterly_4 = revenue_growth_quarterly_4;
	}

	public String getRevenue_growth_quarterly_5() {
		return revenue_growth_quarterly_5;
	}

	public void setRevenue_growth_quarterly_5(String revenue_growth_quarterly_5) {
		this.revenue_growth_quarterly_5 = revenue_growth_quarterly_5;
	}

	public String getRevenue_growth_quarterly_6() {
		return revenue_growth_quarterly_6;
	}

	public void setRevenue_growth_quarterly_6(String revenue_growth_quarterly_6) {
		this.revenue_growth_quarterly_6 = revenue_growth_quarterly_6;
	}

	public String getRevenue_growth_quarterly_7() {
		return revenue_growth_quarterly_7;
	}

	public void setRevenue_growth_quarterly_7(String revenue_growth_quarterly_7) {
		this.revenue_growth_quarterly_7 = revenue_growth_quarterly_7;
	}

	public String getRevenue_growth_quarterly_8() {
		return revenue_growth_quarterly_8;
	}

	public void setRevenue_growth_quarterly_8(String revenue_growth_quarterly_8) {
		this.revenue_growth_quarterly_8 = revenue_growth_quarterly_8;
	}

	public String getDividend_growth_5_year_1() {
		return dividend_growth_5_year_1;
	}

	public void setDividend_growth_5_year_1(String dividend_growth_5_year_1) {
		this.dividend_growth_5_year_1 = dividend_growth_5_year_1;
	}

	public String getDividend_growth_5_year_2() {
		return dividend_growth_5_year_2;
	}

	public void setDividend_growth_5_year_2(String dividend_growth_5_year_2) {
		this.dividend_growth_5_year_2 = dividend_growth_5_year_2;
	}

	public String getDividend_growth_5_year_3() {
		return dividend_growth_5_year_3;
	}

	public void setDividend_growth_5_year_3(String dividend_growth_5_year_3) {
		this.dividend_growth_5_year_3 = dividend_growth_5_year_3;
	}

	public String getDividend_growth_5_year_4() {
		return dividend_growth_5_year_4;
	}

	public void setDividend_growth_5_year_4(String dividend_growth_5_year_4) {
		this.dividend_growth_5_year_4 = dividend_growth_5_year_4;
	}

	public String getDividend_growth_5_year_5() {
		return dividend_growth_5_year_5;
	}

	public void setDividend_growth_5_year_5(String dividend_growth_5_year_5) {
		this.dividend_growth_5_year_5 = dividend_growth_5_year_5;
	}

	public String getDividend_yield_1() {
		return dividend_yield_1;
	}

	public void setDividend_yield_1(String dividend_yield_1) {
		this.dividend_yield_1 = dividend_yield_1;
	}

	public String getDividend_yield_2() {
		return dividend_yield_2;
	}

	public void setDividend_yield_2(String dividend_yield_2) {
		this.dividend_yield_2 = dividend_yield_2;
	}

	public String getDividend_yield_3() {
		return dividend_yield_3;
	}

	public void setDividend_yield_3(String dividend_yield_3) {
		this.dividend_yield_3 = dividend_yield_3;
	}

	public String getDividend_yield_4() {
		return dividend_yield_4;
	}

	public void setDividend_yield_4(String dividend_yield_4) {
		this.dividend_yield_4 = dividend_yield_4;
	}

	public String getDividend_yield_5() {
		return dividend_yield_5;
	}

	public void setDividend_yield_5(String dividend_yield_5) {
		this.dividend_yield_5 = dividend_yield_5;
	}

	public String getDividend_yield_6() {
		return dividend_yield_6;
	}

	public void setDividend_yield_6(String dividend_yield_6) {
		this.dividend_yield_6 = dividend_yield_6;
	}

	public String getDividend_yield_and_1() {
		return dividend_yield_and_1;
	}

	public void setDividend_yield_and_1(String dividend_yield_and_1) {
		this.dividend_yield_and_1 = dividend_yield_and_1;
	}

	public String getDividend_yield_and_2() {
		return dividend_yield_and_2;
	}

	public void setDividend_yield_and_2(String dividend_yield_and_2) {
		this.dividend_yield_and_2 = dividend_yield_and_2;
	}

	public String get_52_week_high() {
		return _52_week_high;
	}

	public void set_52_week_high(String _52_week_high) {
		this._52_week_high = _52_week_high;
	}

	public String get_52_week_high_1() {
		return _52_week_high_1;
	}

	public void set_52_week_high_1(String _52_week_high_1) {
		this._52_week_high_1 = _52_week_high_1;
	}

	public String get_52_week_low() {
		return _52_week_low;
	}

	public void set_52_week_low(String _52_week_low) {
		this._52_week_low = _52_week_low;
	}

	public String get_52_week_low_1() {
		return _52_week_low_1;
	}

	public void set_52_week_low_1(String _52_week_low_1) {
		this._52_week_low_1 = _52_week_low_1;
	}

	public String getPrice_closes_sam_1() {
		return price_closes_sam_1;
	}

	public void setPrice_closes_sam_1(String price_closes_sam_1) {
		this.price_closes_sam_1 = price_closes_sam_1;
	}

	public String getPrice_closes_sam_2() {
		return price_closes_sam_2;
	}

	public void setPrice_closes_sam_2(String price_closes_sam_2) {
		this.price_closes_sam_2 = price_closes_sam_2;
	}

	public String getPrice_closes_sam_3() {
		return price_closes_sam_3;
	}

	public void setPrice_closes_sam_3(String price_closes_sam_3) {
		this.price_closes_sam_3 = price_closes_sam_3;
	}

	public String getPrice_closes_sam_4() {
		return price_closes_sam_4;
	}

	public void setPrice_closes_sam_4(String price_closes_sam_4) {
		this.price_closes_sam_4 = price_closes_sam_4;
	}

	public String getPrice_closes_sam_5() {
		return price_closes_sam_5;
	}

	public void setPrice_closes_sam_5(String price_closes_sam_5) {
		this.price_closes_sam_5 = price_closes_sam_5;
	}

	public String getPrice_closes_sam_6() {
		return price_closes_sam_6;
	}

	public void setPrice_closes_sam_6(String price_closes_sam_6) {
		this.price_closes_sam_6 = price_closes_sam_6;
	}

	public String getPrice_closes_sam_7() {
		return price_closes_sam_7;
	}

	public void setPrice_closes_sam_7(String price_closes_sam_7) {
		this.price_closes_sam_7 = price_closes_sam_7;
	}

	public String getPrice_closes_sam_8() {
		return price_closes_sam_8;
	}

	public void setPrice_closes_sam_8(String price_closes_sam_8) {
		this.price_closes_sam_8 = price_closes_sam_8;
	}

	public String getAbove_below_1() {
		return above_below_1;
	}

	public void setAbove_below_1(String above_below_1) {
		this.above_below_1 = above_below_1;
	}

	public String getAbove_below_2() {
		return above_below_2;
	}

	public void setAbove_below_2(String above_below_2) {
		this.above_below_2 = above_below_2;
	}

	public String get_13_day_sma_1() {
		return _13_day_sma_1;
	}

	public void set_13_day_sma_1(String _13_day_sma_1) {
		this._13_day_sma_1 = _13_day_sma_1;
	}

	public String get_13_day_sma_2() {
		return _13_day_sma_2;
	}

	public void set_13_day_sma_2(String _13_day_sma_2) {
		this._13_day_sma_2 = _13_day_sma_2;
	}

	public String getCnt_asset_class() {
		return cnt_asset_class;
	}

	public void setCnt_asset_class(String cnt_asset_class) {
		this.cnt_asset_class = cnt_asset_class;
	}

	public String getCnt_component() {
		return cnt_component;
	}

	public void setCnt_component(String cnt_component) {
		this.cnt_component = cnt_component;
	}

	public String getCnt_sector_industry() {
		return cnt_sector_industry;
	}

	public void setCnt_sector_industry(String cnt_sector_industry) {
		this.cnt_sector_industry = cnt_sector_industry;
	}

	public String getCnt_g1_c_result() {
		return cnt_g1_c_result;
	}

	public void setCnt_g1_c_result(String cnt_g1_c_result) {
		this.cnt_g1_c_result = cnt_g1_c_result;
	}

	public String getCnt_share_price() {
		return cnt_share_price;
	}

	public void setCnt_share_price(String cnt_share_price) {
		this.cnt_share_price = cnt_share_price;
	}

	public String getCnt_price_change() {
		return cnt_price_change;
	}

	public void setCnt_price_change(String cnt_price_change) {
		this.cnt_price_change = cnt_price_change;
	}

	public String getCnt_price_performance() {
		return cnt_price_performance;
	}

	public void setCnt_price_performance(String cnt_price_performance) {
		this.cnt_price_performance = cnt_price_performance;
	}

	public String getCnt_average_volume() {
		return cnt_average_volume;
	}

	public void setCnt_average_volume(String cnt_average_volume) {
		this.cnt_average_volume = cnt_average_volume;
	}

	public String getCnt_g2_c_result() {
		return cnt_g2_c_result;
	}

	public void setCnt_g2_c_result(String cnt_g2_c_result) {
		this.cnt_g2_c_result = cnt_g2_c_result;
	}

	public String getCnt_pe_ratio() {
		return cnt_pe_ratio;
	}

	public void setCnt_pe_ratio(String cnt_pe_ratio) {
		this.cnt_pe_ratio = cnt_pe_ratio;
	}

	public String getCnt_peg() {
		return cnt_peg;
	}

	public void setCnt_peg(String cnt_peg) {
		this.cnt_peg = cnt_peg;
	}

	public String getCnt_profit_margin() {
		return cnt_profit_margin;
	}

	public void setCnt_profit_margin(String cnt_profit_margin) {
		this.cnt_profit_margin = cnt_profit_margin;
	}

	public String getCnt_price_sale_ratio() {
		return cnt_price_sale_ratio;
	}

	public void setCnt_price_sale_ratio(String cnt_price_sale_ratio) {
		this.cnt_price_sale_ratio = cnt_price_sale_ratio;
	}

	public String getCnt_price_book_ratio() {
		return cnt_price_book_ratio;
	}

	public void setCnt_price_book_ratio(String cnt_price_book_ratio) {
		this.cnt_price_book_ratio = cnt_price_book_ratio;
	}

	public String getCnt_return_equity() {
		return cnt_return_equity;
	}

	public void setCnt_return_equity(String cnt_return_equity) {
		this.cnt_return_equity = cnt_return_equity;
	}

	public String getCnt_return_asset() {
		return cnt_return_asset;
	}

	public void setCnt_return_asset(String cnt_return_asset) {
		this.cnt_return_asset = cnt_return_asset;
	}

	public String getCnt_g3_c_result() {
		return cnt_g3_c_result;
	}

	public void setCnt_g3_c_result(String cnt_g3_c_result) {
		this.cnt_g3_c_result = cnt_g3_c_result;
	}

	public String getCnt_eps_growth_annual() {
		return cnt_eps_growth_annual;
	}

	public void setCnt_eps_growth_annual(String cnt_eps_growth_annual) {
		this.cnt_eps_growth_annual = cnt_eps_growth_annual;
	}

	public String getCnt_revenue_growth_annual() {
		return cnt_revenue_growth_annual;
	}

	public void setCnt_revenue_growth_annual(String cnt_revenue_growth_annual) {
		this.cnt_revenue_growth_annual = cnt_revenue_growth_annual;
	}

	public String getCnt_eps_growth_quarterly() {
		return cnt_eps_growth_quarterly;
	}

	public void setCnt_eps_growth_quarterly(String cnt_eps_growth_quarterly) {
		this.cnt_eps_growth_quarterly = cnt_eps_growth_quarterly;
	}

	public String getCnt_revenue_growth_quarterly() {
		return cnt_revenue_growth_quarterly;
	}

	public void setCnt_revenue_growth_quarterly(String cnt_revenue_growth_quarterly) {
		this.cnt_revenue_growth_quarterly = cnt_revenue_growth_quarterly;
	}

	public String getCnt_dividend_growth_5_year() {
		return cnt_dividend_growth_5_year;
	}

	public void setCnt_dividend_growth_5_year(String cnt_dividend_growth_5_year) {
		this.cnt_dividend_growth_5_year = cnt_dividend_growth_5_year;
	}

	public String getCnt_dividend_yield() {
		return cnt_dividend_yield;
	}

	public void setCnt_dividend_yield(String cnt_dividend_yield) {
		this.cnt_dividend_yield = cnt_dividend_yield;
	}

	public String getCnt_g4_c_result() {
		return cnt_g4_c_result;
	}

	public void setCnt_g4_c_result(String cnt_g4_c_result) {
		this.cnt_g4_c_result = cnt_g4_c_result;
	}

	public String getCnt_52_week_high() {
		return cnt_52_week_high;
	}

	public void setCnt_52_week_high(String cnt_52_week_high) {
		this.cnt_52_week_high = cnt_52_week_high;
	}

	public String getCnt_52_week_low() {
		return cnt_52_week_low;
	}

	public void setCnt_52_week_low(String cnt_52_week_low) {
		this.cnt_52_week_low = cnt_52_week_low;
	}

	public String getCnt_price_closes_sam() {
		return cnt_price_closes_sam;
	}

	public void setCnt_price_closes_sam(String cnt_price_closes_sam) {
		this.cnt_price_closes_sam = cnt_price_closes_sam;
	}

	public String getCnt_13_day_sma() {
		return cnt_13_day_sma;
	}

	public void setCnt_13_day_sma(String cnt_13_day_sma) {
		this.cnt_13_day_sma = cnt_13_day_sma;
	}

	public String getCnt_all_c() {
		return cnt_all_c;
	}

	public void setCnt_all_c(String cnt_all_c) {
		this.cnt_all_c = cnt_all_c;
	}

	public String getCnt_all_c_result() {
		return cnt_all_c_result;
	}

	public void setCnt_all_c_result(String cnt_all_c_result) {
		this.cnt_all_c_result = cnt_all_c_result;
	}

	public String getCnt_g5_c_result() {
		return cnt_g5_c_result;
	}

	public void setCnt_g5_c_result(String cnt_g5_c_result) {
		this.cnt_g5_c_result = cnt_g5_c_result;
	}

	public String getAsset_class_4() {
		return asset_class_4;
	}

	public void setAsset_class_4(String asset_class_4) {
		this.asset_class_4 = asset_class_4;
	}

	public String getPe_ratio_7() {
		return pe_ratio_7;
	}

	public void setPe_ratio_7(String pe_ratio_7) {
		this.pe_ratio_7 = pe_ratio_7;
	}

	public String getPrice_book_ratio_5() {
		return price_book_ratio_5;
	}

	public void setPrice_book_ratio_5(String price_book_ratio_5) {
		this.price_book_ratio_5 = price_book_ratio_5;
	}

	public String getReturn_equity_5() {
		return return_equity_5;
	}

	public void setReturn_equity_5(String return_equity_5) {
		this.return_equity_5 = return_equity_5;
	}

	public String getReturn_asset_5() {
		return return_asset_5;
	}

	public void setReturn_asset_5(String return_asset_5) {
		this.return_asset_5 = return_asset_5;
	}

	public String getReturn_asset_and_1() {
		return return_asset_and_1;
	}

	public void setReturn_asset_and_1(String return_asset_and_1) {
		this.return_asset_and_1 = return_asset_and_1;
	}

	public String getReturn_asset_and_2() {
		return return_asset_and_2;
	}

	public void setReturn_asset_and_2(String return_asset_and_2) {
		this.return_asset_and_2 = return_asset_and_2;
	}

	public String getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
	}

	public String getSortField() {
		return sortField;
	}

	public void setSortField(String sortField) {
		this.sortField = sortField;
	}

	public String getLocale() {
		return I18NUtility.getCurrentLocale(ServletActionContext.getRequest().getSession());
	}

	/**
	 * @return the saveSearchName
	 */
	public String getSaveSearchName() {
		return saveSearchName;
	}

	/**
	 * @param saveSearchName
	 *            the saveSearchName to set
	 */
	public void setSaveSearchName(String saveSearchName) {
		this.saveSearchName = saveSearchName;
	}

	/**
	 * @return the saveSearchId
	 */
	public Long getSaveSearchId() {
		return saveSearchId;
	}

	/**
	 * @param saveSearchId
	 *            the saveSearchId to set
	 */
	public void setSaveSearchId(Long saveSearchId) {
		this.saveSearchId = saveSearchId;
	}

	public SaveSearch getSaveSearch() {
		SaveSearch saveSearch = new SaveSearch();
		saveSearch.setSaveSearchName(this.saveSearchName);
		saveSearch.setSaveSearchId(this.saveSearchId);

		Map<String, String> content = new HashMap<String, String>();
		content.put("asset_class_1", this.asset_class_1);
		content.put("asset_class_2", this.asset_class_2);
		content.put("asset_class_3", this.asset_class_3);
		content.put("asset_class_4", this.asset_class_4);
		content.put("component_1", this.component_1);
		content.put("component_2", this.component_2);
		content.put("component_3", this.component_3);
		content.put("sector", this.sector);
		content.put("industry", this.industry);
		content.put("share_price_from", this.share_price_from);
		content.put("share_price_to", this.share_price_to);
		content.put("price_change_from", this.price_change_from);
		content.put("price_change_1", this.price_change_1);
		content.put("price_change_2", this.price_change_2);
		content.put("price_change_3", this.price_change_3);
		content.put("price_change_4", this.price_change_4);
		content.put("price_change_5", this.price_change_5);
		content.put("price_performance_1", this.price_performance_1);
		content.put("price_performance_2", this.price_performance_2);
		content.put("price_performance_3", this.price_performance_3);
		content.put("price_performance_4", this.price_performance_4);
		content.put("price_performance_5", this.price_performance_5);
		content.put("price_performance_6", this.price_performance_6);
		content.put("over_1", this.over_1);
		content.put("over_2", this.over_2);
		content.put("over_3", this.over_3);
		content.put("average_volume_from", this.average_volume_from);
		content.put("average_volume_to", this.average_volume_to);
		content.put("pe_ratio_1", this.pe_ratio_1);
		content.put("pe_ratio_2", this.pe_ratio_2);
		content.put("pe_ratio_3", this.pe_ratio_3);
		content.put("pe_ratio_4", this.pe_ratio_4);
		content.put("pe_ratio_5", this.pe_ratio_5);
		content.put("pe_ratio_6", this.pe_ratio_6);
		content.put("pe_ratio_7", this.pe_ratio_7);
		content.put("peg_1", this.peg_1);
		content.put("peg_2", this.peg_2);
		content.put("profit_margin_1", this.profit_margin_1);
		content.put("profit_margin_2", this.profit_margin_2);
		content.put("profit_margin_3", this.profit_margin_3);
		content.put("profit_margin_4", this.profit_margin_4);
		content.put("profit_margin_5", this.profit_margin_5);
		content.put("profit_margin_6", this.profit_margin_6);
		content.put("profit_margin_and_1", this.profit_margin_and_1);
		content.put("profit_margin_and_2", this.profit_margin_and_2);
		content.put("price_sale_ratio_1", this.price_sale_ratio_1);
		content.put("price_sale_ratio_2", this.price_sale_ratio_2);
		content.put("price_sale_ratio_3", this.price_sale_ratio_3);
		content.put("price_sale_ratio_4", this.price_sale_ratio_4);
		content.put("price_sale_ratio_5", this.price_sale_ratio_5);
		content.put("price_sale_ratio_6", this.price_sale_ratio_6);
		content.put("price_sale_ratio_7", this.price_sale_ratio_7);
		content.put("price_book_ratio_1", this.price_book_ratio_1);
		content.put("price_book_ratio_2", this.price_book_ratio_2);
		content.put("price_book_ratio_3", this.price_book_ratio_3);
		content.put("price_book_ratio_4", this.price_book_ratio_4);
		content.put("price_book_ratio_5", this.price_book_ratio_5);
		content.put("return_equity_1", this.return_equity_1);
		content.put("return_equity_2", this.return_equity_2);
		content.put("return_equity_3", this.return_equity_3);
		content.put("return_equity_4", this.return_equity_4);
		content.put("return_equity_5", this.return_equity_5);
		content.put("return_equity_and_1", this.return_equity_and_1);
		content.put("return_equity_and_2", this.return_equity_and_2);
		content.put("return_asset_1", this.return_asset_1);
		content.put("return_asset_2", this.return_asset_2);
		content.put("return_asset_3", this.return_asset_3);
		content.put("return_asset_4", this.return_asset_4);
		content.put("return_asset_5", this.return_asset_5);
		content.put("return_asset_and_1", this.return_asset_and_1);
		content.put("return_asset_and_2", this.return_asset_and_2);
		content.put("eps_growth_annual_1", this.eps_growth_annual_1);
		content.put("eps_growth_annual_2", this.eps_growth_annual_2);
		content.put("eps_growth_annual_3", this.eps_growth_annual_3);
		content.put("eps_growth_annual_4", this.eps_growth_annual_4);
		content.put("eps_growth_annual_5", this.eps_growth_annual_5);
		content.put("eps_growth_annual_6", this.eps_growth_annual_6);
		content.put("eps_growth_annual_7", this.eps_growth_annual_7);
		content.put("eps_growth_annual_8", this.eps_growth_annual_8);
		content.put("revenue_growth_annual_1", this.revenue_growth_annual_1);
		content.put("revenue_growth_annual_2", this.revenue_growth_annual_2);
		content.put("revenue_growth_annual_3", this.revenue_growth_annual_3);
		content.put("revenue_growth_annual_4", this.revenue_growth_annual_4);
		content.put("revenue_growth_annual_5", this.revenue_growth_annual_5);
		content.put("revenue_growth_annual_6", this.revenue_growth_annual_6);
		content.put("revenue_growth_annual_7", this.revenue_growth_annual_7);
		content.put("revenue_growth_annual_8", this.revenue_growth_annual_8);
		content.put("eps_growth_quarterly_1", this.eps_growth_quarterly_1);
		content.put("eps_growth_quarterly_2", this.eps_growth_quarterly_2);
		content.put("eps_growth_quarterly_3", this.eps_growth_quarterly_3);
		content.put("eps_growth_quarterly_4", this.eps_growth_quarterly_4);
		content.put("eps_growth_quarterly_5", this.eps_growth_quarterly_5);
		content.put("eps_growth_quarterly_6", this.eps_growth_quarterly_6);
		content.put("eps_growth_quarterly_7", this.eps_growth_quarterly_7);
		content.put("eps_growth_quarterly_8", this.eps_growth_quarterly_8);
		content.put("revenue_growth_quarterly_1", this.revenue_growth_quarterly_1);
		content.put("revenue_growth_quarterly_2", this.revenue_growth_quarterly_2);
		content.put("revenue_growth_quarterly_3", this.revenue_growth_quarterly_3);
		content.put("revenue_growth_quarterly_4", this.revenue_growth_quarterly_4);
		content.put("revenue_growth_quarterly_5", this.revenue_growth_quarterly_5);
		content.put("revenue_growth_quarterly_6", this.revenue_growth_quarterly_6);
		content.put("revenue_growth_quarterly_7", this.revenue_growth_quarterly_7);
		content.put("revenue_growth_quarterly_8", this.revenue_growth_quarterly_8);
		content.put("dividend_growth_5_year_1", this.dividend_growth_5_year_1);
		content.put("dividend_growth_5_year_2", this.dividend_growth_5_year_2);
		content.put("dividend_growth_5_year_3", this.dividend_growth_5_year_3);
		content.put("dividend_growth_5_year_4", this.dividend_growth_5_year_4);
		content.put("dividend_growth_5_year_5", this.dividend_growth_5_year_5);
		content.put("dividend_yield_1", this.dividend_yield_1);
		content.put("dividend_yield_2", this.dividend_yield_2);
		content.put("dividend_yield_3", this.dividend_yield_3);
		content.put("dividend_yield_4", this.dividend_yield_4);
		content.put("dividend_yield_5", this.dividend_yield_5);
		content.put("dividend_yield_6", this.dividend_yield_6);
		content.put("dividend_yield_and_1", this.dividend_yield_and_1);
		content.put("dividend_yield_and_2", this.dividend_yield_and_2);
		content.put("_52_week_high", this._52_week_high);
		content.put("_52_week_high_1", this._52_week_high_1);
		content.put("_52_week_low", this._52_week_low);
		content.put("_52_week_low_1", this._52_week_low_1);
		content.put("price_closes_sam_1", this.price_closes_sam_1);
		content.put("price_closes_sam_2", this.price_closes_sam_2);
		content.put("price_closes_sam_3", this.price_closes_sam_3);
		content.put("price_closes_sam_4", this.price_closes_sam_4);
		content.put("price_closes_sam_5", this.price_closes_sam_5);
		content.put("price_closes_sam_6", this.price_closes_sam_6);
		content.put("price_closes_sam_7", this.price_closes_sam_7);
		content.put("price_closes_sam_8", this.price_closes_sam_8);
		content.put("above_below_1", this.above_below_1);
		content.put("above_below_2", this.above_below_2);
		content.put("_13_day_sma_1", this._13_day_sma_1);
		content.put("_13_day_sma_2", this._13_day_sma_2);
		content.put("cnt_all_c_result", this.cnt_all_c_result);
		try {
			saveSearch.setContent(ObjectEncoder.objectToString(content, true));
		} catch (Exception e) {
		}

		return saveSearch;
	}

	public static SearchStockScreenerBean getSearchStockScreenerBean(SaveSearch saveSearch) {
		SearchStockScreenerBean bean = new SearchStockScreenerBean();

		if (saveSearch == null)
			return bean;

		bean.setSaveSearchId(saveSearch.getSaveSearchId());
		bean.setSaveSearchName(saveSearch.getSaveSearchName());

		Map<String, String> content = new HashMap<String, String>();
		try {
			if (saveSearch.getContent() != null && saveSearch.getContent().length() > 0) {
				content = (Map<String, String>) ObjectEncoder.stringToObject(saveSearch.getContent(), true);
			}
		} catch (Exception e) {
		}
		bean.setAsset_class_1(content.get("asset_class_1"));
		bean.setAsset_class_2(content.get("asset_class_2"));
		bean.setAsset_class_3(content.get("asset_class_3"));
		bean.setAsset_class_4(content.get("asset_class_4"));
		bean.setComponent_1(content.get("component_1"));
		bean.setComponent_2(content.get("component_2"));
		bean.setComponent_3(content.get("component_3"));
		bean.setSector(content.get("sector"));
		bean.setIndustry(content.get("industry"));
		bean.setShare_price_from(content.get("share_price_from"));
		bean.setShare_price_to(content.get("share_price_to"));
		bean.setPrice_change_from(content.get("price_change_from"));
		bean.setPrice_change_1(content.get("price_change_1"));
		bean.setPrice_change_2(content.get("price_change_2"));
		bean.setPrice_change_3(content.get("price_change_3"));
		bean.setPrice_change_4(content.get("price_change_4"));
		bean.setPrice_change_5(content.get("price_change_5"));
		bean.setPrice_performance_1(content.get("price_performance_1"));
		bean.setPrice_performance_2(content.get("price_performance_2"));
		bean.setPrice_performance_3(content.get("price_performance_3"));
		bean.setPrice_performance_4(content.get("price_performance_4"));
		bean.setPrice_performance_5(content.get("price_performance_5"));
		bean.setPrice_performance_6(content.get("price_performance_6"));
		bean.setOver_1(content.get("over_1"));
		bean.setOver_2(content.get("over_2"));
		bean.setOver_3(content.get("over_3"));
		bean.setAverage_volume_from(content.get("average_volume_from"));
		bean.setAverage_volume_to(content.get("average_volume_to"));
		bean.setPe_ratio_1(content.get("pe_ratio_1"));
		bean.setPe_ratio_2(content.get("pe_ratio_2"));
		bean.setPe_ratio_3(content.get("pe_ratio_3"));
		bean.setPe_ratio_4(content.get("pe_ratio_4"));
		bean.setPe_ratio_5(content.get("pe_ratio_5"));
		bean.setPe_ratio_6(content.get("pe_ratio_6"));
		bean.setPe_ratio_7(content.get("pe_ratio_7"));
		bean.setPeg_1(content.get("peg_1"));
		bean.setPeg_2(content.get("peg_2"));
		bean.setProfit_margin_1(content.get("profit_margin_1"));
		bean.setProfit_margin_2(content.get("profit_margin_2"));
		bean.setProfit_margin_3(content.get("profit_margin_3"));
		bean.setProfit_margin_4(content.get("profit_margin_4"));
		bean.setProfit_margin_5(content.get("profit_margin_5"));
		bean.setProfit_margin_6(content.get("profit_margin_6"));
		bean.setProfit_margin_and_1(content.get("profit_margin_and_1"));
		bean.setProfit_margin_and_2(content.get("profit_margin_and_2"));
		bean.setPrice_sale_ratio_1(content.get("price_sale_ratio_1"));
		bean.setPrice_sale_ratio_2(content.get("price_sale_ratio_2"));
		bean.setPrice_sale_ratio_3(content.get("price_sale_ratio_3"));
		bean.setPrice_sale_ratio_4(content.get("price_sale_ratio_4"));
		bean.setPrice_sale_ratio_5(content.get("price_sale_ratio_5"));
		bean.setPrice_sale_ratio_6(content.get("price_sale_ratio_6"));
		bean.setPrice_sale_ratio_7(content.get("price_sale_ratio_7"));
		bean.setPrice_book_ratio_1(content.get("price_book_ratio_1"));
		bean.setPrice_book_ratio_2(content.get("price_book_ratio_2"));
		bean.setPrice_book_ratio_3(content.get("price_book_ratio_3"));
		bean.setPrice_book_ratio_4(content.get("price_book_ratio_4"));
		bean.setPrice_book_ratio_5(content.get("price_book_ratio_5"));
		bean.setReturn_equity_1(content.get("return_equity_1"));
		bean.setReturn_equity_2(content.get("return_equity_2"));
		bean.setReturn_equity_3(content.get("return_equity_3"));
		bean.setReturn_equity_4(content.get("return_equity_4"));
		bean.setReturn_equity_5(content.get("return_equity_5"));
		bean.setReturn_equity_and_1(content.get("return_equity_and_1"));
		bean.setReturn_equity_and_2(content.get("return_equity_and_2"));
		bean.setReturn_asset_1(content.get("return_asset_1"));
		bean.setReturn_asset_2(content.get("return_asset_2"));
		bean.setReturn_asset_3(content.get("return_asset_3"));
		bean.setReturn_asset_4(content.get("return_asset_4"));
		bean.setReturn_asset_5(content.get("return_asset_5"));
		bean.setReturn_asset_and_1(content.get("return_asset_and_1"));
		bean.setReturn_asset_and_2(content.get("return_asset_and_2"));
		bean.setEps_growth_annual_1(content.get("eps_growth_annual_1"));
		bean.setEps_growth_annual_2(content.get("eps_growth_annual_2"));
		bean.setEps_growth_annual_3(content.get("eps_growth_annual_3"));
		bean.setEps_growth_annual_4(content.get("eps_growth_annual_4"));
		bean.setEps_growth_annual_5(content.get("eps_growth_annual_5"));
		bean.setEps_growth_annual_6(content.get("eps_growth_annual_6"));
		bean.setEps_growth_annual_7(content.get("eps_growth_annual_7"));
		bean.setEps_growth_annual_8(content.get("eps_growth_annual_8"));
		bean.setRevenue_growth_annual_1(content.get("revenue_growth_annual_1"));
		bean.setRevenue_growth_annual_2(content.get("revenue_growth_annual_2"));
		bean.setRevenue_growth_annual_3(content.get("revenue_growth_annual_3"));
		bean.setRevenue_growth_annual_4(content.get("revenue_growth_annual_4"));
		bean.setRevenue_growth_annual_5(content.get("revenue_growth_annual_5"));
		bean.setRevenue_growth_annual_6(content.get("revenue_growth_annual_6"));
		bean.setRevenue_growth_annual_7(content.get("revenue_growth_annual_7"));
		bean.setRevenue_growth_annual_8(content.get("revenue_growth_annual_8"));
		bean.setEps_growth_quarterly_1(content.get("eps_growth_quarterly_1"));
		bean.setEps_growth_quarterly_2(content.get("eps_growth_quarterly_2"));
		bean.setEps_growth_quarterly_3(content.get("eps_growth_quarterly_3"));
		bean.setEps_growth_quarterly_4(content.get("eps_growth_quarterly_4"));
		bean.setEps_growth_quarterly_5(content.get("eps_growth_quarterly_5"));
		bean.setEps_growth_quarterly_6(content.get("eps_growth_quarterly_6"));
		bean.setEps_growth_quarterly_7(content.get("eps_growth_quarterly_7"));
		bean.setEps_growth_quarterly_8(content.get("eps_growth_quarterly_8"));
		bean.setRevenue_growth_quarterly_1(content.get("revenue_growth_quarterly_1"));
		bean.setRevenue_growth_quarterly_2(content.get("revenue_growth_quarterly_2"));
		bean.setRevenue_growth_quarterly_3(content.get("revenue_growth_quarterly_3"));
		bean.setRevenue_growth_quarterly_4(content.get("revenue_growth_quarterly_4"));
		bean.setRevenue_growth_quarterly_5(content.get("revenue_growth_quarterly_5"));
		bean.setRevenue_growth_quarterly_6(content.get("revenue_growth_quarterly_6"));
		bean.setRevenue_growth_quarterly_7(content.get("revenue_growth_quarterly_7"));
		bean.setRevenue_growth_quarterly_8(content.get("revenue_growth_quarterly_8"));
		bean.setDividend_growth_5_year_1(content.get("dividend_growth_5_year_1"));
		bean.setDividend_growth_5_year_2(content.get("dividend_growth_5_year_2"));
		bean.setDividend_growth_5_year_3(content.get("dividend_growth_5_year_3"));
		bean.setDividend_growth_5_year_4(content.get("dividend_growth_5_year_4"));
		bean.setDividend_growth_5_year_5(content.get("dividend_growth_5_year_5"));
		bean.setDividend_yield_1(content.get("dividend_yield_1"));
		bean.setDividend_yield_2(content.get("dividend_yield_2"));
		bean.setDividend_yield_3(content.get("dividend_yield_3"));
		bean.setDividend_yield_4(content.get("dividend_yield_4"));
		bean.setDividend_yield_5(content.get("dividend_yield_5"));
		bean.setDividend_yield_6(content.get("dividend_yield_6"));
		bean.setDividend_yield_and_1(content.get("dividend_yield_and_1"));
		bean.setDividend_yield_and_2(content.get("dividend_yield_and_2"));
		bean.set_52_week_high(content.get("_52_week_high"));
		bean.set_52_week_high_1(content.get("_52_week_high_1"));
		bean.set_52_week_low(content.get("_52_week_low"));
		bean.set_52_week_low_1(content.get("_52_week_low_1"));
		bean.setPrice_closes_sam_1(content.get("price_closes_sam_1"));
		bean.setPrice_closes_sam_2(content.get("price_closes_sam_2"));
		bean.setPrice_closes_sam_3(content.get("price_closes_sam_3"));
		bean.setPrice_closes_sam_4(content.get("price_closes_sam_4"));
		bean.setPrice_closes_sam_5(content.get("price_closes_sam_5"));
		bean.setPrice_closes_sam_6(content.get("price_closes_sam_6"));
		bean.setPrice_closes_sam_7(content.get("price_closes_sam_7"));
		bean.setPrice_closes_sam_8(content.get("price_closes_sam_8"));
		bean.setAbove_below_1(content.get("above_below_1"));
		bean.setAbove_below_2(content.get("above_below_2"));
		bean.set_13_day_sma_1(content.get("_13_day_sma_1"));
		bean.set_13_day_sma_2(content.get("_13_day_sma_2"));
		bean.setCnt_all_c_result(content.get("cnt_all_c_result"));

		return bean;
	}

	public int getCriteriaCnt() {
		int criteriaCnt = 0;
		String IS_SELECTED = "1";
		// Asset class
		if (IS_SELECTED.equals(this.asset_class_1) || IS_SELECTED.equals(this.asset_class_2) || IS_SELECTED.equals(this.asset_class_3) || IS_SELECTED.equals(this.asset_class_4)) {
			criteriaCnt++;
		}
		// Exchange floor
		if (IS_SELECTED.equals(this.component_1) || IS_SELECTED.equals(this.component_2) || IS_SELECTED.equals(this.component_3)) {
			criteriaCnt++;
		}
		// Sector/Industry
		if (this.sector != null && this.sector.trim().length() > 0) {
			criteriaCnt++;
		}
		// Close price
		if (Validation.isNumber(this.share_price_from) || Validation.isNumber(this.share_price_to)) {
			criteriaCnt++;
		}
		// Price change
		if (Validation.isNumber(this.price_change_from)
				&& (IS_SELECTED.equals(this.getPrice_change_1()) || IS_SELECTED.equals(this.getPrice_change_2()) || IS_SELECTED.equals(this.getPrice_change_3())
						|| IS_SELECTED.equals(this.getPrice_change_4()) || IS_SELECTED.equals(this.getPrice_change_5()))) {
			criteriaCnt++;
		}
		// Price Performance vs. Index
		if ((IS_SELECTED.equals(this.getOver_1()) || IS_SELECTED.equals(this.getOver_2()) || IS_SELECTED.equals(this.getOver_3()))
				&& (IS_SELECTED.equals(this.getPrice_performance_1()) || IS_SELECTED.equals(this.getPrice_performance_2()) || IS_SELECTED.equals(this.getPrice_performance_3())
						|| IS_SELECTED.equals(this.getPrice_performance_4()) || IS_SELECTED.equals(this.getPrice_performance_5()) || IS_SELECTED.equals(this.getPrice_performance_6()))) {
			criteriaCnt++;
		}
		// Average Volume
		if (Validation.isNumber(this.average_volume_from) || Validation.isNumber(this.average_volume_to)) {
			criteriaCnt++;
		}
		// P/E Ratio
		if (IS_SELECTED.equals(this.getPe_ratio_1()) || IS_SELECTED.equals(this.getPe_ratio_2()) || IS_SELECTED.equals(this.getPe_ratio_3()) || IS_SELECTED.equals(this.getPe_ratio_4())
				|| IS_SELECTED.equals(this.getPe_ratio_5()) || IS_SELECTED.equals(this.getPe_ratio_6()) || IS_SELECTED.equals(this.getPe_ratio_7())) {
			criteriaCnt++;
		}
		// PEG
		if (IS_SELECTED.equals(this.getPeg_1()) || IS_SELECTED.equals(this.getPeg_2())) {
			criteriaCnt++;
		}
		// Profit Margin(TTM)
		if (IS_SELECTED.equals(this.getProfit_margin_1()) || IS_SELECTED.equals(this.getProfit_margin_2()) || IS_SELECTED.equals(this.getProfit_margin_3())
				|| IS_SELECTED.equals(this.getProfit_margin_4()) || IS_SELECTED.equals(this.getProfit_margin_5()) || IS_SELECTED.equals(this.getProfit_margin_and_1())
				|| IS_SELECTED.equals(this.getProfit_margin_and_2())) {
			criteriaCnt++;
		}
		// Price/Sales Ratio(TTM)
		if (IS_SELECTED.equals(this.getPrice_sale_ratio_1()) || IS_SELECTED.equals(this.getPrice_sale_ratio_2()) || IS_SELECTED.equals(this.getPrice_sale_ratio_3())
				|| IS_SELECTED.equals(this.getPrice_sale_ratio_4()) || IS_SELECTED.equals(this.getPrice_sale_ratio_5()) || IS_SELECTED.equals(this.getPrice_sale_ratio_6())
				|| IS_SELECTED.equals(this.getPrice_sale_ratio_7())) {
			criteriaCnt++;
		}
		// Price/Book Ratio(MRQ)
		if (IS_SELECTED.equals(this.getPrice_book_ratio_1()) || IS_SELECTED.equals(this.getPrice_book_ratio_2()) || IS_SELECTED.equals(this.getPrice_book_ratio_3())
				|| IS_SELECTED.equals(this.getPrice_book_ratio_4()) || IS_SELECTED.equals(this.getPrice_book_ratio_5())) {
			criteriaCnt++;
		}
		// Return on Equity(TTM)(%)
		if (IS_SELECTED.equals(this.getReturn_equity_1()) || IS_SELECTED.equals(this.getReturn_equity_2()) || IS_SELECTED.equals(this.getReturn_equity_3())
				|| IS_SELECTED.equals(this.getReturn_equity_4()) || IS_SELECTED.equals(this.getReturn_equity_5()) || IS_SELECTED.equals(this.getReturn_equity_and_1())
				|| IS_SELECTED.equals(this.getReturn_equity_and_2())) {
			criteriaCnt++;
		}
		// Return on Assets(TTM)(%)
		if (IS_SELECTED.equals(this.getReturn_asset_1()) || IS_SELECTED.equals(this.getReturn_asset_2()) || IS_SELECTED.equals(this.getReturn_asset_3())
				|| IS_SELECTED.equals(this.getReturn_asset_4()) || IS_SELECTED.equals(this.getReturn_asset_5()) || IS_SELECTED.equals(this.getReturn_asset_and_1())
				|| IS_SELECTED.equals(this.getReturn_asset_and_2())) {
			criteriaCnt++;
		}
		// EPS Growth Annual(%)
		if (IS_SELECTED.equals(this.getEps_growth_annual_1()) || IS_SELECTED.equals(this.getEps_growth_annual_2()) || IS_SELECTED.equals(this.getEps_growth_annual_3())
				|| IS_SELECTED.equals(this.getEps_growth_annual_4()) || IS_SELECTED.equals(this.getEps_growth_annual_5()) || IS_SELECTED.equals(this.getEps_growth_annual_6())
				|| IS_SELECTED.equals(this.getEps_growth_annual_7()) || IS_SELECTED.equals(this.getEps_growth_annual_8())) {
			criteriaCnt++;
		}
		// Revenue Growth Annual(%)
		if (IS_SELECTED.equals(this.getRevenue_growth_annual_1()) || IS_SELECTED.equals(this.getRevenue_growth_annual_2()) || IS_SELECTED.equals(this.getRevenue_growth_annual_3())
				|| IS_SELECTED.equals(this.getRevenue_growth_annual_4()) || IS_SELECTED.equals(this.getRevenue_growth_annual_5()) || IS_SELECTED.equals(this.getRevenue_growth_annual_6())
				|| IS_SELECTED.equals(this.getRevenue_growth_annual_7()) || IS_SELECTED.equals(this.getRevenue_growth_annual_8())) {
			criteriaCnt++;
		}
		// EPS Growth(Quarterly)(%)
		if (IS_SELECTED.equals(this.getEps_growth_quarterly_1()) || IS_SELECTED.equals(this.getEps_growth_quarterly_2()) || IS_SELECTED.equals(this.getEps_growth_quarterly_3())
				|| IS_SELECTED.equals(this.getEps_growth_quarterly_4()) || IS_SELECTED.equals(this.getEps_growth_quarterly_5()) || IS_SELECTED.equals(this.getEps_growth_quarterly_6())
				|| IS_SELECTED.equals(this.getEps_growth_quarterly_7()) || IS_SELECTED.equals(this.getEps_growth_quarterly_8())) {
			criteriaCnt++;
		}
		// Revenue Growth (Quarterly)(%)
		if (IS_SELECTED.equals(this.getRevenue_growth_quarterly_1()) || IS_SELECTED.equals(this.getRevenue_growth_quarterly_2()) || IS_SELECTED.equals(this.getRevenue_growth_quarterly_3())
				|| IS_SELECTED.equals(this.getRevenue_growth_quarterly_4()) || IS_SELECTED.equals(this.getRevenue_growth_quarterly_5()) || IS_SELECTED.equals(this.getRevenue_growth_quarterly_6())
				|| IS_SELECTED.equals(this.getRevenue_growth_quarterly_7()) || IS_SELECTED.equals(this.getRevenue_growth_quarterly_8())) {
			criteriaCnt++;
		}
		// Dividend Growth 5 Year
		if (IS_SELECTED.equals(this.getDividend_growth_5_year_1()) || IS_SELECTED.equals(this.getDividend_growth_5_year_2()) || IS_SELECTED.equals(this.getDividend_growth_5_year_3())
				|| IS_SELECTED.equals(this.getDividend_growth_5_year_4()) || IS_SELECTED.equals(this.getDividend_growth_5_year_5())) {
			criteriaCnt++;
		}
		// Dividend Yield
		if (IS_SELECTED.equals(this.getDividend_yield_1()) || IS_SELECTED.equals(this.getDividend_yield_2()) || IS_SELECTED.equals(this.getDividend_yield_3())
				|| IS_SELECTED.equals(this.getDividend_yield_4()) || IS_SELECTED.equals(this.getDividend_yield_5()) || IS_SELECTED.equals(this.getDividend_yield_6())
				|| IS_SELECTED.equals(this.getDividend_yield_and_1()) || IS_SELECTED.equals(this.getDividend_yield_and_2())) {
			criteriaCnt++;
		}
		// 52 Week High
		if (Validation.isNumber(this.get_52_week_high()) && IS_SELECTED.equals(this.get_52_week_high_1())) {
			criteriaCnt++;
		}
		// 52 Week Low
		if (Validation.isNumber(this.get_52_week_low()) && IS_SELECTED.equals(this.get_52_week_low_1())) {
			criteriaCnt++;
		}
		/* Price closes [Above/Below] simple moving average (SMA) */
		if ((IS_SELECTED.equals(this.getAbove_below_1()) || IS_SELECTED.equals(this.getAbove_below_2()))
				&& (IS_SELECTED.equals(this.getPrice_closes_sam_1()) || IS_SELECTED.equals(this.getPrice_closes_sam_2()) || IS_SELECTED.equals(this.getPrice_closes_sam_3())
						|| IS_SELECTED.equals(this.getPrice_closes_sam_4()) || IS_SELECTED.equals(this.getPrice_closes_sam_5()) || IS_SELECTED.equals(this.getPrice_closes_sam_6())
						|| IS_SELECTED.equals(this.getPrice_closes_sam_7()) || IS_SELECTED.equals(this.getPrice_closes_sam_8()))) {
			criteriaCnt++;
		}
		/* 13 day SMA is [Above/Below] its 50 day SMA */
		if (IS_SELECTED.equals(this.get_13_day_sma_1()) || IS_SELECTED.equals(this.get_13_day_sma_2())) {
			criteriaCnt++;
		}
		return criteriaCnt;
	}
}
