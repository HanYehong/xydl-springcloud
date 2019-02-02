package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

@AllArgsConstructor//全参数的构造函数
@NoArgsConstructor//空参数的构造函数
@Data//get set 方法
@Accessors(chain=true)//现在流行的链式访问的风格
public class MapImage {
    private Integer id;

    private String mapId;

    private String imageUrl;

    private Date createTime;

    private Date updateTime;
}