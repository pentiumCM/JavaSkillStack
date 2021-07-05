package indi.pentiumcm.hikvision.ipc;

/**
 * 相机信息配置
 */
public interface Camera {

	// =================相机配置信息=================
    /** DVR设备登陆地址 */
    public String dvrIp = "192.168.1.64";

    /** DVR设备登陆端口 */
    public short dvrPort = 8000;

    /** DVR设备登陆账号 */
    public String dvrUserName = "admin";

    /** DVR设备登陆密码 */
    public String dvrPassword = "pg123456";

    /** 手势相机地址 */
    public String gestureIp = "192.168.1.34";

    /** 手势相机端口 */
    public Integer gestureOnvifPort = 80;

    /** 手势相机账号 */
    public String gestureUserName = "admin";

    /** 手势相机密码 */
    public String gesturePassword = "pg123456";

    /** 信号灯相机地址 */
    public String signalLampIp = "192.168.1.94";

    /** 信号灯相机端口 */
    public Integer signalLampOnvifPort = 80;

    /** 信号灯相机账号 */
    public String signalLampUserName = "admin";

    /** 信号灯相机密码 */
    public String signalLampPassword = "pg123456";

    /** 故障指引相机地址 */
    public String guideIp = "192.168.1.104";

    /** 故障指引相机端口 */
    public Integer guideOnvifPort = 80;

    /** 故障指引相机账号 */
    public String guideUserName = "admin";

    /** 故障指引相机密码 */
    public String guidePassword = "pg123456";

    // =================截取视频的时长（秒）=================
    /** 吸烟 */
    public Integer smokeTime = 10;

    /** 打电话 */
    public Integer phoneTime = 10;

    /** 疲劳驾驶 */
    public Integer tiredDrivingTime = 10;

    /** 没有目视前方 */
    public Integer noVisualFrontTime = 10;

    /** 离岗 */
    public Integer noHeadTime = 20;

    /** 不系安全带 */
    public Integer withoutBeltTime = 10;

    /** 捡拾物品 */
    public Integer pickingUpThingTime = 10;

    /** 打哈欠 */
    public Integer yawnTime = 10;

    /** 吃喝 */
    public Integer eatOrDrinkTime = 10;

    /** 聊天 */
    public Integer chattingTime = 10;

    /** 遮挡IPC */
    public Integer tamperingTime = 10;

    /** 未穿工作服 */
    public Integer withoutUniformTime = 10;

    /** 身份识别异常 */
    public Integer driverCmpFailTime = 10;

    /** 驾驶员更换 */
    public Integer driverChangeTime = 10;

    /** 超时驾驶 */
    public Integer driveLongTime = 10;

	/** 红外阻断墨镜 */
	public Integer infraredBlockingSunglasses = 10;

    /** 手势视频 */
    public Integer gestureTime = 5;

    /** 信号灯视频 */
    public Integer signalLampTime = 10;

}
