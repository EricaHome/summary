package com.erica.summary.controller;

import org.apache.poi.ss.formula.functions.T;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author Erica
 * @date 2020/12/20 17:03
 * @description TODO
 */
public class ExcelController {

    /**
     * 通过excel导入数据
     *
     * @param request
     * @param response
     * @return
     */
    public String importExcel(HttpServletRequest request, HttpServletResponse response, Class<T> clazz) {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
        for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
            MultipartFile file = entity.getValue();// 获取上传文件对象
            ImportParams params = new ImportParams();
            params.setTitleRows(2);
            params.setHeadRows(1);
            params.setNeedSave(true);
            try {
                List<T> list = ExcelImportUtil.importExcel(file.getInputStream(), clazz, params);
                long start = System.currentTimeMillis();
                // service.saveBatch(list);
                System.out.println("消耗时间" + (System.currentTimeMillis() - start) + "毫秒");
                return "文件导入成功！数据行数：" + list.size();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    file.getInputStream().close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return "文件导入失败！";
    }

}
