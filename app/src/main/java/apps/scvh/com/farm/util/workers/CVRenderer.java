package apps.scvh.com.farm.util.workers;


import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.jrummyapps.android.util.HtmlBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.inject.Inject;
import javax.inject.Named;

import apps.scvh.com.farm.R;
import apps.scvh.com.farm.util.cv.CV;
import apps.scvh.com.farm.util.di.DaggerAppComponent;
import apps.scvh.com.farm.util.di.ObjectProvider;
import apps.scvh.com.farm.util.enums.CVFields;

public class CVRenderer extends AsyncTask<CV, Integer, String> {

    private Context context;
    private ProgressDialog bar;

    @Inject
    @Named("RendererHelper")
    RenderHelper renderHelper;

    public CVRenderer(Context context) {
        bar = new ProgressDialog(context);
        this.context = context;
        DaggerAppComponent.builder().objectProvider(new
                ObjectProvider(context)).build().inject(this);
    }

    private String renderCV(CV cv) {
        HtmlBuilder builder = new HtmlBuilder();
        builder.h1(cv.getFullName());
        builder.h1(cv.getAbout());
        try {
            drawList(builder, cv.getEducation(), CVFields.EDUCATION);
            drawList(builder, cv.getExperience(), CVFields.EXPERIENCE);
            drawList(builder, cv.getLinks(), CVFields.LINKS);
            drawList(builder, cv.getProjects(), CVFields.PROJECTS);
            drawList(builder, cv.getPrimarySkills(), CVFields.PRIMARY_SKILLS);
            drawList(builder, cv.getSecondarySkills(), CVFields.SECONDARY_SKILLS);
            drawList(builder, cv.getOtherSkills(), CVFields.OTHER_SKILLS);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return builder.toString(); //i'm not building it, i just want html
    }


    private void drawList(HtmlBuilder builder, ArrayList<String> list, CVFields flag)
            throws IOException {
        if (!list.isEmpty()) {
            Iterator<String> iterator = list.iterator();
            builder.h1(renderHelper.getStringForField(flag));
            while (iterator.hasNext()) {
                builder.h2(iterator.next());
            }
        } else {
            Log.d(context.getString(R.string.pdf_render_debug), context.getString(R.string
                    .pdf_render_null));
        }
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        bar.show();
        bar.setTitle(context.getString(R.string.loading));
        bar.setMessage(context.getString(R.string.please_wait));
    }

    @Override
    protected String doInBackground(CV... params) {
        return renderCV(params[0]);
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        bar.dismiss();
    }

}
