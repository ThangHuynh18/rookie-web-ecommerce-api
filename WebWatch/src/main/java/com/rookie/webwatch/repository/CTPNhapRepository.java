package com.rookie.webwatch.repository;

import com.rookie.webwatch.entity.CTPNId;
import com.rookie.webwatch.entity.CTPNhap;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CTPNhapRepository extends JpaRepository<CTPNhap, CTPNId> {
    List<CTPNhap> findAllByCtpnIdNhapId(long nhapId);
    CTPNhap findByCtpnIdNhapId(long nhapId);
}
