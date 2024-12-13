package ru.hogwarts.school.service;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.AvatarRepository;

import java.io.IOException;

@Service
@Transactional
public class AvatarServiceImpl implements AvatarService {

    @Value("${avatar.dir.path}")
    private String avatarImageDir;

    private final StudentService studentService;
    private final AvatarRepository avatarRepository;

    public AvatarServiceImpl(StudentService studentService, AvatarRepository avatarRepository) {
        this.studentService = studentService;
        this.avatarRepository = avatarRepository;
    }

    @Override
    public void uploadAvatar(Long student_id, MultipartFile file) throws IOException {
        Student student = studentService.getStudentById(student_id);
        Path filePath = Path.of(avatarImageDir, student_id + "." + getExtension(file.getOriginalFilename()));
        Files.createDirectories(filePath.getParent());

    }

    private String getExtension(String originalFilename) {
        return originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
    }

    @Override
    public byte[] getImageFromDB() {
        return new byte[0];
    }

    @Override
    public byte[] getImageFromDisk() {
        return new byte[0];
    }
}
