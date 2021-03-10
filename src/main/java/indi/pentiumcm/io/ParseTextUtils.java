package indi.pentiumcm.io;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @projName: JavaSkillStack
 * @packgeName: indi.pentiumcm.io
 * @className: ReadText
 * @author： pentiumCM
 * @email： 842679178@qq.com
 * @date: 2020/11/25 20:17
 * @describe: 读取文本数据，解析成结构化数据
 */
public class ParseTextUtils {


    /**
     * 判断字符串是否以数字开头
     *
     * @param str
     * @return
     */
    public boolean isStartWithNum(String str) {
        boolean flag = false;

        if (str == null) {
            return flag;
        }
        if (str.matches("[0-9]+.*")) {
            flag = true;
        }
        return flag;
    }


    /**
     * 读取文本文件中的数据：读取规则，按行来存入列表中
     *
     * @param textPath 文本文件的路径
     * @return 文本文件中的全部数据
     */
    public List<String> readTextFile(String textPath) {

        List<String> result = new ArrayList<>();

        String encoding = "UTF-8";

        File file = new File(textPath);
        // 判断文件是否存在
        if (file.exists() && file.isFile()) {
            try {
                // 构建输入流
                InputStreamReader reader = new InputStreamReader(new FileInputStream(file), encoding);

                BufferedReader bufferedReader = new BufferedReader(reader);

                // 使用readLine方法，一次读一行
                String line = null;
                while ((line = bufferedReader.readLine()) != null) {

                    // 文本过滤格式：去除开头制表符，中文冒号改英文
                    String filter = line.replace("\t", "").replace("：", ":");
                    result.add(filter);
                }

            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }


    /**
     * 解析文本数据为结构化数据
     *
     * @param textContent  文本数据
     * @param extract_node 文本数据中提取的关键节点
     * @return 解析后的文本数据使用列表来存储，列表中的泛型为 Map
     */
    List<Map<String, String>> text2StructData(List<String> textContent, String[] extract_node) {

        // 利用列表存放结果，泛型 Map 对应解析后的结构化数据格式
        List<Map<String, String>> result = new ArrayList<>();

        int rows = textContent.size();

        for (int begin_index = 0; begin_index < rows; begin_index++) {
            String begline = textContent.get(begin_index);


            // 以数字开头代表：故障结论
            if (isStartWithNum(begline)) {

                // 利用字典存放每个单元的文本数据
                Map<String, String> result_item = new HashMap<>();

                // 寻找下一个数字开头的位置end_index，则 [begin_index，end_index）这个区间为当前故障数据的单元区间
                int end_index = begin_index;
                while (end_index < rows - 1 && (!isStartWithNum(textContent.get(end_index + 1)))) {
                    end_index += 1;
                }

                // 将 end_index 定位到下一单元的开头，方便进行提取
                if (end_index < rows) {
                    end_index += 1;
                }

                // 提取故障结论
                String conclusion = begline.split("、")[1];
                result_item.put("conclusion", conclusion);

                //  确定每个单元中每个待提取节点起始的位置索引
                List<Integer> node_indexs = new ArrayList<>();
                for (String node : extract_node) {

                    for (int index = begin_index; index < end_index; index++) {
                        String matchLine = textContent.get(index);
                        if (matchLine.startsWith(node)) {
                            node_indexs.add(index);
                            break;
                        }
                    }
                }

                // 将节点索引排序
                Collections.sort(node_indexs);

                // 结构化提取的规则：根据提取的节点的位置，然后分别提取该部分文本内容
                for (int index_num = 0; index_num < node_indexs.size(); index_num++) {

                    // [index1 : index2）之间的数据为某一节点的数据
                    int index1 = 0;
                    int index2 = 0;
                    if (index_num < node_indexs.size() - 1) {
                        index1 = node_indexs.get(index_num);
                        index2 = node_indexs.get(index_num + 1);
                    } else {
                        index1 = node_indexs.get(node_indexs.size() - 1);
                        index2 = end_index;
                    }

                    // 提取当前节点内的数据，为 [index1 : index2）中的内容
                    // 1. 处理值和节点在同一行的情况，如，症状：每个球的位置上有椭圆形的磨痕
                    StringBuilder nodeValue = new StringBuilder();
                    if (textContent.get(index1).split(":").length > 1) {
                        nodeValue.append(textContent.get(index1).split(":")[1]);
                    }

                    // 2. 直接循环附加
                    for (int num = index1 + 1; num < index2; num++) {
                        nodeValue.append(textContent.get(num));
                    }

                    // 将提取好的某一节点内的数据存入 Map 中
                    result_item.put(extract_node[index_num], nodeValue.toString());

                }

                result.add(result_item);

                // 当前单元提取完毕，将 索引定位到下一个区间的位置，继续进行提取
                begin_index = end_index - 1;

            }
        }
        return result;
    }


    public static void main(String[] args) {

        ParseTextUtils parseTextUtils = new ParseTextUtils();

        String textPath = "F:\\develop_code\\java\\JavaSkillStack\\src\\main\\java\\indi\\pentiumcm\\io\\newdataFromHtmlText.txt";

        // 读取文本文件
        List<String> textContent = parseTextUtils.readTextFile(textPath);

        // 指定文本中的待提取的数据节点，之后作为转储为结构化数据的表头
        String[] extract_node = {"症状", "原因", "补救方法"};

        // 文本数据转结构化数据
        List<Map<String, String>> structDatas = parseTextUtils.text2StructData(textContent, extract_node);

        // 打印解析好的结构化数据
//        structDatas.forEach((unit) -> System.out.println(unit));

        for (Map<String, String> unit : structDatas) {
            System.out.println(unit);
        }


        // 未完待续：待明确，此版本的文本数据解析成结构化的List<Map>
        // 如果需要将 List<Map> 导出到 excel 中，再继续完善


        String str = "a1 bC,";

        String cc = str.toUpperCase();

        System.out.println(str);


        int code = 200;
        String status = 200 + "";

        if ("200".equals(status)) {
            System.out.println("相等");
        }


        SimpleDateFormat df = new SimpleDateFormat("MM-dd HH:mm:ss");//设置日期格式

        // new Date()为获取当前系统时间
        String format = df.format(new Date());

        System.out.println(format);

    }


}
