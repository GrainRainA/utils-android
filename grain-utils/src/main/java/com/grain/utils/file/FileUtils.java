package com.grain.utils.file;

import com.grain.utils.InitUtilsModule;
import com.grain.utils.hint.L;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.FileCallback;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Progress;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.PostRequest;
import com.lzy.okgo.request.base.Request;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @anthor GrainRain
 * @funcation 文件操作工具类
 * @date 2021/5/17
 */
public class FileUtils {

    /**
     * 获取路径下的所有文件名
     * @param path
     * @return
     */
    public static List<String> getFileAllName(String path) {
        File file = new File(path);
        File[] files = file.listFiles();
        if(files == null) {
            return null;
        }

        List<String> fileNmaeList = new ArrayList<>();
        for (int i = 0; i < files.length; i++) {
            fileNmaeList.add(files[i].getAbsolutePath());
        }

        return fileNmaeList;
    }

    /**
     * 文件下载
     * @param url
     * @param path
     * @param fileName
     * @param callback
     */
    public static void dowloadFile(String url, String path, String fileName, final FileCallback callback) {
        OkGo.<File>get(url).tag(InitUtilsModule.getActivity()).execute(
                new FileCallback(path, fileName) {

                    @Override
                    public void onStart(Request<File, ? extends Request> request) {
                        super.onStart(request);
                        if(callback != null) callback.onStart(request);
                    }

                    @Override
                    public void downloadProgress(Progress progress) {
                        super.downloadProgress(progress);
                        // 进度 progress.fraction
                        if(callback != null) callback.downloadProgress(progress);
                    }

                    @Override
                    public void onSuccess(Response<File> response) {
                        if(callback != null) callback.onSuccess(response);
                    }

                    @Override
                    public void onError(Response<File> response) {
                        super.onError(response);
                        if(callback != null) callback.onError(response);
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                        if(callback != null) callback.onFinish();
                    }
                });
    }

    /**
     * 文件上传
     * @param url
     * @param path
     * @param callback
     */
    public static void uploadFile(String url, String path, final StringCallback callback) {
        PostRequest<String> post = OkGo.<String>post(url);
        post.tag(InitUtilsModule.getActivity());
        //添加参数
        post.params("file", new File(path));
        post.isMultipart(true);
        post.execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                if(callback != null) callback.onSuccess(response);
            }

            @Override
            public void onError(Response<String> response) {
                if(callback != null) callback.onError(response);
            }
        });
    }
}
