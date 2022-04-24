package 爬虫.必应壁纸;

import cn.hutool.core.util.ReUtil;
import cn.hutool.http.HttpUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
//必应壁纸网址
//https://bing.ioliu.cn/
public class SpiderMain {
    // 必应壁纸网址
    public static String URL = "https://bing.ioliu.cn";
    // 必应壁纸页面网址
    public static String PREFIX_URL = "https://bing.ioliu.cn/?p=";
    // 存放路径
    public static String DOWNLOAD_PATH = "C:\\Users\\YFAN\\Desktop\\downLoad";
    // 超时时间
    public static int TIMEOUT = 30000;
    // 休眠时间
    public static int SLEEP_TIME = 2000;

    public static void main(String[] args) throws Exception {
        // 线程池
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(
                5, 10, 3L,
                TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());
        // 任务执行返回结果
        List<Future<ResultVO>> futureList = new ArrayList<>();
        // 图片链接结果
        List<String> imageUrlList = new ArrayList<>();
        // 提交任务
        submitTask(threadPool, futureList, 1);
        // 遍历结果
        foreachResult(futureList, imageUrlList);
        // 下载文件
        downLoad(threadPool, imageUrlList, DOWNLOAD_PATH);
        // 监控线程池
        monitor(threadPool);
    }

    /*
     * 监控
     */
    public static void monitor(ThreadPoolExecutor threadPool) throws Exception {
        // count次遍历中线程池活动线程为零时关闭线程池
        int count = 3;
        StringBuilder sb = new StringBuilder();
        while (true) {
            Thread.sleep(3000);
            sb.append("========================================\n");
            sb.append("活动线程数量：" + threadPool.getActiveCount()+"\n");
            sb.append("当前线程数量：" + threadPool.getPoolSize()+"\n");
            sb.append("任务总共数量：" + threadPool.getTaskCount()+"\n");
            sb.append("任务完成数量：" + threadPool.getCompletedTaskCount()+"\n");
            sb.append("========================================\n");
            System.out.println(sb.toString());
            sb.setLength(0);
            if (threadPool.getActiveCount() == 0) {
                count--;
            }
            if (threadPool.isShutdown()) {
                break;
            }
            if (count <= 0) {
                System.out.println("========关===闭===中========");
                threadPool.shutdown();
            }
        }
    }

    /*
     * 下载
     */
    public static void downLoad(ThreadPoolExecutor threadPool, List<String> imageUrlList, String downLoadPath) {
        imageUrlList.forEach(url -> {
            threadPool.submit(() -> {
                try {
                    //休眠
                    Thread.sleep(SLEEP_TIME);
                    String content = HttpUtil.get(url, TIMEOUT);
                    String imageUrl = ReUtil.get("data-progressive=\"(.*?)\"", content, 1);
                    System.out.println(imageUrl);
                    long l = HttpUtil.downloadFile(imageUrl, new File(downLoadPath), TIMEOUT);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            });
        });
    }

    /*
     * 添加任务
     * pages：遍历页数
     */
    public static void submitTask(ThreadPoolExecutor threadPool, List<Future<ResultVO>> futureList, int pages) {
        for (int i = 1; i <= pages; i++) {
            int finalI = i;
            futureList.add(threadPool.submit(() -> {
                ResultVO resultVO = new ResultVO();
                try {
                    //休眠
                    Thread.sleep(SLEEP_TIME);
                    resultVO.setUrl(PREFIX_URL + finalI);
                    String content = HttpUtil.get(resultVO.getUrl(), TIMEOUT);
                    List<String> urlList = ReUtil.findAll("<a class=\"mark\" href=\"(.*?)\">", content, 1);
                    resultVO.setList(urlList);
                    return resultVO;
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                return resultVO;
            }));
        }
    }

    /*
     * 遍历结果
     */
    public static void foreachResult(List<Future<ResultVO>> futureList, List<String> imageUrlList) {
        System.out.println("futureList的数量为:" + futureList.size());
        futureList.forEach(future -> {
            try {
                // 获取线程执行返回值
                ResultVO resultVO = future.get();
                System.out.println("当前爬取地址为：" + resultVO.getUrl());
                if (!resultVO.getList().isEmpty()) {
                    resultVO.getList().forEach(url -> {
                        String fullUrl = URL + url;
                        System.out.println(fullUrl);
                        imageUrlList.add(fullUrl);
                    });
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        });
    }
}
