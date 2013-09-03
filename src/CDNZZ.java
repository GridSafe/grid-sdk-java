
/*
 *
 *  CDNZZ JAVA SDK 
 *
 *  要了解速致的产品信息，请查看官方网站。
 *  速致官网：https://www.cdnzz.com
 *  api文档地址：https://www.cdnzz.com/help/user_api
 * 
 * @version 0.1.0
 * @copyright 2013 cdnzz.com, Inc.  
 * @author lixiang@grid-safe.com
 *
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLEncoder;
import java.net.URLConnection;
import java.io.UnsupportedEncodingException;

import java.util.regex.Pattern; 
import java.util.regex.Matcher; 

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;

public class CDNZZ{

    public String apiHost = "https://www.cdnzz.com/api/json";

    private String _account = "";
    
    private String _signature = "";

    public CDNZZ(String account, String signature){
        this._account = account;
        this._signature = signature;
    }

    public String sendPost(String toUrl, String content) throws IOException {   
        
        URL url = new URL(apiHost);   
        URLConnection connection = url.openConnection();   
        connection.setDoOutput(true);   
        OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream(), "UTF-8");
        out.write(content);
        
        out.flush();   
        out.close();   
           
        String sCurrentLine;   
        String sTotalString;   
        sCurrentLine = "";   
        sTotalString = "";   
        InputStream l_urlStream;   
        l_urlStream = connection.getInputStream();   
        BufferedReader l_reader = new BufferedReader(new InputStreamReader(l_urlStream));   

        while ((sCurrentLine = l_reader.readLine()) != null) {   
            sTotalString += sCurrentLine;   
  
        }   
        return sTotalString;   
    }


    /**
     * 清缓存调用
     */
    public String purgeCache(String url){
        if( ""==url || !this._validateUrl(url)){
            return "please input a valid url.";
        }
        
        String content = "";
        try{

            content = "method=PurgeCache&user="+this._account+"&signature="+this._signature
                        +"&url=" + URLEncoder.encode(url, "UTF-8");         

        }catch(UnsupportedEncodingException ex){
            return "urlencode error.";
        }

        try{
            String getPost = this.sendPost(this.apiHost, content); 
            if(getPost!=""){
                try{
                JSONObject obj = new JSONObject(getPost);
                if(obj.getString("result")=="success"){
                    return "success";
                }else{
                    return obj.getString("msg");
                }
                }catch(JSONException ej){
                    return "network error, try again";
                }
            }else{
                return "network error, try again";
            }
        }catch(IOException e){
            return "network error, try again";
        }

    }

     /**
     * 预加载调用
     */
    public String preload(String url){
        if( ""==url || !this._validateUrl(url)){
            return "please input a valid url.";
        }
        
        String content = "";
        try{

            content = "method=AddPreload&user="+this._account+"&signature="+this._signature
                        +"&url=" + URLEncoder.encode(url, "UTF-8");         

        }catch(UnsupportedEncodingException ex){
            return "urlencode error.";
        }

        try{
            String getPost = this.sendPost(this.apiHost, content); 
            if(getPost!=""){
                try{
                JSONObject obj = new JSONObject(getPost);
                if(obj.getString("result")=="success"){
                    return "success";
                }else{
                    return obj.getString("msg");
                }
                }catch(JSONException ej){
                    return "network error, try again";
                }
            }else{
                return "network error, try again";
            }
        }catch(IOException e){
            return "network error, try again";
        }

    }


    public boolean _validateUrl(String url){
        if( ""==url ){
            return false;
        }
        String regEx = "^(http|https|ftp)\\://(.*)$";
        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(url); 
        return matcher.matches();
    }


}



