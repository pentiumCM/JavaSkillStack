package indi.pentiumcm.utils.json;

/**
 * @projName: JavaSkillStack
 * @packgeName: indi.pentiumcm.utils.json
 * @className: JsonRootBean
 * @author： pentiumCM
 * @email： 842679178@qq.com
 * @date: 2020/7/27 20:43
 * @describe: Copyright 2020 bejson.com
 */

import java.util.List;

/**
 * Auto-generated: 2020-07-27 20:35:55
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class JsonRootBean {

    private String total;
    private List<Rows> rows;
    public void setTotal(String total) {
        this.total = total;
    }
    public String getTotal() {
        return total;
    }

    public void setRows(List<Rows> rows) {
        this.rows = rows;
    }
    public List<Rows> getRows() {
        return rows;
    }

}