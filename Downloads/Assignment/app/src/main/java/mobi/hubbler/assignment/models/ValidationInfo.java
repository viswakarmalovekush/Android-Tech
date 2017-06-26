package mobi.hubbler.assignment.models;

/**
 * Created by lovekushvishwakarma on 24/06/17.
 */

public class ValidationInfo {
    private boolean nameRequired, ageValidation, genderVisiblity;
    private int ageMin, ageMax;

    public boolean isGenderVisiblity() {
        return genderVisiblity;
    }

    public void setGenderVisiblity(boolean genderVisiblity) {
        this.genderVisiblity = genderVisiblity;
    }

    public boolean isNameRequired() {
        return nameRequired;
    }

    public boolean isAgeValidation() {
        return ageValidation;
    }

    public void setAgeValidation(boolean ageValidation) {
        this.ageValidation = ageValidation;
    }

    public void setNameRequired(boolean nameRequired) {
        this.nameRequired = nameRequired;
    }

    public int getAgeMin() {
        return ageMin;
    }

    public void setAgeMin(int ageMin) {
        this.ageMin = ageMin;
    }

    public int getAgeMax() {
        return ageMax;
    }

    public void setAgeMax(int ageMax) {
        this.ageMax = ageMax;
    }
}
