package com.fastcampus.adminpage.controller.abstracts;

import com.fastcampus.adminpage.ifs.CrudInterface;
import com.fastcampus.adminpage.model.network.Header;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Controller의 추상화를 적용시키는 추상클래스
@Slf4j
public abstract class AbstractCrudController<Req, Res> implements CrudInterface<Req, Res> {

    protected CrudInterface<Req, Res> baseService; // service들은 모두 CurdInterface<Req, Res>를 상속받기 때문에 up-casting으로 선언하여 나중에 초기화를 진행

    @Override
    @PostMapping("")
    public Header<Res> create(@RequestBody Header<Req> request) {
        return baseService.create(request);
    }

    @Override
    @GetMapping("{id}")
    public Header<Res> read(@PathVariable Long id) {
        log.info("read id : {}", id);
        return baseService.read(id);
    }

    @Override
    @PutMapping("")
    public Header<Res> update(@RequestBody Header<Req> request) {
        return baseService.update(request);
    }

    @Override
    @DeleteMapping("{id}")
    public Header delete(@PathVariable Long id) {
        log.info("delete id : {}", id);
        return baseService.delete(id);
    }

    @Override
    @GetMapping("")
    public Header<List<Res>> search(Pageable pageable) {
        log.info("{}", pageable);
        return baseService.search(pageable);
    }
}
