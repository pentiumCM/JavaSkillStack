package indi.pentiumcm.vms;

import org.apache.commons.codec.digest.DigestUtils;

import java.io.UnsupportedEncodingException;

/**
 * @projName: JavaSkillStack
 * @packgeName: indi.pentiumcm.vms
 * @className: SignatureUtil
 * @author： pentiumCM
 * @email： 842679178@qq.com
 * @date: 2021/1/22 16:40
 * @describe: 加密字校验权限算法
 */
public class SignatureUtil {
    /**
     * 获取签名字符串
     *
     * @param uuid       客户唯一标识
     * @param appKey     应用key
     * @param appSecret  应用密钥
     * @param timeMillis 时间戳
     * @param movedCard  移动取模基数
     * @return
     * @throws Exception
     */
    public static String getEncryptStr(String uuid, String appKey, String appSecret, String timeMillis, int movedCard) throws Exception {
        String encryptStr = uuid + appKey + appSecret + timeMillis;
        byte[] encryptByte = encryptStr.getBytes("iso-8859-1");
        byte[] changeByte = change(encryptStr, movedCard);
        byte[] mergeByte = mergeByte(encryptByte, changeByte);
        return DigestUtils.md5Hex(mergeByte);
    }

    /**
     * 简单移位
     */
    private static byte[] change(String encryptStr, int moveCard) throws UnsupportedEncodingException {
        byte[] encryptByte = encryptStr.getBytes("iso-8859-1");
        int encryptLength = encryptByte.length;
        byte temp;
        for (int i = 0; i < encryptLength; i++) {
            temp = ((i % moveCard) > ((encryptLength - i) % moveCard)) ? encryptByte[i] : encryptByte[encryptLength - (i + 1)];
            encryptByte[i] = encryptByte[encryptLength - (i + 1)];
            encryptByte[encryptLength - (i + 1)] = temp;
        }
        return encryptByte;
    }

    /**
     * 合并
     *
     * @param encryptByte
     * @param changeByte
     * @return
     */
    private static byte[] mergeByte(byte[] encryptByte, byte[] changeByte) {
        int encryptLength = encryptByte.length;
        int encryptLength2 = encryptLength * 2;
        byte[] temp = new byte[encryptLength2];
        for (int i = 0; i < encryptByte.length; i++) {
            temp[i] = encryptByte[i];
            temp[encryptLength2 - 1 - i] = changeByte[i];
        }
        return temp;
    }
}
