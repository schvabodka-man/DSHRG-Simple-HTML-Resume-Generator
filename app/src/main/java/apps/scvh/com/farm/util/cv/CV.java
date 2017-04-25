package apps.scvh.com.farm.util.cv;


import java.io.Serializable;
import java.util.ArrayList;

public class CV implements Serializable {

    private String fullName;
    private String about;
    private ArrayList<String> experience;
    private ArrayList<String> education;
    private ArrayList<String> projects;
    private ArrayList<String> links;
    private ArrayList<String> primarySkills;
    private ArrayList<String> secondarySkills;
    private ArrayList<String> otherSkills;


    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public ArrayList<String> getExperience() {
        return experience;
    }

    public void setExperience(ArrayList<String> experience) {
        this.experience = experience;
    }

    public ArrayList<String> getEducation() {
        return education;
    }

    public void setEducation(ArrayList<String> education) {
        this.education = education;
    }

    public ArrayList<String> getProjects() {
        return projects;
    }

    public void setProjects(ArrayList<String> projects) {
        this.projects = projects;
    }

    public ArrayList<String> getLinks() {
        return links;
    }

    public void setLinks(ArrayList<String> links) {
        this.links = links;
    }

    public ArrayList<String> getPrimarySkills() {
        return primarySkills;
    }

    public void setPrimarySkills(ArrayList<String> primarySkills) {
        this.primarySkills = primarySkills;
    }

    public ArrayList<String> getSecondarySkills() {
        return secondarySkills;
    }

    public void setSecondarySkills(ArrayList<String> secondarySkills) {
        this.secondarySkills = secondarySkills;
    }

    public ArrayList<String> getOtherSkills() {
        return otherSkills;
    }

    public void setOtherSkills(ArrayList<String> otherSkills) {
        this.otherSkills = otherSkills;
    }
}
