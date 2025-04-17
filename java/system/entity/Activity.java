package system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@TableName(value = "activity")
public class Activity implements Serializable {
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;
    @TableField("title")
    private String title;
    @TableField("description")
    private String description;
    @TableField("image")
    private String image;
    @TableField("max_choice")
    private Integer maxChoice;
    @TableField("start_time")
    private LocalDateTime startTime;
    @TableField("end_time")
    private LocalDateTime endTime;
    @TableField("create_by")
    private String createBy;

    @TableField(exist = false)
    private Integer status;

    @TableField(exist = false)
    public List<String> options = new ArrayList<>();


    @TableField(exist = false)
    public List<Option> optionList = new ArrayList<>();

    @TableField(exist = false)
    public boolean voted = false;

    // 总票数
    @TableField(exist = false)
    public Integer total;
}