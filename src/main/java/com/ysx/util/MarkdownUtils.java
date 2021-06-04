package com.ysx.util;

import org.commonmark.Extension;
import org.commonmark.ext.gfm.tables.TableBlock;
import org.commonmark.ext.gfm.tables.TablesExtension;
import org.commonmark.ext.heading.anchor.HeadingAnchorExtension;
import org.commonmark.node.Heading;
import org.commonmark.node.Link;
import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.AttributeProvider;
import org.commonmark.renderer.html.AttributeProviderContext;
import org.commonmark.renderer.html.AttributeProviderFactory;
import org.commonmark.renderer.html.HtmlRenderer;

import java.util.*;

public class MarkdownUtils {

    // TODO: 6/4/21 markdown 转为html
    public static String markdownToHtml(String markdown) {
        Parser parser = Parser.builder().build();
        Node document = parser.parse(markdown);
        HtmlRenderer renderer = HtmlRenderer.builder().build();
        return renderer.render(document);
    }

    // TODO: 6/4/21 增加markdown转为html标签
    public static String markdownToHtmlExtensions(String markdown) {
        Set<Extension> headingAnchorExtensions = Collections.singleton(HeadingAnchorExtension.create());
        List<Extension> tableExtesion = Arrays.asList(TablesExtension.create());
        Parser parser = Parser.builder()
                .extensions(tableExtesion).build();
        Node document = parser.parse(markdown);
        HtmlRenderer renderer = HtmlRenderer.builder()
                .extensions(headingAnchorExtensions)
                .extensions(tableExtesion)
                .attributeProviderFactory(new AttributeProviderFactory() {
                    @Override
                    public AttributeProvider create(AttributeProviderContext attributeProviderContext) {
                        return new CustomAttributeProvider();
                    }
                }).build();
        return renderer.render(document);
    }
    static class CustomAttributeProvider implements  AttributeProvider {

        @Override
        public void setAttributes(Node node, String s, Map<String, String> map) {
            if(node instanceof Link) {
                map.put("target","_blank");
            }
            if(node instanceof TableBlock) {
                map.put("class","ui celled table");
            }
        }
    }

    // TODO: 6/4/21 test some rules
    public static void main(String[] args) {
        String table = "|hello|hi|hahahh|\n" +
                "|----|---------|------|\n " +
                "nisanfklja｜akdjfklaj｜kdjafkl\n " +
                "sklajdflkjas|sdjafklja|djfkla|\n" +
                " \n";
        String a = "[ajsldkfj](http://www.djfkla.cn)";
        System.out.println(markdownToHtmlExtensions(a));
        System.out.println(markdownToHtmlExtensions(table));
    }
}
