# FirebaseOTP-Android

## Documentation

### Installation
---
Step 1. Add the JitPack repository to your build file 

```gradle
	allprojects {
		repositories {
			maven { url 'https://jitpack.io' }
		}
	}
```

Step 2. Add the dependency

```gradle
	dependencies {
	        implementation 'com.github.rommansabbir:FirebaseOTP-Android:Tag'
	}
```

---

### Version available

| Releases        
| ------------- |
| v1.1          |
| v1.0          |

# Usages

```kotlin
class MainActivity : AppCompatActivity() {
.....................................................
.....................................................
FirebaseOTP.verify(mAuth, phoneNumber, object : FirebaseOTPCallback {
			override fun onCodeSent() {
			//TODO Implement your logic here
			}

			override fun onVerificationFailed() {
			//TODO Implement your logic here
			}

			override fun onVerificationSuccess() {
			//TODO Implement your logic here
			}

		})
		
....................................................
....................................................

	//To verify the code
	FirebaseOTP.verifyOTP(code)

```

### Contact me
[Portfolio](https://www.rommansabbir.com/) | [LinkedIn](https://www.linkedin.com/in/rommansabbir/) | [Twitter](https://www.twitter.com/itzrommansabbir/) | [Facebook](https://www.facebook.com/itzrommansabbir/)

