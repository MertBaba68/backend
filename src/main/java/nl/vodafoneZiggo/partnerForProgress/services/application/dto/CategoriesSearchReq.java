package nl.vodafoneZiggo.partnerForProgress.services.application.dto;

public class CategoriesSearchReq {
    private String searchTerm;

    public CategoriesSearchReq(String searchTerm) {
        this.searchTerm = searchTerm;
    }

    protected CategoriesSearchReq(){
    }

    public String getSearchTerm() {
        return this.searchTerm;
    }

    public void setSearchTerm(String searchTerm) {
        this.searchTerm = searchTerm;
    }
}
