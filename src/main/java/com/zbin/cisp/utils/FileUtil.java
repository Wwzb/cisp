package com.zbin.cisp.utils;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import java.io.File;
import java.util.UUID;

/**
 * Created by Zbin on 2019-02-18
 */

public class FileUtil {

  private static final String accessKey = "pl7KvcAWGCe1eI2RPKKyrp7zxU_o8PM6rGAb7SG7";

  private static final String secretKey = "aQtmNi3Zvo_qDJ-8tBQ1tObNxJ-M95Bkr2ndIpDK";

  private static final String prefixUrl = "http://cdn.iwzb.top/";

  private static final String bucket = "cisp";

  public static String upload(File file) {
    try {
      UploadManager uploadManager = getUploadManager();

      String token = getToken();
      Response response = uploadManager.put(file.getAbsolutePath(), newName(file.getName()), token);

      //解析上传成功的结果
      DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
      return prefixUrl + putRet.key;
    } catch (QiniuException e) {
      e.printStackTrace();
      return null;
    }
  }

  public static boolean delete(String key) {
    try {
      BucketManager bkm = getBucketManager();
      bkm.delete(bucket, key);
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  private static UploadManager getUploadManager() {
    Configuration cfg = new Configuration(Zone.zone0());
    return new UploadManager(cfg);
  }

  private static BucketManager getBucketManager() {
    Configuration cfg = new Configuration(Zone.zone0());
    Auth auth = Auth.create(accessKey, secretKey);
    return new BucketManager(auth, cfg);
  }

  private static String newName(String oldName) {
    String[] datas = oldName.split("\\.");
    String type = datas[datas.length - 1];
    return UUID.randomUUID().toString() + "." + type;
  }

  private static String getToken() {
    Auth auth = Auth.create(accessKey, secretKey);
    return auth.uploadToken(bucket);
  }
}
