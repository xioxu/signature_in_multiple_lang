# What's it
Demostration how to do signature with private key and verify it with public key with different programming langurate.
Supports now:
+ Java
+ Golang
+ Node.js

# Generate private key(PKCS8 format)

```ssh
openssl genpkey -out rsakey.pem -algorithm RSA -pkeyopt rsa_keygen_bits:2048
```

# Generate the public key

```ssh
openssl rsa -in rsakey.pem -pubout -out rsa_public_key.pem
```

# Tip
## For Java
1. You must remove the first("-----BEGIN...") and last line("----END...") in the key content.
2. You must remove "\n" in key content