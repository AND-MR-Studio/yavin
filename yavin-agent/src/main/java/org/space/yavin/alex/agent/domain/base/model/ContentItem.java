package org.space.yavin.alex.agent.domain.base.model;

/**
 * 参数说明：
 * - text: 可选字符串，默认为 None
 * - image: 可选字符串，默认为 None
 * - file: 可选字符串，默认为 None
 *
 * @author yyHuangfu
 * @create 2024/10/17
 */
public class ContentItem {
    private String text;
    private String image;
    private String file;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file= file;
    }
}
