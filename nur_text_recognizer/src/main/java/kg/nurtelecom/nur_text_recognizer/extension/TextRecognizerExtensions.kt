package kg.nurtelecom.nur_text_recognizer.extension

import android.content.res.Resources

val Int.dp: Float
    get() = (this * Resources.getSystem().displayMetrics.density)
