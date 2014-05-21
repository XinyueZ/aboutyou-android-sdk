package de.aboutyou.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public abstract class FallibleModel {

    @SerializedName("error_message")
    private List<String> errorMessages;
    @SerializedName("error_code")
    private Integer errorCode;

    public List<String> getErrorMessages() {
        return errorMessages;
    }

    public int getErrorCode() {
        return errorCode;
    }

}
