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
    //oBx4Dt37J4GSXlt32V4zGf-EDQQM
    private String getXmlInfo(String content,int msgId) {
        StringBuilder sb = new StringBuilder();
        Date date = new Date();
        Long createTime = date.getTime()/100;
        sb.append("<xml>\n");
        sb.append("\t<ToUserName><![CDATA[gh_5a402ba88fa0]]></ToUserName>\n");
        sb.append("\t<FromUserName><![CDATA[aa]]></FromUserName>\n");
        sb.append("\t<CreateTime>" + createTime + "</CreateTime>\n");
        sb.append("\t<MsgType><![CDATA[text]]></MsgType>\n");
        sb.append("\t<Content><![CDATA[" + content + "]]></Content>\n");
        sb.append("\t<MsgId>"+msgId+"</MsgId>\n");
        sb.append("</xml>");
        return sb.toString();
    }
}