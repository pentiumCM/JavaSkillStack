package indi.pentiumcm.hls;


import org.bytedeco.javacpp.avcodec;
import org.bytedeco.javacpp.avformat;
import org.bytedeco.javacpp.avutil;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.FFmpegFrameRecorder;
import org.bytedeco.javacv.FFmpegLogCallback;

import static org.bytedeco.javacpp.avcodec.av_packet_unref;

/**
 * @projName: JavaSkillStack
 * @packgeName: indi.pentiumcm.hls
 * @className: HlsPush
 * @author： pentiumCM
 * @email： 842679178@qq.com
 * @date: 2021/3/17 20:25
 * @describe: hls流切片
 */

public class HlsPush {


    /**
     * @param input  可以是一个InputStream的流、动态图片(apng,gif等等)，视频文件（mp4,flv,avi等等）,流媒体地址（http-flv,rtmp，rtsp等等）
     * @param output hls切片存放地址
     * @throws org.bytedeco.javacv.FrameGrabber.Exception
     * @throws org.bytedeco.javacv.FrameRecorder.Exception
     * @Title: push
     * @Description: hls切片
     * @return: void
     **/
    public void push(String input, String output)
            throws org.bytedeco.javacv.FrameGrabber.Exception, org.bytedeco.javacv.FrameRecorder.Exception {
        FFmpegFrameGrabber grabber = null;// 采集器
        FFmpegFrameRecorder recorder = null;// 解码器
        int bitrate = 2500000;// 比特率
        double framerate;// 帧率
        int err_index = 0;// 推流过程中出现错误的次数
        int timebase;// 时钟基
        long dts = 0, pts = 0;// pkt的dts、pts时间戳
        try {

            // 开启ffmpeg日志级别；QUIET是屏蔽所有，可选INFO、DEBUG、ERROR等
            avutil.av_log_set_level(avutil.AV_LOG_INFO);
            FFmpegLogCallback.set();
            grabber = new FFmpegFrameGrabber(input);
            grabber.start();

            // 异常的framerate，强制使用25帧
            if (grabber.getFrameRate() > 0 && grabber.getFrameRate() < 100) {
                framerate = grabber.getFrameRate();
            } else {
                framerate = 25.0;
            }

            bitrate = grabber.getVideoBitrate();// 获取到的比特率 0

            recorder = new FFmpegFrameRecorder(output, grabber.getImageWidth(), grabber.getImageHeight(), 0);
            // 设置比特率
            recorder.setVideoBitrate(bitrate);
            // h264编/解码器
            recorder.setVideoCodec(avcodec.AV_CODEC_ID_H264);
            // 设置音频编码
            recorder.setAudioCodec(avcodec.AV_CODEC_ID_AAC);
            // 视频帧率(保证视频质量的情况下最低25，低于25会出现闪屏)
            recorder.setFrameRate(framerate);
            // 关键帧间隔，一般与帧率相同或者是视频帧率的两倍
            recorder.setGopSize((int) framerate);
            // 解码器格式
            recorder.setFormat("hls");
            // 单个切片时长,单位是s，默认为2s
            recorder.setOption("hls_time", "60");
            // HLS播放的列表长度，0标识不做限制
            recorder.setOption("hls_list_size", "0");
            // 设置切片的ts文件序号起始值，默认从0开始，可以通过此项更改
            recorder.setOption("start_number", "120");
//			recorder.setOption("hls_segment_type","mpegts");
            // 自动删除切片，如果切片数量大于hls_list_size的数量，则会开始自动删除之前的ts切片，只保 留hls_list_size个数量的切片
            // recorder.setOption("hls_flags","delete_segments");
            // ts切片自动删除阈值，默认值为1，表示早于hls_list_size+1的切片将被删除
            // recorder.setOption("hls_delete_threshold","1");
            /*
             * hls的切片类型： 'mpegts'：以MPEG-2传输流格式输出ts切片文件，可以与所有HLS版本兼容。 'fmp4':以Fragmented
             * MP4(简称：fmp4)格式输出切片文件，类似于MPEG-DASH，fmp4文件可用于HLS version 7和更高版本。
             */
//			recorder.setOption("hls_segment_type","mpegts");

            avformat.AVFormatContext fc = null;
            fc = grabber.getFormatContext();
            recorder.start(fc);
            // 清空探测时留下的缓存
//			grabber.flush();

            avcodec.AVPacket pkt = null;

            for (int no_pkt_index = 0; no_pkt_index < 5 && err_index < 5; ) {
                pkt = grabber.grabPacket();
                if (pkt == null || pkt.size() <= 0 || pkt.data() == null) {
                    Thread.sleep(1);
                    no_pkt_index++;
                    continue;
                }
                // 获取到的pkt的dts，pts异常，将此包丢弃掉。
                if (pkt.dts() == avutil.AV_NOPTS_VALUE && pkt.pts() == avutil.AV_NOPTS_VALUE || pkt.pts() < dts) {
                    err_index++;
                    av_packet_unref(pkt);
                    continue;
                }
                // 矫正dts，pts
                pkt.pts(pts);
                pkt.dts(dts);
                err_index += (recorder.recordPacket(pkt) ? 0 : 1);
                // pts,dts累加
                timebase = grabber.getFormatContext().streams(pkt.stream_index()).time_base().den();

                pts += (timebase / (int) framerate);
                dts += (timebase / (int) framerate);
            }
        } catch (Exception e) {
            grabber.stop();
            grabber.close();
            if (recorder != null) {
                recorder.stop();
                recorder.release();
            }
        } finally {
            grabber.stop();
            grabber.close();
            if (recorder != null) {
                recorder.stop();
                recorder.release();
            }
        }
    }

    public static void main(String[] args)
            throws org.bytedeco.javacv.FrameGrabber.Exception, org.bytedeco.javacv.FrameRecorder.Exception {
        HlsPush test = new HlsPush();
        test.push("F:/test.mp4",
                "D:\\nginx\\nginx-rtmp-win32\\nginx-rtmp-win32-master\\temp\\hls\\test\\banmajio.m3u8");
    }

}

