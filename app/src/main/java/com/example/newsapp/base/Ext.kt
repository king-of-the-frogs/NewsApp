package com.example.newsapp.base

import android.content.Context
import android.content.res.Configuration
import android.view.View
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

inline fun <reified T> attempt(func: () -> T): Either<Throwable, T> = try {
    Either.Right(func.invoke())
} catch (e: Throwable) {
    Either.Left(e)
}

fun <T : ViewBinding> Fragment.viewBinding(viewBindingFactory: (View) -> T) =
    FragmentBindingDelegate(this, viewBindingFactory)

fun Context.isDarkModeEnabled(): Boolean {
    return resources.configuration.uiMode and
            Configuration.UI_MODE_NIGHT_MASK == Configuration.UI_MODE_NIGHT_YES
}