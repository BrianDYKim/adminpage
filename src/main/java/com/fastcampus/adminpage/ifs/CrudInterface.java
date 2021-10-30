package com.fastcampus.adminpage.ifs;

import com.fastcampus.adminpage.model.network.Header;

// 사용하는 모든 api에 호환이 되어야하므로 제네릭으로 선언
public interface CrudInterface<Req, Res> {

    Header<Res> create(Header<Req> request);

    Header<Res> read(Long id);

    Header<Res> update(Header<Req> request);

    Header delete(Long id);
}
