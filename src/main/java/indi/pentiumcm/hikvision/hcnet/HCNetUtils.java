package indi.pentiumcm.hikvision.hcnet;

import com.sun.jna.NativeLong;
import com.sun.jna.Pointer;
import com.sun.jna.ptr.IntByReference;
import lombok.extern.slf4j.Slf4j;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * @Description: 海康SDK工具类
 */
@Slf4j
public class HCNetUtils {

    private static HCNetSDK hcNetSDK = HCNetSDK.getInstance();

    /**
     * 设备序列号
     */
    public static String serialNumber;

    /**
     * 设备序列号
     */
    public static Long user;

    /**
     * @Description: HCNetSDK初始化
     */
    public static HCNetSDK getHCNetSDK() {
        hcNetSDK.NET_DVR_Init();
        return hcNetSDK;
    }

    /**
     * @param username 用户名
     * @param password 密码
     * @param ip       地址
     * @param port     端口
     * @return Long 用户id
     * @Description: 初始化登陆
     */
    public static Long login(HCNetSDK hcNetSDK, String username, String password, String ip, short port) {
        HCNetSDK.NET_DVR_DEVICEINFO_V30 deviceInfo = new HCNetSDK.NET_DVR_DEVICEINFO_V30();
        user = hcNetSDK.NET_DVR_Login_V30(ip, port, username, password, deviceInfo);
        try {
            // 获取设备序列号
            serialNumber = new String(deviceInfo.sSerialNumber, "GBK").trim();
        } catch (UnsupportedEncodingException e) {
            log.error("布防,设备序列号获取失败UnsupportedEncodingException:{}", e.getMessage());
        }
        if (user.intValue() == 0 || user.intValue() == 1) {
            log.info("设备登陆注册成功userid:{}", user);
            IntByReference ibrBytesReturned = new IntByReference(0);// 获取IP接入配置参数
            HCNetSDK.NET_DVR_IPPARACFG ipConfig = new HCNetSDK.NET_DVR_IPPARACFG();
            ipConfig.write();
            Pointer lpIpParaConfig = ipConfig.getPointer();
            hcNetSDK.NET_DVR_GetDVRConfig(user, HCNetSDK.NET_DVR_GET_IPPARACFG, new NativeLong(0), lpIpParaConfig,
                    ipConfig.size(), ibrBytesReturned);
            ipConfig.read();
            return user;
        } else {
            log.info("设备登陆注册失败 NET_DVR_Login_V30 errCode:{}", hcNetSDK.NET_DVR_GetLastError() + ",userId:" + user);
            return null;
        }
    }

    /**
     * @param user
     * @Description: 退出登陆
     */
    public void logout(Long user) {
        if (user.longValue() > -1) {
            hcNetSDK.NET_DVR_Logout_V30(user);
        }
        hcNetSDK.NET_DVR_Cleanup();
    }

