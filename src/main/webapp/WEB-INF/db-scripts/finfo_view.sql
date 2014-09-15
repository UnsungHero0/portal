-- -----------------------------------------------------
-- Table `IFO_BALANCESHEET_VIEW`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS IFO_BALANCESHEET_VIEW                       
(                                                        
  COMPANY_ID      NUMBER(10),                            
  DATA_HEADER_ID  NUMBER(10) NOT NULL,
  REPORT_TYPE     VARCHAR2(10) NOT NULL,
  FISCAL_DATE     DATE NOT NULL,
  REPORT_DATE     DATE,                                  
  CURRENCY_CODE   VARCHAR2(10) NOT NULL,
  ITEM_CODE       NUMBER(10),                            
  ITEM_NAME       VARCHAR2(250),                    
  NUMERIC_VALUE   NUMBER(20,4),                            
  TEXT_VALUE      VARCHAR2(100),                    
  DISPLAY_ORDER   NUMBER(10),                            
  DISPLAY_LEVEL   NUMBER(10),                            
  LOCALE          VARCHAR2(10)                       
);
CREATE INDEX IDX_IFO_BALANCESHEET_VIEW_COMPANY_ID ON IFO_BALANCESHEET_VIEW(`COMPANY_ID`);
CREATE INDEX IDX_IFO_BALANCESHEET_VIEW_LOCALE ON IFO_BALANCESHEET_VIEW(`LOCALE`);
                                                         
-- -----------------------------------------------------
-- Table `IFO_BREAKDOWN_VIEW`
-- -----------------------------------------------------                           
CREATE  TABLE IF NOT EXISTS IFO_BREAKDOWN_VIEW                          
(                                                        
  COMPANY_ID           NUMBER(10),                       
  STATE_OWNERSHIP      NUMBER(20,4),                     
  INTERNAL_MANAGEMENT  NUMBER(20,4),                     
  STRATEGIC_INVESTOR   NUMBER(20,4),                     
  FOREIGN_OWNERSHIP    NUMBER(20,4),                           
  OTHER                NUMBER(20,4)                            
);                                              
CREATE INDEX IDX_IFO_BREAKDOWN_VIEW_COMPANY_ID ON IFO_BREAKDOWN_VIEW(`COMPANY_ID`);

-- -----------------------------------------------------
-- Table `IFO_CASHFLOW_VIEW`
-- -----------------------------------------------------                                                          
CREATE  TABLE IF NOT EXISTS IFO_CASHFLOW_VIEW                           
(                                                        
  COMPANY_ID      NUMBER(10),                            
  DATA_HEADER_ID  NUMBER(10) NOT NULL,
  REPORT_TYPE     VARCHAR2(10) NOT NULL,
  FISCAL_DATE     DATE NOT NULL,
  REPORT_DATE     DATE,
  CURRENCY_CODE   VARCHAR2(10) NOT NULL,
  ITEM_CODE       NUMBER(10),                            
  ITEM_NAME       VARCHAR2(120),                    
  NUMERIC_VALUE   NUMBER(20, 2),                            
  TEXT_VALUE      VARCHAR2(100),                    
  DISPLAY_ORDER   NUMBER(10),                            
  DISPLAY_LEVEL   NUMBER(10),                            
  LOCALE          VARCHAR2(10)                       
);                                              
CREATE INDEX IDX_IFO_CASHFLOW_VIEW_COMPANY_ID ON IFO_CASHFLOW_VIEW(`COMPANY_ID`);
CREATE INDEX IDX_IFO_CASHFLOW_VIEW_LOCALE ON IFO_CASHFLOW_VIEW(`LOCALE`);

-- -----------------------------------------------------
-- Table `IFO_COMPANY_CALC_VIEW`
-- ----------------------------------------------------- 
CREATE  TABLE IF NOT EXISTS IFO_COMPANY_CALC_VIEW                       
(    
  EXCHANGE_CODE    VARCHAR2(20),                                   
  SEC_CODE         VARCHAR2(20),                    
  INDUSTRY_CODE    VARCHAR2(20),                    
  INDUSTRY_NAME    VARCHAR2(100),                   
  SECTOR_CODE      VARCHAR2(20),                    
  SECTOR_NAME      VARCHAR2(100),                    
  PE               NUMBER(20,4),                               
  PB               NUMBER(20,4),                               
  SCOPE_MAKET_CAP  NUMBER(20,4),                               
  SCOPE_ASSET      NUMBER(20,4),                               
  SCOPE_EQUITY     NUMBER(20,4),                               
  GROWTH_ASSET     NUMBER(20,4),                               
  GROWTH_REVENUE   NUMBER(20,4),                               
  ROA              NUMBER(20,4),                               
  ROE              NUMBER(20,4),                               
  PROFIT_MARGIN    NUMBER(20,4),                               
  DEBT_EQUITY      NUMBER(20,4),                               
  CURRENT_RATIO    NUMBER(20,4),                               
  EBITDA           NUMBER(20,4),                               
  LOCALE           VARCHAR2(10)                      
);                                              
CREATE INDEX IDX_IFO_COMPANY_CALC_VIEW_SEC_CODE ON IFO_COMPANY_CALC_VIEW(`SEC_CODE`);

