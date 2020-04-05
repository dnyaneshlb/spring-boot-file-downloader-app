package code.like.apro.controller;

import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
public class FileDownloadController {

    @GetMapping("/hi")
    public String greet(){
        return "Hello";
    }


    @GetMapping("/api/v1/download")
    public void downloadFile(HttpServletRequest request, HttpServletResponse response) throws FileNotFoundException {
        //Authorized user will download the file
        String dataDirectory = request.getServletContext().getRealPath("./resources/main/static/files/");
        String fileName = "image001.pdf";

        File file1 = ResourceUtils.getFile("classpath:static/files/image001.pdf");

        if(file1.exists()){
            System.out.println("true");
        }

        Path file = Paths.get(file1.getPath());
        if (Files.exists(file))
        {
            response.setContentType("application/pdf");
            response.addHeader("Content-Disposition", "attachment; filename="+fileName);
            try
            {
                Files.copy(file, response.getOutputStream());
                response.getOutputStream().flush();
            }
            catch ( IOException ex) {
                ex.printStackTrace();
            }
        }
        else{
            System.err.println("\n\n File path does not exist!!");
        }
    }
}
