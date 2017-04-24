package apps.scvh.com.farm.util.di;

import javax.inject.Singleton;

import apps.scvh.com.farm.ui.activities.CVInput;
import apps.scvh.com.farm.ui.activities.CVReady;
import dagger.Component;

@Singleton
@Component(modules = {ObjectProvider.class})
public interface AppComponent {

    void inject(CVInput input);

    void inject(CVReady ready);

}
