package com.qchery.kada.descriptor.java;

import java.util.ArrayList;
import java.util.List;

public class ClassInfo implements IClassInfo {

    /**
     * 类型声明
     */
    private TypeInfo typeInfo;
    /**
     * 字段
     */
    private List<FieldInfo> fieldInfos;

    public static ClassInfo of(String packageName, String typeName) {
        return of(new TypeInfo(packageName, typeName));
    }

    public static ClassInfo of(TypeInfo typeInfo) {
        return new ClassInfo(typeInfo);
    }

    public static ClassInfo of(Class<?> clazz) {
        String packageName = clazz.getPackage().getName();
        String simpleName = clazz.getSimpleName();
        return of(new TypeInfo(packageName, simpleName));
    }

    protected ClassInfo(TypeInfo typeInfo) {
        this.typeInfo = typeInfo;
        // 当类型不为基础类型时，分配字段列表
        if (!typeInfo.isPrimitive()) {
            fieldInfos = new ArrayList<>();
        }
    }

    @Override
    public String getPackageName() {
        return typeInfo.getPackageName();
    }

    @Override
    public String getClassName() {
        return typeInfo.getTypeName();
    }

    @Override
    public List<FieldInfo> getFieldInfos() {
        if (typeInfo.isPrimitive()) {
            throw new RuntimeException("基础类型无字段描述");
        }
        return fieldInfos;
    }

    @Override
    public void addFieldInfo(FieldInfo fieldInfo) {
        if (typeInfo.isPrimitive()) {
            throw new RuntimeException("基础类型无字段描述");
        }
        this.fieldInfos.add(fieldInfo);
    }

    @Override
    public boolean isPrimitive() {
        return typeInfo.isPrimitive();
    }

}