    /**
     * @param startTime
     * @param endTime
     * @param filePath
     * @param channel
     * @Description 按时间下载视频
     */
    public static boolean downloadVideo(Long userId, Date startTime, Date endTime, String filePath, Integer channel) {
        log.info("下载视频参数startTime:[{}],endTime:[{}],filePath:[{}],channel:[{}]", startTime, endTime, filePath, channel);
        NativeLong loadHandle = new NativeLong(-1);
        if (loadHandle.intValue() == -1) {
            loadHandle = hcNetSDK.NET_DVR_GetFileByTime(userId, new NativeLong(channel), getHkTime(startTime), getHkTime(endTime), filePath);
            log.info("hksdk(视频)-获取播放句柄信息,状态值:" + hcNetSDK.NET_DVR_GetLastError());
            if (loadHandle.intValue() >= 0) {
                boolean downloadFlag = hcNetSDK.NET_DVR_PlayBackControl(loadHandle, HCNetSDK.NET_DVR_PLAYSTART, 0,
                        null);
                int tmp = -1;
                IntByReference pos = new IntByReference();
                while (true) {
                    boolean backFlag = hcNetSDK.NET_DVR_PlayBackControl(loadHandle, HCNetSDK.NET_DVR_PLAYGETPOS, 0,
                            pos);
                    if (!backFlag) {// 防止单个线程死循环
                        return downloadFlag;
                    }
                    int produce = pos.getValue();
                    if ((produce % 10) == 0 && tmp != produce) {// 输出进度
                        tmp = produce;
                        log.info("hksdk(视频)-视频下载进度:" + "==" + produce + "%");
                    }
                    if (produce == 100) {// 下载成功//回访
                        hcNetSDK.NET_DVR_StopPlayBack(loadHandle);
                        return true;
                    }
                    if (produce > 100) {// 下载失败
                        hcNetSDK.NET_DVR_StopGetFile(loadHandle);
                        log.warn("hksdk(视频)-海康sdk由于网络原因或DVR忙,下载异常终止!错误原因:" + hcNetSDK.NET_DVR_GetLastError());
                        return false;
                    }
                }
            } else {
                log.warn("hksdk(视频)-下载失败" + hcNetSDK.NET_DVR_GetLastError());
                return false;
            }
        }
        return false;
    }

    /**
     * @param time
     * @Description: 获取海康录像机格式的时间
     * @author 纪淮永
     */
    private static HCNetSDK.NET_DVR_TIME getHkTime(Date time) {
        HCNetSDK.NET_DVR_TIME structTime = new HCNetSDK.NET_DVR_TIME();
        String str = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(time);
        String[] times = str.split("-");
        structTime.dwYear = Integer.parseInt(times[0]);
        structTime.dwMonth = Integer.parseInt(times[1]);
        structTime.dwDay = Integer.parseInt(times[2]);
        structTime.dwHour = Integer.parseInt(times[3]);
        structTime.dwMinute = Integer.parseInt(times[4]);
        structTime.dwSecond = Integer.parseInt(times[5]);
        return structTime;
    }

    /**
     * @param devicePosition 车头:head;车尾:tail
     * @param speedStatus    零速:false;有速:true
     * @Description: 根据列车状态设置DVR告警是否开启
     * @author 纪淮永
     */
    public static void net_dvr_xml_config(String devicePosition, boolean speedStatus) {
        String xml = "<vehicleStatus version='2.0' xmlns='http://www.isapi.org/ver20/XMLSchema'>" + "<devicePosition>"
                + devicePosition + "</devicePosition>" + "<speedStatus>" + speedStatus + "</speedStatus>"
                + "</vehicleStatus>";
        HCNetSDK.NET_DVR_STRING_POINTER stringRequest = new HCNetSDK.NET_DVR_STRING_POINTER();
        stringRequest.read();
        String strURL = "PUT /ISAPI/Mobile/vehicleStatus";
        stringRequest.byString = strURL.getBytes();
        stringRequest.write();

        HCNetSDK.NET_DVR_STRING_POINTER stringInBuffer = new HCNetSDK.NET_DVR_STRING_POINTER();
        stringInBuffer.read();
        String strInbuffer = xml;
        stringInBuffer.byString = strInbuffer.getBytes();
        stringInBuffer.write();

        HCNetSDK.NET_DVR_XML_CONFIG_INPUT struXMLInput = new HCNetSDK.NET_DVR_XML_CONFIG_INPUT();
        struXMLInput.read();
        struXMLInput.dwSize = struXMLInput.size();
        struXMLInput.lpRequestUrl = stringRequest.getPointer();
        struXMLInput.dwRequestUrlLen = stringRequest.byString.length;
        struXMLInput.lpInBuffer = stringInBuffer.getPointer();
        struXMLInput.dwInBufferSize = stringInBuffer.byString.length;
        struXMLInput.write();

        HCNetSDK.NET_DVR_STRING_POINTER stringXMLOut = new HCNetSDK.NET_DVR_STRING_POINTER();
        stringXMLOut.read();
        HCNetSDK.NET_DVR_STRING_POINTER struXMLStatus = new HCNetSDK.NET_DVR_STRING_POINTER();
        struXMLStatus.read();

        HCNetSDK.NET_DVR_XML_CONFIG_OUTPUT struXMLOutput = new HCNetSDK.NET_DVR_XML_CONFIG_OUTPUT();
        stringInBuffer.read();
        struXMLOutput.dwSize = struXMLOutput.size();
        struXMLOutput.lpOutBuffer = stringXMLOut.getPointer();
        struXMLOutput.dwOutBufferSize = stringXMLOut.size();
        struXMLOutput.lpStatusBuffer = struXMLStatus.getPointer();
        struXMLOutput.dwStatusSize = struXMLStatus.size();
        stringInBuffer.write();

        if (!hcNetSDK.NET_DVR_STDXMLConfig(user.longValue(), struXMLInput, struXMLOutput)) {
            int iErr = hcNetSDK.NET_DVR_GetLastError();
            log.info("设备指令下发NET_DVR_STDXMLConfig失败，错误号：[{}]", iErr);
        } else {
            stringXMLOut.read();
            struXMLStatus.read();
            String strStatus = new String(struXMLStatus.byString).trim();
            log.info("设备指令下发成功，设备响应返回strStatus：\n" + strStatus);
        }
    }

