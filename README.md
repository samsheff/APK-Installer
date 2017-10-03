# APK Installer
A simple Android app that gets an APK from a URL and installs it in an Android Simulator or Device

# How to
- Clone The repo
- Open the project in Android Studio
- Open APKConfig class and edit the APK_URL variable to point to your APK
- Build and Run
- Open app and push the button

## Notes
This only works on Android devices with an SDK level of 22 or less. I have not yet added support for file providers, which is required for storage on devices with a higher SDK level. If this matters to you and you add support for it, Pull Requests are welcome!
