package com.qchery.generate.hibernate;

import java.util.List;

import com.qchery.generate.Item;
import com.qchery.generate.ObjectDescriptor;
import com.qchery.generate.XMLUtils;
import com.qchery.generate.builder.FileBuilder;
import com.qchery.generate.hibernate.model.Clazz;
import com.qchery.generate.hibernate.model.Column;
import com.qchery.generate.hibernate.model.HibernateMapping;
import com.qchery.generate.hibernate.model.Property;

/**
 * Hibernate配置文件建造器
 * @author chinrui1016@163.com
 * @date 2016年5月15日 - 下午8:47:06
 */
public class HibernateBuilder implements FileBuilder {

    /**
     * <hibernate-mapping package="com.hmg.rmadmin.admin.bean">
     *     <class name="AdminLoginRecord" table="RM_ADMIN_LOGIN_RECORD">
     *         <id name="id" type="long">
     *             <column name="id" not-null="true" unique="true" />
     *         </id>
     *         <property name="userId" type="string">
     *             <column name="user_id" length="60" />
     *         </property>
     *     </class>
     * </hibernate-mapping>
     */
    @Override
    public String getContent(ObjectDescriptor descriptor) {
        HibernateMapping mapping = new HibernateMapping(descriptor.getPackageName());
        Clazz clazz = new Clazz(descriptor.getClassName(), descriptor.getTableName());
        List<Item> items = descriptor.getItems();
        for (Item item : items) {
            Property property = new Property(item.getFieldName(), item.getType());
            Column column = new Column(item.getColumnName());
            column.setNotNull(item.isNotNull());
            if (item.isPK()) {
                column.setUnique(true);
                clazz.setId(property);
            } else {
                column.setLength(item.getLength());
                clazz.add(property);
            }
            property.setColumn(column);
        }
        mapping.setClazz(clazz);
        return XMLUtils.toXML(mapping);
    }

    @Override
    public String getFileName(String className) {
        return className + ".hbm.xml";
    }

}
