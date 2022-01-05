package kr.boxresin.coroutineactivityresult

import android.util.SparseArray
import androidx.lifecycle.ViewModel
import kotlin.coroutines.Continuation

internal class ActivityResultViewModel : ViewModel() {
    val activityResultMap = SparseArray<Continuation<ActivityResult>>()
}
