package kr.boxresin.coroutineactivityresult

import android.content.Intent
import android.content.pm.PackageManager
import android.util.SparseArray
import androidx.core.util.containsKey
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlin.coroutines.Continuation
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

internal class ActivityResultFragment : Fragment() {
    private val viewModel by lazy {
        val factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T = modelClass.newInstance()
        }
        ViewModelProvider(this, factory)[InnerViewModel::class.java]
    }

    suspend fun startActivityForResult(
        intent: Intent
    ): ActivityResult = suspendCoroutine { cont: Continuation<ActivityResult> ->
        // 사용하지 않은 요청 코드 찾기
        var unusedReqCode = 0
        while (this.viewModel.activityResultMap.containsKey(unusedReqCode)) {
            unusedReqCode++
        }

        // 요청 코드와 Continuation 매핑하고 액티비티 실행
        this.viewModel.activityResultMap[unusedReqCode] = cont
        this.startActivityForResult(intent, unusedReqCode)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        // 요청 코드에 해당하는 코루틴 재개
        this.viewModel.activityResultMap[requestCode]?.resume(ActivityResult(resultCode, data))
        this.viewModel.activityResultMap.remove(requestCode)
    }

    suspend fun requestPermissions(
        permissions: Array<out String>
    ): Map<String, Boolean> = suspendCoroutine { cont: Continuation<Map<String, Boolean>> ->
        // 사용하지 않은 요청 코드 찾기
        var unusedReqCode = 0
        while (this.viewModel.permissionResultMap.containsKey(unusedReqCode)) {
            unusedReqCode++
        }

        // 요청 코드와 Continuation 매핑하고 권한 요청
        this.viewModel.permissionResultMap[unusedReqCode] = cont
        this.requestPermissions(permissions, unusedReqCode)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        // 요청 코드에 해당하는 코루틴 재개
        this.viewModel.permissionResultMap[requestCode]?.resume(
            // 요청한 권한 별 획득 성공 여부 매핑
            permissions.zip(grantResults.map { it == PackageManager.PERMISSION_GRANTED }).toMap()
        )
        this.viewModel.permissionResultMap.remove(requestCode)
    }

    private class InnerViewModel : ViewModel() {
        val activityResultMap = SparseArray<Continuation<ActivityResult>>()
        val permissionResultMap = SparseArray<Continuation<Map<String, Boolean>>>()
    }
}