    /**
     * @param certificateNumber 证件号
     * @Description: 根据证件号查询人脸信息
     */
    public static String getFaceDataById(String certificateNumber) {
        //生成一个随机字符串
        UUID randomUUID = UUID.randomUUID();
        String xml = "<?xml version: '1.0' encoding='utf-8'?><FDSearchDescription   version='2.0' xmlns='http://www.std-cgi.org/ver20/XMLSchema' >"
                + "<FDID>1</FDID>"
                + "<certificateType>ID</certificateType>"
                + "<certificateNumber>" + certificateNumber + "</certificateNumber>"
                + "<searchID>" + randomUUID + "</searchID>"
                + "<maxResults>50</maxResults>"
                + "<searchResultPosition>0</searchResultPosition>"
                + "</FDSearchDescription>";
        HCNetSDK.NET_DVR_STRING_POINTER stringRequest = new HCNetSDK.NET_DVR_STRING_POINTER();
        stringRequest.read();
        String strURL = "POST /ISAPI/Intelligent/FDLib/FDSearch";
        stringRequest.byString = strURL.getBytes();
        stringRequest.write();

        HCNetSDK.NET_DVR_STRING_POINTER stringInBuffer = new HCNetSDK.NET_DVR_STRING_POINTER();
        stringInBuffer.read();
        String strInbuffer = xml;
        stringInBuffer.byString = strInbuffer.getBytes();
        stringInBuffer.write();
        HCNetSDK.NET_DVR_XML_CONFIG_INPUT struXMLInput = new HCNetSDK.NET_DVR_XML_CONFIG_INPUT();
        struXMLInput.read();
        struXMLInput.dwSize = struXMLInput.size();
        struXMLInput.lpRequestUrl = stringRequest.getPointer();
        struXMLInput.dwRequestUrlLen = stringRequest.byString.length;
        struXMLInput.lpInBuffer = stringInBuffer.getPointer();
        struXMLInput.dwInBufferSize = stringInBuffer.byString.length;
        struXMLInput.write();

        HCNetSDK.NET_DVR_STRING_POINTER stringXMLOut = new HCNetSDK.NET_DVR_STRING_POINTER();
        stringXMLOut.read();
        HCNetSDK.NET_DVR_STRING_POINTER struXMLStatus = new HCNetSDK.NET_DVR_STRING_POINTER();
        struXMLStatus.read();

        HCNetSDK.NET_DVR_XML_CONFIG_OUTPUT struXMLOutput = new HCNetSDK.NET_DVR_XML_CONFIG_OUTPUT();
        stringInBuffer.read();
        struXMLOutput.dwSize = struXMLOutput.size();
        struXMLOutput.lpOutBuffer = stringXMLOut.getPointer();
        struXMLOutput.dwOutBufferSize = stringXMLOut.size();
        struXMLOutput.lpStatusBuffer = struXMLStatus.getPointer();
        struXMLOutput.dwStatusSize = struXMLStatus.size();
        stringInBuffer.write();

        if (!hcNetSDK.NET_DVR_STDXMLConfig(user.longValue(), struXMLInput, struXMLOutput)) {
            int iErr = hcNetSDK.NET_DVR_GetLastError();
            log.info("失败NET_DVR_STDXMLConfig失败，错误号：" + iErr);
            String msg = "失败NET_DVR_STDXMLConfig失败，错误号：" + iErr;
            return msg;
        } else {
            stringXMLOut.read();
            String strOutXML = new String(stringXMLOut.byString).trim();
            struXMLStatus.read();
            String strStatus = new String(struXMLStatus.byString).trim();
            log.info("输出strStatus：" + strStatus);
            return strOutXML;
        }
    }

