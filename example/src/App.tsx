import { Text, View, StyleSheet } from 'react-native';
import { encryptToken } from 'react-native-rsa-encryptor';

const key = '-----BEGIN PUBLIC KEY-----\n' + 'key' + '-----END PUBLIC KEY-----';

const token = 'hello';
const result = encryptToken(token, key);

export default function App() {
  return (
    <View style={styles.container}>
      <Text>Result: {result}</Text>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    alignItems: 'center',
    justifyContent: 'center',
  },
});
