package com.center.dao;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

public class MyDaoGenerator
{
    
    private static final int version = 2;
    
    private static final String defaultPackage = "com.center.mycode.bean";
    
    private static final String javaPackageDao = "com.center.mycode.dao";
    
    public static void main(String args[]) throws Exception
    {
        Schema schema = new Schema(version, defaultPackage);
        schema.setDefaultJavaPackageDao(javaPackageDao);
        schema.enableKeepSectionsByDefault();
        //schema.enableActiveEntitiesByDefault();
        addUser(schema);
//        addNews(schema);
        new DaoGenerator().generateAll(schema, args[0]);
    }
    
    /**
     * @param schema
     */
    private static void addUser(Schema schema)
    {
        Entity user = schema.addEntity("User");
        user.implementsSerializable();
        // user.setTableName("USER");
        user.addIdProperty().primaryKey().autoincrement().index();
        user.addLongProperty("userId").notNull();
        user.addStringProperty("name").notNull();
        user.addStringProperty("sex");
        user.addIntProperty("age");
        user.addStringProperty("core");
//        Entity OrderItem = schema.addEntity("OrderItem");
//        OrderItem.implementsSerializable();
//        OrderItem.addIdProperty();
//        OrderItem.addStringProperty("itemName");
//        Property orderId = OrderItem.addLongProperty("orderId").getProperty();
//        OrderItem.addToOne(user, orderId);
//        ToMany addToMany = user.addToMany(OrderItem, orderId);
//        addToMany.setName("orderItems");
    }
    
    /**
     * @param schema
     */
    private static void addNews(Schema schema)
    {
        Entity user = schema.addEntity("News");
        // user.setTableName("USER");
        user.addIdProperty().primaryKey().index();
        user.addStringProperty("title").notNull();
        user.addStringProperty("des");
        user.addStringProperty("data");
        user.addBooleanProperty("public");
    }
}
