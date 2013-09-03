

public class example{

    public static void main(String[] args) {

        //传入email与api signature   
        CDNZZ cdn = new CDNZZ("lixiang@aaa.com", "5424ac7f17ddfdb759ae50d2e3b17667"); 
        
        //清缓存
        String ret = cdn.purgeCache("http://www.**.com/api/down.apk"); 
        System.out.println(ret);
        
        //预加载
        ret = cdn.preload("http://www.**.com/api/down.apk"); 
        System.out.println(ret);
    }   

}
