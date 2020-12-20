package com.erica.summary.controller;

import cn.hutool.core.util.RandomUtil;
import com.erica.summary.util.MD5Util;
import com.erica.summary.util.RandImageUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

/**
 * @author Erica
 * @date 2020/12/20 10:11
 * @description TODO
 */
@Controller
public class ErWeiMaController {

    private static final String BASE_CHECK_CODES = "qwertyuiplkjhgfdsazxcvbnmQWERTYUPLKJHGFDSAZXCVBNM1234567890";

    @RequestMapping("/first")
    public String first(Model model){
        System.out.println(randomImage());
        model.addAttribute("imgUrl",randomImage());
        return "first";
    }

    /**
     * @Description: 后台生成图形验证码
     */
    @ApiOperation("获取验证码")
    @GetMapping(value = "/randomImage")
    public  String randomImage() {
        try {
            String code = RandomUtil.randomString(BASE_CHECK_CODES, 4);
            String base64 = RandImageUtil.generate(code);
            return base64;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
