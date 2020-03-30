package com.imooc.zhangxiaoxi.alllearning.mapper;

import com.imooc.zhangxiaoxi.alllearning.domain.entity.UserDO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.List;

/**
 * 类名称：UserMapperTest
 * ********************************
 * <p>
 * 类描述：
 *
 * @author
 * @date 下午1:52
 */
@SpringBootTest
@Slf4j
public class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void find() {
        HashMap<String, Object> param = new HashMap<>();
        param.put("username", "username1");

        List<UserDO> userDOList = userMapper.selectByMap(param);

        log.info("{}", userDOList);
    }

}
