package com.laptrinhjavaweb.constant;

public class SystemConstants {
    /*Spring security 4: ROLE_ADMIN, Spring security 3 not required*/
    public static final String MANAGER_ROLE = "ROLE_MANAGER";
    public static final String STAFF_ROLE = "ROLE_STAFF";
    public static final String MANAGER = "MANAGER";
    public static final String STAFF = "STAFF";
    public static final String HOME = "/home";
    public static final String ADMIN_HOME = "/admin/home";
    public static final String CREATE_SUCCESS = "create_success";
    public static final String UPDATE_SUCCESS = "update_success";
    public static final String DELETE_SUCCESS = "delete_success";
    public static final String ERROR_SYSTEM = "error_system";
    public static final String ALERT = "alert";
    public static final String MESSAGE_RESPONSE = "messageResponse";
    public static final String PASSWORD_DEFAULT = "123456";

    // Status
    public static final int ACTIVE_STATUS = 1;
    public static final int NO_ACTIVE_STATUS = 0;

    // Model
    public static final String MODEL = "model";
    public static final String MODEL_SEARCH = "modelSearch";
    public static final String MODEL_RESPONSE = "modelResponse";
    public static final String MODEL_TRANSACTION_TYPES = "transactionTypeList";
    public static final String MODEL_DISTRICTS = "districts";
    public static final String MODEL_BUILDING_TYPES = "buildingTypes";
    public static final String MODEL_STAFFS = "staffs";
    public static final String MODEL_ROLES = "roles";

    // File
    public static final String UPLOAD_BUILDING_FILE_DIRECTORY_PATH = "/home/kynhanht/images/buildings/";
    public static final String LOAD_BUILDING_FILE_PATH = "/static/images/buildings/";
    public static final String DEFAULT_IMAGE = "default-image.jpg";




}
