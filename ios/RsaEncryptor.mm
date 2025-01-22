#import "RsaEncryptor.h"
#import <Foundation/Foundation.h>
#import <Security/Security.h>

@implementation RsaEncryptor
RCT_EXPORT_MODULE()

- (NSString *)encryptToken:(NSString *)token
                      Bkey:(NSString *)Bkey{

  // Remove the header, footer, and newlines from the public key
      NSString *cleanedKey = [[Bkey
          stringByReplacingOccurrencesOfString:@"-----BEGIN PUBLIC KEY-----" withString:@""]
          stringByReplacingOccurrencesOfString:@"-----END PUBLIC KEY-----" withString:@""];
      cleanedKey = [cleanedKey stringByReplacingOccurrencesOfString:@"\n" withString:@""];

      // Decode the Base64-encoded key
      NSData *keyData = [[NSData alloc] initWithBase64EncodedString:cleanedKey options:0];
      if (!keyData) {
          NSLog(@"Invalid public key");
          return nil;
      }

      // Create a SecKeyRef from the public key data
      NSDictionary *keyAttributes = @{
          (__bridge id)kSecAttrKeyType: (__bridge id)kSecAttrKeyTypeRSA,
          (__bridge id)kSecAttrKeyClass: (__bridge id)kSecAttrKeyClassPublic,
      };
      SecKeyRef publicKey = SecKeyCreateWithData((__bridge CFDataRef)keyData,
                                                 (__bridge CFDictionaryRef)keyAttributes,
                                                 nil);
      if (!publicKey) {
          NSLog(@"Failed to create public key");
          return nil;
      }

      // Convert the token to NSData
      NSData *tokenData = [token dataUsingEncoding:NSUTF8StringEncoding];

      // Encrypt the token using the public key
      CFErrorRef error = NULL;
      NSData *encryptedData = (__bridge_transfer NSData *)SecKeyCreateEncryptedData(
          publicKey,
          kSecKeyAlgorithmRSAEncryptionPKCS1,
          (__bridge CFDataRef)tokenData,
          &error);

      CFRelease(publicKey);

      if (!encryptedData) {
          NSLog(@"Encryption failed: %@", (__bridge NSError *)error);
          if (error) CFRelease(error);
          return nil;
      }
      return [encryptedData base64EncodedStringWithOptions:0];
}

- (std::shared_ptr<facebook::react::TurboModule>)getTurboModule:
    (const facebook::react::ObjCTurboModule::InitParams &)params
{
    return std::make_shared<facebook::react::NativeRsaEncryptorSpecJSI>(params);
}

@end
