package indi.pentiumcm.hls;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @projName: JavaSkillStack
 * @packgeName: indi.pentiumcm.hls
 * @className: HlsUtils
 * @author： pentiumCM
 * @email： 842679178@qq.com
 * @date: 2021/3/9 21:22
 * @describe: HLS 媒体流工具类
 */
public class HlsUtils {

    private static String rootPath = "F:/m3u8dir";

    /**
     * 下载索引文件： (m3u8) 文件
     *
     * @param urlpath
     * @return
     */
    public static String getIndexFile(String urlpath) {
        URL url = null;
        BufferedReader in = null;
        try {
            url = new URL(urlpath);

            //下载资源
            in = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));

            String content = "";
            String line;
            while ((line = in.readLine()) != null) {
                content += line + "\n";
            }
            in.close();
            System.out.println(content);
            return content;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }


    /**
     * 解析索引文件
     *
     * @param content
     * @return
     */
    public static List analysisIndex(String content) {
        Pattern pattern = Pattern.compile(".*ts");
        Matcher ma = pattern.matcher(content);

        List<String> list = new ArrayList<String>();

        while (ma.find()) {
            String s = ma.group();
            list.add(s);
            System.out.println(s);
        }
        return list;
    }

    /**
     * 下载视频片段 —— 同步下载
     *
     * @param urlList
     * @return
     */
    public static List<String> downLoadIndexFile(String preUrlPath, List<String> urlList) {
        try {
            List<String> filePathList = new ArrayList<String>();
            String uuid = UUID.randomUUID().toString().replaceAll("-", "");
            for (String urlpath : urlList) {
                URL url = new URL(preUrlPath + urlpath);
                //下在资源
                DataInputStream dataInputStream = new DataInputStream(url.openStream());
                String fileOutPath = rootPath + File.separator + uuid + File.separator + urlpath;
                File file = new File(rootPath + File.separator + uuid);
                if (!file.exists()) {
                    file.mkdirs();
                }
                FileOutputStream fileOutputStream = new FileOutputStream(new File(fileOutPath));
                byte[] bytes = new byte[1024];
                int length = 0;
                while ((length = dataInputStream.read(bytes)) != -1) {
                    fileOutputStream.write(bytes, 0, length);
                }
                System.out.println("下载完成..." + fileOutPath);
                dataInputStream.close();
                filePathList.add(fileOutPath);
            }

            return filePathList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public static void main(String[] args) {
        String indexPath = "https://hls-dss-cn01.bcloud365.net:8188/hls/YWNiZTQxOWI0NnwxYmMwNjAwZjIzODIzZGE3fGV5SjFJam9pWVdSdGFXNGlMQ0p3SWpvaW3RHeEtkM0JpYnpZaWZRPT18MTYxNTMwMTMxNjM5M3xkc3N8MTE5LjMuNjIuMTA5fG1kNQ%3D%3D.4f26aa2d0193ee43bf2cf4cd214d1d58/1bc0600f23823da7/1.m3u8";

        String prePath = indexPath.substring(0,indexPath.lastIndexOf("/")+1);
        System.out.println(prePath);

        //下载索引文件
        String indexStr = HlsUtils.getIndexFile(indexPath);

        //解析索引文件
        List videoUrlList = HlsUtils.analysisIndex(indexStr);

        //下载视频片段
        List<String> fileList = HlsUtils.downLoadIndexFile(prePath, videoUrlList);


        System.out.printf("");
    }

}