    /**
     * @param pid
     * @Description: 根据PID删除人脸信息
     * @author 纪淮永
     */
    public static void delFaceDataByPid(String pid) {

        HCNetSDK.NET_DVR_STRING_POINTER stringRequest = new HCNetSDK.NET_DVR_STRING_POINTER();
        stringRequest.read();
        String strURL = "DELETE /ISAPI/Intelligent/FDLib/1/picture/" + pid;

        stringRequest.byString = strURL.getBytes();
        stringRequest.write();


        HCNetSDK.NET_DVR_XML_CONFIG_INPUT struXMLInput = new HCNetSDK.NET_DVR_XML_CONFIG_INPUT();
        struXMLInput.read();
        struXMLInput.dwSize = struXMLInput.size();
        struXMLInput.lpRequestUrl = stringRequest.getPointer();
        struXMLInput.dwRequestUrlLen = stringRequest.byString.length;

        struXMLInput.write();

        HCNetSDK.NET_DVR_STRING_POINTER stringXMLOut = new HCNetSDK.NET_DVR_STRING_POINTER();
        stringXMLOut.read();
        HCNetSDK.NET_DVR_STRING_POINTER struXMLStatus = new HCNetSDK.NET_DVR_STRING_POINTER();
        struXMLStatus.read();

        HCNetSDK.NET_DVR_XML_CONFIG_OUTPUT struXMLOutput = new HCNetSDK.NET_DVR_XML_CONFIG_OUTPUT();
        struXMLOutput.dwSize = struXMLOutput.size();
        struXMLOutput.lpOutBuffer = stringXMLOut.getPointer();
        struXMLOutput.dwOutBufferSize = stringXMLOut.size();
        struXMLOutput.lpStatusBuffer = struXMLStatus.getPointer();
        struXMLOutput.dwStatusSize = struXMLStatus.size();
//      stringInBuffer.write();

        if (!hcNetSDK.NET_DVR_STDXMLConfig(user.longValue(), struXMLInput, struXMLOutput)) {
            int iErr = hcNetSDK.NET_DVR_GetLastError();
            // JOptionPane.showMessageDialog(null, "NET_DVR_STDXMLConfig失败，错误号：" + iErr);
            log.info("失败NET_DVR_STDXMLConfig失败，错误号：" + iErr);
        } else {
            stringXMLOut.read();
            String strOutXML = new String(stringXMLOut.byString).trim();
            log.info("输出strOutXML" + strOutXML);
            //jTextAreaOutBuffer.setText(strOutXML);
            struXMLStatus.read();
            String strStatus = new String(struXMLStatus.byString).trim();
            log.info("输出strStatus：" + strStatus);
        }
    }
}
