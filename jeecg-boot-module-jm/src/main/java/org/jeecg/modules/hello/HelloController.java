package org.jeecg.modules.hello;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.api.vo.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author songhx
 * @create 2020-02-26 13:14
 */
@Slf4j
@Api(tags="新建module--jm")
@RestController
@RequestMapping("/hello")
public class HelloController  {
    @ApiOperation("测试hello方法")
    @GetMapping(value="/")
    public Result<String> hello(){
        Result<String> result = new Result<String>();
        result.setResult("hello word!");
        result.setSuccess(true);
        return result;
    }
}
