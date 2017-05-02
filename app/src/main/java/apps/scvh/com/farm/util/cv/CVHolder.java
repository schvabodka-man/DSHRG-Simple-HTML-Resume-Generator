package apps.scvh.com.farm.util.cv;


import java.io.File;

import apps.scvh.com.farm.util.enums.FSStates;

/**
 * ENG simple wrapper i made for operations with fs
 * RUS класс который я запилил просто для того чтобы врапать резюме с нужной мне информацией
 */
public class CVHolder {

    private String cv;
    private File file;
    private FSStates flag;

    public CVHolder(FSStates flag, File file, String cv) {
        this.flag = flag;
        this.file = file;
        this.cv = cv;
    }

    public String getCv() {
        return cv;
    }

    public File getFile() {
        return file;
    }

    public FSStates getFlag() {
        return flag;
    }
}
