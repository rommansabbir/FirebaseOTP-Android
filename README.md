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
| v1.0          |


# Usages

```kotlin
class MainActivity : AppCompatActivity(), FirebaseOTPInterface {
    private var auth: FirebaseAuth? = null
    private var firebaseOTP: FirebaseOTP? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        //Instantiate FirebaseAuth
        auth = FirebaseAuth.getInstance()

        //Instantiate FirebaseOTPCallback
        firebaseOTP = FirebaseOTP(this@MainActivity, auth, "PHONE_NUMBER_HERE")

        //Call verifyOTP() to verify your code
        firebaseOTP!!.verifyOTP("RECEIVED_CODE")
        }
    }


    override fun onVerificationSuccess(msg: String) {
        //TODO implement your login here
    }

    override fun onVerificationFailed(msg: String) {
        //TODO implement your login here
    }

    override fun onCodeSent(msg: String) {
        //TODO implement your logic here
    }

    override fun onDestroy() {
        super.onDestroy()
        /*
        Call selfDestroy() after verification done
         */
        firebaseOTP!!.selfDestroy()
    }
}

```

### Contact me
[Portfolio](https://www.rommansabbir.com/) | [LinkedIn](https://www.linkedin.com/in/rommansabbir/) | [Twitter](https://www.twitter.com/itzrommansabbir/) | [Facebook](https://www.facebook.com/itzrommansabbir/)