-- -----------------------------------------------------
-- Table `IFO_COMPANY_DOCUMENT`
-- ----------------------------------------------------- 
CREATE  TABLE IF NOT EXISTS IFO_COMPANY_DOCUMENT                        
(                                                        
  COMPANY_ID     NUMBER(10),                             
  DOCUMENT_ID    NUMBER(10),                             
  CREATED_DATE   DATE,                                   
  CREATED_BY     VARCHAR2(20),                      
  MODIFIED_DATE  DATE,                                   
  MODIFIED_BY    VARCHAR2(20)                       
);                                              
CREATE INDEX IDX_IFO_COMPANY_DOCUMENT_COMPANY_ID ON IFO_COMPANY_DOCUMENT(`COMPANY_ID`);
CREATE INDEX IDX_IFO_COMPANY_DOCUMENT_DOCUMENT_ID ON IFO_COMPANY_DOCUMENT(`DOCUMENT_ID`);

-- -----------------------------------------------------
-- Table `IFO_COMPANY_NAME_VIEW`
-- -----------------------------------------------------                                                          
CREATE  TABLE IF NOT EXISTS IFO_COMPANY_NAME_VIEW                       
(                                                        
  COMPANY_ID          NUMBER(10),                        
  SYMBOL              VARCHAR2(16),                 
  COMPANY_NAME        VARCHAR2(255),                
  COMPANY_FULL_NAME   VARCHAR2(255),                
  ABB_NAME            VARCHAR2(255),                
  FIRST_TRADING_DATE  DATE                               
);                                              
CREATE INDEX IDX_IFO_COMPANY_NAME_VIEW_COMPANY_ID ON IFO_COMPANY_NAME_VIEW(`COMPANY_ID`);
CREATE INDEX IDX_IFO_COMPANY_NAME_VIEW_SYMBOL ON IFO_COMPANY_NAME_VIEW(`SYMBOL`);

-- -----------------------------------------------------
-- Table `IFO_COMPANY_OFFICERS_VIEW`
-- -----------------------------------------------------                                                         
CREATE  TABLE IF NOT EXISTS IFO_COMPANY_OFFICERS_VIEW                   
(                                                        
  COMPANY_ID         NUMBER(10),                         
  NAME               VARCHAR2(152),                 
  POSITION           VARCHAR2(60),                  
  LOCALE             VARCHAR2(10),                   
  DISPLAY_ORDER      NUMBER(5),                          
  COMPANY_ROLE_TYPE  VARCHAR2(10),                  
  EXPIRATION_DATE    DATE,                               
  EFFECTIVE_DATE     DATE                                
);                                              
CREATE INDEX IDX_IFO_COMPANY_OFFICERS_VIEW_COMPANY_ID ON IFO_COMPANY_OFFICERS_VIEW(`COMPANY_ID`);

