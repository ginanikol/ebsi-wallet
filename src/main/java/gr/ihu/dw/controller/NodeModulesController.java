package gr.ihu.dw.controller;

import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.nio.file.Files;

@Controller
public class NodeModulesController {

    @GetMapping("/node_modules/{file:.+}")
    @ResponseBody
    public ResponseEntity<byte[]> getNodeModuleFile(@PathVariable String file) throws IOException {
        // Build the path to the requested file in the 'node_modules' directory
        String nodeModulesPath = "static/node_modules/"; // Adjust the path as needed
        String filePath = nodeModulesPath + file;

        // Load the file as a resource from the classpath
        ClassPathResource resource = new ClassPathResource(filePath);

        // Check if the file exists
        if (!resource.exists()) {
            return ResponseEntity.notFound().build();
        }

        // Read the file content
        byte[] fileContent = Files.readAllBytes(resource.getFile().toPath());

        // Set the Content-Type header based on the file's media type
        MediaType mediaType = MediaType.APPLICATION_OCTET_STREAM;
        try {
            mediaType = MediaType.parseMediaType(Files.probeContentType(resource.getFile().toPath()));
        } catch (IOException e) {
            // Handle any errors when determining the media type (optional)
        }

        // Set appropriate headers and return the file content as a ResponseEntity
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(mediaType);
        headers.setContentLength(fileContent.length);

        return ResponseEntity.ok()
                .headers(headers)
                .body(fileContent);
    }

}
