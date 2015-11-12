package com.simpletech.wifiprobe.mac;

import com.simpletech.wifiprobe.util.JacksonUtil;
import org.junit.Test;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * mac地址 品牌测试类
 * Created by Administrator on 2015/11/2.
 */
public class MacBrandMemoryTester {

    private static final String DELETE = "delete";
    private static final String STAY = "stay";
    private static final String DELETE_STR = "delete:";

    /**
     * 不能看作公司或品牌的关键词
     */
    Map<String,String> nkeys = new HashMap<String,String>(){{
        put("SHENZHEN",DELETE);
        put("SICHUAN",DELETE);
        put("GUANGZHOU",DELETE);
        put("SHANGHAI",DELETE);
        put("ZHEJIANG",DELETE);
        put("FUJIAN",DELETE);
        put("SUZHOU",DELETE);
        put("BEIJING",DELETE);
        put("CHINA",DELETE);
        put("HANGZHOU",DELETE);
        put("HEFEI",DELETE);
        put("QINGDAO",DELETE);
        put("NANJING",DELETE);
        put("CHENGDU",DELETE);
        put("JIANGSU",DELETE);
        put("HUIZHOU",DELETE);
        put("JIANGXI",DELETE);
        put("GUANGDONG",DELETE);//广东
        put("JAPAN",DELETE);//日本
        put("XIAMEN",DELETE);//厦门



        put("SHEN",DELETE_STR + "SHEN ZHEN SHI");//深圳市

        put("ASIA",STAY);//亚洲
        put("CISCO",STAY);//思科公司（数量太多）
        put("JAVA",STAY);//Java语言
        put("STEP",STAY);//英文单词单步
        put("FUJI",STAY);//日本富士山
        put("LONG",STAY);//长形容词
        put("WI-FI",STAY);//通用名称
        put("NETWORK",STAY);//通用名称
        put("3COM",STAY);//3Com
        put("TEXAS",STAY);//3Com

    }};

    /**
     * 信息不完整的品牌
     */
    Map<String,String> brands = new HashMap<String,String>(){{
        put("2C - TRIFONOV & CO","2C - Trifonov");
        put("AT & T LABORATORIES - CAMBRIDGE LTD","AT&T");
        put("JIAGNSU AETNA ELECTRIC CO.,LTD","JIAGNSU AETNA");
        put("3 COM CORPORATION","3Com Corporation");
        put("3COM CORPORATION","3Com Corporation");
        put("SHANGHAI JING REN ELECTRONIC TECHNOLOGY CO., LTD","Jing Ren Electronic");
        put("FA","FA Dungchon-Dong");
        put("GE","Waukesha");
        put("F3, INC.","F3, Inc. Hsinchu");//Hsinchu 台湾新竹
        put("BQ","BQ Sofia");
        put("BQ,SOFIA","BQ Sofia");
        put("FUJI-XEROX","Fuji Xerox");
    }};

    /**
     * 品牌备注
     */
    Map<String,String> remarks = new HashMap<String,String>(){{
        put("APPLE","苹果");
        put("GOOGLE","谷歌");
        put("INTEL","英特尔");
        put("XIAOMI","小米");
        put("MEIZU","魅族");
        put("ONEPLUS","一加");
        put("SMARTISAN","锤子");
        put("LETV","乐视");
        put("SAMSUNG","三星");
        put("HUAWEI","华为");
        put("LENOVO","联想");
        put("NOKIA","诺基亚");
        put("AZUREWAVE","海华");
        put("ESPRESSIF","乐鑫");
        put("DELL","戴尔");
        put("SONY","索尼");
        put("YULONG","宇龙");
        put("JINGHANDA","晶汉达");
        put("WAUKESHA","沃喀莎");
        put("BQ SOFIA","BQ索菲亚");
        put("ZTE CORPORATION","中兴");
        put("HTC CORPORATION","HTC");
        put("MICROSOFT","微软");
        put("LG ELECTRONICS","LG");
        put("JIN HUA TAI ELECTRONICS CO.","金华泰");
        put("SHANSHUI","江西山水光电科");
        put("IEEE","电气和电子工程师协会");
        put("JUNIPER","瞻博网络");
        put("ASUSTEK","华硕");
        put("NINTENDO","任天堂");
        put("SAGEMCOM","萨基姆");
        put("MOTOROLA","摩托罗拉");
        put("LITEON","光寶科技");
        put("AZUREWAVE","海华科技");
        put("JUNIPER","瞻博网络");
        put("MURATA","村田");
        put("HON HAI","HON HAI（鸿海科技）");
        put("HEWLETT","惠普");
        put("SYNTECH","Syntech(鑫河科技)");
        put("GIONEE","金立");
        put("LONGCHEER","龙旗集团");
        put("MEDIATEK","联发科");
        put("CHANGHONG","长虹");
        put("GEMTEK","Gemtek 正文科技（台湾）");
        put("OGEMRAY","奥金瑞");
        put("HISENSE","海信");
        put("FORD","Ford（福特）");
        put("JING REN ELECTRONIC","Jing Ren（京仁电子）");
        put("Huaqin","Huaqin（华勤）");
        put("MERCURY","Mercury（水星路由器）");
        put("REALTEK","Realtek（瑞昱）");
        put("FEIXUN","Feixun（斐讯）");
        put("WINGTECH","Wingtech（闻泰）");
        put("BASICOM","Basicom（宝捷讯）");
        put("XEROX","Xerox（施乐）");
        put("FLYAUDIO","FlyAudio（飞歌）");
        put("RAGENTEK","Ragentek（锐嘉科科）");
        put("BENY","Beny（邦宁）");
        put("AMOI","AMOI（夏新）");
        put("DEWAV","Dewav（鼎为通讯）");
        put("PARAGON","Paragon软件集团");
        put("PROWARE","Proware（豪威）");

    }};

