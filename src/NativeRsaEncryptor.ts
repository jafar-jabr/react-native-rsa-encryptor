import type { TurboModule } from 'react-native';
import { TurboModuleRegistry } from 'react-native';

export interface Spec extends TurboModule {
  encryptToken(token: string, Bkey: string): string;
}

export default TurboModuleRegistry.getEnforcing<Spec>('RsaEncryptor');
