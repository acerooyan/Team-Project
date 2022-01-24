package com.example.emrestserver.controller;


import com.example.emrestserver.entity.PersonalDocument;
import com.example.emrestserver.service.AwsService;
import com.example.emrestserver.service.PersonalDocumentService;
import com.example.emrestserver.service.UtilService;
import org.springframework.beans.factory.annotation.Autowired;

        import org.springframework.core.io.ByteArrayResource;
        import org.springframework.http.HttpStatus;
        import org.springframework.http.ResponseEntity;
        import org.springframework.web.bind.annotation.*;
        import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/jwt/file")
public class AwsController {

    @Autowired
    private AwsService service;

    @Autowired
    private PersonalDocumentService personalDocumentService;

    @Autowired
    private UtilService utilService;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestPart(value = "file") MultipartFile file, @RequestPart(value = "employeeId")Integer id) {
        String fileName = service.uploadFile(file);
        PersonalDocument personalDocument =  personalDocumentService.buildDocument(fileName,utilService.getEmployeeById(id));
        return ResponseEntity.ok().body(fileName);
    }


    @GetMapping("/download/{fileName}")
    public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable String fileName) {
        byte[] data = service.downloadFile(fileName);
        ByteArrayResource resource = new ByteArrayResource(data);
        return ResponseEntity
                .ok()
                .contentLength(data.length)
                .header("Content-type", "application/octet-stream")
                .header("Content-disposition", "attachment; filename=\"" + fileName + "\"")
                .body(resource);
    }

    @DeleteMapping("/delete/{fileName}")
    public ResponseEntity<String> deleteFile(String fileName) {
        return new ResponseEntity<>(service.deleteFile(fileName), HttpStatus.OK);
    }
}

