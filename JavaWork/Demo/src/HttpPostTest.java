import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;

public class HttpPostTest {
    void testPost(String urlStr,String content,int msgId) {
        try {
            URL url = new URL(urlStr);
            URLConnection con = url.openConnection();
            con.setDoOutput(true);
            con.setRequestProperty("Pragma:", "no-cache");
            con.setRequestProperty("Cache-Control", "no-cache");
            con.setRequestProperty("Content-Type", "text/xml");

            OutputStreamWriter out = new OutputStreamWriter(con.getOutputStream());    
            String xmlInfo = getXmlInfo(content,msgId);
            System.out.println("urlStr=" + urlStr);
            System.out.println("xmlInfo=" + xmlInfo);
            out.write(new String(xmlInfo.getBytes("UTF-8")));
            out.flush();
            out.close();
            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String line = "";
            for (line = br.readLine(); line != null; line = br.readLine()) {
                System.out.println(line);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getXmlInfo(String content,int msgId) {
        StringBuilder sb = new StringBuilder();
        Date date = new Date();
        Long createTime = date.getTime()/100;
        sb.append("<xml>");
        sb.append("<ToUserName><![CDATA[gh_5a402ba88fa0]]></ToUserName>");
        sb.append("<FromUserName><![CDATA[oBx4Dt37J4GSXlt32V4zGf-EDQQM]]></FromUserName>");
        sb.append("<CreateTime>" + createTime + "</CreateTime>");
        sb.append("<MsgType><![CDATA[text]]></MsgType>");
        sb.append("<Content><![CDATA[" + content + "]]></Content>");
        sb.append("<MsgId>"+msgId+"</MsgId>");
        sb.append("</xml>");
        return sb.toString();
    }
}