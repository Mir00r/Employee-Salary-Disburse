package com.mir00r.salarydisburse.domains.fileuploads.repositories;

import com.mir00r.salarydisburse.domains.fileuploads.models.entities.UploadProperties;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileUploadRepository extends JpaRepository<UploadProperties, Long> {
    UploadProperties findByUuid(String uuid);
}
