package com.javaex.service;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.dao.GalleryDao;
import com.javaex.vo.GalleryVo;

@Service
public class GalleryService {

	@Autowired
	private GalleryDao galleryDao;
	
	// 전체리스트 가져오기
	public List<GalleryVo> exeList() {
		List<GalleryVo> galleryList = galleryDao.listAll();		
		return galleryList;
	}
	
	// 등록
	public int exeAdd(GalleryVo galleryVo,MultipartFile file) {
		String saveDir = "C:\\javaStudy\\upload"; // 저장공간
		String orgName = file.getOriginalFilename(); // 파일 원본 이름
		String exName = orgName.substring(orgName.lastIndexOf(".")); // 확장자
		String saveName = System.currentTimeMillis() + UUID.randomUUID().toString() + exName; // 파일 저장 이름
		long fileSize = file.getSize(); // 파일 사이즈
		String filePath = saveDir + "\\" + saveName; // 저장경로
		
		galleryVo.setFilePath(filePath);
		galleryVo.setFileSize(fileSize);
		galleryVo.setOrgName(orgName);
		galleryVo.setSaveName(saveName);
		
		int count = galleryDao.insert(galleryVo);
		if(count == 1) {
			try {
				byte[] fileData = file.getBytes();
				OutputStream os = new FileOutputStream(filePath);
				BufferedOutputStream bos = new BufferedOutputStream(os);
				bos.write(fileData);
				bos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return count;
	}
	
}
