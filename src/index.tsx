import RsaEncryptor from './NativeRsaEncryptor';

export function encryptToken(token: string, Bkey: string): string {
  return RsaEncryptor.encryptToken(token, Bkey);
}
