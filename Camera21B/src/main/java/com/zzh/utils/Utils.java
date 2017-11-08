package com.zzh.utils;

import android.os.Environment;

import com.zzh.zlibs.utils.ZUtils;

import java.io.File;

/**
 * Created by user on 2017/11/8.
 *
 * @date: 2017/11/8
 * @email: zzh_hz@126.com
 * @QQ: 1299234582
 * @author: zzh
 * @description:
 */
public class Utils extends ZUtils {

    /**
     * @return
     */
    public String getPublicDCIMDirectory() {
        return getSDCardDirectory(Environment.DIRECTORY_PICTURES);
    }

    /**
     * 应用程序使用的相册存储路径
     *
     * @return
     */
    public String getAppDCIMDirectory() {
        return getPublicDCIMDirectory() + File.separator + "Camera21b";
    }

}
