package com.soho.postCardTailor.pojo.postCard;


import com.soho.postCardTailor.pojo.graphics.Point;
import com.soho.postCardTailor.pojo.graphics.Rectangle;
import com.soho.postCardTailor.pojo.graphics.Size;

public class CropInfo {
    private PostCard postCard;
    private Rectangle cropBox;
    private Rectangle pictureBox;

    public CropInfo() {
    }

    public CropInfo(PostCard postCard) {
        this.postCard = postCard;
        this.cropBox = new Rectangle(new Point(0, 0), new Size(100, 100));
        this.pictureBox = new Rectangle(new Point(0, 0), new Size(100, 100));
        System.out.println("正在检测=========================");
        System.out.println(postCard);
        System.out.println("正在检测=========================");
        if ("B".equalsIgnoreCase(postCard.getOrder().getType())) {
            int width = postCard.getOrder().getSize().getWidth() - 10;
            int height = postCard.getOrder().getSize().getHeight() - 10;
            cropBox.setSize(new Size(width, height));
        } else if ("A".equalsIgnoreCase(postCard.getOrder().getType())) {
            cropBox.setSize(postCard.getOrder().getSize());
        } else if ("C".equalsIgnoreCase(postCard.getOrder().getType())) {
            cropBox.setSize(new Size(100, 100));
        }
    }

    public CropInfo(PostCard postCard, Rectangle cropBox, Rectangle pictureBox) {
        this.postCard = postCard;
        this.cropBox = cropBox;
        this.pictureBox = pictureBox;
    }

    @Override
    public String toString() {
        return "CropInfo{" +
                "postCard=" + postCard +
                ", cropBox=" + cropBox +
                ", pictureBox=" + pictureBox +
                '}';
    }

    public PostCard getPostCard() {
        return postCard;
    }

    public void setPostCard(PostCard postCard) {
        this.postCard = postCard;
    }

    public Rectangle getCropBox() {
        return cropBox;
    }

    public void setCropBox(Rectangle cropBox) {
        this.cropBox = cropBox;
    }

    public Rectangle getPictureBox() {
        return pictureBox;
    }

    public void setPictureBox(Rectangle pictureBox) {
        this.pictureBox = pictureBox;
    }
}
