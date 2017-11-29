package com.hooray.common.sqldialect;

import org.hibernate.dialect.MySQL5InnoDBDialect;

/**
 * hibernate自动建表的编码格式
 *
 * @author yangfeng
 * @create 2017-11-22 11:29
 * Email: yangfeng@hooray.cn
 **/
public class CustomMySQL5Dialect extends MySQL5InnoDBDialect{

    @Override
    public String getTableTypeString() {
        return "ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8";
    }
}
