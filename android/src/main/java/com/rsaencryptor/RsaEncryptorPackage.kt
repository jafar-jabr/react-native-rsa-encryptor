package com.rsaencryptor

import com.facebook.react.BaseReactPackage
import com.facebook.react.bridge.NativeModule
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.module.model.ReactModuleInfo
import com.facebook.react.module.model.ReactModuleInfoProvider
import java.util.HashMap

class RsaEncryptorPackage : BaseReactPackage() {
  override fun getModule(name: String, reactContext: ReactApplicationContext): NativeModule? {
    return if (name == RsaEncryptorModule.NAME) {
      RsaEncryptorModule(reactContext)
    } else {
      null
    }
  }

  override fun getReactModuleInfoProvider(): ReactModuleInfoProvider {
    return ReactModuleInfoProvider {
      val moduleInfos: MutableMap<String, ReactModuleInfo> = HashMap()
      moduleInfos[RsaEncryptorModule.NAME] = ReactModuleInfo(
        RsaEncryptorModule.NAME,
        RsaEncryptorModule.NAME,
        false,  // canOverrideExistingModule
        false,  // needsEagerInit
        true,  // hasConstants
        false,  // isCxxModule
        true // isTurboModule
      )
      moduleInfos
    }
  }
}
