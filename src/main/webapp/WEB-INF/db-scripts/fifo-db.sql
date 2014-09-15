-- -----------------------------------------------------
-- Table `DB_EMBED_CONFIG`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `DB_EMBED_CONFIG` ;

CREATE  TABLE IF NOT EXISTS `DB_EMBED_CONFIG` (
	`GROUP_CODE` VARCHAR(100) NOT NULL ,
	`ITEM_CODE` VARCHAR(100) NOT NULL ,
	`NUMBER_VALUE` NUMBER(20, 2) NULL ,
	`TEXT_VALUE` VARCHAR(250) NULL ,
	`DATE_VALUE` TIMESTAMP  NULL
);

INSERT INTO `DB_EMBED_CONFIG`(`GROUP_CODE`, `ITEM_CODE`, `NUMBER_VALUE`, `DATE_VALUE`) VALUES ('SYS_CFG','INIT_DB', 1, CURRENT_TIMESTAMP());

-- -----------------------------------------------------
-- Table `STOCK_MARKET`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `STOCK_EXCHANGE` ;

CREATE  TABLE IF NOT EXISTS `STOCK_EXCHANGE` (
	`SYMBOL` VARCHAR(100) NOT NULL ,	
	`EXCHANGE_CODE` VARCHAR(100) NOT NULL ,	
	`EXCHANGE_NAME` VARCHAR(250) NOT NULL ,
	`COMPANY_ID` NUMBER(10) NULL ,	
	`COMPANY_NAME` VARCHAR(250) NULL ,
	`COMPANY_FULL_NAME` VARCHAR(250) NULL ,
	`ABB_NAME` VARCHAR(250) NULL ,
	`FIRST_TRADING_DATE` DATE NULL ,
	`INDUSTRY_CODE` VARCHAR(100) NULL ,
	`INDUSTRY_GROUP_CODE` VARCHAR(100) NULL ,
	`SECTOR_CODE` VARCHAR(100) NULL ,
	`SECTOR_GROUP_CODE` VARCHAR(100) NULL,
	PRIMARY KEY (`SYMBOL`) 
);

DROP INDEX IF EXISTS IDX_STOCK_MARKET_SYMBOL;
CREATE INDEX IDX_STOCK_MARKET_SYMBOL ON STOCK_EXCHANGE(`SYMBOL`);

DROP INDEX IF EXISTS IDX_STOCK_MARKET_EXCHANGE_CODE;
CREATE INDEX IDX_STOCK_MARKET_EXCHANGE_CODE ON STOCK_EXCHANGE(`EXCHANGE_CODE`);

