# 안드로이드 코루틴 ActivityResult
블로그 포스팅: https://blog.naver.com/eominsuk55/222612063409
- [소스코드 바로가기](app/src/main/java/kr/boxresin/coroutineactivityresult)
- [레이아웃 바로가기](app/src/main/res/layout)

# 사용
```kotlin
val scope: CoroutineScope = ...
scope.launch {
    val activity: AppCompatActivity = ...
    val intent = Intent(...)

    // 액티비티 실행 및 결과 수신
    val result: ActivityResult = activity.startActivityForResult(intent)
}
```
