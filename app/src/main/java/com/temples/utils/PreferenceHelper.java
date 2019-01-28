package com.temples.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.util.HashSet;

public class PreferenceHelper { public final static String IS_HYDROCARE_NOTIFICATION_ENABLE = "isHydrocareNotificationEnable";
    public final static String IS_HYDROCARE_SUBSCRIPTION_ENABLE = "isHydrocareSubscriptionEnable";
    static final String TAG = "RegisterGcmIdInfo";
    private static final String PREF_IS_HRA_COMPLETED = "isHraCompleted";
    private static final String PREF_ORDER_MEDS = "isOrderMeds";
    private static final String PREF_ENCRYTION = "isEncryption";
    private static final String PREF_FIRST_REGISTER = "isFirstRegister";
    private static final String PREF_IS_FIT_CONNECTED = "isFitConnected";
    private static final String PREF_UPDATE_APP_LATER = "updateLater";
    private static final String PACKAGE_LIST = "packages";
    private static final String DIALOG_PACKAGE_LIST = "dialog_package_list";
    private static final String IS_DIALOG_VIEW = "isDialogView";
    private final SharedPreferences mPrefs;
    Context context;
    private String PREF_CheckManual = "ManualData";
    private String PREF_APKVersion = "apkVersion";
    private String PREF_SkipMobileNumber = "skipMobileNumber";
    private String PREF_IsLogin = "IsLogin";
    private String PREF_IsCheckLastStatus = "IsLastStatus";
    private String PREF_AppToken = "AppToken";
    private String PREF_UserId = "UserId";
    private String PREF_FIRST_USER_WIZARD = "IsWizardCompliteFirst";
    private String PREF_Lat = "Lat";
    private String PREF_Long = "Long";
    private String PREF_UserName = "UserName";
    private String PREF_Password = "Password";
    private String PREF_Device_Password = "Device_Password";
    private String PREF_Device_Name = "Device_Name";
    private String PREF_X_EKINCARE_KEY = "EkinCareKey";
    private String PREF_X_CUSTOMER_KEY = "CustomerKey";
    private String PREF_ProfileModel = "ProfileModel";
    private String PREF_ProfileData = "ProfileData";
    private String PREF_Immunization = "Immunization";
    private String PREF_FamilyMemCount = "FamilyMemCount";
    private String PREF_LastQuestion = "LastQuestion";
    private String PREF_IsFamilyWizrdComplete = "IsFamilyWizrdComplete";
    private String PREF_IsLoginVisible = "IsLoginVisible";
    private String PREF_IsHaveCodeVisible = "IsHaveCodeVisible";
    private String PREF_IsYou = "IsYou";
    private String PREF_Target = "Target";
    private String PREF_BloodGroup = "BloodGroup";
    private String PREF_HydrocareIntakesTimeStamp = "HydrocareIntakesTimeStamp";
    private String PREF_TemperatureTimeStamp = "TemperatureTimeStamp";
    private String PREF_Temperature = "Temperature";
    private String PREF_isHydrocareSubscriptionEnable = "isHydrocareSubscriptionEnable";
    private String PREF_isBloodSOSSubscriptionEnable = "isBloodSOSSubscriptionEnable";
    private String PREF_YouCustomer = "YouCustomer";
    private String PREF_FamilyMemberList = "FamilyMemberList";
    private String PREF_HydrocareSubscripted = "HydrocareSubscripted";
    //private String PREF_RegistrationId = "regId";
    private String REG_ID = "regId";
    private String APP_VERSION = "appVersion";
    private String PREF_StepsCount = "StepsCount";
    private String PREF_StepsTodayDate = "StepsTodaydate";
    private String PREF_CaloriesCount = "CaloriesCount";
    private String PREF_DistanceCount = "DistanceCount";
    private String PREF_ABLYTOKEN = "AblyToken";
    private String PREF_PACKAGETYPE = "PackageType";
    private String PREF_PACKAGEID = "PackageID";
    private String PREF_PROVIDER_NAME = "ProviderName";
    private String PREF_PROVIDER_ID = "ProviderID";
    private String PREF_UNIQUE_ID = "UniqueID";
    private String PREF_APPOINTMENT_TIME = "Time";
    private String PREF_FIRSTAPPOINTMENT = "FirstAppointment";
    private String PREF_APPOINTMENT_TIME_DATE = "FirstDate";
    private String PREFS_HOMECARE_TIME_DATE_YEAR = "HomecareTimeDate";
    private String PREF_LOCATION_LAT = "Locationlatitude";
    private String PREF_LOCATION_LAN = "Locationlongitude";
    private String PREF_SAVE_LOC_NAME = "SaveGoogleLocAddress";
    private String PREF_CONSULT_URL = "ConsultUrl";
    private String PREF_TRENDS = "TrendsId";
    private String PREF_INSTALL_TIME = "InstallTime";
    private String PREF_UPDATE_WATER = "updateWater";
    private String PREF_UPDATE_WATER_TOTAL = "totalUpdateWater";
    private String PREF_RATING_FOR_COUNT = "CountsPerDay";
    private String PREF_RATING_FOR_DAY = "CountOfDay";
    private String PREF_IS_RATED = "IsRated";
    private String PREF_ATE_ON_TIME = "ateOnTime";
    private String PREF_SMOKED_TODAY = "smokedToday";
    private String PREF_DRINKED_ALCOHOL = "drinkedAlcohol";
    private String PREF_DRINKED_COFFEE = "drinkedCoffee";
    private String PREF_SLEEP_ON_TIME = "sleepOnTime";
    private String PREF_HAD_BREAKFAST = "hadbreakfast";
    private String PREF_SKIPPED_MEAL = "skippedMeals";
    private String PREF_HAD_GOODSLEEP = "hadGoodSleep";
    private String PREF_TODAY_HOW_FEEL = "howWasDay";
    private String PREF_SLEEP_TIME = "sleepTime";
    private String PREF_WOKE_TIME = "wakeTime";
    private String PREF_IMAGE_URL = "imageUrl";
    private String PREF_CUSTOMER_PROFILE_IMAGE_COLOR = "imageColor";
    private String PREF_CUSTOMER_TOKEN = "customerNToken";
    private String PREF_IS_ANY_DIGITIZED_RECORED = "isAnyDigitizedRecords";
    private String PREF_HAS_SEEN_DIGITIZED_RECORED = "hasSeenDigitizedRecords";
    private String PREF_HAS_HOW_WAS_YOUR_DAY_DATA_FILLED = "showHowWasYourDay";
    private String FAMILY_MEMBER_LIST = "totalFamilyMemberList";
    private String PREF_TAKEN_MORNING_MEDICATION_ID = "medicationMorningId";
    private String PREF_TAKEN_EVENING_MEDICATION_ID = "medicationEveningId";
    private String PREF_TAKEN_AFTERNOON_MEDICATION_ID = "medicationAfternoonId";
    private String PREF_HAS_SEEN_TUTORIAL = "hasSeenTutorial";
    private String PREF_HAS_SEEN_ACTIVITY_FAMILY_MEMBER_TUTORIAL = "hasSeenActivityFamilyMemberTutorial";
    private String PREF_HAS_SEEN_DOC_FAMILY_MEMBER_TUTORIAL = "hasSeenDOCFamilyMemberTutorial";
    private String PREF_HAS_SEEN_MEDICICATION_FAMILY_MEMBER_TUTORIAL = "hasSeenMedicationFamilyMemberTutorial";
    private String PREF_HAS_SEEN_ALLERGY_FAMILY_MEMBER_TUTORIAL = "hasSeenAllergyFamilyMemberTutorial";
    private String PREF_HAS_SEEN_HISTORY_FAMILY_MEMBER_TUTORIAL = "hasSeenHistoryFamilyMemberTutorial";
    private String PREF_HAS_SEEN_DOCUMENT_UPLOAD_TUTORIAL = "hasSeenDocumentUploadTutorial";
    private String PREF_NEVER_SHOW_DOCUMENT_UPLOAD_TUTORIAL = "hasSeenDocumentUploadTutorial";
    private String PREF_RUNTIME_PERMISSION = "runtimePermission";
    private String PREF_DOC_MAIN = "MAIN_DOC";
    private String PREF_IS_FIRST_TIME_CHAT = "isFirstTimeChat";
    private String PREF_IS_FIRST_TIME_DOCTORCHAT = "isFirstTimeDoctorChat";
    private String PREF_IS_FIRST_WELLCOME = "isFirstTimeWellcome";
    private String PREF_IS_FIRST_TIMER = "isFirstTimeTimer";
    private String PREF_IS_FIRST_TIMER_BADGE = "isFirstTimeTimerBadge";
    private String PREF_DOCTORID = "DoctorID";
    private String PREF_DOCTORCHATID = "DoctorChatID";
    private String PREF_IsFirstTimeRegister = "IsFirstTimeRegister";
    private String PREF_LOGGED_IN_CUSTOMER_DOB = "loggedInCustomerDob";
    private String PREF_LOGGED_IN_CUSTOMER_GENDER = "loggedInCustomerGender";
    private String PREF_LOGGED_IN_CUSTOMER_WIZARD = "loggedInCustomerWizard";
    private String PREF_LOGGED_IN_CUSTOMER_MOBILENUMBER = "loggedInCustomerMobileNumber";
    private String PREF_IsAttachImageDoctor = "IsDoctorUploadImage";

