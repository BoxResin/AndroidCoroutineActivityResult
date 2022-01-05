package kr.boxresin.coroutineactivityresult

import android.content.Intent
import androidx.fragment.app.FragmentActivity

private const val TAG = "ActivityResultFragment"

/**
 * 액티비티를 실행하고 실행된 액티비티가 닫힐 때까지 코루틴을 정지한다.
 * @return 실행된 액티비티의 결과 값
 */
suspend fun FragmentActivity.startActivityForResult(intent: Intent): ActivityResult {
    // 이미 등록된 프래그먼트가 있으면 가져오고, 그렇지 않으면 프래그먼트 등록
    val fragment = this.supportFragmentManager.findFragmentByTag(TAG) as? ActivityResultFragment
        ?: ActivityResultFragment().also {
            this.supportFragmentManager.beginTransaction()
                .add(it, TAG)
                .commitNow()
        }

    // 액티비티 실행 및 결과 반환
    return fragment.startActivityForResult(intent)
}

/**
 * 액티비티 실행 결과
 * @param resultCode 결과 코드
 * @param data 추가 데이터
 */
data class ActivityResult(
    val resultCode: Int,
    val data: Intent?
)
