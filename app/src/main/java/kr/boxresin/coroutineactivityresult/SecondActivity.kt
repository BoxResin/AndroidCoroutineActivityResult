package kr.boxresin.coroutineactivityresult

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

/** 두 번째 액티비티 */
class SecondActivity : AppCompatActivity() {
    /** 두 번째 액티비티가 생성될 때 호출된다. */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        // 액티비티 결과 설정
        this.setResult(2022)
    }
}
