package com.ychp.mybatis.builder.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.ychp.code.builder.Builder;
import com.ychp.mybatis.builder.dto.MybatisColumnDto;
import com.ychp.mybatis.builder.utils.MybatisUtils;

import java.sql.*;
import java.util.List;
import java.util.Map;


/**
 * Desc:
 * Author: <a href="ychp@terminus.io">应程鹏</a>
 * Date: 17/2/10
 */
public class MybatisBuilder extends Builder {

    protected static Map<String, Object> generalTemplateParamMap(String tableName, String basePackage) {
        Map<String, Object> templateParamMap = Maps.newHashMap();
        templateParamMap.put("tableName", tableName);
        templateParamMap.put("modelName", MybatisUtils.camelName(tableName));
        templateParamMap.put("modelPackage", basePackage + ".model");
        templateParamMap.put("package", basePackage);

        String host = "127.0.0.1";
        String port = "2206";
        String database = "blog_new";
        String username = "root";
        String password = "anywhere";

        String url="jdbc:mysql://" + host + ":" + port + "/" + database + "?user=" + username + "&password=" + password;
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            connection = DriverManager.getConnection(url);
            DatabaseMetaData dbMetaData = connection.getMetaData();

            MybatisColumnDto columnDto;
            String columnName;
            String columnType;
            int datasize;

            //主键
            MybatisColumnDto primaryColumnDto = null;
            String catalog = connection.getCatalog();
            ResultSet columnRs = dbMetaData.getPrimaryKeys(catalog, null, tableName);
            while(columnRs.next()){
                columnName = columnRs.getString("COLUMN_NAME");
                primaryColumnDto = new MybatisColumnDto();
                primaryColumnDto.setSqlColumn(columnName);
                primaryColumnDto.setJavaColumn(MybatisUtils.camelName(columnName));
                primaryColumnDto.setJavaXmlColumn("{" + MybatisUtils.camelName(columnName) + "}");
                primaryColumnDto.setJavaXmlColumn(MybatisUtils.camelName(columnName));
            }

            List<MybatisColumnDto> columns = Lists.newArrayList();
            ResultSet colRet = dbMetaData.getColumns(null,"%", tableName,"%");
            while(colRet.next()) {
                columnName = colRet.getString("COLUMN_NAME");
                columnType = colRet.getString("TYPE_NAME");
                datasize = colRet.getInt("COLUMN_SIZE");
                if(primaryColumnDto != null && primaryColumnDto.getSqlColumn().equals(columnName)){
                    primaryColumnDto.setJavaType(MybatisUtils.getJavaTypeByDBType(columnType, datasize));
                    primaryColumnDto.setModelColumn(false);
                    templateParamMap.put("primaryColumn", primaryColumnDto);
                    continue;
                }

                columnDto = new MybatisColumnDto();
                columnDto.setSqlColumn(columnName);
                columnDto.setJavaColumn(MybatisUtils.camelName(columnName));
                columnDto.setJavaXmlColumn("{" + MybatisUtils.camelName(columnName) + "}");
                columnDto.setJavaXmlColumn(MybatisUtils.camelName(columnName));
                columnDto.setJavaType(MybatisUtils.getJavaTypeByDBType(columnType, datasize));
                columnDto.setComment(colRet.getString("REMARKS"));

                columns.add(columnDto);
            }

            templateParamMap.put("columns", columns);


        } catch (SQLException | IllegalAccessException | InstantiationException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
          if(connection != null){
              try {
                  connection.close();
              } catch (SQLException e) {
                  e.printStackTrace();
              }
          }
        }
        return templateParamMap;
    }

    public static void main(String[] args){
        String templatePath = "mybatis";
        String outPath = "mybatis-out";
        String tableName = "sky_user";
        String basePackage = "com.ychp.user";
        Builder builder = new MybatisBuilder();
        builder.build(templatePath, outPath, generalTemplateParamMap(tableName, basePackage), true, true);
    }
}
