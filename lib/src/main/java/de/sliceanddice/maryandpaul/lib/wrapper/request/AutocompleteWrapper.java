package de.sliceanddice.maryandpaul.lib.wrapper.request;

import com.google.gson.annotations.SerializedName;

import de.sliceanddice.maryandpaul.lib.models.Autocomplete;

public class AutocompleteWrapper extends BaseWrapper {

    @SerializedName("autocompletion")
    private Autocomplete autocomplete;

    public Autocomplete getAutocomplete() {
        return autocomplete;
    }
}
