package amyu.presentation

import android.content.Context
import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.provider
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers


fun applicationModule(context: Context) = Kodein.Module {
    bind<Context>() with instance(context)
    bind<Scheduler>("ui") with provider { AndroidSchedulers.mainThread() }
}