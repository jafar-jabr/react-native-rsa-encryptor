# react-native-rsa-encryptor

rsa-encryptor

## Installation

```sh
npm install react-native-rsa-encryptor
```

## Usage


```js
import { encryptToken } from 'react-native-rsa-encryptor';

// ...

const key =
  '-----BEGIN PUBLIC KEY-----\n' +
  'key' +
  '-----END PUBLIC KEY-----';

const txt = 'any text';
const token = encryptToken(txt, key);
```


## Contributing

See the [contributing guide](CONTRIBUTING.md) to learn how to contribute to the repository and the development workflow.

## License

MIT

---

Made with [create-react-native-library](https://github.com/callstack/react-native-builder-bob)
