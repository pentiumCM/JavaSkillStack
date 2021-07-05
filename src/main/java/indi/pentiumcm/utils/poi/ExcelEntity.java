package indi.pentiumcm.utils.poi;

import lombok.Data;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelTarget;

/**
 * @projName: JavaSkillStack
 * @packgeName: indi.pentiumcm.utils.poi
 * @className: ExcelEntity
 * @author： pentiumCM
 * @email： 842679178@qq.com
 * @date: 2021/5/8 21:24
 * @describe:
 */
@Data
@ExcelTarget("courseEntity")
public class ExcelEntity implements java.io.Serializable {
    /**
     * 主键
     */
    private String id;
    /**
     * 姓名
     */
    @Excel(name = "姓名", orderNum = "1", needMerge = true)
    private String name;

    /**
     * 年龄
     */
    @Excel(name = "年龄", orderNum = "1", needMerge = true)
    private String age;
}



