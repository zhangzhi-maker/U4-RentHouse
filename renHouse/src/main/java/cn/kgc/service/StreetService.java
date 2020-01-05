package cn.kgc.service;

import cn.kgc.domain.Street;

import java.util.List;

public interface StreetService {
    List<Street> selectByDistrictId(Integer did);
}
