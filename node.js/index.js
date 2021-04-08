const crypto = require("crypto");

const PK = `-----BEGIN PRIVATE KEY-----
MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQC7uNyidq0s3HcX
HWTdnpUmE7zp8jWvPkZ9aVewp6Be9gvwVElyrtpxWjQdRPYU/s2jQdsBMlHCkODv
2C8Cn3K29fvIg3g2dfaEo6/lv2qt+1Ox/mbRufM/kE9kAiuRYso0rcsC2ywqz63E
AQo2tli7+35w2r3inOtuMuIaJcKGme4ctvAT3T5wk702M9WSzuJDl2xl32kTIV6p
ScfEWCSBd3kWQJ8jt1jj9+MwJxlFqMGiCORAS8/4j2iMudsrzhAbkzfA128IPWr3
6xIUEH1m5cKLZ6WXmUuTDLW7m0deVHuiZHu0J3hBrqhisXv2IUTyEkXwniLQGLHK
o0eOVeIHAgMBAAECggEAOVj3JjalHuBXDVuwdbe9jr1W6A1Tcy7OL//fhxsedgQq
j4fgTEW4fAp3gz2PA8j3n1k+A3aQ3aSO4xai/eUiEVzCR3ZKYjDVV5393W0FtRLj
davnU0jAU/nNFxtGngqVBVTWgQ/dxVJenlNHAIHiR8ND1Ewu2CziC29F/YDtlVqp
FgenqZO+1GXtqR+MF/NxIa7DUFiMLTjcjZH4l/8HG4G2pGxmruLbzKprW3FMBzS4
cQly7LTO1zi401m0JpmZlNwkZlsoWhMOwUc7bs8aaWmsust+SINpqXyLy8JcH3VI
mtgPQzxYaYEzVuloyvOUhLQRMWA/Goh8TuU5DaavgQKBgQDgbcvmPlhY3a+FMFX1
l0Zq29L14sAB5A+8NJpVDDjlNAZL76pE+FLCwUNILqnNFzkuvvcMEZsbJKXLlmSt
kzbyYVL6xohN9LUzp1I6zD7OimWawLG9grEhvuktp4tT62sqImv4jVDlPJzvRGz2
kMW/hCbY8MDOezCYRJOBeq2IRwKBgQDWIStiZ1hENJSvg0ZWpfvqVFiR4wlaMhYF
9JUusfAtttGL//IbeSdoH23ogctahYC5nuFbt0RfGzDFEqKrzvZFgC2HMozUAja+
ARpiqp00Qc66JBX74A0TdBVY/opfAwlVy2ctwlp/dVCSNQ5525pceGjwq4GEA/+a
e/QUI/N4QQKBgF3hsDTdiamK+rx1bs/wexWCZWpUc0uXMnnha1n+ZeKDc8raM9js
I/qyS8nrMz7G4zXJzqBu0Pp2xyA2BmGRvp9/1O1wlV4+6nn0GXLdM7zEqiIlT3Te
MejS6sodVyxAw7B5e78apmduhpuUfRxbjU1DI9kGRTFa2QYpUacNQYBLAoGBAKkL
Qo7K+1nNENyGjE73dqsFr25siAOWquBEuElG3E+alk8p3d5mhP+kYEY/wWRTJM5l
aY/YXLegZZ4PGjD8kFtJ16d19suge8sAX+4otzZ8BVHaDyjrNid3ayr4uBBN+16p
12i+mGcrHFJwZF87SN9bh60IGJU5+t3goU4NG7aBAoGAYyqYD9gx3g9QL4I03gbw
EYqXX1U+u+exN+PBSoIiIynapQ3yYPRyRHT7/vt/vUKsGYQONlwA8xrjqz4SStT8
lk/6o+zk7csZv4gt8dB20ZzMckCx5Ve9Hq7pMsav4Usbl9RCGgFHAZyGM4pLx4UB
K/VjsfzGkRZErocpNwb8NCc=
-----END PRIVATE KEY-----`;

function sign(data){
    var sign = crypto.createSign('RSA-SHA256');
    sign.update(data);
    sig = sign.sign(PK, 'base64');
    return sig;
}

let data = '{"success":true,"message":"msg test"}';

let signatureStr = sign(data);
console.log(signatureStr);