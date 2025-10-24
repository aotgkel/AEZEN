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

    // =============================
    // Getters
    // =============================

    public int getFileNo() {
        return fileNo;
    }

    public String getLogicalFileName() {
        return logicalFileName;
    }

    public String getPhysicalFileName() {
        return physicalFileName;
    }

    public long getFileSize() {
        return fileSize;
    }

    public String getFileExt() {
        return fileExt;
    }

    public int getBoardNo() {
        return boardNo;
    }

    // =============================
    // Setters
    // =============================

    public void setFileNo(int fileNo) {
        this.fileNo = fileNo;
    }

    public void setLogicalFileName(String logicalFileName) {
        this.logicalFileName = logicalFileName;
    }

    public void setPhysicalFileName(String physicalFileName) {
        this.physicalFileName = physicalFileName;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    public void setFileExt(String fileExt) {
        this.fileExt = fileExt;
    }

    public void setBoardNo(int boardNo) {
        this.boardNo = boardNo;
    }
}