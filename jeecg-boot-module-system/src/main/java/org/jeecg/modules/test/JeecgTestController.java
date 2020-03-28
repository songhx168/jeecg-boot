package org.jeecg.modules.test;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.api.vo.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author songhx
 * @create 2020-02-26 9:40
 */
@RestController
@RequestMapping("/test/jeecgTest")
@Slf4j
@Api(tags="新建测试swagger")
public class JeecgTestController {
    @ApiOperation("测试hello方法")
    @GetMapping(value = "/hello")
    public Result<String> hello() {
        Result<String> result = new Result<String>();
        result.setResult("Hello World!");
        result.setSuccess(true);
        return result;
    }
}