-- -----------------------------------------------------
-- Table `IFO_COMPANY_PROFILE_VIEW`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS IFO_COMPANY_PROFILE_VIEW                    
(                                                        
  COMPANY_ID          NUMBER(10),                        
  VIETNAMESE_NAME     VARCHAR2(255),                
  INTERNATIONAL_NAME  VARCHAR2(255),                
  ABBREVIATION        VARCHAR2(255),                
  HEADQUARTER         VARCHAR2(255),                
  TELEPHONE           VARCHAR2(50),                 
  FAX                 VARCHAR2(50),                 
  WEBSITE             VARCHAR2(50),                 
  INDEX_MEMBERSHIP    VARCHAR2(60),                 
  SECTOR              VARCHAR2(60),                 
  INDUSTRY            VARCHAR2(100),                
  NUMBER_OF_BRANCHES  NUMBER,                            
  FULL_EMPLOYEE       NUMBER,                            
  IPO_INFORMATION     VARCHAR2(3),                      
  IPO_LOCATION        VARCHAR2(3),                      
  IPO_HIGHEST_BID     NUMBER(20,4),                            
  IPO_LOWEST_BID      NUMBER(20,4),                            
  IPO_AVERAGE_BID     NUMBER(20,4),                            
  OVERVIEW            CLOB,                              
  OVERVIEW_STATUS     NUMBER,                            
  HISTORY             CLOB,                              
  MARKET_POSITION     CLOB,                              
  PLAN                CLOB,                              
  MAIN_BUSINESS       CLOB,                              
  LOCALE              VARCHAR2(10)                   
);                                              
CREATE INDEX IDX_IFO_COMPANY_PROFILE_VIEW_COMPANY_ID ON IFO_COMPANY_PROFILE_VIEW(`COMPANY_ID`);
CREATE INDEX IDX_IFO_COMPANY_PROFILE_VIEW_LOCALE ON IFO_COMPANY_PROFILE_VIEW(`LOCALE`);

-- -----------------------------------------------------
-- Table `IFO_COMPANY_SNAPSHOT_VIEW`
-- -----------------------------------------------------                                                         
CREATE  TABLE IF NOT EXISTS IFO_COMPANY_SNAPSHOT_VIEW                   
(                                                        
  COMPANY_ID             NUMBER(10),                     
  EXCHANGE_CODE          VARCHAR2(10),              
  WEEK_HIGH              NUMBER(20,4),                         
  WEEK_LOW               NUMBER(20,4),                         
  MARKET_CAPITAL         NUMBER(20,4),                         
  SHARE_OUTSTANDING      NUMBER(20,4),                         
  DECLARED_DIVIDEND      NUMBER(20,4),                   
  DIVIDEND_YIELD         NUMBER(20,4),                         
  EX_DIVIDEND_DATE       DATE,                           
  DIVIDEND_PAYABLE_DATE  DATE,                           
  EPS                    NUMBER(20,4),                         
  ROA                    NUMBER(20,4),                         
  ROE                    NUMBER(20,4),                         
  LEVERAGE               NUMBER(20,4),                         
  EARNINGS_VALUE         NUMBER(20,4),                         
  BOOK_VALUE             NUMBER(20,4),                         
  BETA                   NUMBER(20,4),                         
  AVERAGE_VOLUMN         NUMBER(20,4),                         
  PE                     NUMBER(20,4),                         
  FOREIGN_OWNERSHIP      NUMBER(20,4),                         
  FOREIGN_SOLD_VOL       NUMBER,                         
  FOREIGN_BOUGHT_VOL     NUMBER,                         
  COMPANY_OVERVIEW       CLOB,                           
  OVERVIEW_STATUS        NUMBER,                         
  LOCALE                 VARCHAR2(10)                
);                                              
CREATE INDEX IDX_IFO_COMPANY_SNAPSHOT_VIEW_COMPANY_ID ON IFO_COMPANY_SNAPSHOT_VIEW(`COMPANY_ID`);
CREATE INDEX IDX_IFO_COMPANY_SNAPSHOT_VIEW_LOCALE ON IFO_COMPANY_SNAPSHOT_VIEW(`LOCALE`);

-- -----------------------------------------------------
-- Table `IFO_DOCUMENT`
-- -----------------------------------------------------                                                           
CREATE  TABLE IF NOT EXISTS IFO_DOCUMENT                                
(                                                        
  DOCUMENT_ID    NUMBER(10),                             
  DOCUMENT_TYPE  VARCHAR2(20)  NOT NULL,
  TITLE          VARCHAR2(255),                     
  CONTRIBUTOR    VARCHAR2(150),                     
  AUTHOR         VARCHAR2(150),                     
  RELEASED_DATE  DATE,                                   
  ABSTRACT       VARCHAR2(2400),                    
  FILE_NAME      VARCHAR2(150) NOT NULL,
  FILE_PATH      VARCHAR2(300),                     
  STATUS         VARCHAR2(20),                      
  ACCESS_LEVEL   NUMBER(10),                             
  ACCESS_TYPE    NUMBER(10),                             
  CREATED_DATE   DATE,                                   
  CREATED_BY     VARCHAR2(20),                      
  MODIFIED_DATE  DATE,                                   
  MODIFIED_BY    VARCHAR2(20),                      
  LOCALE         VARCHAR2(10)                       
);                                              
CREATE INDEX IDX_IFO_DOCUMENT_DOCUMENT_ID ON IFO_DOCUMENT(`DOCUMENT_ID`);
CREATE INDEX IDX_IFO_DOCUMENT_LOCALE ON IFO_DOCUMENT(`LOCALE`);
                                               
