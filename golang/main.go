package main

import (
	"crypto"
	"crypto/rsa"
	"crypto/sha256"
	"crypto/x509"
	"encoding/base64"
	"encoding/pem"
	"errors"
	"fmt"
)

func main(){
	pubKey := `-----BEGIN PUBLIC KEY-----
MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAu7jconatLNx3Fx1k3Z6V
JhO86fI1rz5GfWlXsKegXvYL8FRJcq7acVo0HUT2FP7No0HbATJRwpDg79gvAp9y
tvX7yIN4NnX2hKOv5b9qrftTsf5m0bnzP5BPZAIrkWLKNK3LAtssKs+txAEKNrZY
u/t+cNq94pzrbjLiGiXChpnuHLbwE90+cJO9NjPVks7iQ5dsZd9pEyFeqUnHxFgk
gXd5FkCfI7dY4/fjMCcZRajBogjkQEvP+I9ojLnbK84QG5M3wNdvCD1q9+sSFBB9
ZuXCi2ell5lLkwy1u5tHXlR7omR7tCd4Qa6oYrF79iFE8hJF8J4i0BixyqNHjlXi
BwIDAQAB
-----END PUBLIC KEY-----`

	sign := "ClJFo5Kua3gTvu7BQRjcBMcsfkkCcz9Hug3JZxymCuauvrHAFPjWRVv8WdsoFnrTOVeJby7A2HBntvOEdTwdXM/HWDCQzi+BI4fpPpv4v384JUgFAyJEK++JNWg9oi0rRQFWEeoQtWZXY9QTzCyGjaE6fBDTa2mhZyboiUW8WW4rjb3pJOsmv/zF+5Jfwd5pRylZGOIIsbx/jkxOZcjhhp9QIr0BYz9ACp2AtwZSC3xqbUGneQPUjWaDFpWu6GrRIQM2WPJNJMW24iAizOaEJ1sSiL9abDDa2v+lJTetW1TNJobgZRTCdDfH56mQBYClzAqJsqXYKLiKU+RssK/IGQ=="

	data := "{\"success\":true,\"message\":\"msg test\"}"
	err := CheckSignature(sign,[]byte(pubKey),[]byte(data))

	if err != nil{
		fmt.Println("Failed")
		fmt.Print(err)
	}else{
		fmt.Println("Success")
	}

}


func CheckSignature(rawSign string, pubPem []byte, data []byte) error {
	signBytes,_ := base64.StdEncoding.DecodeString(rawSign)
	hashed := sha256.Sum256(data)

	block, _ := pem.Decode(pubPem)
	if block == nil {
		return errors.New("Failed to decode public PEM")
	}
	pub, err := x509.ParsePKIXPublicKey(block.Bytes)
	if err != nil {
		return err
	}

	pKey :=   pub.(*rsa.PublicKey)
	err = rsa.VerifyPKCS1v15(pKey, crypto.SHA256, hashed[:], signBytes)
	return err
}



