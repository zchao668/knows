package com.work.knows.mapper;

import com.work.knows.domain.Test;

import java.util.List;
//  对于mapper接口，可以在这里加入@Mapper @Repository  也可以在APP中加入下面注解
//  @MapperScan("com.work.knows.mapper")
public interface TestMapper {

    public List<Test> list();
}
