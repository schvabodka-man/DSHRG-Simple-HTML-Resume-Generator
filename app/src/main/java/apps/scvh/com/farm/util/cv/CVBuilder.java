package apps.scvh.com.farm.util.cv;

import android.widget.EditText;
import android.widget.LinearLayout;

import apps.scvh.com.farm.ui.workers.LinearStringsReader;

public class CVBuilder {

    private EditText fullName;
    private EditText about;
    private LinearLayout experience;
    private LinearLayout education;
    private LinearLayout projects;
    private LinearLayout links;
    private LinearLayout primarySkills;
    private LinearLayout secondarySkills;
    private LinearLayout otherSkills;


    private LinearStringsReader reader;

    public CVBuilder() {
        reader = new LinearStringsReader();
    }

    public CVBuilder setFullName(EditText fullName) {
        this.fullName = fullName;
        return this;
    }

    public CVBuilder setAbout(EditText about) {
        this.about = about;
        return this;
    }

    public CVBuilder setExperience(LinearLayout experience) {
        this.experience = experience;
        return this;
    }

    public CVBuilder setEducation(LinearLayout education) {
        this.education = education;
        return this;
    }

    public CVBuilder setProjects(LinearLayout projects) {
        this.projects = projects;
        return this;
    }

    public CVBuilder setLinks(LinearLayout links) {
        this.links = links;
        return this;
    }

    public CVBuilder setPrimarySkills(LinearLayout primarySkills) {
        this.primarySkills = primarySkills;
        return this;
    }

    public CVBuilder setSecondarySkills(LinearLayout secondarySkills) {
        this.secondarySkills = secondarySkills;
        return this;
    }

    public CVBuilder setOtherSkills(LinearLayout otherSkills) {
        this.otherSkills = otherSkills;
        return this;
    }

    public CV buildCV() {
        CV cv = new CV();
        cv.setFullName(fullName.getText().toString());
        cv.setAbout(about.getText().toString());
        cv.setEducation(reader.readStrings(education));
        cv.setExperience(reader.readStrings(experience));
        cv.setLinks(reader.readStrings(links));
        cv.setProjects(reader.readStrings(projects));
        cv.setPrimarySkills(reader.readStrings(primarySkills));
        cv.setSecondarySkills(reader.readStrings(secondarySkills));
        cv.setOtherSkills(reader.readStrings(otherSkills));
        return cv;
    }
}
