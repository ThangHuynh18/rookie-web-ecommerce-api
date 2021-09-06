package com.rookie.webwatch.repository;

import com.rookie.webwatch.entity.CTPDHId;
import com.rookie.webwatch.entity.CTPDat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CTPDatRepository extends JpaRepository<CTPDat, CTPDHId> {
//    CTPDat findAllByDatIdAndProductId(long datId, long productId);
}
