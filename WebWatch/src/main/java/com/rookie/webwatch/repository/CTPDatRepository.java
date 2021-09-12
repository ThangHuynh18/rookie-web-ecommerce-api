package com.rookie.webwatch.repository;

import com.rookie.webwatch.entity.CTPDHId;
import com.rookie.webwatch.entity.CTPDat;
import com.rookie.webwatch.entity.PhieuDat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CTPDatRepository extends JpaRepository<CTPDat, CTPDHId> {
//    CTPDat findAllByDatIdAndProductId(long datId, long productId);
    List<CTPDat> findAllByCtpdhIdDatId(long ctpdhId_datId);
    CTPDat findByCtpdhIdDatId(long datId);
    List<CTPDat> findCTPDatByPhieuDat(PhieuDat phieuDat);
}
