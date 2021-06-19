package demo4j;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * @author JellyfishMIX
 * @date 2021/5/12 19:27
 */
public class Demo4j {
    public static void main(String[] args) throws IOException, DocumentException {
        // 读取。用的是SAX方式
        SAXReader reader = new SAXReader();
        Document document = reader.read("src/main/resources/book-input.xml");

        // 得到根结点
        Element root = document.getRootElement();

        // 得到所有书的子结点
        List bookList = root.elements("book");

        // 得到第一本书
        Element firstBook = root.element("book");

        // xpath
        // 所有书
        // List bookNodeList = document.selectNodes("book");
        // 第一本书。下标没有0
        // Node firstBookNode = document.selectSingleNode("book[1]");

        // 添加结点
        root.addElement("bookAdded").setText("this is an added book");
        root.addAttribute("title","No.1");

        // 删除。父结点.remove(子结点);
        root.addElement("bookToDelete").setText("you can not see this node");
        Element deletedNode = root.element("bookToDelete");
        root.remove(deletedNode);

        // 修改结点。该结点.setText("");
        root.addElement("bookToModify").setText("before modified");
        Element modifiedNode = root.element("bookToModify");
        modifiedNode.setText("after modified");

        // 子结点个数。root.nodeCount();
        int nodeCount = root.nodeCount();
        System.out.println("number of child nodes: " + nodeCount);

        // 写入。用的是DOM的方式
        OutputFormat format = OutputFormat.createPrettyPrint();
        XMLWriter writer = new XMLWriter(new FileOutputStream("src/main/resources/book-output.xml"), format);
        writer.write(document);
        writer.close();
    }
}