    private String PREF_IsAblyInitFirst = "IsAblyFirst";

    private String PREF_IsDoctorPayment = "IsDoctorPayment";
    private String PREF_HOW_WAS_DONE = "howWasDayDone";
    private String PREF_HOW_WAS_DAY_REMINDER = "howWasDayReminder";
    private String PREF_CHAT_FLAG = "isChatWithSoctor";
    private String PREF_BOOK_HEALTH_FLAG = "isBookHealthCheckUp";
    private String PREF_AddFamilyMember_FLAG = "isAddFamilyMember";
    private String PREF_ORDER_MEDICINE_FLAG = "isOrderMedicine";
    private String PREF_WALLET_FLAG = "isWallet";
    private String PREF_HEALTH_COACH_FLAG = "isHealthCoach";
    private String PREF_DOB_FLAG = "isDobEnabled";
    private String PREF_SOCIAL_CHALLENGE_FLAG = "isSocialChallenges";
    private String PREF_GYM_FLAG = "isGymEnable";


    private String PREF_LEADERBOARD_FLAG = "isLeaderboard";
    private String PREF_GroupOne = "GROUPONE";
    private String PREF_GroupTwo = "GroupTwo";


    public PreferenceHelper(Context context) {
        mPrefs = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
    }

    public String getPREF_GroupOne() {
        String str = mPrefs.getString(PREF_GroupOne, "step");
        return str;
    }

