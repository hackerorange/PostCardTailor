package com.soho.postCardTailor.controller;

import com.soho.postCardTailor.business.IPostCardBusiness;
import com.soho.postCardTailor.pojo.postCard.CropInfo;
import com.soho.postCardTailor.pojo.postCard.PostCard;
import org.aspectj.util.FileUtil;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * 明信片裁切相关控制器
 */
@Controller
@RequestMapping("postCardTailor")
public class PostCardController {
    @Resource
    IPostCardBusiness postCardBusiness;

    @RequestMapping("")
    public String getView() {
        return "postCardTailorForm";
    }

    //    @ResponseBody
//    @RequestMapping(value = "nextPostCard", method = RequestMethod.GET)
//    public CropInfo nextPostCard() {
//        CropInfo cropInfo = postCardBusiness.getCropInfo(1);
//        cropInfo.getPostCard().getOrder().getOperator().setPassword("");//清除密码信息;
//        return cropInfo;
//    }
    @ResponseBody
    @RequestMapping(value = "nextPostCard", consumes = "application/json")
    public CropInfo nextPostCard(@RequestBody(required = false) CropInfo cropInfo) {
        if (cropInfo != null) {
            System.out.println(cropInfo);
            postCardBusiness.modifyCropInfo(cropInfo);
        }
//        生成新的裁切信息
        cropInfo = postCardBusiness.getCropInfo();
        if (cropInfo == null) {
            cropInfo = new CropInfo();
        } else {
            cropInfo.getPostCard().getOrder().getOperator().setPassword("");//清除密码信息;
            System.out.println(cropInfo.getCropBox().getSize());
        }
        return cropInfo;
    }

    @RequestMapping("picture/{id}")
    public ResponseEntity<byte[]> getPicture(@PathVariable("id") Integer id) throws IOException {
        HttpHeaders headers = new HttpHeaders();
        System.out.println(id);
        PostCard postCard = postCardBusiness.getById(id);
        postCard.getFilePath();
        try {
            headers.setContentDispositionFormData("attachment", new String(postCard.getFilePath().getBytes("GBK"), "ISO-8859-1"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentType(MediaType.IMAGE_PNG);
        File file = new File(postCard.getFilePath());
        return new ResponseEntity<>(FileUtil.readAsByteArray(file), headers, HttpStatus.OK);
    }

    /**
     * 获取明信片缩略图
     *
     * @param id 缩略图ID
     * @return 缩略图文件
     * @throws IOException 文件读取异常
     */
    @RequestMapping("thumb/{id}")
    public ResponseEntity<byte[]> getThumb(@PathVariable("id") Integer id) throws IOException {
        HttpHeaders headers = new HttpHeaders();
        System.out.println(id);
        PostCard postCard = postCardBusiness.getById(id);
        postCard.getFilePath();
        try {
            headers.setContentDispositionFormData("attachment", new String(postCard.getFilePath().getBytes("GBK"), "ISO-8859-1"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentType(MediaType.IMAGE_PNG);
        File file = new File(postCard.getThumbPath());
        return new ResponseEntity<>(FileUtil.readAsByteArray(file), headers, HttpStatus.OK);
    }
}
