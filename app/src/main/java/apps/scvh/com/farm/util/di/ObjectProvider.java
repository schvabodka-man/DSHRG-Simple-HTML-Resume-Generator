package apps.scvh.com.farm.util.di;

import android.content.Context;
import android.preference.PreferenceManager;

import javax.inject.Named;

import apps.scvh.com.farm.ui.workers.IgnoredFieldsWorker;
import apps.scvh.com.farm.ui.workers.TextBoxFactory;
import apps.scvh.com.farm.ui.workers.ViewChecker;
import apps.scvh.com.farm.util.workers.CVRenderer;
import apps.scvh.com.farm.util.workers.RenderHelper;
import dagger.Module;
import dagger.Provides;

@Module
public class ObjectProvider {

    private Context context;

    public ObjectProvider(Context context) {
        this.context = context;
    }

    @Provides
    @Named("textBoxFactory")
    TextBoxFactory getFactory() {
        return new TextBoxFactory(context);
    }

    @Provides
    @Named("viewChecker")
    ViewChecker getChecker() {
        return new ViewChecker();
    }

    @Provides
    @Named("IgnoreHelper")
    IgnoredFieldsWorker getWorker() {
        return new IgnoredFieldsWorker(context, PreferenceManager.getDefaultSharedPreferences
                (context));
    }

    @Provides
    @Named("Renderer")
    CVRenderer getRenderer() {
        return new CVRenderer(context);
    }

    @Provides
    @Named("RendererHelper")
    RenderHelper getRendererHelper() {
        return new RenderHelper(context, PreferenceManager.getDefaultSharedPreferences(context));
    }
}
