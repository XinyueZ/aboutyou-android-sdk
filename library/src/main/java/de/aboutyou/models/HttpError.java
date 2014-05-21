package de.aboutyou.models;

import com.google.gson.annotations.SerializedName;

public class HttpError {

    @SerializedName("error_type")
    private String errorType;
    @SerializedName("error_code")
    private int errorCode;
    @SerializedName("error_message")
    private String errorMessage;

    public String getErrorType() {
        return errorType;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
