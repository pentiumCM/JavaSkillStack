package indi.pentiumcm.hls;

import sun.misc.BASE64Decoder;

import javax.crypto.Cipher;
import javax.xml.bind.DatatypeConverter;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPublicKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * @projName: Fengyun2
 * @packgeName: PACKAGE_NAME
 * @className: demo
 * @author： dengteng
 * @date: 2021/3/10 13:41
 * @describe:
 */
public class demo {

    public static String encrypt(PublicKey pk, String param) throws Exception {
        try {
            byte[] data = param.getBytes(StandardCharsets.UTF_8);
            Cipher cipher = Cipher.getInstance("RSA", new org.bouncycastle.jce.provider.BouncyCastleProvider());
            cipher.init(Cipher.ENCRYPT_MODE, pk);
            int blockSize = cipher.getBlockSize();// 获得加密块大小，如：加密前数据为128个byte，而key_size=1024
            // 加密块大小为127byte,加密后为128个byte;因此共有2个加密块，第一个127byte第二个为1个byte
            int outputSize = cipher.getOutputSize(data.length);// 获得加密块加密后块大小
            int leavedSize = data.length % blockSize;
            int blocksSize = leavedSize != 0 ? data.length / blockSize + 1
                    : data.length / blockSize;
            byte[] raw = new byte[outputSize * blocksSize];
            int i = 0;
            while (data.length - i * blockSize > 0) {
                if (data.length - i * blockSize > blockSize) {
                    cipher.doFinal(data, i * blockSize, blockSize, raw, i * outputSize);
                } else {
                    cipher.doFinal(data, i * blockSize, data.length - i * blockSize, raw, i * outputSize);
                }
                i++;
            }
            return DatatypeConverter.printHexBinary(raw);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public static RSAPublicKey generateRSAPublicKey(String modulus, String publicExponent) throws Exception {
        KeyFactory keyFac = null;
        try {
            keyFac = KeyFactory.getInstance("RSA",
                    new org.bouncycastle.jce.provider.BouncyCastleProvider());
        } catch (NoSuchAlgorithmException ex) {
            throw new Exception(ex.getMessage());
        }
        RSAPublicKeySpec pubKeySpec = new RSAPublicKeySpec(new BigInteger(
                modulus), new BigInteger(publicExponent));
        try {
            return (RSAPublicKey) keyFac.generatePublic(pubKeySpec);
        } catch (InvalidKeySpecException ex) {
            throw new Exception(ex.getMessage());
        }
    }

    public static void main(String[] args) {
        String modulus = "98120034845286155668365118662355154044554882628870578516850764607769514752911306067588574849398095266355883702849972401450295197194862380691656040146005099376100717541056754990410741826997398610752193563114826139636147126483707158928198673609468756370054734961503280181592476186152942732225661142234636409743";
        String publicExponent = "65537";
        String param = "083149";
        try {
            RSAPublicKey rsaPublicKey = generateRSAPublicKey(modulus, publicExponent);
            String encrypt = encrypt(rsaPublicKey, param);

            System.out.println(encrypt);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
