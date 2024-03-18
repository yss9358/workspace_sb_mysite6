package com.javaex.vo;

public class GalleryVo {

	private int no;
	private String name;
	private String content;
	private String orgName;
	private String saveName;
	private String filePath;
	private int userNo;
	private long fileSize;
	
	public GalleryVo() {
		
	}

	public GalleryVo(int no, String name, String content, String orgName, String saveName, String filePath, int userNo,
			long fileSize) {
		this.no = no;
		this.name = name;
		this.content = content;
		this.orgName = orgName;
		this.saveName = saveName;
		this.filePath = filePath;
		this.userNo = userNo;
		this.fileSize = fileSize;
	}

	public long getFileSize() {
		return fileSize;
	}

	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getSaveName() {
		return saveName;
	}

	public void setSaveName(String saveName) {
		this.saveName = saveName;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public int getUserNo() {
		return userNo;
	}

	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}

	@Override
	public String toString() {
		return "GalleryVo [no=" + no + ", name=" + name + ", content=" + content + ", orgName=" + orgName
				+ ", saveName=" + saveName + ", filePath=" + filePath + ", userNo=" + userNo + ", fileSize=" + fileSize
				+ "]";
	}

	
	
}
