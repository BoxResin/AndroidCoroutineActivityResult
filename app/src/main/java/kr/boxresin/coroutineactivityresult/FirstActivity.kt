package kr.boxresin.coroutineactivityresult

import android.Manifest
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

/** 첫 번째 액티비티 */
class FirstActivity : AppCompatActivity() {
    // 코루틴 실행을 위한 스코프
    private val scope = MainScope()

    /** 첫 번째 액티비티가 생성될 때 호출된다. */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first)
    }

    /** 첫 번째 액티비티가 파괴될 때 호출된다. */
    override fun onDestroy() {
        super.onDestroy()
        this.scope.cancel()
    }

    /** 권한 요청 버튼을 클릭할 때 호출된다. */
    fun onClick(view: View) {
        this.scope.launch {
            // 카메라 접근과 전화 걸기 권한 요청
            val granted: Map<String, Boolean> = requestPermissions(
                Manifest.permission.CAMERA, Manifest.permission.CALL_PHONE
            )

            // 카메라 접근 권한 획득 실패 시
            if (granted[Manifest.permission.CAMERA] != true) {
                findViewById<TextView>(R.id.txt_message).append("카메라 이용 불가\n")
            }
            // 전화 걸기 권한 획득 실패 시
            if (granted[Manifest.permission.CALL_PHONE] != true) {
                findViewById<TextView>(R.id.txt_message).append("전화 걸기 불가\n")
            }
        }
    }
}
