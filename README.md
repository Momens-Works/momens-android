

#  Momens 서비스 소개
> 흩어진 프로젝트 맥락을 하나의 메모리로 연결하고, 팀원이 오늘의 할 일·결정·리스크를 빠르게 확인하고 업데이트할 수 있는 프로젝트 실행 인터페이스


<br/>

## Momens 주요 기능

- 프로젝트 Signal 기반 중요 이슈 확인

- Signal 근거 요약 및 원천 도구 연결

- 민수 AI의 리스크·결정·질문 요약 및 액션 제안

- Signal 기반 공유, 태스크 전환, 결정 기록, 해결 처리

<br/>

## Tech Stack

| Category                | Stack                                             |
| ----------------------- | ------------------------------------------------- |
| **Architecture**        | Google Recommended App Architecture               |
| **UI**                  | Jetpack Compose                                   |
| **DI**                  | Dagger-Hilt                                       |
| **Asynchronous**        | Kotlin Coroutine, Flow                            |
| **Project Structure**   | Single Module, Package-based Structure            |
| **Build Configuration** | Gradle Version Catalog, Custom Convention Plugins |

<br/>

###  Tech Stack 소개 ###

**1️⃣ Architecture: Google Recommended App Architecture** <br/>
Google Recommended App Architecture는 UI, 상태, 비즈니스 로직, 데이터 계층의 책임을 명확히 분리하기 위해 도입했습니다.  
각 계층의 역할을 분리하면 특정 화면이나 기능이 변경되더라도 영향 범위를 줄일 수 있고, 기능이 확장되었을 때도 유지보수하기 쉬운 구조를 만들 수 있다고 판단했습니다.  
또한 ViewModel, Flow, Compose 등 Android Jetpack 구성 요소와 자연스럽게 연동되기 때문에 공식 가이드와의 일관성을 유지할 수 있어 선택했습니다.

**2️⃣ Pattern: MVVM** <br/>
MVVM은 UI와 비즈니스 로직을 분리하고, ViewModel을 중심으로 화면 상태를 관리하기 위해 도입했습니다.  
MVI는 상태 흐름을 명확하게 관리할 수 있다는 장점이 있지만, 초기 프로젝트 단계에서는 러닝커브가 높고 구조가 다소 복잡해질 수 있다고 판단했습니다.  
Momens에서는 MVVM을 기반으로 View는 UI 표현에 집중하고, ViewModel은 상태 관리와 이벤트 처리를 담당하도록 구성했습니다.  
이를 통해 Compose 화면에서 상태를 관찰하고, 상태 변화에 따라 UI가 자연스럽게 갱신되는 구조를 만들고자 했습니다.

**3️⃣ Dependency Injection: Hilt** <br/>
Hilt는 의존성 주입을 통해 ViewModel, Repository, DataSource 등의 객체 생성을 일관되게 관리하기 위해 도입했습니다.  
객체를 직접 생성하는 방식을 줄이면 코드 간 결합도를 낮출 수 있고, 테스트나 기능 확장 시 필요한 의존성을 더 쉽게 교체할 수 있습니다.  
Hilt는 Google에서 공식 지원하는 DI 라이브러리이기 때문에 Android Jetpack과의 연동이 자연스럽고, 반복적인 보일러플레이트 코드를 줄일 수 있어 선택했습니다.

**4️⃣ Navigation: Type-Safety Navigation**<br/>
기존 문자열 기반 Navigation은 route를 잘못 작성해도 컴파일 단계에서 오류를 확인하기 어렵고, 런타임 오류로 이어질 수 있습니다.  
이를 보완하기 위해 타입 안정성을 지원하는 Type-safe Navigation을 도입했습니다.  
화면 이동 시 필요한 인자와 route를 타입 기반으로 관리함으로써 네비게이션 관련 실수를 줄이고, 화면 구조가 늘어나도 더 안정적으로 관리할 수 있다고 판단했습니다.

<br/>


## **Convention**

🪾 [Git & Branch Convention](https://app.notion.com/p/564ac442a85782e1bc1d0157a2e3695a?source=copy_link)<br/>
✍️ [Code Convention](https://app.notion.com/p/844ac442a8578304946101d3459701e5?source=copy_link)<br/>
📂 [Packaging Convention](https://app.notion.com/p/ffeac442a8578227b59d810fa687e8a5?source=copy_link)<br/>
🔐 [Debug Keystore Setup](docs/debug-keystore.md)<br/>

<br/>

## Contributors

| 🤴남궁혜민<br/>[@hyeminililo](https://github.com/hyeminililo) | 🖌️ 최승재<br/>[@seungjae708](https://github.com/seungjae708) | 🖌️ 안태훈<br/>[@taehoon-An](https://github.com/taehoon-An) | 🖌️ 윤갑유<br/>[@gahbyu](https://github.com/gahbyu) | 🖌️ 최은지<br/>[@chldmswll](https://github.com/chldmswll) |
| --------------------------------------------------------- | ---------------------------------------------------------- | -------------------------------------------------------- | ------------------------------------------------ | ------------------------------------------------------ |
| <img width="88" height="88" alt="image" src="https://github.com/user-attachments/assets/7ece411b-8b80-4373-aaf2-f9988088bc6f" />|<img width="88" height="88" alt="image" src="https://github.com/user-attachments/assets/5f9812b4-eb55-4ad7-8161-aa26e1c3c67c" />| <img width="88" height="88" alt="image" src="https://github.com/user-attachments/assets/7e6fb4e2-5a0a-497f-8150-99171a47301b" />|<img width="88" height="88" alt="image" src="https://github.com/user-attachments/assets/a4e10902-7b7f-449c-975d-6fc1c93ed511" />|<img width="88" height="88" alt="image" src="https://github.com/user-attachments/assets/0a3df16d-b6df-4f21-b229-593816f34e6f" />|
| `홈`                                                       | `태스크 작성`                                                   | `로그인` `온보딩`                                              | `프로젝트 상세`                                        | `브리프`                                                  |


<br/>

## **Foldering**
```
📂 momens
┣ 📂 core
┃ ┣ 📂 common
┃ ┣ 📂 designsystem
┃ ┣ 📂 local
┃ ┣ 📂 network
┃ ┣ 📂 util
┣ 📂 data
┃ ┣ 📂 di
┃ ┣ 📂 local
┃ ┣ 📂 model
┃ ┣ 📂 remote
┃ ┣ 📂 repository
┃ ┣ 📂 repositoryimpl  
┣ 📂 presentation  
┃ ┣ 📂 brief    
┃ ┣ 📂 home 
┃ ┣ 📂 main
┃ ┣ 📂 project 
┃ ┣ 📂 signal  
┃ ┣ 📂 signin 
┃ ┣ 📂 splash

```
  
---  

<p align="center">  
  Made by Momens
</p>
