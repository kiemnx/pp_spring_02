package vn.plusplus.spring.services;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FileService {

    private final Path root = Paths.get("uploads");

    public void save(MultipartFile file){
        try {
            Files.copy(file.getInputStream(), this.root.resolve(file.getOriginalFilename()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Saved file: " + file.getOriginalFilename());
    }
    public Resource download(String fileName){
        Path file = root.resolve(fileName);
        Resource resource = null;
        try {
            resource = new UrlResource(file.toUri());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        if(resource.exists() || resource.isReadable()){
            return resource;
        } else {
            throw new RuntimeException("Cound not read file");
        }
    }
}
