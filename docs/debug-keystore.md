# Debug Keystore 설정

Momens debug 빌드는 팀에서 공유하는 하나의 공용 debug keystore를 사용합니다.
팀원마다 새 SHA-1을 발급하거나 Google Console에 따로 등록하지 않습니다.

모든 팀원이 같은 `momens-debug.keystore`로 debug 앱을 서명하면 Google Sign-In과
Firebase debug 인증에 사용하는 SHA-1도 모두 동일해집니다.

## 1. 공용 keystore 저장

팀에서 공유받은 동일한 `momens-debug.keystore` 파일을 각자 컴퓨터에 저장합니다.
파일은 모두 같은 것이어야 하며, 각자 새로 생성하지 않습니다.

Git으로 관리되는 소스 파일과 분리된 위치에 두는 것을 권장합니다.

예시:

```text
/Users/hyem/keys/momens-debug.keystore
```

## 2. local.properties 설정

각자 로컬의 `local.properties`에 다음 값을 추가합니다. 파일 경로만 본인
컴퓨터에 저장한 위치로 바꿉니다.

```properties
debug.store.file=/Users/hyem/keys/momens-debug.keystore
debug.store.password=android
debug.key.alias=androiddebugkey
debug.key.password=android
```

`debug.store.file`에는 본인 컴퓨터에 저장한 공용 keystore의 절대 경로를 적습니다.

## 3. SHA-1 확인

다음 명령어를 실행합니다.

```bash
./gradlew signingReport
```

`debug` variant의 SHA-1이 팀에서 공유한 공용 SHA-1과 같은지 확인합니다.

Google Console 또는 Firebase에는 공용 keystore에서 나온 SHA-1 하나만 등록합니다.
팀원별 SHA-1을 따로 등록하지 않습니다.

## 4. 필요하면 앱 삭제 후 재설치

기존 앱이 개인 debug keystore로 설치되어 있었다면, 공용 debug keystore로 서명된
앱이 업데이트 설치되지 않을 수 있습니다.

```bash
adb uninstall com.momens.android
```

삭제 후 Android Studio에서 debug 앱을 다시 실행합니다.

## 주의사항

- `momens-debug.keystore`는 커밋하지 않습니다.
- release keystore는 이 방식으로 공유하지 않습니다.
- 공용 `momens-debug.keystore`는 팀에서 공유받은 파일을 그대로 사용합니다.
- Google Console 또는 Firebase의 SHA-1 등록은 공용 keystore 기준으로 한 번만
  처리합니다.
