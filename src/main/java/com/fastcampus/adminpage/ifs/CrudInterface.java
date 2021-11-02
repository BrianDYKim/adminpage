package com.fastcampus.adminpage.ifs;

import com.fastcampus.adminpage.model.network.Header;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;

import java.util.List;

// 사용하는 모든 api에 호환이 되어야하므로 제네릭으로 선언
public interface CrudInterface<Req, Res> {

    Header<Res> create(Header<Req> request);

    Header<Res> read(Long id);

    Header<Res> update(Header<Req> request);

    Header delete(Long id);

    // 페이징 처리하는 메소드
    Header<List<Res>> search(@PageableDefault(sort = "id", size = 15, direction = Sort.Direction.ASC) Pageable pageable);
}
