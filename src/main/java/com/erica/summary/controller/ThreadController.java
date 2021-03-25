package com.erica.summary.controller;

import com.erica.summary.service.ThreadService;

/**
 * @author Erica
 * @date 2021/3/25 23:40
 * @description 线程练习
 */
public class ThreadController {



    public static void main(String[] args) throws InterruptedException {
        ThreadService threadService = new ThreadService();
        threadService.threadAlter();
    }

}