-- -----------------------------------------------------
-- Table `IFO_FINANCIAL_HIGHLIGHT_VIEW`
-- ----------------------------------------------------- 
CREATE  TABLE IF NOT EXISTS IFO_FINANCIAL_HIGHLIGHT_VIEW                
(                                                        
  COMPANY_ID      NUMBER(10),                            
  DATA_HEADER_ID  NUMBER(10) NOT NULL,
  REPORT_TYPE     VARCHAR2(10) NOT NULL,
  FISCAL_DATE     DATE NOT NULL,
  REPORT_DATE     DATE,                                  
  CURRENCY_CODE   VARCHAR2(10) NOT NULL,
  ITEM_CODE       NUMBER(10),                            
  ITEM_NAME       VARCHAR2(120),                    
  NUMERIC_VALUE   NUMBER(20,4),                            
  TEXT_VALUE      VARCHAR2(20),                     
  DISPLAY_ORDER   NUMBER(10),                            
  DISPLAY_LEVEL   NUMBER(10),                            
  LOCALE          VARCHAR2(10)                       
);
CREATE INDEX IDX_IFO_FINANCIAL_HIGHLIGHT_VIEW_COMPANY_ID ON IFO_FINANCIAL_HIGHLIGHT_VIEW(`COMPANY_ID`);                                              
                                                         
-- -----------------------------------------------------
-- Table `IFO_FOREIGN_TRADING_VIEW`
-- ----------------------------------------------------- 
CREATE  TABLE IF NOT EXISTS IFO_FOREIGN_TRADING_VIEW                    
(                                                        
  FLOOR_CODE      VARCHAR2(10) NOT NULL,
  TRADING_DATE    DATE NOT NULL,
  SEC_CODE        VARCHAR2(20) NOT NULL,
  TOTAL_ROOM      NUMBER,                                
  CURRENT_ROOM    NUMBER,                                
  BUYING_VOLUME   NUMBER NOT NULL,
  BUYING_VALUE    NUMBER(20,4) NOT NULL,
  SELLING_VOLUME  NUMBER NOT NULL,
  SELLING_VALUE   NUMBER(20,4) NOT NULL 
);
CREATE INDEX IDX_IFO_FOREIGN_TRADING_VIEW_FLOOR_CODE ON IFO_FOREIGN_TRADING_VIEW(`FLOOR_CODE`);
CREATE INDEX IDX_IFO_FOREIGN_TRADING_VIEW_TRADING_DATE ON IFO_FOREIGN_TRADING_VIEW(`TRADING_DATE`);
                                                         
-- -----------------------------------------------------
-- Table `IFO_INCOME_VIEW`
-- ----------------------------------------------------- 
CREATE  TABLE IF NOT EXISTS IFO_INCOME_VIEW                             
(                                                        
  COMPANY_ID      NUMBER(10),                            
  DATA_HEADER_ID  NUMBER(10) NOT NULL,
  REPORT_TYPE     VARCHAR2(10) NOT NULL,
  FISCAL_DATE     DATE NOT NULL,
  REPORT_DATE     DATE,                                  
  CURRENCY_CODE   VARCHAR2(10) NOT NULL,
  ITEM_CODE       NUMBER(10),                            
  ITEM_NAME       VARCHAR2(150),                    
  NUMERIC_VALUE   NUMBER(20,4),                            
  TEXT_VALUE      VARCHAR2(100),                    
  DISPLAY_ORDER   NUMBER(10),                            
  DISPLAY_LEVEL   NUMBER(10),                            
  LOCALE          VARCHAR2(10)                       
);                                              
CREATE INDEX IDX_IFO_INCOME_VIEW_COMPANY_ID ON IFO_INCOME_VIEW(`COMPANY_ID`);
CREATE INDEX IDX_IFO_INCOME_VIEW_LOCALE ON IFO_INCOME_VIEW(`LOCALE`);

