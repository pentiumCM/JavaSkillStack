package indi.pentiumcm.hikvision.hcnet;

import indi.pentiumcm.hikvision.ipc.Camera;
import lombok.extern.slf4j.Slf4j;

/**
 * @projName: JavaSkillStack
 * @packgeName: indi.pentiumcm.hikvision
 * @className: Test
 * @author： pentiumCM
 * @email： 842679178@qq.com
 * @date: 2021/5/8 13:42
 * @describe:
 */
@Slf4j
public class Test {


    /**
     * 海康设备设备初始化
     *
     * @param args
     * @throws Exception
     */
    public static void HCNetInit(String... args) throws Exception {
        HCNetSDK hcNetSDK = HCNetUtils.getHCNetSDK();
        Long user = HCNetUtils.login(hcNetSDK, Camera.dvrUserName, Camera.dvrPassword, Camera.dvrIp, Camera.dvrPort);
        while (user == null) {
            log.error("DVR登陆失败,5秒后重新登陆");
            Thread.sleep(100 * 1000);
            user = HCNetUtils.login(hcNetSDK, Camera.dvrUserName, Camera.dvrPassword, Camera.dvrIp, Camera.dvrPort);

        }
//        Boolean callback = hcNetSDK.NET_DVR_SetDVRMessageCallBack_V31(hcNetAlarmDataService, null);
//        while (!callback) {
//            log.error("DVR设置回调函数失败,5秒后重新设置");
//            Thread.sleep(5000);
//            callback = hcNetSDK.NET_DVR_SetDVRMessageCallBack_V31(hcNetAlarmDataService, null);
//        }
//        HCNetSDK.NET_DVR_SETUPALARM_PARAM m_strAlarmInfo = new HCNetSDK.NET_DVR_SETUPALARM_PARAM();
//        m_strAlarmInfo.dwSize = m_strAlarmInfo.size();
//        m_strAlarmInfo.byLevel = 1;// 智能交通布防优先级：0- 一等级（高），1- 二等级（中），2- 三等级（低）
//        m_strAlarmInfo.byAlarmInfoType = 1;// 智能交通报警信息上传类型：0- 老报警信息（NET_DVR_PLATE_RESULT），1-新报警信息(NET_ITS_PLATE_RESULT)
//        m_strAlarmInfo.byDeployType = 0; // 布防类型(仅针对门禁主机、人证设备)：0-客户端布防(会断网续传)，1-实时布防(只上传实时数据)
//        m_strAlarmInfo.write();
//        int lAlarmHandle = hcNetSDK.NET_DVR_SetupAlarmChan_V41(user.intValue(), m_strAlarmInfo);
//        if (lAlarmHandle == -1) {
//            log.error("布防失败，错误号:" + hcNetSDK.NET_DVR_GetLastError());
//        } else {
//            log.info("布防成功！");
//        }
    }


    public static void main(String[] args) throws Exception {
        HCNetInit();

    }
}
