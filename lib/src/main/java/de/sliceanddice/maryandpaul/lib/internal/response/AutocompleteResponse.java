package de.sliceanddice.maryandpaul.lib.internal.response;

import com.google.gson.annotations.SerializedName;

import de.sliceanddice.maryandpaul.lib.models.Autocomplete;

public class AutocompleteResponse extends CollinsResponse<Autocomplete> {

    @SerializedName("autocompletion")
    private Autocomplete autocomplete;

    public Autocomplete get() {
        return autocomplete;
    }
}
