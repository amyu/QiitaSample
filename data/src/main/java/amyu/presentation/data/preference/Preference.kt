package amyu.presentation.data.preference

import android.content.Context
import android.content.SharedPreferences
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.rxkotlin.toSingle

interface Preference {
    val context: Context
    val preferenceName: String
    val sharedPreference: SharedPreferences
        get() = context.getSharedPreferences(preferenceName, Context.MODE_PRIVATE)

    fun write(writer: (SharedPreferences.Editor) -> Unit): Completable =
            Completable.create {
                val editor = sharedPreference.edit()
                writer(editor)
                editor.apply()
                it.onComplete()
            }

    fun readString(key: String, default: String): Single<String> = sharedPreference.getString(key, default).toSingle()
    fun readStringWithOptional(key: String, default: String?): Single<String> = sharedPreference.getString(key, null).toSingle()
    fun readInt(key: String, default: Int): Single<Int> = sharedPreference.getInt(key, default).toSingle()
    fun readLong(key: String, default: Long): Single<Long> = sharedPreference.getLong(key, default).toSingle()
    fun readFloat(key: String, default: Float): Single<Float> = sharedPreference.getFloat(key, default).toSingle()
    fun readBoolean(key: String, default: Boolean): Single<Boolean> = sharedPreference.getBoolean(key, default).toSingle()

    fun clear(): Completable = Completable.create {
        sharedPreference.edit().clear().apply()
        it.onComplete()
    }

}