# SmsToWebhook

SmsToWebhook은 SMS 메시지를 Webhook으로 전송하는 간단한 Android 애플리케이션입니다. 이 프로젝트는 Android 디바이스에서 수신된 SMS를 특정 Webhook URL로 전달하는 기능을 제공합니다.

## 주요 기능
- SMS 메시지 수신 및 Webhook으로 전송
- 전화번호 필터링 설정 가능
- Webhook URL 설정 가능

## 설치 방법
1. 저장소를 클론합니다:
   ```bash
   git clone https://github.com/username/SmsToWebhook.git
   ```

2. Android Studio에서 프로젝트를 엽니다.

3. `local.properties` 파일에 sdk 경로 및 Webhook URL을 설정합니다:
   ```
   sdk.dir=/Users/[ID]/Library/Android/sdk
   ```

4. 필요한 의존성을 설치합니다.

5. 애플리케이션을 실행합니다.

## 사용 방법
1. 애플리케이션을 실행합니다.
2. 설정창에서 전송할 Webhook 주소와 전화번호를 설정합니다.
3. SMS 메시지를 수신하면 자동으로 설정된 Webhook으로 전송됩니다.

## 주의 사항
- SMS 메시지를 수신하려면 `RECEIVE_SMS` 권한이 필요합니다.
- 갤럭시에서 `채팅+` 를 사용중일 경우 제대로 동작하지 않을 수 있습니다.