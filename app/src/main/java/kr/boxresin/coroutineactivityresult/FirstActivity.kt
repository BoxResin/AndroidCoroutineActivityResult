package kr.boxresin.coroutineactivityresult

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
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

    /** 액티비티 시작 버튼을 클릭할 때 호출된다. */
    fun onClick(view: View) {
        this.scope.launch {
            // 두 번째 액티비티 실행
            val intent = Intent(this@FirstActivity, SecondActivity::class.java)
            val result: ActivityResult = startActivityForResult(intent)

            // 액티비티 결과를 토스트로 출력
            Toast.makeText(this@FirstActivity, "$result", Toast.LENGTH_LONG).show()
        }
    }
}
