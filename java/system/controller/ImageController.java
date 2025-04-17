package system.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import system.common.Result;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@RestController
@RequestMapping("/api/image")
@RequiredArgsConstructor
public class ImageController {

    private static final String UPLOAD_DIR = "src/main/resources/static/image/";

    @PostMapping("/upload")
    public Result uploadImage(@RequestParam("file") MultipartFile file) {
        try {
            // 确保上传目录存在
            File uploadDir = new File(UPLOAD_DIR);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }

            // 获取原始文件名和扩展名
            String originalFilename = file.getOriginalFilename();
            String fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));

            // 生成随机文件名
            String randomFilename = UUID.randomUUID() + fileExtension;

            // 构建完整文件路径
            Path filePath = Paths.get(UPLOAD_DIR + randomFilename);

            // 保存文件
            Files.write(filePath, file.getBytes());

            return Result.success().data(randomFilename);
        } catch (IOException e) {
            e.printStackTrace();
            return Result.fail();
        }
    }
}
