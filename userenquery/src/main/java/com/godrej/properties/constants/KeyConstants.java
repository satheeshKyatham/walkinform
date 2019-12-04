package com.godrej.properties.constants;

public class KeyConstants {

	public static final String REFERENCE_CODE_BUDGET="BUDGET";
	public static final String REFERENCE_CODE_PURPOSE="PURPOSE";
	public static final String REFERENCE_CODE_SALUTATION="SALUTATION";
	public static final String REFERENCE_CODE_COUNTRY="COUNTRY";
	public static final String REFERENCE_CODE_COMMUNICATION_MEDIUM="COMMUNICATION_MEDIUM";
	public static final String REFERENCE_CODE_POSSESION_TIME="REQ_POSSESSION_TIME_LINE";
	public static final String REFERENCE_CODE_OCCUPATION="OCCUPATION";
	public static final String REFERENCE_CODE_AGE_GROUP="AGE_GROUP";
	public static final String REFERENCE_CODE_CURRENT_RESIDENCE_TYPE="CURRENT_RESIDENCE_TYPE";
	public static final String REFERENCE_CODE_INDUSTRY="INDUSTRY";
	public static final String REFERENCE_CODE_REQUIREMENT_TYPE="REQUIREMENT_TYPE";
	public static final String REFERENCE_CODE_TYPOLOGY_AREA="TYPOLOGY_AREA";
	
	public static final String DEFAULT_DATE_FORMAT = "dd-MM-yyyy";
	public static final String DEFAULT_DATETIME_FORMAT = "dd-MM-yyyy HH:mm";
	
	public static final String RECORD_TYPE_PROSPECT = "01290000000uHV9AAM";
	public static final String RECORD_TYPE_CUSTOMER = "01290000000uHV8AAM";
	public static final int SITE_VISIT_DAYS_LIMIT = 90;
	public static final int LAST_MODIFY_DAYS_LIMIT  = 30;
	
	public static final String COMMON_EMAIL  = "marketing@godrejproperties.com";
	public static final String BASE_PATH  = "C:\\userenquiry\\EOI\\";
	
	/*Added By Satheesh - 13th June 2019  - CC Gateway Keys*/
//	public static final String WORKINGKEY = "89F76AC5DDB8C91A2D05F756DE1BF446";//Test
//	public static final String ACCESSCODE= "AVWH85GE35BD30HWDB";//Test
	public static final String WORKINGKEY = "3437CBD465037577531F3E7084E14C0E";//Lake Garden
	public static final String ACCESSCODE= "AVMP85GE10AD62PMDA";//Lake Garden
	public static final String CCPRDAPI= "https://api.ccavenue.com/apis/servlet/DoWebTrans";
	public static final String CCSANDBOXAPI= "https://apitest.ccavenue.com/apis/servlet/DoWebTrans";
	
	
	public static final String SFDC_USERNAME = "external.app@godrejproperties.com";
	public static final String SFDC_PASSWORD= "Nov@12345Svgw3cZob0A7HWlJs8I07Jcdb";
	public static final String SFDC_LOGINURL= "https://godrej.my.salesforce.com";
	public static final String SFDC_GRANTSERVICE= "/services/oauth2/token?grant_type=password";
	public static final String SFDC_CLIENTID = "3MVG9Y6d_Btp4xp7lt5FL02Cc.bHBCI_vpcrJYvpeBev1Ob5nXpobkxkmhygUekoOvbQMscya0i3r7EacuDWz";
	public static final String SFDC_CLIENTSECRET = "332639414523432900";
	
	//Sandbox Credentials
	/*static final String USERNAME = "sachin_more@magicsoftware.com";//pass@12345fp0D4yeOj49FYAowsRSgDm0H
	static final String PASSWORD = "Godrej@2018cVsYTP80dGI8lHM1kqzahnj6W";
	static final String LOGINURL = "https://test.salesforce.com";
	static final String GRANTSERVICE ="/services/oauth2/token?grant_type=password";
	static final String CLIENTID = "3MVG9Nvmjd9lcjRnoT4GG3E8o7ZbQcp3HqKaX6KsWkBg77OzU6SN.6oqr00W1pLR_P50oeF8xzGIk7RWT9TTA";
	static final String CLIENTSECRET = "8491910721028248323";*/
	
	public static final String LDAP_HOST = "10.21.48.21";
	public static final String LDAP_PORT = "389";
	public static final String LDAP_DN = "selfservice.portal@godrejproperties.com";
	public static final String LDAP_PW = "DFER$#34";
	
	//Error Messages
	public static final String ERROR_MSG_101 = "errorNoUnit101";//This unit is no longer available please select another unit.
	public static final String ERROR_MSG_102 = "errorUnitInactive102"; //Inventory is not activated
	public static final String ERROR_MSG_103 = "errorInCode103"; //Yes, There is some technical problem.
	
	public static final String SUCCESS_MSG_101 = "successOfferCreate101"; //Offer Successfully Created
	public static final String SUCCESS_MSG_102 = "successUnitAvailable102"; //Unit Available
	
	public static final String SFDC_OFFERAPI = "https://godrej.my.salesforce.com/services/apexrest/api/CreateOfferPrepayment";
	public static final String SFDC_OFFERAPI_BULKY = "https://godrej.my.salesforce.com/services/apexrest/api/CreateOfferPrepaymentBulky";
	public static final String SFDC_OFFERPREPAYMENTAPI = "https://godrej.my.salesforce.com/services/apexrest/api/OfferPrepayments";
	
}