-- -----------------------------------------------------
-- Table `IFO_INDUSTRY_CALC_VIEW`
-- ----------------------------------------------------- 
CREATE  TABLE IF NOT EXISTS IFO_INDUSTRY_CALC_VIEW                      
(                                                        
  INDUSTRY_CODE    VARCHAR2(20),                    
  INDUSTRY_NAME    VARCHAR2(150),                   
  SECTOR_CODE      VARCHAR2(20),                    
  SECTOR_NAME      VARCHAR2(150),                    
  PE               NUMBER(20,4),                               
  PB               NUMBER(20,4),                               
  SCOPE_MAKET_CAP  NUMBER(20,4),                               
  SCOPE_ASSET      NUMBER(20,4),                               
  SCOPE_EQUITY     NUMBER(20,4),                               
  GROWTH_ASSET     NUMBER(20,4),                               
  GROWTH_REVENUE   NUMBER(20,4),                               
  ROA              NUMBER(20,4),                               
  ROE              NUMBER(20,4),                               
  PROFIT_MARGIN    NUMBER(20,4),                               
  DEBT_EQUITY      NUMBER(20,4),                               
  CURRENT_RATIO    NUMBER(20,4),                               
  EBITDA           NUMBER(20,4),                               
  LOCALE           VARCHAR2(10)                      
);                                              
CREATE INDEX IDX_IFO_INDUSTRY_CALC_VIEW_INDUSTRY_CODE ON IFO_INDUSTRY_CALC_VIEW(`INDUSTRY_CODE`);
CREATE INDEX IDX_IFO_INDUSTRY_CALC_VIEW_SECTOR_CODE ON IFO_INDUSTRY_CALC_VIEW(`SECTOR_CODE`);
CREATE INDEX IDX_IFO_INDUSTRY_CALC_VIEW_LOCALE ON IFO_INDUSTRY_CALC_VIEW(`LOCALE`);

-- -----------------------------------------------------
-- Table `IFO_INSIDER_TRANSACTION_VIEW`
-- -----------------------------------------------------                                                          
CREATE  TABLE IF NOT EXISTS IFO_INSIDER_TRANSACTION_VIEW                
(                                                        
  COMPANY_ID        NUMBER(10),                          
  INSIDER           VARCHAR2(255),                  
  POSITION          VARCHAR2(60),                   
  SHARES_HELD       NUMBER(20,4),                        
  TRANSACTION       VARCHAR2(10),                   
  PRICE             NUMBER(20,4),                        
  TRANSACTION_DATE  DATE,                                
  LOCALE            VARCHAR2(10),                    
  INSIDER_ID        NUMBER(10),                          
  CREATED_BY        VARCHAR2(20),                   
  CREATED_DATE      DATE,                                
  MODIFIED_BY       VARCHAR2(20),                   
  MODIFIED_DATE     DATE                                 
);                                              
CREATE INDEX IDX_IFO_INSIDER_TRANSACTION_VIEW_COMPANY_ID ON IFO_INSIDER_TRANSACTION_VIEW(`COMPANY_ID`);
CREATE INDEX IDX_IFO_INSIDER_TRANSACTION_VIEW_TRANSACTION_DATE ON IFO_INSIDER_TRANSACTION_VIEW(`TRANSACTION_DATE`);
CREATE INDEX IDX_IFO_INSIDER_TRANSACTION_VIEW_LOCALE ON IFO_INSIDER_TRANSACTION_VIEW(`LOCALE`);

-- -----------------------------------------------------
-- Table `IFO_INVESTOR_RIGHTS_VIEW`
-- -----------------------------------------------------                                                          
CREATE  TABLE IF NOT EXISTS IFO_INVESTOR_RIGHTS_VIEW                    
(                                                        
  COMPANY_ID          NUMBER(10),                        
  SEC_CODE            VARCHAR2(20)         NOT NULL,
  X_DATE              DATE,                              
  RATIO               NUMBER(20,4),                      
  SHARES_ACTION_DESC  VARCHAR2(250),                 
  LOCALE              VARCHAR2(10)                   
);
CREATE INDEX IDX_IFO_INVESTOR_RIGHTS_VIEW_COMPANY_ID ON IFO_INVESTOR_RIGHTS_VIEW(`COMPANY_ID`);
CREATE INDEX IDX_IFO_INVESTOR_RIGHTS_VIEW_LOCALE ON IFO_INVESTOR_RIGHTS_VIEW(`LOCALE`);
                                                         
