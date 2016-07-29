package com.gome.splunk.common.constance;

/**
 * 角色类型
 * @author liubing-ds3
 * Created by Administrator on 2016/6/8.
 */
public enum RoleTypeEnum implements EnumInterface
{

    MANAGE("高管"), OWNER("自营"), COMB("联营"), FINANCE("金融"), OVERSEA("海外购"), VIRTUAL("虚拟");


    private final String id;

    RoleTypeEnum(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name();
    }

    public String getDesc() {
        return null;
    }

    /**
     * 通过Id 获取Name
     * @param id
     * @return
     */
    public static String getNameById(String id){
        for(RoleTypeEnum roleTypeEnum  : RoleTypeEnum.values()){
            if(id.equals(roleTypeEnum.getId())){
                return roleTypeEnum.getName();
            }
        }
        return "";
    }
}
