package com.lin.common.plugin;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.internal.DefaultCommentGenerator;
import org.springframework.util.StringUtils;

public class CommentGenerator extends DefaultCommentGenerator {
    @Override
    public void addFieldComment(Field field, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn) {
        super.addFieldComment(field, introspectedTable, introspectedColumn);
        if(!StringUtils.isEmpty(introspectedColumn.getRemarks())){
            field.addJavaDocLine("/**");
            field.addJavaDocLine("*" + introspectedColumn.getRemarks());
            addJavadocTag(field,false);
            field.addJavaDocLine("*/");
        }
    }
}
