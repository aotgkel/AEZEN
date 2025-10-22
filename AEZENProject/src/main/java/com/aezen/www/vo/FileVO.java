package com.aezen.www.vo;

/*
 * Ŭ���� ��� : ���� ���� (file ���̺�)
 */
public class FileVO {
    private int fileNo; // ���� ��ȣ (Primary Key)
    private String logicalFileName; // ����ڰ� ���ε��� ���� ���� �̸�
    private String physicalFileName; // ������ ����� ���� ���� �̸� (UUID ��)
    private long fileSize; // ���� ũ�� (����Ʈ)
    private String fileExt; // ���� Ȯ����
    private int boardNo; // ������ ����� �Խñ� ��ȣ (Foreign Key)
	public int getFileNo() {
		return fileNo;
	}
	public void setFileNo(int fileNo) {
		this.fileNo = fileNo;
	}
	public String getLogicalFileName() {
		return logicalFileName;
	}
	public void setLogicalFileName(String logicalFileName) {
		this.logicalFileName = logicalFileName;
	}
	public String getPhysicalFileName() {
		return physicalFileName;
	}
	public void setPhysicalFileName(String physicalFileName) {
		this.physicalFileName = physicalFileName;
	}
	public long getFileSize() {
		return fileSize;
	}
	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}
	public String getFileExt() {
		return fileExt;
	}
	public void setFileExt(String fileExt) {
		this.fileExt = fileExt;
	}
	public int getBoardNo() {
		return boardNo;
	}
	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}

    
}