    @Test
    public void parserMapper() throws Exception {
        System.out.println(JacksonUtil.toJson(MacBrandMemory.parser("009069")));
        System.out.println(JacksonUtil.toJson(MacBrandMemory.parserBrandMac("009069")));
    }

    @Test
    public void buildMapper() throws Exception {
        Pattern pattern = Pattern.compile("(\\S+)\\s{2,}\\(base 16\\)\\s{2,}(.+)", Pattern.CASE_INSENSITIVE);
        InputStream stream = ClassLoader.getSystemResourceAsStream("premac.txt");

        List<Map.Entry<String,String[]>> list = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(stream))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Matcher matcher = pattern.matcher(line.trim());
                if (matcher.find()) {
                    final String patterm = matcher.group(1);
                    String company = matcher.group(2).replaceAll(" {2,}", " ").replaceAll(",.*", "");
                    String[] companys = company.split(" ");
                    String brand = brands.get(matcher.group(2).toUpperCase());
                    String remark = null;
                    if (brand == null) {
                        String nkey = nkeys.get(companys[0].toUpperCase());
                        if (nkey != null) {//如果以不可以当中公司或品牌的关键字开始
                            if (nkey.startsWith(DELETE_STR)) {//如果特殊再匹配
                                brand = company.replace(nkey.substring(DELETE_STR.length()),"").trim();
                            } else if (companys.length > 1){
                                remark = companys[1];
                                if (remark.replaceAll(" |&|\\.|\\-|\\+","").length() < 4 && companys.length > 2) {
                                    remark = remark + " " + companys[2];
                                }
                                if (remark.replaceAll(" |&|\\.|\\-|\\+","").length() < 4 && companys.length > 3) {
                                    remark = remark + " " + companys[3];
                                }
                                if (remark.replaceAll(" |&|\\.|\\-|\\+","").length() < 4 && companys.length > 4) {
                                    remark = remark + " " + companys[4];
                                }
                                brand = companys[0] + " " + remark;
                                if (!nkey.startsWith(DELETE)) {
                                    remark = null;
                                }
                            } else {
                                brand = companys[0];
                            }
                        } else {//小于4
                            brand = companys[0];
                            if (brand.replaceAll(" |&|\\.|\\-|\\+","").length() < 4 && companys.length > 1) {
                                brand = brand + " " + companys[1];
                            }
                            if (brand.replaceAll(" |&|\\.|\\-|\\+","").length() < 4 && companys.length > 2) {
                                brand = brand + " " + companys[2];
                            }
                        }
                    }
                    String _remark = remarks.get(brand.toUpperCase());
                    remark = (_remark==null)?remark:_remark;
                    String out = patterm + ":" + brand + ((remark==null)?"":"," + remark);
//                        System.out.println(out);
                    if (brand.length() < 3) {
                        System.out.println(company + "-" + line);
                        String model = reader.readLine().trim().replaceAll(" {2,}", " ");
                        String city = reader.readLine().trim().replaceAll(" {2,}", " ");
                        String country = reader.readLine().trim().replaceAll(" {2,}", " ");
                        System.out.println("\t" + model);
                        System.out.println("\t" + city);
                        System.out.println("\t" + country);
                    }
                    list.add(new AbstractMap.SimpleEntry<>(matcher.group(1),out.split(":")[1].split(",")));
                }
            }
        }
        Collections.sort(list, new Comparator<Map.Entry<String, String[]>>() {
            @Override
            public int compare(Map.Entry<String, String[]> o1, Map.Entry<String, String[]> o2) {
                int compare = o1.getValue()[0].compareTo(o2.getValue()[0]);//品牌正序
                return compare!=0?compare:o2.getKey().compareTo(o1.getKey());//mac倒序
            }
        });
        try (PrintWriter writer = new PrintWriter(new FileWriter("src\\test\\resources\\premac.mem.txt"))) {
            for (Map.Entry<String, String[]> entry : list) {
                String out = entry.getKey() + ":" + entry.getValue()[0] + ((entry.getValue().length<2)?"":"," + entry.getValue()[1]);
                writer.println(out);
                System.out.println(out);
            }
        }
    }

}
