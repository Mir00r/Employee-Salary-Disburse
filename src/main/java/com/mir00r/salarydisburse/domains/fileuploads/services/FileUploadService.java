package com.mir00r.salarydisburse.domains.fileuploads.services;


import com.mir00r.salarydisburse.domains.fileuploads.models.entities.UploadProperties;
import com.mir00r.salarydisburse.exceptions.notfound.NotFoundException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileUploadService {
    UploadProperties uploadFile(MultipartFile file, String namespace, String uniqueProperty, int scalingHeight) throws IOException;

    UploadProperties find(Long imageId) throws NotFoundException;

    UploadProperties findByUuid(String uuid) throws NotFoundException;
}
