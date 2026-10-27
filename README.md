# FinVest
FinVest is an Android banking and investment app. It covers onboarding, account login, and a home dashboard with portfolio totals plus live NIFTY and SENSEX values.
## Features
- Splash screen as the app entry point
- Onboarding walkthrough (ViewPager2)
- Register, login, forgot password, OTP, and reset password screens
- Home dashboard with portfolio value, invested amount, and P&amp;L
- Watchlist list
- Live NIFTY and SENSEX quotes from a backend stock API (Retrofit)
## Screens
| Screen | Activity |
| --- | --- |
| Splash | `SplashActivity` |
| Onboarding | `OnboardingActivity` |
| Login | `LoginActivity` |
| Register | `RegisterActivity` |
| Forgot password | `ForgotPasswordActivity` |
| OTP | `OtpActivity`, `OtpVerificationActivity` |
| Reset password | `ResetPasswordActivity` |
| Home / market | `HomeActivity` |
Bottom navigation labels: **Home**, **Market**, **Portfolio**, **Profile**.
## Tech stack
- Java 11
- Android Gradle Plugin 9.0.1
- Minimum SDK 24, target SDK 36
- AndroidX AppCompat, Material, ConstraintLayout, RecyclerView, ViewPager2
- Retrofit, Gson, OkHttp for HTTP
Package name: `com.example.finvest`
## Prerequisites
- Android Studio (latest stable recommended)
- JDK 11 or newer
- An Android emulator (API 24+) or a physical device with USB debugging
## How to run
1. Clone this repository (or open the folder you already have).
2. In Android Studio: **File → Open** and select the `FinVest` folder.
3. Wait for Gradle sync to finish.
4. Choose an emulator or device, then click **Run**.
The first launch starts at `SplashActivity`.
## Project structure
```
FinVest/
├── app/
│   ├── build.gradle.kts
│   └── src/main/
│       ├── AndroidManifest.xml
│       ├── java/com/example/finvest/   # Activities, models, API
│       └── res/                        # Layouts, drawables, strings
├── gradle/
├── build.gradle.kts
└── settings.gradle.kts
```
Stock API base URL is set in `app/src/main/java/com/example/finvest/ApiClient.java`.  
Live quotes are requested as `GET stock/{symbol}` (for example `NIFTY` and `SENSEX`).
Portfolio numbers on the home screen currently use sample local data in `HomeActivity`. Google Sign-In on the login screen is a placeholder.
## Build
From the project root:
```bash
./gradlew assembleDebug
```
On Windows PowerShell:
```powershell
.\gradlew.bat assembleDebug
```
The debug APK is written to `app/build/outputs/apk/debug/`.
## License
This project is for learning and demonstration. Add a license file if you want to publish it under a specific license.
