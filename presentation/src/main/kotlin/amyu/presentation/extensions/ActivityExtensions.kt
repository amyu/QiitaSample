package amyu.presentation.extensions

import amyu.presentation.R
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity


fun AppCompatActivity.shiftFragmentToContainer(fragment: Fragment) {
    supportFragmentManager.beginTransaction().apply {
        replace(R.id.container, fragment, fragment.javaClass.canonicalName)
    }.commit()
}