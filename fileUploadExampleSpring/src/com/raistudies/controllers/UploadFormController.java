package com.raistudies.controllers;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.raistudies.domain.UploadForm;

@Controller
@RequestMapping(value="/FileUploadForm.htm")
public class UploadFormController implements HandlerExceptionResolver{
	
	@RequestMapping(method=RequestMethod.GET)
	public String showForm(ModelMap model){
		UploadForm form = new UploadForm();
		model.addAttribute("FORM", form);
		return "FileUploadForm";
	}

	@RequestMapping(method=RequestMethod.POST)
	public String processForm(@ModelAttribute(value="FORM") UploadForm form,BindingResult result){
		if(!result.hasErrors()){
			FileOutputStream outputStream = null;
			String filePath = System.getProperty("java.io.tmpdir") + "/" + form.getFile().getOriginalFilename();
			try {
				System.out.println("file path is : "+filePath);
				outputStream = new FileOutputStream(new File(filePath));
				outputStream.write(form.getFile().getFileItem().get());
				outputStream.close();
				File file=new File(filePath);
				System.out.println(file.exists());
				System.out.println(file.canRead());
				System.out.println(file.getAbsolutePath());
				 BufferedReader br = new BufferedReader(new FileReader(file.getAbsolutePath()));
				 
				 String line;
				 StringBuilder bdr=new StringBuilder();
			        while((line = br.readLine()) != null) {
			        	System.out.println(line);
			        	bdr.append(line+"<br>");
			        }
				//form.setUploadedData(bdr.toString());
			        File file1=new File(filePath);
			        FileWriter fw=new FileWriter(file1);
			        fw.write(bdr.toString());
				form.setFilePath(filePath);
			} catch (Exception e) {
				System.out.println("Error while saving file");
				return "FileUploadForm";
			}
			return "success";
		}else{
			return "FileUploadForm";
		}		
	}
	

	@Override
	public ModelAndView resolveException(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception exception) {
		Map<Object, Object> model = new HashMap<Object, Object>();
        if (exception instanceof MaxUploadSizeExceededException)
        {
            model.put("errors", "File size should be less then "+((MaxUploadSizeExceededException)exception).getMaxUploadSize()+" byte.");
        } else
        {
            model.put("errors", "Unexpected error: " + exception.getMessage());
        }
        model.put("FORM", new UploadForm());
        return new ModelAndView("/FileUploadForm", (Map) model);

	}
}
