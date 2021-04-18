package indi.pentiumcm.hls;

import cn.hutool.core.util.IdUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * @projName: JavaSkillStack
 * @packgeName: indi.pentiumcm.hls
 * @className: MergeTs
 * @author： pentiumCM
 * @email： 842679178@qq.com
 * @date: 2021/4/7 14:48
 * @describe: 合并 ts 视频
 */
public class MergeTs {


    /**
     * 合并ts文件
     *
     * @param tsInputPath ts输入的文件夹
     * @param savePath    合并之后的MP4文件
     */
    private static String mergeTs(String tsInputPath, String savePath) {

        // 获取 ts 输入文件夹中的全部文件
        String[] list = new File(tsInputPath).list();
        Arrays.sort(list);
        List<String> urls = Arrays.asList(list);

        FileOutputStream fos = null;
        FileInputStream fis = null;

        String mergeFileName = IdUtil.simpleUUID() + ".mp4";

        try {
            File file = new File(savePath + mergeFileName);
            fos = new FileOutputStream(file);
            byte[] buf = new byte[4096];
            int len;
            for (int i = 0; i < urls.size(); i++) {
                String tsName = urls.get(i);
                String[] types = tsName.split("\\.");
                if (types.length < 2 || !"ts".equals(types[1])) {
                    continue;
                }
                // fis = new FileInputStream(Dir + "000" + (i + 1) + ".ts");
                fis = new FileInputStream(tsInputPath + tsName);
                while ((len = fis.read(buf)) != -1) {
                    fos.write(buf, 0, len);
                }
                fos.flush();
                fis.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (fis != null) {
                    fis.close();
                }
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return mergeFileName;
    }


    public static String[] getFileName() {
        String basePath = "E:\\demo1\\1372068323730096129231d61cbef01b7bb\\20210407";
        String[] list = new File(basePath).list();
        return list;
    }

    public static void main(String[] args) {


        String s = mergeTs("E:\\examtest\\video\\1372068323730096129\\231d61cbef01b7bb\\20210408204756/", "E:\\examtest\\video\\1372068323730096129\\231d61cbef01b7bb\\20210408204756/");
        System.out.println("文件名：" + s);
    }
}
