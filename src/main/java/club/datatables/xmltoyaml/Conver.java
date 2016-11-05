package club.datatables.xmltoyaml;

import org.json.JSONObject;
import org.json.XML;
import org.junit.Test;

import java.io.*;
import java.util.List;
import java.util.Scanner;


/**
 * xml转json
 * <p>
 * http://codebeautify.org/xml-to-yaml
 * <p>
 * http://stackoverflow.com/questions/1823264/quickest-way-to-convert-xml-to-json-in-java#
 */
public class Conver {


    public static void main(String[] args) {

        Conver conver = new Conver();

        String rootPath = Conver.class.getClassLoader().getResource("").getPath();
        String resourcesXmlPath = Conver.class.getClassLoader().getResource("xml").getPath();

        conver.common(resourcesXmlPath, rootPath, "option");
        conver.common(resourcesXmlPath, rootPath, "api");
        conver.common(resourcesXmlPath, rootPath, "event");
        conver.common(resourcesXmlPath, rootPath, "type");

    }

    public void common(String resourcesXmlPath, String rootPath, String path) {
        File resourcesOption = new File(resourcesXmlPath + File.separator + path);
        File[] listFiles = resourcesOption.listFiles();
        for (int i = 0; i < listFiles.length; i++) {
            String fileName = listFiles[i].getName().replace("xml", "json");
            String filePath = listFiles[i].getPath();
            String xmlFile = readFile(filePath);
            String jsonFile = xml2json(xmlFile);
            writeFile(rootPath + File.separator + "json" + File.separator + path + File.separator, fileName, jsonFile);
        }
    }

    @Test
    public void testPath() {
        String path = Conver.class.getClassLoader().getResource("").getPath();
        System.out.println(path);
    }

    /**
     * 把内容写到文件里
     *
     * @param fileName
     * @param content
     */
    public void writeFile(String path, String fileName, String content) {
        BufferedWriter writer = null;
        File filePath = new File(path);
        if (!filePath.exists()) {
            filePath.mkdirs();
        }
        try {
            writer = new BufferedWriter(new FileWriter(path + fileName));
            writer.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (writer != null)
                    writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 读取文件
     *
     * @param filePath
     * @return
     */
    public String readFile(String filePath) {
        String content = "";
        try {
            content = new Scanner(new File(filePath)).useDelimiter("\\Z").next();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return content;
    }

    /**
     * xml 转 json
     *
     * @param xml
     * @return
     */
    public String xml2json(String xml) {
        JSONObject xmlJSONObj = XML.toJSONObject(xml);
        String jsonPrettyPrintString = xmlJSONObj.toString();
        return jsonPrettyPrintString;
    }


}
