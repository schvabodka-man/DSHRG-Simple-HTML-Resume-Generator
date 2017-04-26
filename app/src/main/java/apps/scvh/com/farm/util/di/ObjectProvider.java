package apps.scvh.com.farm.util.di;

import android.content.Context;

import javax.inject.Named;

import apps.scvh.com.farm.ui.workers.IgnoredFieldsWorker;
import apps.scvh.com.farm.ui.workers.TextBoxFactory;
import apps.scvh.com.farm.ui.workers.ViewChecker;
import apps.scvh.com.farm.util.workers.CVRenderer;
import apps.scvh.com.farm.util.workers.FSWorker;
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
        return new IgnoredFieldsWorker(context);
    }

    @Provides
    @Named("Renderer")
    CVRenderer getRenderer() {
        return new CVRenderer(context);
    }

    @Provides
    @Named("FSWorker")
    FSWorker getFSWorker() {
        return new FSWorker(context);
    }
}
