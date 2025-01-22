package com.rsaencryptor

import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.module.annotations.ReactModule

import java.security.PublicKey
import android.util.Base64
import java.security.KeyFactory
import java.security.spec.X509EncodedKeySpec
import javax.crypto.Cipher

@ReactModule(name = RsaEncryptorModule.NAME)
class RsaEncryptorModule(reactContext: ReactApplicationContext) :
  NativeRsaEncryptorSpec(reactContext) {

  override fun getName(): String {
    return NAME
  }

  private fun loadPublicKey(publicKeyString: String): PublicKey {
      val publicKeyPem = publicKeyString
        .replace("-----BEGIN PUBLIC KEY-----", "")
        .replace("-----END PUBLIC KEY-----", "")
        .replace("\n", "")

      val publicKeyBytes = Base64.decode(publicKeyPem, Base64.DEFAULT)
      val keySpec = X509EncodedKeySpec(publicKeyBytes)
      val keyFactory = KeyFactory.getInstance("RSA")
      return keyFactory.generatePublic(keySpec)
    }

    override fun encryptToken(token: String, Bkey: String): String {
      try {
        val publicKey = loadPublicKey(Bkey)
        val cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding")
        cipher.init(Cipher.ENCRYPT_MODE, publicKey)
        val encryptedBytes = cipher.doFinal(token.toByteArray())
        val encryptedToken = Base64.encodeToString(encryptedBytes, Base64.DEFAULT)
        return encryptedToken
      } catch (e: Exception) {
        return ""
      }
    }

  companion object {
    const val NAME = "RsaEncryptor"
  }
}