-- -----------------------------------------------------
-- Table `IFO_KEY_RATIO_VIEW`
-- ----------------------------------------------------- 
CREATE  TABLE IF NOT EXISTS IFO_KEY_RATIO_VIEW                          
(                                                        
  COMPANY_ID      NUMBER(10),                            
  DATA_HEADER_ID  NUMBER,                                
  REPORT_TYPE     VARCHAR2(10),                          
  FISCAL_DATE     VARCHAR2(20),                         
  REPORT_DATE     VARCHAR2(20),                         
  CURRENCY_CODE   VARCHAR2(10),                          
  ITEM_CODE       NUMBER(10),                            
  ITEM_NAME       VARCHAR2(120),                    
  NUMERIC_VALUE   NUMBER(22,4),                          
  TEXT_VALUE      VARCHAR2(100),                    
  DISPLAY_ORDER   NUMBER(10),                            
  DISPLAY_LEVEL   NUMBER(10),                            
  LOCALE          VARCHAR2(10)                       
);
CREATE INDEX IDX_IFO_KEY_RATIO_VIEW_COMPANY_ID ON IFO_KEY_RATIO_VIEW(`COMPANY_ID`);
CREATE INDEX IDX_IFO_KEY_RATIO_VIEW_LOCALE ON IFO_KEY_RATIO_VIEW(`LOCALE`);
                                                         
-- -----------------------------------------------------
-- Table `IFO_MAJOR_HOLDER_VIEW`
-- ----------------------------------------------------- 
CREATE  TABLE IF NOT EXISTS IFO_MAJOR_HOLDER_VIEW                       
(                                                        
  COMPANY_ID     NUMBER(10),                             
  HOLDERS        VARCHAR2(255),                     
  POSITION       VARCHAR2(60),                      
  SHARES         NUMBER(10),                             
  REPORTED       DATE,                                   
  OWNERSHIP      NUMBER,                                 
  LOCALE         VARCHAR2(10),                       
  OWNER_ID       NUMBER(10),                             
  CREATED_BY     VARCHAR2(20),                      
  CREATED_DATE   DATE,                                   
  MODIFIED_BY    VARCHAR2(20),                      
  MODIFIED_DATE  DATE                                    
);                                              
CREATE INDEX IDX_IFO_MAJOR_HOLDER_VIEW_COMPANY_ID ON IFO_MAJOR_HOLDER_VIEW(`COMPANY_ID`);
CREATE INDEX IDX_IFO_MAJOR_HOLDER_VIEW_LOCALE ON IFO_MAJOR_HOLDER_VIEW(`LOCALE`);

                                                             
-- -----------------------------------------------------
-- Table `IFO_SEC_PRICE_VIEW`
-- ----------------------------------------------------- 
CREATE  TABLE IF NOT EXISTS IFO_SEC_PRICE_VIEW                          
(                                                        
  COMPANY_ID        NUMBER(10),                          
  EXCHANGE_CODE     VARCHAR2(10),                   
  SYMBOL            VARCHAR2(16),                   
  OPEN_PRICE        NUMBER(20,4),                              
  HIGH_PRICE        NUMBER(20,4),                              
  LOW_PRICE         NUMBER(20,4),                              
  CLOSE_PRICE       NUMBER(20,4),                              
  AVERAGE_PRICE     NUMBER(22,4),                        
  VOLUME            NUMBER(20),                              
  TRANS_DATE        DATE,                                
  AD_OPEN_PRICE     NUMBER(20,4),                        
  AD_HIGH_PRICE     NUMBER(20,4),                        
  AD_LOW_PRICE      NUMBER(20,4),                        
  AD_CLOSE_PRICE    NUMBER(20,4),                        
  AD_AVERAGE_PRICE  NUMBER(20,4),                        
  RIGHTS_TYPE       VARCHAR2(10)                    
);
CREATE INDEX IDX_IFO_SEC_PRICE_VIEW_COMPANY_ID ON IFO_SEC_PRICE_VIEW(`COMPANY_ID`);                                              
CREATE INDEX IDX_IFO_SEC_PRICE_VIEW_EXCHANGE_CODE ON IFO_SEC_PRICE_VIEW(`EXCHANGE_CODE`);
CREATE INDEX IDX_IFO_SEC_PRICE_VIEW_SYMBOL ON IFO_SEC_PRICE_VIEW(`SYMBOL`);
CREATE INDEX IDX_IFO_SEC_PRICE_VIEW_TRANS_DATE ON IFO_SEC_PRICE_VIEW(`TRANS_DATE`);
                                                         
