//
//  NSData+AES256.m
//  ios
//
//  Created by Peter Zhan on 2017/10/16.
//  Copyright © 2017年 com.enuocms. All rights reserved.
//


#import "NSData+AES256.h"
#import "GTMBase64.h"

#define PASSWORD @"a30ed6b77fb54907"


static const char encodingTable[] = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";
const NSUInteger kAlgorithmKeySize = kCCKeySizeAES128;
const NSUInteger kPBKDFRounds = 10000;  // ~80ms on an iPhone 4

static Byte saltBuff[] = {0,1,2,3,4,5,6,7,8,9,0xA,0xB,0xC,0xD,0xE,0xF};

static Byte ivBuff[]  = {0xA,1,0xB,5,4,0xF,7,9,0x17,3,1,6,8,0xC,0xD,91};

@implementation NSData (AES256)

+ (NSData *)AESKeyForPassword:(NSString *)password{
    NSMutableData *derivedKey = [NSMutableData dataWithLength:kAlgorithmKeySize];
    NSData *salt = [NSData dataWithBytes:saltBuff length:kCCKeySizeAES128];
    int result = CCKeyDerivationPBKDF(kCCPBKDF2,        // algorithm算法
                                      password.UTF8String,  // password密码
                                      password.length,      // passwordLength密码的长度
                                      salt.bytes,           // salt内容
                                      salt.length,          // saltLen长度
                                      kCCPRFHmacAlgSHA1,    // PRF
                                      kPBKDFRounds,         // rounds循环次数
                                      derivedKey.mutableBytes, // derivedKey
                                      derivedKey.length);   // derivedKeyLen derive:出自
    
    NSAssert(result == kCCSuccess,
             @"Unable to create AES key for spassword: %d", result);
    return derivedKey;
}

/*加密方法*/
+ (NSString *)AES256EncryptWithPlainText:(NSString *)plain {
    NSData *plainText = [plain dataUsingEncoding:NSUTF8StringEncoding];
    char keyPtr[kAlgorithmKeySize+1]; // room for terminator (unused)
    bzero(keyPtr, sizeof(keyPtr)); // fill with zeroes (for padding)
    
    NSUInteger dataLength = [plainText length];
    
    size_t bufferSize = dataLength + kCCBlockSizeAES128;
    void *buffer = malloc(bufferSize);
    bzero(buffer, sizeof(buffer));
    
    size_t numBytesEncrypted = 0;
    
    CCCryptorStatus cryptStatus = CCCrypt(kCCEncrypt, kCCAlgorithmAES128,kCCOptionPKCS7Padding,
                                          [[NSData AESKeyForPassword:PASSWORD] bytes], kAlgorithmKeySize,
                                          ivBuff /* initialization vector (optional) */,
                                          [plainText bytes], dataLength, /* input */
                                          buffer, bufferSize, /* output */
                                          &numBytesEncrypted);
    if (cryptStatus == kCCSuccess) {
        NSData *encryptData = [NSData dataWithBytesNoCopy:buffer length:numBytesEncrypted];
        return [self AESBase64Encode:encryptData];
    }
    
    free(buffer); //free the buffer;
    return nil;
}

/*解密方法*/
+ (NSString *)AES256DecryptWithCiphertext:(NSString *)ciphertexts{
    NSData *cipherData =[self AESBase64Decode:ciphertexts];
    
    // 'key' should be 32 bytes for AES256, will be null-padded otherwise
    char keyPtr[kAlgorithmKeySize+1]; // room for terminator (unused)
    bzero(keyPtr, sizeof(keyPtr)); // fill with zeroes (for padding)
    
    NSUInteger dataLength = [cipherData length];
    
    size_t bufferSize = dataLength + kCCBlockSizeAES128;
    void *buffer = malloc(bufferSize);
    
    size_t numBytesDecrypted = 0;
    CCCryptorStatus cryptStatus = CCCrypt(kCCDecrypt, kCCAlgorithmAES128, kCCOptionPKCS7Padding,
                                          [[NSData AESKeyForPassword:PASSWORD] bytes], kAlgorithmKeySize,
                                          ivBuff ,/* initialization vector (optional) */
                                          [cipherData bytes], dataLength, /* input */
                                          buffer, bufferSize, /* output */
                                          &numBytesDecrypted);
    
    if (cryptStatus == kCCSuccess) {
        NSData *encryptData = [NSData dataWithBytesNoCopy:buffer length:numBytesDecrypted];
        return [[NSString alloc] initWithData:encryptData encoding:NSUTF8StringEncoding];
    }
    
    free(buffer); //free the buffer;
    return nil;
}

+ (NSString*)AESBase64Encode:(NSData*)data{
    return [GTMBase64 stringByWebSafeEncodingData:data padded:YES];
}

+ (NSData*)AESBase64Decode:(NSString*)base64String{
    return [GTMBase64 webSafeDecodeString:base64String];
}
@end


