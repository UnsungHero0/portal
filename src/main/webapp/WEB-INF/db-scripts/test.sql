select * from STOCK_EXCHANGE;
-- select * from ITEM_CODE_REF;

-- select * from COMPANY_CALC_VIEW;
-- select * from INDUSTRY_CALC_VIEW;
-- select * from SECTOR_CALC_VIEW;

-- select * from ANALYSIS_VIEW;
-- select * from COMPANY_ITEM_CALC_VIEW;

-- select * from COMPANY_ITEM_CALC_VIEW WHERE SYMBOL='COM' ORDER BY item_code;

-- select distinct a.symbol from COMPANY_ITEM_CALC_VIEW a
-- where (a.item_code = '51015' and a.NUMERIC_VALUE > 20 ) 
-- OR (a.item_code = '51012' and (a.NUMERIC_VALUE between 1 and 20.4))

-- where (a.item_code = '51015' and (a.NUMERIC_VALUE > 20 and a.NUMERIC_VALUE < 100) )

-- select count(distinct a.item_code) from COMPANY_ITEM_CALC_VIEW a

-- select count(distinct a.item_code) from ANALYSIS_VIEW a
-- select count(distinct a.symbol) from ANALYSIS_VIEW a

select * from ANALYSIS_VIEW_TEMP;

select * from COMPANY_ITEM_CALC_VIEW;

select * from DB_EMBED_CONFIG;

select * from COMPANY_ITEM_CALC_VIEW_TEMP;

select count(*) from ANALYSIS_VIEW_TEMP

select count(*) from COMPANY_ITEM_CALC_VIEW_TEMP

select a.* from STOCK_EXCHANGE a



select count(a.*) from STOCK_EXCHANGE a where a.EXCHANGE_CODE = 'HASTC' OR a.EXCHANGE_CODE = 'HOSTC'

select count(a.*) from INDUSTRY_CALC_VIEW a;

select count(DISTINCT a.INDUSTRY_CODE) from INDUSTRY_CALC_VIEW a;

select a.INDUSTRY_CODE from INDUSTRY_CALC_VIEW a order by a.INDUSTRY_CODE;

select DISTINCT a.SYMBOL, a.INDUSTRY_CODE from STOCK_EXCHANGE a, COMPANY_ITEM_CALC_VIEW b WHERE a.SYMBOL=b.SYMBOL

SELECT * FROM COMPANY_ITEM_CALC_VIEW

SELECT * FROM INDUSTRY_ITEM_CALC_VIEW_TEMP

select * FROM STOCK_EXCHANGE a, SECTOR_CALC_VIEW b WHERE a.SECTOR_GROUP_CODE = b.SECTOR_CODE

select DISTINCT a.SYMBOL, a.INDUSTRY_GROUP_CODE, a.SECTOR_GROUP_CODE, a.EXCHANGE_CODE from STOCK_EXCHANGE a, COMPANY_ITEM_CALC_VIEW b WHERE a.SYMBOL=b.SYMBOL