-- -----------------------------------------------------
-- Table `IFO_SEC_PRICE_VIEW`
-- ----------------------------------------------------- 
CREATE  TABLE IF NOT EXISTS IFO_SECTOR_CALC_VIEW                        
(                                                        
  SECTOR_CODE      VARCHAR2(10),                    
  SECTOR_NAME      VARCHAR2(150),                    
  PE               NUMBER(20,4),                               
  PB               NUMBER(20,4),                               
  SCOPE_MAKET_CAP  NUMBER(20,4),                               
  SCOPE_ASSET      NUMBER(20,4),                               
  SCOPE_EQUITY     NUMBER(20,4),                               
  GROWTH_ASSET     NUMBER(20,4),                               
  GROWTH_REVENUE   NUMBER(20,4),                               
  ROA              NUMBER(20,4),                               
  ROE              NUMBER(20,4),                               
  PROFIT_MARGIN    NUMBER(20,4),                               
  DEBT_EQUITY      NUMBER(20,4),                               
  CURRENT_RATIO    NUMBER(20,4),                               
  EBITDA           NUMBER(20,4),                               
  LOCALE           VARCHAR2(10)                      
);                                              
CREATE INDEX IDX_IFO_SECTOR_CALC_VIEW_SECTOR_CODE ON IFO_SECTOR_CALC_VIEW(`SECTOR_CODE`);
CREATE INDEX IDX_IFO_SECTOR_CALC_VIEW_LOCALE ON IFO_SECTOR_CALC_VIEW(`LOCALE`);
                                                         
-- -----------------------------------------------------
-- Table `IFO_TRADING_STATISTICS_VIEW`
-- ----------------------------------------------------- 
CREATE  TABLE IF NOT EXISTS IFO_TRADING_STATISTICS_VIEW                 
(                                                        
  SEC_CODE       VARCHAR2(20),                      
  FLOOR_CODE     VARCHAR2(10),                       
  TRANS_DATE     DATE,                                   
  OPEN_PRICE     NUMBER(20,4),                                 
  HIGH_PRICE     NUMBER(20,4),                                 
  LOW_PRICE      NUMBER(20,4),                                 
  CLOSE_PRICE    NUMBER(20,4),                                 
  EVERAGE_PRICE  NUMBER(20,4),                           
  BID_ORDER      NUMBER(20,4),                                 
  BID_VOLUMN     NUMBER(20),                                 
  OFFER_ORDER    NUMBER(20,4),                                 
  OFFER_VOLUMN   NUMBER(20),                                 
  TOTAL_VOLUMN   NUMBER(20),                                 
  TOTAL_VALUE    NUMBER(20,4)                                  
);
CREATE INDEX IDX_IFO_TRADING_STATISTICS_VIEW_SEC_CODE ON IFO_TRADING_STATISTICS_VIEW(`SEC_CODE`);
CREATE INDEX IDX_IFO_TRADING_STATISTICS_VIEW_FLOOR_CODE ON IFO_TRADING_STATISTICS_VIEW(`FLOOR_CODE`);
CREATE INDEX IDX_IFO_TRADING_STATISTICS_VIEW_TRANS_DATE ON IFO_TRADING_STATISTICS_VIEW(`TRANS_DATE`);

-- -----------------------------------------------------
-- Table `IFO_VALUATION_MEASURES_VIEW`
-- -----------------------------------------------------                                                         
CREATE TABLE IF NOT EXISTS IFO_VALUATION_MEASURES_VIEW                 
(                                                        
  COMPANY_ID     NUMBER(10),                             
  ITEM_CODE      NUMBER(10),                             
  ITEM_NAME      VARCHAR2(120),                     
  NUMERIC_VALUE  NUMBER(22,4),                           
  TEXT_VALUE     VARCHAR2(100),                     
  DISPLAY_ORDER  NUMBER(10),                             
  DISPLAY_LEVEL  NUMBER(10),                             
  LOCALE         VARCHAR2(5)                        
);
CREATE INDEX IDX_IFO_VALUATION_MEASURES_VIEW_COMPANY_ID ON IFO_VALUATION_MEASURES_VIEW(`COMPANY_ID`);
CREATE INDEX IDX_IFO_VALUATION_MEASURES_VIEW_LOCALE ON IFO_VALUATION_MEASURES_VIEW(`LOCALE`);
                         
