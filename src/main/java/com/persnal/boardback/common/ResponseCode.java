package com.persnal.boardback.common;

public interface ResponseCode {
    // http code 200
    String SUCCESS = "SU";

    // http code 400
    String VALIDATION_FAILED = "VF";
    String DUPLICATE_EMAIL = "DE";
    String DUPLICATE_NICKNAME = "DN";
    String DUPLICATE_TEL_NUMBER = "DT";
    String NOT_EXIST_USER = "NU";
    String NOT_EXIST_BOARD = "NB";

    // http code 401
    String SIGN_IN_FAILED = "SF";
    String AUTHENTICATION_FAILED = "AF";

    //  http code 403
    String NO_PERMISSION = "NP";

    // http code 500
    String DATABASE_ERROR = "DBE";


}
