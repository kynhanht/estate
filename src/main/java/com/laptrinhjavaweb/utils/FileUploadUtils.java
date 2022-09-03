package com.laptrinhjavaweb.utils;

import com.laptrinhjavaweb.constant.ErrorMessageConstants;
import com.laptrinhjavaweb.constant.SystemConstants;
import com.laptrinhjavaweb.exception.FileUploadException;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class FileUploadUtils {


    public static void uploadFile(MultipartFile file) {

        if (file == null || file.isEmpty()) {
            return;
        }
        // Get file name, Example: images.jpg
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        try {
            // Get absolute path where stores this file
            Path directoryLocation = Paths.get(SystemConstants.UPLOAD_BUILDING_FILE_DIR).toAbsolutePath().normalize();
            // Check if the file's name contains invalid characters
            if (fileName.contains("..")) {
                throw new FileUploadException(ErrorMessageConstants.FILE_UPLOAD_PATH_INVALID + fileName);
            }
            // Create location where stores this file
            Files.createDirectories(directoryLocation);
            // Copy file to the target location (Replacing existing file with the same name)
            Path fileLocation = directoryLocation.resolve(fileName);
            Files.copy(file.getInputStream(), fileLocation, StandardCopyOption.REPLACE_EXISTING);
            // Optional(In linux): Set permission for file when it is uploaded
//            Set<PosixFilePermission> perms = PosixFilePermissions.fromString("rwxrwxrwx");
//            Files.setPosixFilePermissions(fileLocation, perms);
            //Optional(In linux): Set owner for file when it is uploaded
//            UserPrincipal owner = fileLocation.getFileSystem().getUserPrincipalLookupService()
//                    .lookupPrincipalByName("kynhanht");
//            Files.setOwner(fileLocation, owner);
        } catch (IOException e) {
            throw new FileUploadException(ErrorMessageConstants.COULD_NOT_UPLOAD_FILE + fileName, e);
        }
    }

    public static String loadPathFile(String fileName) {

        if (org.apache.commons.lang.StringUtils.isBlank(fileName)) {
            return null;
        }
        Path filePath = Paths.get(SystemConstants.UPLOAD_BUILDING_FILE_DIR).resolve(fileName).normalize();
        return filePath.toString();
    }

    public static String getFileName(MultipartFile file) {
        if (file == null || file.isEmpty()) {
            return null;
        }
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        return fileName;
    }
}
