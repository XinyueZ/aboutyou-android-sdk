package de.sliceanddice.maryandpaul.lib.requests;

import java.util.List;

import de.sliceanddice.maryandpaul.lib.enums.Type;

public class AutocompleteRequest extends BaseRequest {

    private Autocompletion autocompletion = new Autocompletion();

    public void setLimit(Integer limit) {
        autocompletion.limit = limit;
    }

    public void setSearchword(String searchword) {
        autocompletion.searchword = searchword;
    }

    public void setTypes(List<Type> types) {
        autocompletion.types = types;
    }

    private static class Autocompletion {

        Integer limit;
        String searchword;
        List<Type> types;

    }

}