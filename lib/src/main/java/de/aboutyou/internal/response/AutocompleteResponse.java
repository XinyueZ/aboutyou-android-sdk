package de.aboutyou.internal.response;

import com.google.gson.annotations.SerializedName;

import de.aboutyou.models.Autocomplete;

public class AutocompleteResponse extends CollinsResponse<Autocomplete> {

    @SerializedName("autocompletion")
    private Autocomplete autocomplete;

    public Autocomplete get() {
        return autocomplete;
    }
}