-- -----------------------------------------------------
-- Table `ITEM_CODE_REF` for
-- COMPANY_ITEM_CALC_VIEW
-- INDUSTRY_CALC_VIEW
-- SECTOR_CALC_VIEW
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ITEM_CODE_REF` ;

CREATE  TABLE IF NOT EXISTS `ITEM_CODE_REF` (
	`GROUP_CODE` VARCHAR(100) NOT NULL ,	
	`ITEM_CODE` VARCHAR(100) NOT NULL ,	
	`ITEM_NAME` VARCHAR(250) NOT NULL ,
	`LOCALE` VARCHAR(20) NOT NULL
);

DROP INDEX IF EXISTS IDX_ITEM_CODE_REF_GROUP_CODE;
CREATE INDEX IDX_ITEM_CODE_REF_GROUP_CODE ON ITEM_CODE_REF(`GROUP_CODE`);

DROP INDEX IF EXISTS IDX_ITEM_CODE_REF_LOCALE;
CREATE INDEX IDX_ITEM_CODE_REF_LOCALE ON ITEM_CODE_REF(`LOCALE`);

-- -----------------------------------------------------
-- Table `COMPANY_ITEM_CALC_VIEW `
-- -----------------------------------------------------
DROP TABLE IF EXISTS `COMPANY_ITEM_CALC_VIEW` ;

CREATE  TABLE IF NOT EXISTS `COMPANY_ITEM_CALC_VIEW` (
  `SYMBOL` VARCHAR(100) NOT NULL ,
  `ITEM_CODE` VARCHAR(20) NULL ,
  `TEXT_VALUE` VARCHAR(30) NULL ,
  `NUMERIC_VALUE` NUMBER(30,4) NULL,
  `TRANS_DATE` TIMESTAMP NULL
);

DROP INDEX IF EXISTS IDX_COMPANY_ITEM_CALC_VIEW_SYMBOL;
CREATE INDEX IDX_COMPANY_ITEM_CALC_VIEW_SYMBOL ON COMPANY_ITEM_CALC_VIEW(`SYMBOL`);

-- -----------------------------------------------------
-- Table `INDUSTRY_ITEM_CALC_VIEW `
-- -----------------------------------------------------
DROP TABLE IF EXISTS `INDUSTRY_ITEM_CALC_VIEW` ;

CREATE  TABLE IF NOT EXISTS `INDUSTRY_ITEM_CALC_VIEW` (
  `INDUSTRY_CODE` VARCHAR(100) NOT NULL ,
  `ITEM_CODE` VARCHAR(20) NULL ,
  `TEXT_VALUE` VARCHAR(30) NULL ,
  `NUMERIC_VALUE` NUMBER(30,4) NULL,
  `TRANS_DATE` TIMESTAMP NULL
);

DROP INDEX IF EXISTS IDX_INDUSTRY_ITEM_CALC_VIEW_INDUSTRY_CODE;
CREATE INDEX IDX_INDUSTRY_ITEM_CALC_VIEW_INDUSTRY_CODE ON INDUSTRY_ITEM_CALC_VIEW(`INDUSTRY_CODE`);

-- -----------------------------------------------------
-- Table `IFO_SECTOR_CALC_VIEW `
-- -----------------------------------------------------
DROP TABLE IF EXISTS `SECTOR_CALC_VIEW` ;

CREATE  TABLE IF NOT EXISTS `SECTOR_CALC_VIEW` (  
  `SECTOR_CODE` VARCHAR(50) NOT NULL ,
  `PE` NUMBER(30,4) NULL,
  `PB` NUMBER(30,4) NULL,
  `SCOPE_MAKET_CAP` NUMBER(30,4) NULL,
  `SCOPE_ASSET` NUMBER(30,4) NULL,
  `SCOPE_EQUITY` NUMBER(30,4) NULL,
  `GROWTH_ASSET` NUMBER(30,4) NULL,
  `GROWTH_REVENUE` NUMBER(30,4) NULL,
  `ROA` NUMBER(30,4) NULL,
  `ROE` NUMBER(30,4) NULL,
  `PROFIT_MARGIN` NUMBER(30,4) NULL,
  `DEBT_EQUITY` NUMBER(30,4) NULL,
  `CURRENT_RATIO` NUMBER(30,4) NULL,
  `EBITDA` NUMBER(30,4) NULL,
  PRIMARY KEY (`SECTOR_CODE`)
);

DROP INDEX IF EXISTS IDX_SECTOR_CALC_VIEW_SECTOR_CODE;
CREATE INDEX IDX_SECTOR_CALC_VIEW_SECTOR_CODE ON SECTOR_CALC_VIEW(`SECTOR_CODE`);

-- -----------------------------------------------------
-- Table `IFO_INDUSTRY_CALC_VIEW `
-- -----------------------------------------------------
DROP TABLE IF EXISTS `INDUSTRY_CALC_VIEW` ;

CREATE  TABLE IF NOT EXISTS `INDUSTRY_CALC_VIEW` (
  `INDUSTRY_CODE` VARCHAR(50) NOT NULL ,
  `SECTOR_CODE` VARCHAR(50) NOT NULL ,
  `PE` NUMBER(30,4) NULL,
  `PB` NUMBER(30,4) NULL,
  `SCOPE_MAKET_CAP` NUMBER(30,4) NULL,
  `SCOPE_ASSET` NUMBER(30,4) NULL,
  `SCOPE_EQUITY` NUMBER(30,4) NULL,
  `GROWTH_ASSET` NUMBER(30,4) NULL,
  `GROWTH_REVENUE` NUMBER(30,4) NULL,
  `ROA` NUMBER(30,4) NULL,
  `ROE` NUMBER(30,4) NULL,
  `PROFIT_MARGIN` NUMBER(30,4) NULL,
  `DEBT_EQUITY` NUMBER(30,4) NULL,
  `CURRENT_RATIO` NUMBER(30,4) NULL,
  `EBITDA` NUMBER(30,4) NULL,
  PRIMARY KEY (`INDUSTRY_CODE`)
);

DROP INDEX IF EXISTS IDX_INDUSTRY_CALC_VIEW_INDUSTRY_CODE;
CREATE INDEX IDX_INDUSTRY_CALC_VIEW_INDUSTRY_CODE ON INDUSTRY_CALC_VIEW(`INDUSTRY_CODE`);

DROP INDEX IF EXISTS IDX_INDUSTRY_CALC_VIEW_SECTOR_CODE;
CREATE INDEX IDX_INDUSTRY_CALC_VIEW_SECTOR_CODE ON INDUSTRY_CALC_VIEW(`SECTOR_CODE`);

-- -----------------------------------------------------
-- Table `COMPANY_CALC_VIEW `
-- -----------------------------------------------------
DROP TABLE IF EXISTS `COMPANY_CALC_VIEW` ;

CREATE  TABLE IF NOT EXISTS `COMPANY_CALC_VIEW` (	
  `EXCHANGE_CODE` VARCHAR(50) NOT NULL ,
  `SEC_CODE` VARCHAR(50) NOT NULL ,
  `INDUSTRY_CODE` VARCHAR(50) NOT NULL ,
  `SECTOR_CODE` VARCHAR(50) NOT NULL ,
  `PE` NUMBER(30,4) NULL,
  `PB` NUMBER(30,4) NULL,
  `SCOPE_MAKET_CAP` NUMBER(30,4) NULL,
  `SCOPE_ASSET` NUMBER(30,4) NULL,
  `SCOPE_EQUITY` NUMBER(30,4) NULL,
  `GROWTH_ASSET` NUMBER(30,4) NULL,
  `GROWTH_REVENUE` NUMBER(30,4) NULL,
  `ROA` NUMBER(30,4) NULL,
  `ROE` NUMBER(30,4) NULL,
  `PROFIT_MARGIN` NUMBER(30,4) NULL,
  `DEBT_EQUITY` NUMBER(30,4) NULL,
  `CURRENT_RATIO` NUMBER(30,4) NULL,
  `EBITDA` NUMBER(30,4) NULL,
  PRIMARY KEY (`SEC_CODE`)
);

DROP INDEX IF EXISTS IDX_COMPANY_CALC_VIEW_SEC_CODE;
CREATE INDEX IDX_COMPANY_CALC_VIEW_SEC_CODE ON COMPANY_CALC_VIEW(`SEC_CODE`);

DROP INDEX IF EXISTS IDX_COMPANY_CALC_VIEW_INDUSTRY_CODE;
CREATE INDEX IDX_COMPANY_CALC_VIEW_INDUSTRY_CODE ON COMPANY_CALC_VIEW(`INDUSTRY_CODE`);

DROP INDEX IF EXISTS IDX_COMPANY_CALC_VIEW_SECTOR_CODE;
CREATE INDEX IDX_COMPANY_CALC_VIEW_SECTOR_CODE ON COMPANY_CALC_VIEW(`SECTOR_CODE`);

-- -----------------------------------------------------
-- Table `SEC_LAST_PRICE_VIEW `
-- -----------------------------------------------------
DROP TABLE IF EXISTS `SEC_LAST_PRICE_VIEW` ;

CREATE  TABLE IF NOT EXISTS `SEC_LAST_PRICE_VIEW` (	
  `COMPANY_ID` NUMBER(10) NULL ,
  `SYMBOL` VARCHAR(50) NOT NULL ,
  `EXCHANGE_CODE` VARCHAR(50) NOT NULL ,
  `OPEN_PRICE` NUMBER(30,4) NULL,
  `HIGH_PRICE` NUMBER(30,4) NULL,
  `LOW_PRICE` NUMBER(30,4) NULL,
  `CLOSE_PRICE` NUMBER(30,4) NULL,
  `AVERAGE_PRICE` NUMBER(30,4) NULL,
  `VOLUME` NUMBER(20) NULL,
  `AD_OPEN_PRICE` NUMBER(30,4) NULL,
  `AD_HIGH_PRICE` NUMBER(30,4) NULL,
  `AD_LOW_PRICE` NUMBER(30,4) NULL,
  `AD_CLOSE_PRICE` NUMBER(30,4) NULL,
  `AD_AVERAGE_PRICE` NUMBER(30,4) NULL,  
  `RIGHTS_TYPE` VARCHAR2(50) NULL,
  `TRANS_DATE` TIMESTAMP NULL,
  PRIMARY KEY (`SYMBOL`)
);

DROP INDEX IF EXISTS IDX_SEC_LAST_PRICE_VIEW_SYMBOL;
CREATE INDEX IDX_SEC_LAST_PRICE_VIEW_SYMBOL ON SEC_LAST_PRICE_VIEW(`SYMBOL`);

-- -----------------------------------------------------
-- Table `BALANCESHEET_VIEW`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `BALANCESHEET_VIEW` ;

CREATE  TABLE IF NOT EXISTS BALANCESHEET_VIEW                       
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
DROP INDEX IF EXISTS IDX_BALANCESHEET_VIEW_COMPANY_ID;
CREATE INDEX IDX_BALANCESHEET_VIEW_COMPANY_ID ON BALANCESHEET_VIEW(`COMPANY_ID`);

DROP INDEX IF EXISTS IDX_BALANCESHEET_VIEW_LOCALE;
CREATE INDEX IDX_BALANCESHEET_VIEW_LOCALE ON BALANCESHEET_VIEW(`LOCALE`);
                                                         
-- -----------------------------------------------------
-- Table `BREAKDOWN_VIEW`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `BREAKDOWN_VIEW`;
                           
CREATE  TABLE IF NOT EXISTS BREAKDOWN_VIEW                          
(                                                        
  COMPANY_ID           NUMBER(10),                       
  STATE_OWNERSHIP      NUMBER(20,4),                     
  INTERNAL_MANAGEMENT  NUMBER(20,4),                     
  STRATEGIC_INVESTOR   NUMBER(20,4),                     
  FOREIGN_OWNERSHIP    NUMBER(20,4),                           
  OTHER                NUMBER(20,4)                            
);                          

DROP INDEX IF EXISTS IDX_BREAKDOWN_VIEW_COMPANY_ID;                    
CREATE INDEX IDX_BREAKDOWN_VIEW_COMPANY_ID ON BREAKDOWN_VIEW(`COMPANY_ID`);

-- -----------------------------------------------------
-- Table `CASHFLOW_VIEW`
-- ----------------------------------------------------- 
DROP TABLE IF EXISTS `CASHFLOW_VIEW`;
                                                         
CREATE  TABLE IF NOT EXISTS CASHFLOW_VIEW                           
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

DROP INDEX IF EXISTS IDX_CASHFLOW_VIEW_COMPANY_ID;                                    
CREATE INDEX IDX_CASHFLOW_VIEW_COMPANY_ID ON CASHFLOW_VIEW(`COMPANY_ID`);

DROP INDEX IF EXISTS IDX_CASHFLOW_VIEW_LOCALE;
CREATE INDEX IDX_CASHFLOW_VIEW_LOCALE ON CASHFLOW_VIEW(`LOCALE`);

-- -----------------------------------------------------
-- Table `COMPANY_DOCUMENT`
-- ----------------------------------------------------- 
DROP TABLE IF EXISTS `COMPANY_DOCUMENT`;

CREATE  TABLE IF NOT EXISTS COMPANY_DOCUMENT                        
(                                                        
  COMPANY_ID     NUMBER(10),                             
  DOCUMENT_ID    NUMBER(10),                             
  CREATED_DATE   DATE,                                   
  CREATED_BY     VARCHAR2(20),                      
  MODIFIED_DATE  DATE,                                   
  MODIFIED_BY    VARCHAR2(20)                       
);                                  

DROP INDEX IF EXISTS IDX_COMPANY_DOCUMENT_COMPANY_ID;            
CREATE INDEX IDX_COMPANY_DOCUMENT_COMPANY_ID ON COMPANY_DOCUMENT(`COMPANY_ID`);

DROP INDEX IF EXISTS IDX_COMPANY_DOCUMENT_DOCUMENT_ID;
CREATE INDEX IDX_COMPANY_DOCUMENT_DOCUMENT_ID ON COMPANY_DOCUMENT(`DOCUMENT_ID`);

-- -----------------------------------------------------
-- Table `COMPANY_NAME_VIEW` --> get from STOCK_EXCHANGE
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Table `COMPANY_OFFICERS_VIEW`
-- ----------------------------------------------------- 
DROP TABLE IF EXISTS `COMPANY_OFFICERS_VIEW`;
                                                        
CREATE  TABLE IF NOT EXISTS COMPANY_OFFICERS_VIEW                   
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

DROP INDEX IF EXISTS IDX_COMPANY_OFFICERS_VIEW_COMPANY_ID;                      
CREATE INDEX IDX_COMPANY_OFFICERS_VIEW_COMPANY_ID ON COMPANY_OFFICERS_VIEW(`COMPANY_ID`);

-- -----------------------------------------------------
-- Table `COMPANY_PROFILE_VIEW`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `COMPANY_PROFILE_VIEW`;

CREATE  TABLE IF NOT EXISTS COMPANY_PROFILE_VIEW                    
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

DROP INDEX IF EXISTS IDX_COMPANY_PROFILE_VIEW_COMPANY_ID;            
CREATE INDEX IDX_COMPANY_PROFILE_VIEW_COMPANY_ID ON COMPANY_PROFILE_VIEW(`COMPANY_ID`);

DROP INDEX IF EXISTS IDX_COMPANY_PROFILE_VIEW_LOCALE;
CREATE INDEX IDX_COMPANY_PROFILE_VIEW_LOCALE ON COMPANY_PROFILE_VIEW(`LOCALE`);

-- -----------------------------------------------------
-- Table `COMPANY_SNAPSHOT_VIEW`
-- -----------------------------------------------------  
DROP TABLE IF EXISTS `COMPANY_SNAPSHOT_VIEW`;
                                                       
CREATE  TABLE IF NOT EXISTS COMPANY_SNAPSHOT_VIEW                   
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

DROP INDEX IF EXISTS IDX_COMPANY_SNAPSHOT_VIEW_COMPANY_ID;               
CREATE INDEX IDX_COMPANY_SNAPSHOT_VIEW_COMPANY_ID ON COMPANY_SNAPSHOT_VIEW(`COMPANY_ID`);

DROP INDEX IF EXISTS IDX_COMPANY_SNAPSHOT_VIEW_LOCALE;
CREATE INDEX IDX_COMPANY_SNAPSHOT_VIEW_LOCALE ON COMPANY_SNAPSHOT_VIEW(`LOCALE`);

-- -----------------------------------------------------
-- Table `DOCUMENT`
-- ----------------------------------------------------- 
DROP TABLE IF EXISTS `DOCUMENT`;
                                                          
CREATE  TABLE IF NOT EXISTS DOCUMENT                                
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

DROP INDEX IF EXISTS IDX_DOCUMENT_DOCUMENT_ID;
CREATE INDEX IDX_DOCUMENT_DOCUMENT_ID ON DOCUMENT(`DOCUMENT_ID`);

DROP INDEX IF EXISTS IDX_DOCUMENT_LOCALE;
CREATE INDEX IDX_DOCUMENT_LOCALE ON DOCUMENT(`LOCALE`);
                                               
-- -----------------------------------------------------
-- Table `FINANCIAL_HIGHLIGHT_VIEW`
-- ----------------------------------------------------- 
DROP TABLE IF EXISTS `FINANCIAL_HIGHLIGHT_VIEW`;

CREATE  TABLE IF NOT EXISTS FINANCIAL_HIGHLIGHT_VIEW                
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

DROP INDEX IF EXISTS IDX_FINANCIAL_HIGHLIGHT_VIEW_COMPANY_ID;
CREATE INDEX IDX_FINANCIAL_HIGHLIGHT_VIEW_COMPANY_ID ON FINANCIAL_HIGHLIGHT_VIEW(`COMPANY_ID`);                                              
                                                         
-- -----------------------------------------------------
-- Table `FOREIGN_TRADING_VIEW`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `FOREIGN_TRADING_VIEW`;
 
CREATE  TABLE IF NOT EXISTS FOREIGN_TRADING_VIEW                    
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

DROP INDEX IF EXISTS IDX_FOREIGN_TRADING_VIEW_FLOOR_CODE;
CREATE INDEX IDX_FOREIGN_TRADING_VIEW_FLOOR_CODE ON FOREIGN_TRADING_VIEW(`FLOOR_CODE`);

DROP INDEX IF EXISTS IDX_FOREIGN_TRADING_VIEW_TRADING_DATE;
CREATE INDEX IDX_FOREIGN_TRADING_VIEW_TRADING_DATE ON FOREIGN_TRADING_VIEW(`TRADING_DATE`);
                                                         
-- -----------------------------------------------------
-- Table `INCOME_VIEW`
-- ----------------------------------------------------- 
DROP TABLE IF EXISTS `INCOME_VIEW`;

CREATE  TABLE IF NOT EXISTS INCOME_VIEW                             
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

DROP INDEX IF EXISTS IDX_INCOME_VIEW_COMPANY_ID;       
CREATE INDEX IDX_INCOME_VIEW_COMPANY_ID ON INCOME_VIEW(`COMPANY_ID`);

DROP INDEX IF EXISTS IDX_INCOME_VIEW_LOCALE;
CREATE INDEX IDX_INCOME_VIEW_LOCALE ON INCOME_VIEW(`LOCALE`);

-- -----------------------------------------------------
-- Table `INSIDER_TRANSACTION_VIEW`
-- -----------------------------------------------------  
DROP TABLE IF EXISTS `INSIDER_TRANSACTION_VIEW`;
                                                        
CREATE  TABLE IF NOT EXISTS INSIDER_TRANSACTION_VIEW                
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

DROP INDEX IF EXISTS IDX_INSIDER_TRANSACTION_VIEW_COMPANY_ID;            
CREATE INDEX IDX_INSIDER_TRANSACTION_VIEW_COMPANY_ID ON INSIDER_TRANSACTION_VIEW(`COMPANY_ID`);

DROP INDEX IF EXISTS IDX_INSIDER_TRANSACTION_VIEW_TRANSACTION_DATE;
CREATE INDEX IDX_INSIDER_TRANSACTION_VIEW_TRANSACTION_DATE ON INSIDER_TRANSACTION_VIEW(`TRANSACTION_DATE`);

DROP INDEX IF EXISTS IDX_INSIDER_TRANSACTION_VIEW_LOCALE;
CREATE INDEX IDX_INSIDER_TRANSACTION_VIEW_LOCALE ON INSIDER_TRANSACTION_VIEW(`LOCALE`);

-- -----------------------------------------------------
-- Table `INVESTOR_RIGHTS_VIEW`
-- ----------------------------------------------------- 
DROP TABLE IF EXISTS `INVESTOR_RIGHTS_VIEW`;
                                                         
CREATE  TABLE IF NOT EXISTS INVESTOR_RIGHTS_VIEW                    
(                                                        
  COMPANY_ID          NUMBER(10),                        
  SEC_CODE            VARCHAR2(20)         NOT NULL,
  X_DATE              DATE,                              
  RATIO               NUMBER(20,4),                      
  SHARES_ACTION_DESC  VARCHAR2(250),                 
  LOCALE              VARCHAR2(10)                   
);

DROP INDEX IF EXISTS IDX_INVESTOR_RIGHTS_VIEW_COMPANY_ID;
CREATE INDEX IDX_INVESTOR_RIGHTS_VIEW_COMPANY_ID ON INVESTOR_RIGHTS_VIEW(`COMPANY_ID`);

DROP INDEX IF EXISTS IDX_INVESTOR_RIGHTS_VIEW_LOCALE;
CREATE INDEX IDX_INVESTOR_RIGHTS_VIEW_LOCALE ON INVESTOR_RIGHTS_VIEW(`LOCALE`);
                                                         
-- -----------------------------------------------------
-- Table `KEY_RATIO_VIEW`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `KEY_RATIO_VIEW`;
 
CREATE  TABLE IF NOT EXISTS KEY_RATIO_VIEW                          
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

DROP INDEX IF EXISTS IDX_KEY_RATIO_VIEW_COMPANY_ID;
CREATE INDEX IDX_KEY_RATIO_VIEW_COMPANY_ID ON KEY_RATIO_VIEW(`COMPANY_ID`);

DROP INDEX IF EXISTS IDX_KEY_RATIO_VIEW_LOCALE;
CREATE INDEX IDX_KEY_RATIO_VIEW_LOCALE ON KEY_RATIO_VIEW(`LOCALE`);
                                                         
-- -----------------------------------------------------
-- Table `MAJOR_HOLDER_VIEW`
-- ----------------------------------------------------- 
DROP TABLE IF EXISTS `MAJOR_HOLDER_VIEW`;

CREATE  TABLE IF NOT EXISTS MAJOR_HOLDER_VIEW                       
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

DROP INDEX IF EXISTS IDX_MAJOR_HOLDER_VIEW_COMPANY_ID;                                              
CREATE INDEX IDX_MAJOR_HOLDER_VIEW_COMPANY_ID ON MAJOR_HOLDER_VIEW(`COMPANY_ID`);

DROP INDEX IF EXISTS IDX_MAJOR_HOLDER_VIEW_LOCALE;
CREATE INDEX IDX_MAJOR_HOLDER_VIEW_LOCALE ON MAJOR_HOLDER_VIEW(`LOCALE`);

                                                             
-- -----------------------------------------------------
-- Table `SEC_PRICE_VIEW`
-- ----------------------------------------------------- 
DROP TABLE IF EXISTS `SEC_PRICE_VIEW`;

CREATE  TABLE IF NOT EXISTS SEC_PRICE_VIEW                          
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

DROP INDEX IF EXISTS IDX_SEC_PRICE_VIEW_COMPANY_ID;
CREATE INDEX IDX_SEC_PRICE_VIEW_COMPANY_ID ON SEC_PRICE_VIEW(`COMPANY_ID`);
    
DROP INDEX IF EXISTS IDX_SEC_PRICE_VIEW_EXCHANGE_CODE;                                          
CREATE INDEX IDX_SEC_PRICE_VIEW_EXCHANGE_CODE ON SEC_PRICE_VIEW(`EXCHANGE_CODE`);

DROP INDEX IF EXISTS IDX_SEC_PRICE_VIEW_SYMBOL;
CREATE INDEX IDX_SEC_PRICE_VIEW_SYMBOL ON SEC_PRICE_VIEW(`SYMBOL`);

DROP INDEX IF EXISTS IDX_SEC_PRICE_VIEW_TRANS_DATE;
CREATE INDEX IDX_SEC_PRICE_VIEW_TRANS_DATE ON SEC_PRICE_VIEW(`TRANS_DATE`);
                                                         
-- -----------------------------------------------------
-- Table `TRADING_STATISTICS_VIEW`
-- ----------------------------------------------------- 
DROP TABLE IF EXISTS `TRADING_STATISTICS_VIEW`;

CREATE  TABLE IF NOT EXISTS TRADING_STATISTICS_VIEW                 
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

DROP INDEX IF EXISTS IDX_TRADING_STATISTICS_VIEW_SEC_CODE;
CREATE INDEX IDX_TRADING_STATISTICS_VIEW_SEC_CODE ON TRADING_STATISTICS_VIEW(`SEC_CODE`);

DROP INDEX IF EXISTS IDX_TRADING_STATISTICS_VIEW_FLOOR_CODE;
CREATE INDEX IDX_TRADING_STATISTICS_VIEW_FLOOR_CODE ON TRADING_STATISTICS_VIEW(`FLOOR_CODE`);

DROP INDEX IF EXISTS IDX_TRADING_STATISTICS_VIEW_TRANS_DATE;
CREATE INDEX IDX_TRADING_STATISTICS_VIEW_TRANS_DATE ON TRADING_STATISTICS_VIEW(`TRANS_DATE`);

-- -----------------------------------------------------
-- Table `VALUATION_MEASURES_VIEW`
-- ----------------------------------------------------- 
DROP TABLE IF EXISTS `VALUATION_MEASURES_VIEW`;
                                                        
CREATE TABLE IF NOT EXISTS VALUATION_MEASURES_VIEW                 
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

DROP INDEX IF EXISTS IDX_VALUATION_MEASURES_VIEW_COMPANY_ID;
CREATE INDEX IDX_VALUATION_MEASURES_VIEW_COMPANY_ID ON VALUATION_MEASURES_VIEW(`COMPANY_ID`);

DROP INDEX IF EXISTS IDX_VALUATION_MEASURES_VIEW_LOCALE;
CREATE INDEX IDX_VALUATION_MEASURES_VIEW_LOCALE ON VALUATION_MEASURES_VIEW(`LOCALE`);
                         
-- -----------------------------------------------------
-- Table `MARKET_CALENDAR_VIEW`
-- ----------------------------------------------------- 
DROP TABLE IF EXISTS `MARKET_CALENDAR_VIEW`;

CREATE  TABLE IF NOT EXISTS MARKET_CALENDAR_VIEW                    
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

DROP INDEX IF EXISTS IDX_MARKET_CALENDAR_VIEW_COMPANY_ID;
CREATE INDEX IDX_MARKET_CALENDAR_VIEW_COMPANY_ID ON MARKET_CALENDAR_VIEW(`COMPANY_ID`);

DROP INDEX IF EXISTS IDX_MARKET_CALENDAR_VIEW_SYMBOL;
CREATE INDEX IDX_MARKET_CALENDAR_VIEW_SYMBOL ON MARKET_CALENDAR_VIEW(`SYMBOL`);

DROP INDEX IF EXISTS IDX_MARKET_CALENDAR_VIEW_LOCALE;
CREATE INDEX IDX_MARKET_CALENDAR_VIEW_LOCALE ON MARKET_CALENDAR_VIEW(`LOCALE`);

-- -----------------------------------------------------
-- Table `NEWS`
-- ----------------------------------------------------- 
DROP TABLE IF EXISTS `NEWS`;
                                                         
CREATE  TABLE IF NOT EXISTS NEWS                                    
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

DROP INDEX IF EXISTS IDX_NEWS_NEWS_ID;
CREATE INDEX IDX_NEWS_NEWS_ID ON NEWS(`NEWS_ID`);    

DROP INDEX IF EXISTS IDX_NEWS_NEWS_TYPE;             
CREATE INDEX IDX_NEWS_NEWS_TYPE ON NEWS(`NEWS_TYPE`);

DROP INDEX IF EXISTS IDX_NEWS_STATUS;
CREATE INDEX IDX_NEWS_STATUS ON NEWS(`STATUS`);

DROP INDEX IF EXISTS IDX_NEWS_IS_HOT_NEWS;
CREATE INDEX IDX_NEWS_IS_HOT_NEWS ON NEWS(`IS_HOT_NEWS`);                                                         

-- -----------------------------------------------------
-- Table `ATTACHMENTS`
-- ----------------------------------------------------- 
DROP TABLE IF EXISTS `ATTACHMENTS`;

CREATE TABLE ATTACHMENTS (
    ATTACMENT_ID NUMBER(10) NOT NULL,
    NEWS_ID NUMBER(10) NOT NULL,
    ORIGINAL_LINK VARCHAR2(400) NOT NULL,
    URI_LINK VARCHAR2(400) NOT NULL,
    DESCRIPTION VARCHAR2(200),
    CONSTRAINT ATT_PK PRIMARY KEY (ATTACMENT_ID)
);

DROP INDEX IF EXISTS IDX_ATTACHMENTS_NEWS_ID;
CREATE INDEX IDX_ATTACHMENTS_NEWS_ID ON ATTACHMENTS(`NEWS_ID`);

-- -----------------------------------------------------
-- Table `COMPANY_NEWS_VIEW`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `COMPANY_NEWS_VIEW`;
                                                         
CREATE  TABLE IF NOT EXISTS COMPANY_NEWS_VIEW                       
(                                                        
  COMPANY_ID  NUMBER(10)  NOT NULL,
  NEWS_ID     NUMBER(10) NOT NULL 
);

DROP INDEX IF EXISTS IDX_COMPANY_NEWS_VIEW_COMPANY_ID;                                              
CREATE INDEX IDX_COMPANY_NEWS_VIEW_COMPANY_ID ON COMPANY_NEWS_VIEW(`COMPANY_ID`);

DROP INDEX IF EXISTS IDX_COMPANY_NEWS_VIEW_NEWS_ID;
CREATE INDEX IDX_COMPANY_NEWS_VIEW_NEWS_ID ON COMPANY_NEWS_VIEW(`NEWS_ID`);

COMMIT;
                                                         