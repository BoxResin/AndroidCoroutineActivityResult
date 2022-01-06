# 안드로이드 코루틴 ActivityResult
블로그 포스팅: https://blog.naver.com/eominsuk55/222612063409
- [소스코드 바로가기](app/src/main/java/kr/boxresin/coroutineactivityresult)
- [레이아웃 바로가기](app/src/main/res/layout)

# 사용
## 액티비티 실행 및 결과 수신
```kotlin
val scope: CoroutineScope = ...
scope.launch {
    val activity: AppCompatActivity = ...
    val intent = Intent(...)

    // 액티비티 실행 및 결과 수신
    val result: ActivityResult = activity.startActivityForResult(intent)
}
```

## 권한 요청 및 권한 획득 여부 확인
```kotlin
val scope: CoroutineScope = ...
scope.launch {
    val activity: AppCompatActivity = ...

    // 카메라 접근과 전화 걸기 권한 요청
    val granted: Map<String, Boolean> = activity.requestPermissions(
        Manifest.permission.CAMERA, Manifest.permission.CALL_PHONE
    )
    
    // 카메라 접근 권한 획득 실패 시
    if (granted[Manifest.permission.CAMERA] != true) {
        ...
    }
    // 전화 걸기 권한 획득 실패 시
    if (granted[Manifest.permission.CALL_PHONE] != true) {
        ...
    }
}
```
