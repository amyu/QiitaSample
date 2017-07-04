package amyu.presentation.data

import amyu.presentation.data.api.ApiFactory
import amyu.presentation.data.api.OkHttpClientFactory
import amyu.presentation.data.preference.auth.AuthPreference
import amyu.presentation.data.preference.auth.AuthPreferenceImpl
import amyu.presentation.data.repository.cache.CacheList
import amyu.presentation.data.repository.cache.CacheMap
import amyu.presentation.data.service.login.LoginServiceImpl
import amyu.presentation.domain.service.login.LoginService
import com.github.salomonbrys.kodein.*
import org.threeten.bp.Clock
import org.threeten.bp.ZoneId


val dataModule = Kodein.Module {
    //api
    bind<OkHttpClientFactory>() with singleton { OkHttpClientFactory(instance()) }
    bind<ApiFactory>() with singleton { ApiFactory(instance()) }

    //preference
    bind<AuthPreference>() with singleton { AuthPreferenceImpl(instance()) }

    //cache
    bind<Clock>("cache") with provider { Clock.system(ZoneId.systemDefault()) }
    bind<CacheMap.Provider>() with provider { CacheMap.Provider(instance("cache")) }
    bind<CacheList.Provider>() with provider { CacheList.Provider(instance("cache")) }

    //data
    bind<LoginService>() with provider { LoginServiceImpl(ApiFactory(instance()).login(), instance()) }
}