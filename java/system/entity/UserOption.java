package system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName(value ="user_option")
public class UserOption implements Serializable {
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;
    @TableField("user_id")
    private String userId;
    @TableField("option_id")
    private String optionId;
    @TableField("activity_id")
    private String activityId;
    @TableField("selected")
    private Boolean selected;
}