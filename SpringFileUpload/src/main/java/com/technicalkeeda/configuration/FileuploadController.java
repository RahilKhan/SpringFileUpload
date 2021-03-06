package com.technicalkeeda.configuration;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
 
import javax.servlet.ServletContext;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
 
import com.technicalkeeda.bean.FileInfo;
 
@Controller
public class FileuploadController {
    @Autowired
    ServletContext context;
 
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public ModelAndView upload(@RequestParam("file") List < MultipartFile > files) {
        List < FileInfo > uploadedFiles = new ArrayList < FileInfo > ();
        if (!files.isEmpty()) {
            try {
                for (MultipartFile file: files) {
                    String path = "C:\\Users\\rahikhan\\Downloads\\mockData" + File.separator + file.getOriginalFilename();
                    File destinationFile = new File(path);
                    System.out.println("\t path : " + path);
                    file.transferTo(destinationFile);
                    uploadedFiles.add(new FileInfo(destinationFile.getName(), path));
                }
 
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
 
        }
        ModelAndView modelAndView = new ModelAndView("success1");
        modelAndView.addObject("files", uploadedFiles);
        return modelAndView;
    }
}
