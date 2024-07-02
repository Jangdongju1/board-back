package com.persnal.boardback.common;

public interface ResponseMessage {
    String SUCCESS = "Success";

    // http code 400
    String VALIDATION_FAILED = "validation failed";
    String DUPLICATE_EMAIL = "Duplicate email";
    String DUPLICATE_NICKNAME = "Duplicate Nickname";
    String DUPLICATE_TEL_NUMBER = "Duplicate tel Number";
    String NOT_EXIST_USER = "This user does not exist";
    String NOT_EXIST_BOARD = "This board does not exist";

    // http code 401
    String SIGN_IN_FAILED = "Information for Login is mismatched.";
    String AUTHENTICATION_FAILED = "Authentication failed";

    //  http code 403
    String NO_PERMISSION = "No permission";

    // http code 500
    String DATABASE_ERROR = "DB Error";

}
