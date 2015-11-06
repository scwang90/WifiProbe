package com.simpletech.wifiprobe.page;

import com.simpletech.wifiprobe.model.entity.BrandValue;
import com.simpletech.wifiprobe.util.ServiceException;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ErrorMessages_zh_CN;

import java.util.ArrayList;
import java.util.List;

/**
 * WifiProbe
 * Created by ChenQi on 2015/11/6 16:15.
 */
public class Page {
    /**
     * 对数据集中的数据分页显示（假分页）
     * @param list
     * @param limit
     * @param skip
     * @return
     */
    public List<BrandValue> paging(List<BrandValue> list,int limit,int skip)throws Exception{
        List<BrandValue> pageList=new ArrayList<>();
        int totalCount = list.size();//数据总数

        int pageCount = 0;//总的页数
        int endNum = limit;//每页显示的条数

        int startNum = skip;//当前页码

        //计算出总共能分成多少页
        if (totalCount % endNum > 0) //数据总数和每页显示的总数不能整除的情况
        {
            pageCount = totalCount / endNum + 1;
        }
        else   //数据总数和每页显示的总数能整除的情况
        {
            pageCount = totalCount / endNum;
        }
        if(totalCount > 0) {
            if (startNum <= pageCount) {
                if (startNum == 1)     //当前页数为第一页
                {
                    if (totalCount <= endNum)  //数据总数小于每页显示的数据条数
                    {
                        //截止到总的数据条数(当前数据不足一页，按一页显示)，这样才不会出现数组越界异常
                        pageList = list.subList(0, totalCount);
                    } else {
                        pageList = list.subList(0, endNum);
                    }
                } else {
                    //截取起始下标
                    int fromIndex = (startNum - 1) * endNum;
                    //截取截止下标
                    int toIndex = startNum * endNum;
                    /*计算截取截止下标*/
                    if ((totalCount - toIndex) % endNum >= 0) {
                        toIndex = startNum * endNum;
                    } else {
                        toIndex = (startNum - 1) * endNum + (totalCount % endNum);
                    }
                    if (totalCount >= toIndex) {
                        pageList = list.subList(fromIndex, toIndex);
                    }
                }
            } else {
                throw new ServiceException("分页超出限制，请调整显示条数或页数，起始页为1 ！");
            }
        }
        return pageList;
    }
}
