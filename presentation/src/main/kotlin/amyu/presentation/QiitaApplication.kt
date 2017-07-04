package amyu.presentation

import amyu.presentation.data.dataModule
import amyu.presentation.domain.domainModule
import amyu.presentation.ui.presentationModule
import android.app.Application
import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.KodeinAware
import com.github.salomonbrys.kodein.android.autoAndroidModule
import com.github.salomonbrys.kodein.lazy


class QiitaApplication : Application(), KodeinAware {
    override val kodein: Kodein by Kodein.lazy {
        import(autoAndroidModule(this@QiitaApplication))
        import(applicationModule(this@QiitaApplication))
        import(presentationModule)
        import(domainModule)
        import(dataModule)
    }
}