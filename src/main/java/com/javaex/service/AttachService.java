package com.javaex.service;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.dao.AttachDao;
import com.javaex.vo.AttachVo;

@Service
public class AttachService {

	@Autowired
	private AttachDao attachDao;
	
	public String exeUpload(MultipartFile file) {
		// 파일저장 폴더
		String saveDir = "C:\\javaStudy\\upload";
		
		// (0) 파일관련 정보수집
		// 오리지널 파일명
		String orgName = file.getOriginalFilename();
		
		// 확장자
		String exName = orgName.substring(orgName.lastIndexOf("."));
//		System.out.println(orgName.substring(4));
//		System.out.println(orgName.lastIndexOf("."));
	
		// 저장파일명(겹치지 않아야함) - 업로드한 파일을 구분해서 저장하기 위해 씀.
		String saveName = System.currentTimeMillis() + UUID.randomUUID().toString() + exName;
		
		// 파일사이즈
		long fileSize = file.getSize();
		
		// 파일 전체 경로(저장파일명 포함)
		String filePath = saveDir + "\\" + saveName;
		
		//(1) 파일정보를 DB에 저장
		// Vo 에 묶어준다
		AttachVo attachVo = new AttachVo(orgName, saveName, filePath, fileSize);
		
		// db에 저장 - dao 메소드 추가 , 활용
		attachDao.insertFile(attachVo);
		
		////////////////////////////////////////////////////////////////////////////////////
		//(2) 파일을 하드디스크에 저장
		// * 파일저장
		try {
			byte[] fileData = file.getBytes();
			OutputStream os = new FileOutputStream(filePath);
			BufferedOutputStream bos = new BufferedOutputStream(os);
			bos.write(fileData);
			bos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		///////////////////////////////////////////////////////////////////////////////////
		
		return saveName;
	};
}
