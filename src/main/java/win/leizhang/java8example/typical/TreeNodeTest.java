package win.leizhang.java8example.typical;

import com.alibaba.fastjson.JSON;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * [Java 8 中的 Stream 轻松遍历树形结构](https://mp.weixin.qq.com/s/3No-NtDAu6l9TzpB4mKCvw)Java技术栈 2022-03-08,[2](https://blog.csdn.net/qq_19244927/article/details/106481777/)
 * Created by zhanglei863 on 2022/3/16.
 */
public class TreeNodeTest {
    @Test
    public void testTree() {
        // 造数据
        List<Menu> menus = Arrays.asList(
                new Menu(1, "根节点0", 0),
                new Menu(2, "子节点1", 1),
                new Menu(3, "子节点1.1", 2),
                new Menu(4, "子节点1.2", 2),
                new Menu(5, "子节点1.3", 2),
                new Menu(6, "子节点2", 1),
                new Menu(7, "子节点2.1", 6),
                new Menu(8, "子节点2.2", 6),
                new Menu(9, "子节点2.2.1", 8),
                new Menu(10, "子节点2.2,2", 8),
                new Menu(11, "子节点3", 1),
                new Menu(12, "子节点3.1", 11)
        );

        // 获取父节点
        List<Menu> ll = menus.stream()
                .filter(o -> o.getParentId() == 0)
                .map(o -> {
                    o.setChildList(getChildrens(o, menus));
                    return o;
                })
                .collect(Collectors.toList());

        System.out.printf("转json输出结果\n%s\n", JSON.toJSON(ll));
    }

    /**
     * 递归查询子节点
     *
     * @param root 根节点
     * @param all  所有节点
     * @return 根节点信息
     */
    private List<Menu> getChildrens(Menu root, List<Menu> all) {
        // peek跟上面map用法很像

        // FIXME 建议考虑使用排序加迭代器剔除试试，可以提高效率和节省空间
        // TODO 不太建议这种写法，o(2n)复杂度硬生生写成o(n^2)
        return all.stream()
                .filter(o -> Objects.equals(o.getParentId(), root.getId()))
                .peek(o -> o.setChildList(getChildrens(o, all)))
                .collect(Collectors.toList());
    }
}

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
class Menu {
    private Integer id;
    // 名称
    private String name;
    // 父节点，跟节点为0
    private Integer parentId;
    // 子节点信息
    private List<Menu> childList;

    public Menu(Integer id, String name, Integer parentId) {
        this.id = id;
        this.name = name;
        this.parentId = parentId;
    }
}