-- -----------------------------------------------------
-- Table `IFO_MARKET_CALENDAR_VIEW`
-- ----------------------------------------------------- 
CREATE  TABLE IF NOT EXISTS IFO_MARKET_CALENDAR_VIEW                    
(                                                        
  COMPANY_ID     NUMBER(10),                             
  SYMBOL         VARCHAR2(16),                      
  RIGHTS_DATE    DATE,                                   
  REGISTER_DATE  DATE,                                   
  EVENT_DATE     DATE,                                   
  EVENT_TYPE     VARCHAR2(10),                      
  EVENT_DESC     VARCHAR2(250),                      
  LOCALE         VARCHAR2(10),                       
  EVENT_NOTE     VARCHAR2(500)                      
);                                              
CREATE INDEX IDX_IFO_MARKET_CALENDAR_VIEW_COMPANY_ID ON IFO_MARKET_CALENDAR_VIEW(`COMPANY_ID`);
CREATE INDEX IDX_IFO_MARKET_CALENDAR_VIEW_SYMBOL ON IFO_MARKET_CALENDAR_VIEW(`SYMBOL`);
CREATE INDEX IDX_IFO_MARKET_CALENDAR_VIEW_LOCALE ON IFO_MARKET_CALENDAR_VIEW(`LOCALE`);

-- -----------------------------------------------------
-- Table `IFO_NEWS`
-- -----------------------------------------------------                                                          
CREATE  TABLE IF NOT EXISTS IFO_NEWS                                    
(                                                        
  NEWS_ID        NUMBER(10),                             
  NEWS_TYPE      VARCHAR2(20),                      
  NEWS_HEADER    VARCHAR2(255),                     
  NEWS_ABSTRACT  VARCHAR2(1000),                    
  NEWS_CONTENT   CLOB,                                   
  NEWS_DATE      DATE,                                   
  NEWS_RANK      NUMBER(10),                             
  STATUS         VARCHAR2(50),                      
  NEWS_URL       VARCHAR2(255),                     
  NEWS_SOURCE    VARCHAR2(255),                     
  ACCESS_TYPE    NUMBER(10),                             
  LOCALE         VARCHAR2(20),                      
  CREATED_DATE   DATE,                                   
  CREATED_BY     VARCHAR2(20),                      
  MODIFIED_DATE  DATE,                                   
  MODIFIED_BY    VARCHAR2(20),                      
  HASHCODE       VARCHAR2(255),                     
  SOURCE_ID      NUMBER(10),                             
  NEWS_RESOURCE  VARCHAR2(255),                     
  IS_HOT_NEWS    VARCHAR2(10)                        
);                             
CREATE INDEX IDX_IFO_NEWS_NEWS_ID ON IFO_NEWS(`NEWS_ID`);                 
CREATE INDEX IDX_IFO_NEWS_NEWS_TYPE ON IFO_NEWS(`NEWS_TYPE`);
CREATE INDEX IDX_IFO_NEWS_STATUS ON IFO_NEWS(`STATUS`);
CREATE INDEX IDX_IFO_NEWS_IS_HOT_NEWS ON IFO_NEWS(`IS_HOT_NEWS`);                                                         

-- -----------------------------------------------------
-- Table `ATTACHMENTS`
-- ----------------------------------------------------- 
CREATE TABLE ATTACHMENTS (
    ATTACMENT_ID NUMBER(10) NOT NULL,
    NEWS_ID NUMBER(10) NOT NULL,
    ORIGINAL_LINK VARCHAR2(400) NOT NULL,
    URI_LINK VARCHAR2(400) NOT NULL,
    DESCRIPTION VARCHAR2(200),
    CONSTRAINT ATT_PK PRIMARY KEY (ATTACMENT_ID)
);
CREATE INDEX IDX_ATTACHMENTS_NEWS_ID ON ATTACHMENTS(`NEWS_ID`);

-- -----------------------------------------------------
-- Table `IFO_COMPANY_NEWS_VIEW`
-- -----------------------------------------------------                                                         
CREATE  TABLE IF NOT EXISTS IFO_COMPANY_NEWS_VIEW                       
(                                                        
  COMPANY_ID  NUMBER(10)                        NOT NULL,
  NEWS_ID     NUMBER(10)                        NOT NULL 
);                                              
CREATE INDEX IDX_IFO_COMPANY_NEWS_VIEW_COMPANY_ID ON IFO_COMPANY_NEWS_VIEW(`COMPANY_ID`);
CREATE INDEX IDX_IFO_COMPANY_NEWS_VIEW_NEWS_ID ON IFO_COMPANY_NEWS_VIEW(`NEWS_ID`);

COMMIT;
                                                         
                                                         