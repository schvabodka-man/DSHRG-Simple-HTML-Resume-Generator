package apps.scvh.com.farm.util.cv;


import com.tom_roush.pdfbox.pdmodel.PDDocument;

import java.io.File;

import apps.scvh.com.farm.util.FSStates;

public class CVHolder {

    private PDDocument cv;
    private File file;
    private FSStates flag;

    public CVHolder(FSStates flag, File file, PDDocument cv) {
        this.flag = flag;
        this.file = file;
        this.cv = cv;
    }

    public PDDocument getCv() {
        return cv;
    }

    public File getFile() {
        return file;
    }

    public FSStates getFlag() {
        return flag;
    }
}
