package com.godrej.properties.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class FileDownloadController {

   private static final String FILE_PATH = "D:/apk/walkinform.apk";

 
	@RequestMapping(value = { "/downloadfile"}, method = RequestMethod.GET)
	public String indexPost(ModelMap model) {
		 return "downloadfile";
	}

   
   // Using ResponseEntity<InputStreamResource>
   @GetMapping("/downloadapk")
   public ResponseEntity<InputStreamResource> downloadFile1() throws IOException {

      File file = new File(FILE_PATH);
      InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

      return ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_DISPOSITION,"attachment;filename=" + file.getName())
            .contentType(MediaType.APPLICATION_OCTET_STREAM).contentLength(file.length())
            .body(resource);
   }
 
}