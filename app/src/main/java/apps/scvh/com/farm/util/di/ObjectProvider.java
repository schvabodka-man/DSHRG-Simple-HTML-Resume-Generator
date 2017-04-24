package apps.scvh.com.farm.util.di;

import android.content.Context;

import javax.inject.Named;

import apps.scvh.com.farm.ui.TextBoxFactory;
import apps.scvh.com.farm.ui.ViewChecker;
import apps.scvh.com.farm.util.CVRenderer;
import apps.scvh.com.farm.util.FSWorker;
import apps.scvh.com.farm.util.IgnoredFieldsWorker;
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