    public void setPREF_GroupOne(String pref_groupone) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putString(PREF_GroupOne, pref_groupone);
        mEditor.commit();
    }

    public String getPREF_GroupTwo() {
        String str = mPrefs.getString(PREF_GroupTwo, "this week");
        return str;
    }

    public void setPREF_GroupTwo(String pref_grouptwo) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putString(PREF_GroupTwo, pref_grouptwo);
        mEditor.commit();
    }


    public Boolean getPREF_LEADERBOARD_FLAG() {
        boolean str = mPrefs.getBoolean(PREF_LEADERBOARD_FLAG, false);
        return str;
    }

    public void setPREF_LEADERBOARD_FLAG(Boolean pref_leaderboard_flag) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putBoolean(PREF_LEADERBOARD_FLAG, pref_leaderboard_flag);
        mEditor.commit();
    }

    public Boolean getPREF_SOCIAL_CHALLENGE_FLAG() {
        boolean str = mPrefs.getBoolean(PREF_SOCIAL_CHALLENGE_FLAG, false);
        return str;
    }

    public void setPREF_SOCIAL_CHALLENGE_FLAG(Boolean pref_social_challenge_flag) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putBoolean(PREF_SOCIAL_CHALLENGE_FLAG, pref_social_challenge_flag);
        mEditor.commit();
    }


    public Boolean getPREF_HEALTH_COACH_FLAG() {
        boolean str = mPrefs.getBoolean(PREF_HEALTH_COACH_FLAG, false);
        return str;
    }

    public void setPREF_HEALTH_COACH_FLAG(Boolean pref_health_coach_flag) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putBoolean(PREF_HEALTH_COACH_FLAG, pref_health_coach_flag);
        mEditor.commit();
    }

    public Boolean getPREF_DOB_FLAG() {
        boolean str = mPrefs.getBoolean(PREF_DOB_FLAG, false);
        return str;
    }

    public void setPREF_DOB_FLAG(Boolean pref_dob_flag) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putBoolean(PREF_DOB_FLAG, pref_dob_flag);
        mEditor.commit();
    }

    public Boolean getPREF_ORDER_MEDICINE_FLAG() {
        boolean str = mPrefs.getBoolean(PREF_ORDER_MEDICINE_FLAG, false);
        return str;
    }

    public void setPREF_ORDER_MEDICINE_FLAG(Boolean pref_order_medicine_flag) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putBoolean(PREF_ORDER_MEDICINE_FLAG, pref_order_medicine_flag);
        mEditor.commit();
    }

    public Boolean getPREF_WALLET_FLAG() {
        boolean str = mPrefs.getBoolean(PREF_WALLET_FLAG, false);
        return str;
    }

    public void setPREF_WALLET_FLAG(Boolean pref_wallet_flag) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putBoolean(PREF_WALLET_FLAG, pref_wallet_flag);
        mEditor.commit();
    }


    public Boolean getPREF_AddFamilyMember_FLAG() {
        boolean str = mPrefs.getBoolean(PREF_AddFamilyMember_FLAG, false);
        return str;
    }

    public void setPREF_AddFamilyMember_FLAG(Boolean pref_addfamilymember_flag) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putBoolean(PREF_AddFamilyMember_FLAG, pref_addfamilymember_flag);
        mEditor.commit();
    }


    public Boolean getPREF_BOOK_HEALTH_FLAG() {
        boolean str = mPrefs.getBoolean(PREF_BOOK_HEALTH_FLAG, false);
        return str;
    }

    public void setPREF_BOOK_HEALTH_FLAG(Boolean pref_book_health_flag) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putBoolean(PREF_BOOK_HEALTH_FLAG, pref_book_health_flag);
        mEditor.commit();
    }


    public Boolean getPREF_GYM_FLAG() {
        boolean str = mPrefs.getBoolean(PREF_GYM_FLAG, false);
        return str;
    }

    public void setPREF_GYM_FLAG(Boolean pref_gym_flag) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putBoolean(PREF_GYM_FLAG, pref_gym_flag);
        mEditor.commit();
    }

    public Boolean getPREF_CHAT_FLAG() {
        boolean str = mPrefs.getBoolean(PREF_CHAT_FLAG, false);
        return str;
    }

    public void setPREF_CHAT_FLAG(Boolean pref_chat_flag) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putBoolean(PREF_CHAT_FLAG, pref_chat_flag);
        mEditor.commit();
    }

    public SharedPreferences getmPrefs() {
        return mPrefs;
    }

    public boolean getIsDoctorPayment() {
        boolean str = mPrefs.getBoolean(PREF_IsDoctorPayment, false);
        return str;
    }

    public void setDoctorPayment(boolean pref_isdoctorpayment) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putBoolean(PREF_IsDoctorPayment, pref_isdoctorpayment);
        mEditor.commit();
    }

    public boolean getIsAblyInitFirst() {
        boolean str = mPrefs.getBoolean(PREF_IsAblyInitFirst, false);
        return str;
    }

    public void setIsAblyInitFirst(boolean pref_isablyinitfirst) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putBoolean(PREF_IsAblyInitFirst, pref_isablyinitfirst);
        mEditor.commit();
    }

    public boolean getIsUploadImageDoctor() {
        boolean str = mPrefs.getBoolean(PREF_IsAttachImageDoctor, false);
        return str;
    }

    public void setIsUploadImageDoctor(boolean pref_isattachimagedoctor) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putBoolean(PREF_IsAttachImageDoctor, pref_isattachimagedoctor);
        mEditor.commit();
    }

    public boolean isFitConnected() {
        return mPrefs.getBoolean(PREF_IS_FIT_CONNECTED, false);
    }

    public void setIsFitConnected(boolean b) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putBoolean(PREF_IS_FIT_CONNECTED, b);
        mEditor.commit();
    }


    public boolean getIsFirstTimeRegister() {
        boolean str = mPrefs.getBoolean(PREF_IsFirstTimeRegister, true);
        return str;
    }

    public void setIsFirstTimeRegister(boolean pref_isfirsttimeregister) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putBoolean(PREF_IsFirstTimeRegister, pref_isfirsttimeregister);
        mEditor.commit();
    }

    public boolean getIsHraCompleted() {
        boolean str = mPrefs.getBoolean(PREF_IS_HRA_COMPLETED, false);
        return str;
    }

    public void setIsHraCompleted(boolean pref_isfirsttimeregister) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putBoolean(PREF_IS_HRA_COMPLETED, pref_isfirsttimeregister);
        mEditor.commit();
    }

    public String getCustomerDOB() {
        String str = mPrefs.getString(PREF_LOGGED_IN_CUSTOMER_DOB, "");
        return str;
    }

    public void setLoggedinUserDOB(String pref_modequery) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putString(PREF_LOGGED_IN_CUSTOMER_DOB, pref_modequery);
        mEditor.commit();
    }

    public String getCustomerGender() {
        String str = mPrefs.getString(PREF_LOGGED_IN_CUSTOMER_GENDER, "");
        return str;
    }

    public String getCustomerMobileNumber() {
        String str = mPrefs.getString(PREF_LOGGED_IN_CUSTOMER_MOBILENUMBER, "");
        return str;
    }

    public void setLoggedinUserMobileNumber(String pref_mobilenumber) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putString(PREF_LOGGED_IN_CUSTOMER_MOBILENUMBER, pref_mobilenumber);
        mEditor.commit();
    }

    public void setLoggedinUserGender(String pref_modequery) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putString(PREF_LOGGED_IN_CUSTOMER_GENDER, pref_modequery);
        mEditor.commit();
    }

    public int getCustomerWizardStatus() {
        return mPrefs.getInt(PREF_LOGGED_IN_CUSTOMER_WIZARD, 0);
    }

    public void setCustomerWizardStatus(int data) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putInt(PREF_LOGGED_IN_CUSTOMER_WIZARD, data);
        mEditor.commit();
    }

    public String getPREF_DOCTORCHATID() {
        String str = mPrefs.getString(PREF_DOCTORCHATID, "");
        return str;
    }

    public void setPREF_DOCTORCHATID(String pref_doctorchatid) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putString(PREF_DOCTORCHATID, pref_doctorchatid);
        mEditor.commit();
    }

    public String getPREF_DOCTORID() {
        String str = mPrefs.getString(PREF_DOCTORID, "");
        return str;
    }

    public void setPREF_DOCTORID(String pref_doctorid) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putString(PREF_DOCTORID, pref_doctorid);
        mEditor.commit();
    }

    public boolean getIsFirstTimerBadge() {
        boolean bool = mPrefs.getBoolean(PREF_IS_FIRST_TIMER_BADGE, false);
        return bool;
    }

    public void setIsFirstTimerBadge(boolean flag) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putBoolean(PREF_IS_FIRST_TIMER_BADGE, flag);
        mEditor.commit();
    }

    public boolean getIsFirstTimer() {
        boolean bool = mPrefs.getBoolean(PREF_IS_FIRST_TIMER, false);
        return bool;
    }

    public void setIsFirstTimer(boolean flag) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putBoolean(PREF_IS_FIRST_TIMER, flag);
        mEditor.commit();
    }

    public boolean getIsFirstWellcome() {
        boolean bool = mPrefs.getBoolean(PREF_IS_FIRST_WELLCOME, false);
        return bool;
    }

    public void setIsFirstWellcome(boolean flag) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putBoolean(PREF_IS_FIRST_WELLCOME, flag);
        mEditor.commit();
    }

    public boolean getIsFirstTimeDoctorChat() {
        boolean bool = mPrefs.getBoolean(PREF_IS_FIRST_TIME_DOCTORCHAT, true);
        return bool;
    }

    public void setIsFirstTimeDoctorChat(boolean flag) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putBoolean(PREF_IS_FIRST_TIME_DOCTORCHAT, flag);
        mEditor.commit();
    }

    public boolean getIsFirstTimeChat() {
        boolean bool = mPrefs.getBoolean(PREF_IS_FIRST_TIME_CHAT, true);
        return bool;
    }

    public void setIsFirstTimeChat(boolean flag) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putBoolean(PREF_IS_FIRST_TIME_CHAT, flag);
        mEditor.commit();
    }

    public boolean getIsMainDoc() {
        boolean bool = mPrefs.getBoolean(PREF_DOC_MAIN, false);
        return bool;
    }

    public void setIsMainDoc(boolean flag) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putBoolean(PREF_DOC_MAIN, flag);
        mEditor.commit();
    }

    public boolean getIsOrderMeds() {
        boolean bool = mPrefs.getBoolean(PREF_ORDER_MEDS, false);
        return bool;
    }

    public void setIsOrderMeds(boolean flag) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putBoolean(PREF_ORDER_MEDS, flag);
        mEditor.commit();
    }

    public boolean getHasSeenDocUploadTuts() {
        boolean bool = mPrefs.getBoolean(PREF_HAS_SEEN_DOCUMENT_UPLOAD_TUTORIAL, false);
        return bool;
    }

    public void setHasSeenDocUploadTuts(boolean flag) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putBoolean(PREF_HAS_SEEN_DOCUMENT_UPLOAD_TUTORIAL, flag);
        mEditor.commit();
    }

    public boolean getNeverShowDocUploadTuts() {
        boolean bool = mPrefs.getBoolean(PREF_NEVER_SHOW_DOCUMENT_UPLOAD_TUTORIAL, false);
        return bool;
    }

    public void setNeverShowDocUploadTuts(boolean flag) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putBoolean(PREF_NEVER_SHOW_DOCUMENT_UPLOAD_TUTORIAL, flag);
        mEditor.commit();
    }

    public String getPREF_RUNTIME_PERMISSION() {
        String str = mPrefs.getString(PREF_RUNTIME_PERMISSION, "");
        return str;

    }

    public void setPREF_RUNTIME_PERMISSION(String pref_runtime_permission) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putString(PREF_RUNTIME_PERMISSION, pref_runtime_permission);
        mEditor.commit();
    }

    public boolean getHasSeenTutorial() {
        boolean bool = mPrefs.getBoolean(PREF_HAS_SEEN_TUTORIAL, false);
        return bool;
    }

    public void setHasSeenTutorial(boolean flag) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putBoolean(PREF_HAS_SEEN_TUTORIAL, flag);
        mEditor.commit();
    }

    public boolean getHasSeenActivityFamilyMemeberTutorial() {
        boolean bool = mPrefs.getBoolean(PREF_HAS_SEEN_ACTIVITY_FAMILY_MEMBER_TUTORIAL, false);
        return bool;
    }

    public void setHasSeenActivityFamilyMemeberTutorial(boolean flag) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putBoolean(PREF_HAS_SEEN_ACTIVITY_FAMILY_MEMBER_TUTORIAL, flag);
        mEditor.commit();
    }

    public boolean getHasSeenDocumentFamilyMemeberTutorial() {
        boolean bool = mPrefs.getBoolean(PREF_HAS_SEEN_DOC_FAMILY_MEMBER_TUTORIAL, false);
        return bool;
    }

    public void setHasSeenDocumentFamilyMemeberTutorial(boolean flag) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putBoolean(PREF_HAS_SEEN_DOC_FAMILY_MEMBER_TUTORIAL, flag);
        mEditor.commit();
    }

    public boolean getHasSeenHistoryFamilyMemeberTutorial() {
        boolean bool = mPrefs.getBoolean(PREF_HAS_SEEN_HISTORY_FAMILY_MEMBER_TUTORIAL, false);
        return bool;
    }

    public void setHasSeenHistoryFamilyMemeberTutorial(boolean flag) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putBoolean(PREF_HAS_SEEN_HISTORY_FAMILY_MEMBER_TUTORIAL, flag);
        mEditor.commit();
    }

    public boolean getHasSeenMedeicationFamilyMemeberTutorial() {
        boolean bool = mPrefs.getBoolean(PREF_HAS_SEEN_MEDICICATION_FAMILY_MEMBER_TUTORIAL, false);
        return bool;
    }

    public void setHasSeenMedicationFamilyMemeberTutorial(boolean flag) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putBoolean(PREF_HAS_SEEN_MEDICICATION_FAMILY_MEMBER_TUTORIAL, flag);
        mEditor.commit();
    }

    public boolean getHasSeenAllergyFamilyMemeberTutorial() {
        boolean bool = mPrefs.getBoolean(PREF_HAS_SEEN_ALLERGY_FAMILY_MEMBER_TUTORIAL, false);
        return bool;
    }

    public void setHasSeenAllergyFamilyMemeberTutorial(boolean flag) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putBoolean(PREF_HAS_SEEN_ALLERGY_FAMILY_MEMBER_TUTORIAL, flag);
        mEditor.commit();
    }

    public String getAfterNoonTakenMedicationId() {
        String str = mPrefs.getString(PREF_TAKEN_AFTERNOON_MEDICATION_ID, "");
        return str;
    }

    public void setAfterNoonTakenMedicationId(String ids) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putString(PREF_TAKEN_AFTERNOON_MEDICATION_ID, ids);
        mEditor.commit();
    }

    public String getMorningTakenMedicationId() {
        String str = mPrefs.getString(PREF_TAKEN_MORNING_MEDICATION_ID, "");
        return str;
    }

    public void setMorningTakenMedicationId(String ids) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putString(PREF_TAKEN_MORNING_MEDICATION_ID, ids);
        mEditor.commit();
    }

    public String getEveningTakenMedicationId() {
        String str = mPrefs.getString(PREF_TAKEN_EVENING_MEDICATION_ID, "");
        return str;
    }

    public void setEveningTakenMedicationId(String ids) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putString(PREF_TAKEN_EVENING_MEDICATION_ID, ids);
        mEditor.commit();
    }

    public String gettotalFamilyMemberList() {
        String str = mPrefs.getString(FAMILY_MEMBER_LIST, "");
        return str;
    }

    public void settotalFamilyMemberList(String family_member_list) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putString(FAMILY_MEMBER_LIST, family_member_list);
        mEditor.commit();
    }

    public boolean getHasHowWasYourDayDataFilled() {
        boolean flag = mPrefs.getBoolean(PREF_HAS_HOW_WAS_YOUR_DAY_DATA_FILLED, false);
        return flag;
    }

    public void setHasHowWasYourDayDataFilled(boolean flag) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putBoolean(PREF_HAS_HOW_WAS_YOUR_DAY_DATA_FILLED, flag);
        mEditor.commit();
    }

    public boolean getHasSeenDigitizedRecords() {
        boolean flag = mPrefs.getBoolean(PREF_HAS_SEEN_DIGITIZED_RECORED, false);
        return flag;
    }

    public void setHasSeenDigitizedRecords(boolean flag) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putBoolean(PREF_HAS_SEEN_DIGITIZED_RECORED, flag);
        mEditor.commit();
    }

    public boolean getIsAnyDigitizedRecords() {
        boolean flag = mPrefs.getBoolean(PREF_IS_ANY_DIGITIZED_RECORED, false);
        return flag;
    }

    public void setIsAnyDigitizedRecords(boolean flag) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putBoolean(PREF_IS_ANY_DIGITIZED_RECORED, flag);
        mEditor.commit();
    }

    public int getCustomerImageColor() {
        int color = mPrefs.getInt(PREF_CUSTOMER_PROFILE_IMAGE_COLOR, 0);
        return color;
    }

    public void setcustomerNToken(String pref_customer_token) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putString(PREF_CUSTOMER_TOKEN, pref_customer_token);
        mEditor.commit();


    }

    public void setimageUrl(String pref_image_url) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putString(PREF_IMAGE_URL, pref_image_url);
        mEditor.commit();


    }

    public String getSleepTime() {
        String time = mPrefs.getString(PREF_SLEEP_TIME, "0");
        return time;
    }

    public void setSleepTime(String time) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putString(PREF_SLEEP_TIME, time);
        mEditor.commit();
    }

    public String getWakeTime() {
        String time = mPrefs.getString(PREF_WOKE_TIME, "0");
        return time;
    }

    public void setWakeTime(String time) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putString(PREF_WOKE_TIME, time);
        mEditor.commit();
    }

    public boolean gethowWasDayReminder() {
        boolean str = mPrefs.getBoolean(PREF_HOW_WAS_DAY_REMINDER, true);
        return str;
    }

    public void sethowWasDayReminder(boolean flag) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putBoolean(PREF_HOW_WAS_DAY_REMINDER, flag);
        mEditor.commit();
    }


    public String gethowWasDayDone() {
        String str = mPrefs.getString(PREF_HOW_WAS_DONE, "");
        return str;
    }

    public void sethowWasDayDone(String pref_how_was_done) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putString(PREF_HOW_WAS_DONE, pref_how_was_done);
        mEditor.commit();
    }

    public String gethowWasDay() {
        String str = mPrefs.getString(PREF_TODAY_HOW_FEEL, "");
        return str;
    }

    public void sethowWasDay(String pref_today_how_feel) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putString(PREF_TODAY_HOW_FEEL, pref_today_how_feel);
        mEditor.commit();
    }

    public boolean gethadGoodSleep() {
        boolean flag = mPrefs.getBoolean(PREF_HAD_GOODSLEEP, false);
        return flag;
    }

    public void sethadGoodSleep(boolean flag) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putBoolean(PREF_HAD_GOODSLEEP, flag);
        mEditor.commit();
    }

    public boolean getskippedMeals() {
        boolean flag = mPrefs.getBoolean(PREF_SKIPPED_MEAL, false);
        return flag;
    }

    public void setskippedMeals(boolean flag) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putBoolean(PREF_SKIPPED_MEAL, flag);
        mEditor.commit();
    }

    public boolean gethadbreakfast() {
        boolean flag = mPrefs.getBoolean(PREF_HAD_BREAKFAST, false);
        return flag;
    }

    public void sethadbreakfast(boolean flag) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putBoolean(PREF_HAD_BREAKFAST, flag);
        mEditor.commit();
    }


    public boolean getLateNightPhoneUsuage() {
        boolean flag = mPrefs.getBoolean(PREF_SLEEP_ON_TIME, false);
        return flag;
    }

    public void setLateNightPhoneUsuage(boolean flag) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putBoolean(PREF_SLEEP_ON_TIME, flag);
        mEditor.commit();
    }


    public void setDrinkedCoffeeToday(boolean flag) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putBoolean(PREF_DRINKED_COFFEE, flag);
        mEditor.commit();
    }


    public boolean getDrinkedAlcoholToday() {
        boolean flag = mPrefs.getBoolean(PREF_DRINKED_ALCOHOL, false);
        return flag;
    }

    public void setDrinkedAlcoholToday(boolean flag) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putBoolean(PREF_DRINKED_ALCOHOL, flag);
        mEditor.commit();
    }

    public boolean getSmokedToday() {
        boolean flag = mPrefs.getBoolean(PREF_SMOKED_TODAY, false);
        return flag;
    }

    public void setSmokedToday(boolean flag) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putBoolean(PREF_SMOKED_TODAY, flag);
        mEditor.commit();
    }


    public void setAteOnTime(boolean flag) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putBoolean(PREF_ATE_ON_TIME, flag);
        mEditor.commit();
    }

    public boolean getIsRated() {
        boolean flag = mPrefs.getBoolean(PREF_IS_RATED, false);
        return flag;
    }

    public void setIsRated(boolean flag) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putBoolean(PREF_IS_RATED, flag);
        mEditor.commit();
    }


    public void setUserRatingDayCount(int count) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putInt(PREF_RATING_FOR_DAY, count);
        mEditor.commit();
    }

    public int getUserRatingCount() {
        int str = mPrefs.getInt(PREF_RATING_FOR_COUNT, 0);
        return str;
    }

    public void setUserRatingCount(int count) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putInt(PREF_RATING_FOR_COUNT, count);
        mEditor.commit();
    }

    public String gettotalUpdateWater() {
        String str = mPrefs.getString(PREF_UPDATE_WATER_TOTAL, "");
        return str;
    }

    public void settotalUpdateWater(String pref_update_water_total) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putString(PREF_UPDATE_WATER_TOTAL, pref_update_water_total);
        mEditor.commit();
    }

    public String getupdateWater() {
        String str = mPrefs.getString(PREF_UPDATE_WATER, "");
        return str;
    }

    public void setupdateWater(String pref_update_water) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putString(PREF_UPDATE_WATER, pref_update_water);
        mEditor.commit();
    }

    public String getInstallTime() {
        String str = mPrefs.getString(PREF_INSTALL_TIME, "");
        return str;
    }

    public void setInstallTime(String pref_install_time) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putString(PREF_INSTALL_TIME, pref_install_time);
        mEditor.commit();
    }

    public String getTrendsId() {
        String str = mPrefs.getString(PREF_TRENDS, "");
        return str;

    }

    public void setTrendsId(String prefs_trends_id) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putString(PREF_TRENDS, prefs_trends_id);
        mEditor.commit();

    }

    public String getSaveGoogleLocAddress() {
        String str = mPrefs.getString(PREF_SAVE_LOC_NAME, "");
        return str;
    }

    public void setSaveGoogleLocAddress(String pref_save_loc_name) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putString(PREF_SAVE_LOC_NAME, pref_save_loc_name);
        mEditor.commit();
    }

    public String getLocationlongitude() {
        String str = mPrefs.getString(PREF_LOCATION_LAN, "");
        return str;
    }

    public void setLocationlongitude(String pref_location_lan) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putString(PREF_LOCATION_LAN, pref_location_lan);
        mEditor.commit();
    }

    public String getLocationlatitude() {
        String str = mPrefs.getString(PREF_LOCATION_LAT, "");
        return str;
    }

    public void setLocationlatitude(String pref_location_lat) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putString(PREF_LOCATION_LAT, pref_location_lat);
        mEditor.commit();
    }

    public String getSelectedDate() {
        String str = mPrefs.getString(PREF_APPOINTMENT_TIME_DATE, "");
        return str;

    }

    public void setSelectedDate(String prefs_first_date) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putString(PREF_APPOINTMENT_TIME_DATE, prefs_first_date);
        mEditor.commit();

    }

    public String getSelectedAppointment() {
        String str = mPrefs.getString(PREF_FIRSTAPPOINTMENT, "");
        return str;

    }

    public void setSelectedAppointment(String prefs_first_appointment) {

        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putString(PREF_FIRSTAPPOINTMENT, prefs_first_appointment);
        mEditor.commit();

    }


    public String getProviderID() {
        String str = mPrefs.getString(PREF_PROVIDER_ID, "");
        return str;
    }

    public void setProviderID(String prefs_providerid) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putString(PREF_PROVIDER_ID, prefs_providerid);
        mEditor.commit();
    }

    public void setProviderName(String pref_provider_name) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putString(PREF_PROVIDER_NAME, pref_provider_name);
        mEditor.commit();
    }

    public String getPackageID() {
        String str = mPrefs.getString(PREF_PACKAGEID, "");
        return str;
    }

    public void setPackageID(String pref_packageid) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putString(PREF_PACKAGEID, pref_packageid);
        mEditor.commit();
    }


    public String getAblyToken() {
        String str = mPrefs.getString(PREF_ABLYTOKEN, "");
        return str;
    }

    public void setAblyToken(String pref_ablytoken) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putString(PREF_ABLYTOKEN, pref_ablytoken);
        mEditor.commit();
    }

    public String getPackageType() {
        String str = mPrefs.getString(PREF_PACKAGETYPE, "");
        return str;
    }

    public void setPackageType(String pref_packagetype) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putString(PREF_PACKAGETYPE, pref_packagetype);
        mEditor.commit();
    }


    public String getPREF_StepsTodayDate() {
        String str = mPrefs.getString(PREF_StepsTodayDate, "");
        return str;
    }

    public void setPREF_StepsTodayDate(String PREF_Stepstodaydate) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putString(PREF_StepsTodayDate, PREF_Stepstodaydate);
        mEditor.commit();
    }

    public String getStepsCount() {
        String str = mPrefs.getString(PREF_StepsCount, "0");
        return str;
    }

    public void setStepsCount(String pREF_StepsCount) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putString(PREF_StepsCount, pREF_StepsCount);
        mEditor.commit();
    }

    public String getCaloriesCount() {
        String str = mPrefs.getString(PREF_CaloriesCount, "0");
        return str;
    }

    public void setCaloriesCount(String pREF_CaloriesCount) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putString(PREF_CaloriesCount, pREF_CaloriesCount);
        mEditor.commit();
    }


    public boolean getIsFIrstWizard() {
        boolean str = mPrefs.getBoolean(PREF_FIRST_USER_WIZARD, false);
        return str;
    }

    public void setIsFIrstWizard(boolean pPREF_FIRST_USER_WIZARD) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putBoolean(PREF_FIRST_USER_WIZARD, pPREF_FIRST_USER_WIZARD);
        mEditor.commit();
    }


    public boolean getPREF_IsCheckLastStatus() {
        boolean str = mPrefs.getBoolean(PREF_IsCheckLastStatus, false);
        return str;
    }

    public void setPREF_IsCheckLastStatus(boolean pref_ischecklaststatus) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putBoolean(PREF_IsCheckLastStatus, pref_ischecklaststatus);
        mEditor.commit();
    }

    public boolean getIsLogin() {
        boolean str = mPrefs.getBoolean(PREF_IsLogin, false);
        return str;
    }

    public void setIsLogin(boolean pREF_IsLogin) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putBoolean(PREF_IsLogin, pREF_IsLogin);
        mEditor.commit();
    }


    public String getAppToken() {
        String str = mPrefs.getString(PREF_AppToken, "");
        return str;
    }

    public void setAppToken(String pREF_AppToken) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putString(PREF_AppToken, pREF_AppToken);
        mEditor.commit();
    }

    public boolean isSentTokenFor(String regId) {

        return mPrefs.getString("token_push", "").equals(regId);

    }

    public void sentTokenFor(String regId) {

        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putString("token_push", regId);
        mEditor.commit();


    }

    public String getLong() {
        String str = mPrefs.getString(PREF_Long, "");
        return str;
    }

    public void setLong(String pREF_Long) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putString(PREF_Long, pREF_Long);
        mEditor.commit();
    }

    public String getManualData() {
        String str = mPrefs.getString(PREF_CheckManual, "");
        return str;
    }

    public void setManualData(String pref_checkmanual) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putString(PREF_CheckManual, pref_checkmanual);
        mEditor.commit();
    }

    public String getUserName() {
        String str = mPrefs.getString(PREF_UserName, "");
        return str;
    }

    public void setLoggedinUserName(String pREF_UserName) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putString(PREF_UserName, pREF_UserName);
        mEditor.commit();
    }


    public String getEkinKey() {
        String str = mPrefs.getString(PREF_X_EKINCARE_KEY, "");
        return str;
    }


    public void setEkinKey(String pREF_EkinKey) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putString(PREF_X_EKINCARE_KEY, pREF_EkinKey);
        mEditor.commit();
    }

    public String getCustomerKey() {
        String str = mPrefs.getString(PREF_X_CUSTOMER_KEY, "");
        return str;
    }

    public void setCustomerKey(String pREF_CustomerKey) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putString(PREF_X_CUSTOMER_KEY, pREF_CustomerKey);
        mEditor.commit();
    }


    public String getProfileData() {
        String str = mPrefs.getString(PREF_ProfileData, "");
        return str;
    }

    public void setProfileData(String pREF_ProfileData) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putString(PREF_ProfileData, pREF_ProfileData);
        mEditor.commit();
    }

    public void setYouCustomer(String pREF_youCustomer) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putString(PREF_YouCustomer, pREF_youCustomer);
        mEditor.commit();
    }

    public float getTarget() {
        float str = mPrefs.getFloat(PREF_Target, 0);
        return str;
    }

    public void setTarget(float pREF_target) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putFloat(PREF_Target, pREF_target);
        mEditor.commit();
    }


    public boolean getisHydrocareSubscriptionEnable() {
        boolean str = mPrefs.getBoolean(PREF_isHydrocareSubscriptionEnable, false);
        return str;
    }

    public void setisHydrocareSubscriptionEnable(boolean pREF_IsLogin) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putBoolean(PREF_isHydrocareSubscriptionEnable, pREF_IsLogin);
        mEditor.commit();
    }


    public void setHydrocareSubscripted(String pREF_HydrocareSubscripted) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putString(PREF_HydrocareSubscripted, pREF_HydrocareSubscripted);
        mEditor.commit();
    }

    public String getRegistrationId() {

        String registrationId = mPrefs.getString(REG_ID, "");
        if (registrationId.isEmpty()) {
            return "";
        }
        return registrationId;
    }


    public void storeRegistrationId(Context context, String regId) {
        SharedPreferences.Editor editor = mPrefs.edit();
        editor.putString(REG_ID, regId);
        editor.commit();
    }


    public void ClearAllData() {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putString(PREF_UPDATE_WATER, "0");
        mEditor.putString(PREF_GroupOne, "step");
        mEditor.putString(PREF_GroupTwo, "this week");
        mEditor.putString(PREF_TODAY_HOW_FEEL, "");
        mEditor.putString(PREF_UPDATE_WATER_TOTAL, "0");
        mEditor.putBoolean(PREF_IsLogin, false);
        mEditor.putBoolean(PREF_IS_FIRST_TIMER_BADGE, false);
        mEditor.putString(PREF_AppToken, "");
        mEditor.putString(PREF_UserId, "");
        mEditor.putString(PREF_Lat, "");
        mEditor.putString(PREF_Long, "");
        mEditor.putString(PREF_UserName, "");
        mEditor.putString(PREF_Device_Password, "");
        mEditor.putString(PREF_Device_Name, "");
        mEditor.putString(PREF_X_EKINCARE_KEY, "");
        mEditor.putString(PREF_X_CUSTOMER_KEY, "");
        mEditor.putString(PREF_Password, "");
        mEditor.putString(PREF_ProfileModel, "");
        mEditor.putString(PREF_ProfileData, "");
        mEditor.putString(PREF_Immunization, "");
        mEditor.putInt(PREF_FamilyMemCount, 0);
        mEditor.putBoolean(PREF_FIRST_USER_WIZARD, false);
        mEditor.putInt(PREF_LastQuestion, 0);
        mEditor.putStringSet(PREF_IsFamilyWizrdComplete, new HashSet<String>());
        mEditor.putBoolean(PREF_IsLoginVisible, false);
        mEditor.putBoolean(PREF_IsHaveCodeVisible, false);
        mEditor.putBoolean(PREF_IsYou, true);
        mEditor.putFloat(PREF_Target, 0);
        mEditor.putString(PREF_BloodGroup, "");
        mEditor.putString(PREF_HydrocareIntakesTimeStamp, "");
        mEditor.putString(PREF_TemperatureTimeStamp, "");
        mEditor.putString(PREF_Temperature, "");
        mEditor.putBoolean(PREF_isHydrocareSubscriptionEnable, false);
        mEditor.putBoolean(PREF_isBloodSOSSubscriptionEnable, false);
        mEditor.putString(PREF_FamilyMemberList, "");
        mEditor.putString(PREF_YouCustomer, "");
        mEditor.putString(PREF_RUNTIME_PERMISSION, "");
        mEditor.putString(PREF_HydrocareSubscripted, "0");
        mEditor.putString(PREF_StepsCount, "0");
        mEditor.putString(PREF_CaloriesCount, "0");
        mEditor.putBoolean(PREF_HAS_SEEN_DOCUMENT_UPLOAD_TUTORIAL, false);
        mEditor.putBoolean(PREF_NEVER_SHOW_DOCUMENT_UPLOAD_TUTORIAL, false);
        mEditor.putString(PREF_LOGGED_IN_CUSTOMER_MOBILENUMBER, "");
        mEditor.putString(PREF_LOGGED_IN_CUSTOMER_GENDER, "");
        mEditor.putString(PREF_LOGGED_IN_CUSTOMER_DOB, "");
        setStepsCount("0");
        setCaloriesCount("0");
        setAteOnTime(false);
        setDrinkedCoffeeToday(false);
        setDrinkedAlcoholToday(false);
        setSmokedToday(false);
        setLateNightPhoneUsuage(false);
        setSleepTime("0");
        setWakeTime("0");
        setSleepTime("0");
        sethadbreakfast(false);
        settotalUpdateWater("0");
        setskippedMeals(false);
        setHasHowWasYourDayDataFilled(false);
        sethowWasDay("");
        setIsOrderMeds(false);
        mEditor.commit();
        setHasSeenMedicationFamilyMemeberTutorial(false);
        setHasSeenActivityFamilyMemeberTutorial(false);
        setHasSeenAllergyFamilyMemeberTutorial(false);
        setHasSeenDocumentFamilyMemeberTutorial(false);
        setHasSeenHistoryFamilyMemeberTutorial(false);
        setHasSeenTutorial(false);

        setIsFirstTimeRegister(false);

        setIsFitConnected(false);
        setUserRatingCount(0);
        setUserRatingDayCount(0);
        setIsRated(false);

        setIsFirstTimeChat(true);
    }

    public int getAPKVersion() {
        int str = mPrefs.getInt(PREF_APKVersion, 0);
        return str;
    }

    public void setAPKVersion(int APKVersion) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putInt(PREF_APKVersion, APKVersion);
        mEditor.commit();
    }

    public void setSkipMobileNumber(boolean skipMobileNumber) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putBoolean(PREF_SkipMobileNumber, skipMobileNumber);
        mEditor.commit();
    }

    public boolean getPREF_SkipMobileNumber() {
        return mPrefs.getBoolean(PREF_SkipMobileNumber, false);
    }

    public boolean isEncryted() {
        return mPrefs.getBoolean(PREF_ENCRYTION, false);
    }

    public void setIsEncrypted(boolean b) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putBoolean(PREF_ENCRYTION, b);
        mEditor.commit();
    }

    public void setFirstRegister(boolean b) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putBoolean(PREF_FIRST_REGISTER, b);
        mEditor.commit();
    }

    public boolean isFirstRegister() {
        return mPrefs.getBoolean(PREF_FIRST_REGISTER, false);
    }

    public void setUpdateLater(boolean b) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putBoolean(PREF_UPDATE_APP_LATER, b);
        mEditor.commit();
    }

    public boolean isUpdate() {
        return mPrefs.getBoolean(PREF_UPDATE_APP_LATER, false);
    }


    public boolean getDialogView() {
        return mPrefs.getBoolean(IS_DIALOG_VIEW, false);
    }

    public void setDialogView(boolean b) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putBoolean(IS_DIALOG_VIEW, b);
        mEditor.commit();
    }
}