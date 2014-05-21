package de.sliceanddice.maryandpaul.lib.internal.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import de.sliceanddice.maryandpaul.lib.models.Autocomplete;
import de.sliceanddice.maryandpaul.lib.models.Suggest;

public class SuggestResponse extends CollinsResponse<Suggest> {

    @SerializedName("suggest")
    private Suggest suggests;

    public Suggest get() {
        return suggests;
    }
}
